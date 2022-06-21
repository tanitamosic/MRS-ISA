package com.Projekat.repository;


import com.Projekat.model.services.QuickAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<QuickAction, Integer> {

}
