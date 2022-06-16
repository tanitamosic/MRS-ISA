package com.Projekat.service;


import com.Projekat.model.reservations.submitions.Complaint;
import com.Projekat.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;
    public List<Complaint> getOpenComplaints() { return complaintRepository.getOpenComplaints(); }
    public Complaint getComplaintById(Integer id) { return complaintRepository.getComplaintById(id); }
    public void answerComplaint(Integer id, String answer) { complaintRepository.answerComplaint(id, answer);  }
}
