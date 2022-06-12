package com.Projekat.service;

import com.Projekat.model.VerificationToken;
import com.Projekat.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public VerificationToken getVerificationToken(String token) { return tokenRepository.getToken(token); }
}
