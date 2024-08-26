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

@GrpcService
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    @Override
    public void register(RegisterRequest req, StreamObserver<RegisterResponse> responseObserver) {
        RegisterResponse res = RegisterResponse.newBuilder().setToken(req.getEmail()).build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void login(LoginRequest req, StreamObserver<LoginResponse> responseObserver) {
        LoginResponse res = LoginResponse.newBuilder().setToken(req.getEmail()).build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }

    @Override
    public void verifyToken(VerifyTokenRequest req, StreamObserver<VerifyTokenResponse> responseObserver) {
        VerifyTokenResponse res = VerifyTokenResponse.newBuilder().setStatus(req.getToken()).build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}