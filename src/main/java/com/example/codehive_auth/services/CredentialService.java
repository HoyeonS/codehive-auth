package com.example.codehive_auth.services;

import com.example.codehive_auth.entity.Credential;
import com.example.codehive_auth.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;
    // Public method to authorize user based on email and password
    @Nullable
    public String authorize(String email, String password) {
        return credentialRepository.authorize(email, password);
    }
}
