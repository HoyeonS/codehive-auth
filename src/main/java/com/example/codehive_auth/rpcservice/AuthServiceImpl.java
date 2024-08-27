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

@GrpcService
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    CredentialRepository credentialRepository;
    @Override
    public void register(RegisterRequest req, StreamObserver<RegisterResponse> responseObserver) {
        try{
            Credential credential = new Credential(req.getEmail(), req.getPassword(), req.getUid());

            credentialRepository.save(credential);

            RegisterResponse res = RegisterResponse.newBuilder().setToken("TEMP").setUid(req.getUid()).setStatus("Success").build();
            responseObserver.onNext(res);
            responseObserver.onCompleted();
        } catch (Exception e) {
            RegisterResponse res = RegisterResponse.newBuilder().setStatus("Failed").build();
            responseObserver.onNext(res);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void login(LoginRequest req, StreamObserver<LoginResponse> responseObserver) {

        try{
            String uid = credentialRepository.authorize(req.getEmail(), req.getPassword());

            LoginResponse res = LoginResponse.newBuilder().setToken("TEMP").setUid(uid).setStatus("Success").build();


            responseObserver.onNext(res);
            responseObserver.onCompleted();
        } catch (Exception e){

            LoginResponse res = LoginResponse.newBuilder().setStatus("Failed").build();


            responseObserver.onNext(res);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void verifyToken(VerifyTokenRequest req, StreamObserver<VerifyTokenResponse> responseObserver) {
        VerifyTokenResponse res = VerifyTokenResponse.newBuilder().setStatus("Success").build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}