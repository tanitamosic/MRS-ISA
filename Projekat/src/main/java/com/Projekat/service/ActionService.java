package com.Projekat.service;


import com.Projekat.model.services.QuickAction;
import com.Projekat.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    public void saveNewAction(QuickAction newAction) { actionRepository.saveAndFlush(newAction); }
}
