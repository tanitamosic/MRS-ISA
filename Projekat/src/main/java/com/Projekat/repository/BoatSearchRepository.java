package com.Projekat.repository;

import com.Projekat.dto.BoatSearchDTO;
import com.Projekat.model.services.Boat;
import com.fasterxml.jackson.core.Base64Variant;
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
public class BoatSearchRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    private BoatSearchDTO searchCriteria;

    public BoatSearchRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Boat> findAllWithFilters(Pageable page, BoatSearchDTO boatSearchDTO) {
        this.searchCriteria = boatSearchDTO;

        CriteriaQuery<Boat> criteriaQuery = criteriaBuilder.createQuery(Boat.class);
        Root<Boat> boatRoot = criteriaQuery.from(Boat.class);
        Predicate predicate = getPredicate(boatRoot);

        criteriaQuery.where(predicate);

        TypedQuery<Boat> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(page.getPageNumber() * page.getPageSize());
        typedQuery.setMaxResults(page.getPageSize());

        long boatsCount = getBoatsCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), page, boatsCount);
    }

    private Predicate getPredicate(Root<Boat> boatRoot) {
        Predicate generalSearchPredicate = getGeneralSearchPredicate(boatRoot);
        Predicate predicate = getPredicateForRemainingFields(boatRoot);
        if(null == generalSearchPredicate && null != predicate) {
            return predicate;
        }
        return criteriaBuilder.and(predicate, generalSearchPredicate);
    }

    private Predicate getPredicateForRemainingFields(Root<Boat> boatRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(searchCriteria.getAvailabilityStart())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(boatRoot.get("availabilityStart"),
                            searchCriteria.getAvailabilityStart())
            );
        }
        if(Objects.nonNull(searchCriteria.getAvailabilityEnd())) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(boatRoot.get("availabilityEnd"),
                            searchCriteria.getAvailabilityEnd())
            );
        }
        if(Objects.nonNull(searchCriteria.getState())) {
            boatRoot.join("address", JoinType.INNER);
            predicates.add(
                    criteriaBuilder.like(boatRoot.get("address").get("state"),
                            "%" + searchCriteria.getState() + "%")
            );
        }
        if(Objects.nonNull(searchCriteria.getCity())) {
            boatRoot.join("address", JoinType.INNER);
            predicates.add(
                    criteriaBuilder.like(boatRoot.get("address").get("city"),
                            "%" + searchCriteria.getCity() + "%")
            );
        }
        if(Objects.nonNull(searchCriteria.getBoatName())) {
            predicates.add(
                    criteriaBuilder.like(boatRoot.get("name"),
                            "%" + searchCriteria.getBoatName() + "%")
            );
        }
        if(Objects.nonNull(searchCriteria.getPriceFrom())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(boatRoot.get("price"),
                            searchCriteria.getPriceFrom())
            );
        }
        if(Objects.nonNull(searchCriteria.getPriceTo())) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(boatRoot.get("price"),
                            searchCriteria.getPriceTo())
            );
        }
        if(Objects.nonNull(searchCriteria.getRating())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(boatRoot.get("rating"),
                            searchCriteria.getRating())
            );
        }
        if(Objects.nonNull(searchCriteria.getBoatType())) {
            predicates.add(
                    criteriaBuilder.like(boatRoot.get("type"),
                            "%" + searchCriteria.getBoatType() + "%")
            );
        }
        if(Objects.nonNull(searchCriteria.getCapacity())) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(boatRoot.get("capacity"),
                            searchCriteria.getCapacity())
            );
        }
        if(Objects.nonNull(searchCriteria.getNavigationalEquipment())) {
            predicates.add(
                    criteriaBuilder.like(boatRoot.get("navigationalEquipment"),
                            "%" + searchCriteria.getNavigationalEquipment() + "%")
            );
        }
        if(Objects.nonNull(searchCriteria.getFishingEquipment())) {
            predicates.add(
                    criteriaBuilder.like(boatRoot.get("fishingEquipment"),
                            "%" + searchCriteria.getFishingEquipment() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }

    private Predicate getGeneralSearchPredicate(Root<Boat> boatRoot) {
        List<Predicate> predicatesGeneralSearch = new ArrayList<>();
        Predicate generalSearchPredicate = null;

        if (Objects.nonNull(searchCriteria.getGeneralSearchField())) {
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

            boatRoot.join("address", JoinType.INNER);
            predicatesGeneralSearch.add(
                    criteriaBuilder.like(boatRoot.get("address").get("state"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );
            predicatesGeneralSearch.add(
                    criteriaBuilder.like(boatRoot.get("address").get("city"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );
            predicatesGeneralSearch.add(
                    criteriaBuilder.like(boatRoot.get("name"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );

            predicatesGeneralSearch.add(
                    criteriaBuilder.like(boatRoot.get("type"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );
            predicatesGeneralSearch.add(
                    criteriaBuilder.like(boatRoot.get("navigationalEquipment"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );
            predicatesGeneralSearch.add(
                    criteriaBuilder.like(boatRoot.get("fishingEquipment"),
                            "%" + searchCriteria.getGeneralSearchField() + "%")
            );

            if (isDouble) {
                predicatesGeneralSearch.add(
                        criteriaBuilder.greaterThanOrEqualTo(boatRoot.get("price"),
                                doubleNumber)
                );
                predicatesGeneralSearch.add(
                        criteriaBuilder.greaterThanOrEqualTo(boatRoot.get("rating"),
                                doubleNumber)
                );
            }

            if (isInteger) {
                predicatesGeneralSearch.add(
                        criteriaBuilder.greaterThanOrEqualTo(boatRoot.get("capacity"),
                                integerNumber)
                );
            }

            generalSearchPredicate = criteriaBuilder.or(predicatesGeneralSearch.toArray(new Predicate[0]));

        }

        return generalSearchPredicate;
    }

    private long getBoatsCount(Predicate predicate) {
        return 0;
    }


}
