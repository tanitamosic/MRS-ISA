package com.Projekat.service;


import com.Projekat.model.services.QuickAction;
import com.Projekat.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    public void saveNewAction(QuickAction newAction) { actionRepository.saveAndFlush(newAction); }

    public QuickAction findById(Integer id) {
        return actionRepository.findById(id).orElse(null);
    }

    public List<QuickAction> getQuickActionsForService(Integer service_id) {
        return actionRepository.getQuickActionsForService(service_id);
    }

}
