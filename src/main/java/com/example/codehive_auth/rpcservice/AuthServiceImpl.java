package com.example.codehive_auth.rpcservice;

import com.example.codehive_auth.AuthServiceGrpc;
import com.example.codehive_auth.LoginRequest;
import com.example.codehive_auth.LoginResponse;
import com.example.codehive_auth.RegisterRequest;
import com.example.codehive_auth.RegisterResponse;
import com.example.codehive_auth.VerifyTokenRequest;
import com.example.codehive_auth.VerifyTokenResponse;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import com.example.codehive_auth.repository.CredentialRepository;
import com.example.codehive_auth.entity.Credential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.codehive_auth.utils.JwtUtil;

@GrpcService
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public void register(RegisterRequest req, StreamObserver<RegisterResponse> responseObserver) {
        try {
            String token = JwtUtil.generateToken(req.getUid());
            Credential credential = new Credential(req.getEmail(), req.getPassword(), req.getUid());
            credentialRepository.save(credential);

            RegisterResponse res = RegisterResponse.newBuilder()
                .setToken(token)
                .setUid(req.getUid())
                .setStatus("Success")
                .build();
            responseObserver.onNext(res);
            responseObserver.onCompleted();
        } catch (Exception e) {
            RegisterResponse res = RegisterResponse.newBuilder()
                .setStatus("Failed: " + e.getMessage())
                .build();
            responseObserver.onNext(res);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void login(LoginRequest req, StreamObserver<LoginResponse> responseObserver) {
        try {
            
            String uid = credentialRepository.authorize(req.getEmail(), req.getPassword());
            String token = JwtUtil.generateToken(uid);

            LoginResponse res = LoginResponse.newBuilder()
                .setToken(token)
                .setUid(uid)
                .setStatus("Success")
                .build();
            responseObserver.onNext(res);
            responseObserver.onCompleted();
        } catch (Exception e) {
            LoginResponse res = LoginResponse.newBuilder()
                .setStatus("Failed: " + e.getMessage())
                .build();
            responseObserver.onNext(res);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void verifyToken(VerifyTokenRequest req, StreamObserver<VerifyTokenResponse> responseObserver) {
        try{
        String verifiedId = JwtUtil.extractUID(req.getToken());
        if(verifiedId.equals(req.getUid())){
                    VerifyTokenResponse res = VerifyTokenResponse.newBuilder()
            .setStatus("Success")
            .build();
            responseObserver.onNext(res);
            responseObserver.onCompleted();
        } else {
            VerifyTokenResponse res = VerifyTokenResponse.newBuilder()
                .setStatus("Failed")
                .build();
            responseObserver.onNext(res);
            responseObserver.onCompleted();
        }
        }catch (Exception e) {
           VerifyTokenResponse res = VerifyTokenResponse.newBuilder()
                .setStatus("Failed: " + e.getMessage())
                .build();
            responseObserver.onNext(res);
            responseObserver.onCompleted();
        }
    }
}
