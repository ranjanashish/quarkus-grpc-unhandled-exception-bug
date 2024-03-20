package org.acme;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;

import io.smallrye.common.annotation.Blocking;

@GrpcService
public class HelloGrpcService extends HelloGrpcGrpc.HelloGrpcImplBase {
    @Override
    @Blocking
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        var name = request.getName();
        if (name.equals("IllegalArgument")) {
            throw new IllegalArgumentException("You're not allowed!");
        }
        var message = "Hello " + name + "!";
        var reply = HelloReply.newBuilder().setMessage(message).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
