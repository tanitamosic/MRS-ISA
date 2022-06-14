package com.Projekat.repository;

import com.Projekat.model.AccountDeletionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccDeletionRepository extends JpaRepository<AccountDeletionRequest, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM acc_del_requests WHERE acc_del_requests.accepted=false AND acc_del_requests.rejected=false")
    List<AccountDeletionRequest> getAccDelRequests();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE acc_del_requests SET accepted=true WHERE acc_id=?1")
    void accept(Integer acc_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE acc_del_requests SET rejected=true WHERE acc_id=?1")
    void reject(Integer acc_id);

}
