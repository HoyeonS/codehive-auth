package com.example.codehive_auth.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    @Override
    public void Register(RegisterRequest req, StreamObserver<RegisterResponse> responseObserver) {
        RegisterResponse res = RegisterResponse.newBuilder().setToken(req.getEmail()).build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}