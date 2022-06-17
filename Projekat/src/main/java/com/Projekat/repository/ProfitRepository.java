package com.Projekat.repository;

import com.Projekat.model.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitRepository extends JpaRepository<Profit, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM PROFITS WHERE PROFITS.res_id=?1")
    Integer getCalculatedPriceCut(Integer res_id);
}
