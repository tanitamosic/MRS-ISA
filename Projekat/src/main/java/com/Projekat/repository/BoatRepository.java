package com.Projekat.repository;

import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatRepository extends JpaRepository<Boat,Integer> {
}
