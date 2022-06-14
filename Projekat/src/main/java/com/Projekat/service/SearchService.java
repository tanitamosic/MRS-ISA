package com.Projekat.service;

import com.Projekat.dto.AdventureSearchDTO;
import com.Projekat.dto.CottageSearchDTO;
import com.Projekat.model.services.Adventure;
import com.Projekat.model.services.Cottage;
import com.Projekat.repository.AdventureSearchRepository;
import com.Projekat.repository.CottageRepository;
import com.Projekat.repository.CottageSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    private CottageSearchRepository cottageSearchRepository;

    @Autowired
    private AdventureSearchRepository adventureSearchRepository;

    public Page<Cottage> getCottages(Pageable page, CottageSearchDTO searchDTO) {
        return cottageSearchRepository.findAllWithFilters(page, searchDTO);
    }

    public Page<Adventure> getAdventures(Pageable page, AdventureSearchDTO adventureSearchDTO) {
        return adventureSearchRepository.findAllWithFilters(page, adventureSearchDTO);
    }
}
