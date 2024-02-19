package com.epam.learn.client;

import com.epam.learn.stubs.ping_pong.PingPongServiceGrpc;
import com.epam.learn.stubs.ping_pong.PingRequest;
import com.epam.learn.stubs.ping_pong.PongResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forTarget("localhost:8080")
                .usePlaintext()
                .build();
        PingPongServiceGrpc.PingPongServiceBlockingStub stub =
                PingPongServiceGrpc.newBlockingStub(channel);

        PingRequest request = PingRequest.newBuilder()
                .setMessage("Ping")
                .build();

        System.out.println("[Client] sending request: " + request.getMessage());
        PongResponse response = stub.ping(request);

        System.out.println("[Client] received response: " + response.getMessage());
        channel.shutdown();
    }
}
