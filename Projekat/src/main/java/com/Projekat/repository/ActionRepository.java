package com.Projekat.repository;


import com.Projekat.model.services.QuickAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<QuickAction, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE quick_action SET status=1 WHERE id=?1")
    void setStateClosed(Integer id);


    @Query(nativeQuery = true, value = "SELECT * FROM quick_action WHERE action_id=?1")
    List<QuickAction> getQuickActionsForService(Integer service_id);
}
