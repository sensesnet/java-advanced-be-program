package com.epam.learn;

import io.grpc.stub.StreamObserver;
import com.epam.learn.stubs.ping_pong.PingRequest;
import com.epam.learn.stubs.ping_pong.PongResponse;
import com.epam.learn.stubs.ping_pong.PingPongServiceGrpc;

public class PingPongServiceImpl extends PingPongServiceGrpc.PingPongServiceImplBase {
    @Override
    public void ping(PingRequest request, StreamObserver<PongResponse> responseObserver) {
        System.out.println("[Server] received request: " + request.getMessage());
        PongResponse response = PongResponse.newBuilder()
                .setMessage("Pong")
                .build();

        responseObserver.onNext(response);
        System.out.println("[Server] sending request: " + response.getMessage());
        responseObserver.onCompleted();
    }
}

