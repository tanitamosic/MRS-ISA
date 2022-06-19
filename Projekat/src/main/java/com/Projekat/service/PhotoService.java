package com.Projekat.service;

import com.Projekat.model.Photo;
import com.Projekat.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public void saveNewPhoto(String assetPath, Integer serviceId) { photoRepository.saveNewPhoto(assetPath, serviceId); }

    public Integer getPhotoId(String assetPath) { return  photoRepository.getPhotoId(assetPath); }
}
