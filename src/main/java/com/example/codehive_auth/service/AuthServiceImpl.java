package com.example.codehive_auth.service;

import com.example.codehive_auth.AuthServiceGrpc;
import com.example.codehive_auth.RegisterRequest;
import com.example.codehive_auth.RegisterResponse;

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
}