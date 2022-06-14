package com.Projekat.service;

import com.Projekat.model.AccountDeletionRequest;
import com.Projekat.repository.AccDeletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeletionService {

    @Autowired
    private AccDeletionRepository accDeletionRepository;

    public void makeDelRequest(AccountDeletionRequest adr) { accDeletionRepository.saveAndFlush(adr); }

    public List<AccountDeletionRequest> getAccDelRequests() { return accDeletionRepository.getAccDelRequests(); }

    public void accept(Integer acc_id) { accDeletionRepository.accept(acc_id); }

    public void reject(Integer acc_id) { accDeletionRepository.reject(acc_id); }
}
