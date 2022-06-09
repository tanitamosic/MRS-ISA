package com.Projekat.repository;

import com.Projekat.dto.CottageSearchDTO;
import com.Projekat.model.services.Cottage;
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
public class CottageSearchRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    private CottageSearchDTO searchCriteria;

    public CottageSearchRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Cottage> findAllWithFilters(Pageable page, CottageSearchDTO searchCriteria) {
        this.searchCriteria = searchCriteria;

        CriteriaQuery<Cottage> criteriaQuery = criteriaBuilder.createQuery(Cottage.class);
        Root<Cottage> cottageRoot = criteriaQuery.from(Cottage.class);
        Predicate predicate = getPredicate(cottageRoot);

        criteriaQuery.where(predicate);
//        setOrder(page, criteriaQuery, cottageRoot);

        TypedQuery<Cottage> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(page.getPageNumber() * page.getPageSize());
        typedQuery.setMaxResults(page.getPageSize());

        long cottagesCount = getCottagesCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), page, cottagesCount);
    }

    private Predicate getPredicate(Root<Cottage> cottageRoot) {
        Predicate generalSearchPredicate = getGeneralSearchPredicate(cottageRoot);
        Predicate predicate = getPredicateForRemainingFields(cottageRoot);
        return criteriaBuilder.and(predicate, generalSearchPredicate);
    }

    private Predicate getPredicateForRemainingFields(Root<Cottage> cottageRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(searchCriteria.getAvailabilityStart())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(cottageRoot.get("availabilityStart"),
                            searchCriteria.getAvailabilityStart())
            );
        }
        if(Objects.nonNull(searchCriteria.getAvailabilityEnd())) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(cottageRoot.get("availabilityEnd"),
                            searchCriteria.getAvailabilityEnd())
            );
        }
        if(Objects.nonNull(searchCriteria.getState())) {
            cottageRoot.join("address", JoinType.INNER);
            predicates.add(
                    criteriaBuilder.like(cottageRoot.get("address").get("state"),
                            "%" + searchCriteria.getState() + "%")
            );
        }
        if(Objects.nonNull(searchCriteria.getCity())) {
            cottageRoot.join("address", JoinType.INNER);
            predicates.add(
                    criteriaBuilder.like(cottageRoot.get("address").get("city"),
                            "%" + searchCriteria.getCity() + "%")
            );
        }
        if(Objects.nonNull(searchCriteria.getCottageName())) {
            predicates.add(
                    criteriaBuilder.like(cottageRoot.get("name"),
                            "%" + searchCriteria.getCottageName() + "%")
            );
        }
        if(Objects.nonNull(searchCriteria.getPriceFrom())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(cottageRoot.get("price"),
                            searchCriteria.getPriceFrom())
            );
        }
        if(Objects.nonNull(searchCriteria.getPriceTo())) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(cottageRoot.get("price"),
                            searchCriteria.getPriceTo())
            );
        }
        // NEOPHODNO JE PROSIRITI MODEL TAKO DA SERVICE IMA REVIEW
//        if(Objects.nonNull(searchCriteria.getReview())) {
//            predicates.add(
//                    criteriaBuilder.greaterThanOrEqualTo(cottageRoot.get("review"),
//                            searchCriteria.getReview())
//            );
//        }
        if(Objects.nonNull(searchCriteria.getNumberOfRooms())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(cottageRoot.get("numberOfRooms"),
                            searchCriteria.getNumberOfRooms())
            );
        }
        if(Objects.nonNull(searchCriteria.getNumberOfBeds())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(cottageRoot.get("numberOfBeds"),
                            searchCriteria.getNumberOfBeds())
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getGeneralSearchPredicate(Root<Cottage> cottageRoot) {
        List<Predicate> predicatesGeneralSearch = new ArrayList<>();
        Predicate generalSearchPredicate = null;
        if(Objects.nonNull(searchCriteria.getGeneralSearchField())) {       // field for general search
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

            cottageRoot.join("address", JoinType.INNER);
            predicatesGeneralSearch.add(
                    criteriaBuilder.like(cottageRoot.get("address").get("state"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );
            predicatesGeneralSearch.add(
                    criteriaBuilder.like(cottageRoot.get("address").get("city"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );
            predicatesGeneralSearch.add(
                    criteriaBuilder.like(cottageRoot.get("name"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );
            if (isDouble) {
                predicatesGeneralSearch.add(
                        criteriaBuilder.greaterThanOrEqualTo(cottageRoot.get("price"),      // obratiti paznju na tip
                                doubleNumber)
                );
            }
            if (isInteger) {
                predicatesGeneralSearch.add(
                        criteriaBuilder.greaterThanOrEqualTo(cottageRoot.get("numberOfRooms"),
                                integerNumber)
                );
                predicatesGeneralSearch.add(
                        criteriaBuilder.greaterThanOrEqualTo(cottageRoot.get("numberOfBeds"),
                                integerNumber)
                );
            }
            generalSearchPredicate = criteriaBuilder.or(predicatesGeneralSearch.toArray(new Predicate[0]));
        }
        return generalSearchPredicate;
    }

    private long getCottagesCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Cottage> countRoot = countQuery.from(Cottage.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }


}
