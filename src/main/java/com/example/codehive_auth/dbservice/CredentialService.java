package com.example.codehive_auth.dbservice;

import com.example.codehive_auth.entity.Credential;
import com.example.codehive_auth.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;

    // Private helper method to create and save the credential
    private Credential createCredentialHelper(String uid, String email, String password) {
        Credential credential = new Credential(email, password, uid);
        return credentialRepository.save(credential); // Call save on the repository
    }

    // Public method to create a credential and return the uid
    public String createCredential(String uid, String email, String password) {
        Credential credential = createCredentialHelper(uid, email, password);
        return credential.getUid();
    }

    // Public method to authorize user based on email and password
    @Nullable
    public String authorize(String email, String password) {
        return credentialRepository.authorize(email, password);
    }
}
