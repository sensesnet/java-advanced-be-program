package com.epam.learn;

import io.grpc.stub.StreamObserver;
import com.epam.learn.stubs.ping_pong.PingRequest;
import com.epam.learn.stubs.ping_pong.PongResponse;
import com.epam.learn.stubs.ping_pong.PingPongServiceGrpc;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PingPongServiceImpl extends PingPongServiceGrpc.PingPongServiceImplBase {
    @Override
    public void ping(PingRequest request, StreamObserver<PongResponse> responseObserver) {
        log.info("[Server] received request: " + request.getMessage());
        PongResponse response = PongResponse.newBuilder()
                .setMessage("Pong")
                .build();

        responseObserver.onNext(response);
        log.info("[Server] sending request: " + response.getMessage());
        responseObserver.onCompleted();
    }
}

