package com.example.codehive_auth.repository;

import com.example.codehive_auth.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    @Query("SELECT c.uid FROM Credential c WHERE c.email = :email AND c.password = :password")
    @Nullable
    String authorize(@Param("email") String email, @Param("password") String password);

    
}
