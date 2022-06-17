package com.Projekat.service;

import com.Projekat.repository.ProfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfitService {

    @Autowired
    private ProfitRepository profitRepository;

    public Integer getCalculatedPriceCut(Integer res_id) { return profitRepository.getCalculatedPriceCut(res_id); }
}
