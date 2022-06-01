package com.Projekat.service;

import com.Projekat.model.Address;
import com.Projekat.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void saveNewAddress(Address address) { addressRepository.saveAndFlush(address); }
}
