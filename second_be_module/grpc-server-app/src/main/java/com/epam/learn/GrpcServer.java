package com.epam.learn;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new PingPongServiceImpl())
                .build();
        server.start();
        server.awaitTermination();
    }
}
