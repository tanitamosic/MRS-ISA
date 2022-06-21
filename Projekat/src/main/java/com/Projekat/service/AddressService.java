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

    public void upsertAddress(String state, String city, String street) { addressRepository.upsertAddress(state, city, street); }

    public Integer getAddressId(String state, String city, String street) { return addressRepository.getAddressId(state, city, street); }
    public Address getAddress(String state, String city, String street) { return addressRepository.getAddress(state, city, street); }
}
