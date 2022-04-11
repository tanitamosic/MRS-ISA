package com.Projekat.repository;

import com.Projekat.model.services.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface CottageRepository extends JpaRepository<Cottage,Integer> {

}
