package com.Projekat.repository;

import com.Projekat.model.reservations.submitions.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM COMPLAINT WHERE COMPLAINT.status=0")
    List<Complaint> getOpenComplaints();
    Complaint getComplaintById(Integer id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE COMPLAINT SET answer=?2, status=1 WHERE id=?1")
    void answerComplaint(Integer id, String answer);
}
