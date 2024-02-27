package com.epam.learn.client;

import com.epam.learn.stubs.ping_pong.PingPongServiceGrpc;
import com.epam.learn.stubs.ping_pong.PingRequest;
import com.epam.learn.stubs.ping_pong.PongResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forTarget("localhost:9090")
                .usePlaintext()
                .build();
        PingPongServiceGrpc.PingPongServiceBlockingStub stub =
                PingPongServiceGrpc.newBlockingStub(channel);

        PingRequest request = PingRequest.newBuilder()
                .setMessage("Ping")
                .build();

        log.info("[Client] sending request: " + request.getMessage());
        PongResponse response = stub.ping(request);

        log.info("[Client] received response: " + response.getMessage());
        channel.shutdown();
    }
}
