package com.Projekat.repository;

import com.Projekat.dto.AdventureSearchDTO;
import com.Projekat.model.services.Adventure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class AdventureSearchRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    private AdventureSearchDTO searchCriteria;

    public AdventureSearchRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Adventure> findAllWithFilters(Pageable page, AdventureSearchDTO adventureSearchDTO) {
        this.searchCriteria = adventureSearchDTO;

        CriteriaQuery<Adventure> criteriaQuery = criteriaBuilder.createQuery(Adventure.class);
        Root<Adventure> adventureRoot = criteriaQuery.from(Adventure.class);
        Predicate predicate = getPredicate(adventureRoot);

        criteriaQuery.where(predicate);

        TypedQuery<Adventure> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(page.getPageNumber() * page.getPageSize());
        typedQuery.setMaxResults(page.getPageSize());

        long adventuresCount = getAdventuresCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), page, adventuresCount);
    }


    private Predicate getPredicate(Root<Adventure> adventureRoot) {
        Predicate generalSearchPredicate = getGeneralSearchPredicate(adventureRoot);
        Predicate predicate = getPredicateForRemainingFields(adventureRoot);
        if(null == generalSearchPredicate && null != predicate) {
            return predicate;
        }
        return criteriaBuilder.and(predicate, generalSearchPredicate);
    }

    private Predicate getPredicateForRemainingFields(Root<Adventure> adventureRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(searchCriteria.getAvailabilityStart())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(adventureRoot.get("availabilityStart"),
                            searchCriteria.getAvailabilityStart())
            );
        }
        if(Objects.nonNull(searchCriteria.getAvailabilityEnd())) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(adventureRoot.get("availabilityEnd"),
                            searchCriteria.getAvailabilityEnd())
            );
        }
        if(Objects.nonNull(searchCriteria.getState())) {
            adventureRoot.join("address", JoinType.INNER);
            predicates.add(
                    criteriaBuilder.like(adventureRoot.get("address").get("state"),
                            "%" + searchCriteria.getState() + "%")
            );
        }
        if(Objects.nonNull(searchCriteria.getCity())) {
            adventureRoot.join("address", JoinType.INNER);
            predicates.add(
                    criteriaBuilder.like(adventureRoot.get("address").get("city"),
                            "%" + searchCriteria.getCity() + "%")
            );
        }
        if(Objects.nonNull(searchCriteria.getAdventureName())) {
            predicates.add(
                    criteriaBuilder.like(adventureRoot.get("name"),
                            "%" + searchCriteria.getAdventureName() + "%")
            );
        }
        if(Objects.nonNull(searchCriteria.getPriceFrom())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(adventureRoot.get("price"),
                            searchCriteria.getPriceFrom())
            );
        }
        if(Objects.nonNull(searchCriteria.getPriceTo())) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(adventureRoot.get("price"),
                            searchCriteria.getPriceTo())
            );
        }
        if(Objects.nonNull(searchCriteria.getRating())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(adventureRoot.get("rating"),
                            searchCriteria.getRating())
            );
        }
        if(Objects.nonNull(searchCriteria.getNumberOfPersons())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(adventureRoot.get("capacity"),
                            searchCriteria.getNumberOfPersons())
            );
        }
        if(Objects.nonNull(searchCriteria.getFishingEquipment())) {
            predicates.add(
                    criteriaBuilder.like(adventureRoot.get("fishingEquipment"),
                            "%" + searchCriteria.getFishingEquipment() + "%")
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getGeneralSearchPredicate(Root<Adventure> adventureRoot) {
        List<Predicate> predicatesGeneralSearch = new ArrayList<>();
        Predicate generalSearchPredicate = null;
        if(Objects.nonNull(searchCriteria.getGeneralSearchField())) {
            boolean isDouble;
            double doubleNumber = -1.0;
            try {
                doubleNumber = Double.parseDouble(searchCriteria.getGeneralSearchField());
                isDouble = true;
            }
            catch (Exception e) {
                isDouble = false;
            }

            boolean isInteger;
            int integerNumber = -1;
            try {
                integerNumber = Integer.parseInt(searchCriteria.getGeneralSearchField());
                isInteger = true;
            }
            catch (Exception e) {
                isInteger = false;
            }

            adventureRoot.join("address", JoinType.INNER);
            predicatesGeneralSearch.add(
                    criteriaBuilder.like(adventureRoot.get("address").get("state"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );
            predicatesGeneralSearch.add(
                    criteriaBuilder.like(adventureRoot.get("address").get("city"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );
            predicatesGeneralSearch.add(
                    criteriaBuilder.like(adventureRoot.get("name"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );

            predicatesGeneralSearch.add(
                    criteriaBuilder.like(adventureRoot.get("fishingEquipment"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );

            if (isDouble) {
                predicatesGeneralSearch.add(
                        criteriaBuilder.greaterThanOrEqualTo(adventureRoot.get("price"),      // obratiti paznju na tip
                                doubleNumber)
                );
                predicatesGeneralSearch.add(
                        criteriaBuilder.greaterThanOrEqualTo(adventureRoot.get("rating"),
                                doubleNumber)
                );
            }
            if (isInteger) {
                predicatesGeneralSearch.add(
                        criteriaBuilder.greaterThanOrEqualTo(adventureRoot.get("capacity"),
                                integerNumber)
                );
            }
            generalSearchPredicate = criteriaBuilder.or(predicatesGeneralSearch.toArray(new Predicate[0]));
        }
        return generalSearchPredicate;
    }

    private long getAdventuresCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Adventure> countRoot = countQuery.from(Adventure.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
