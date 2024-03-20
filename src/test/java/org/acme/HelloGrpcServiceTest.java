package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

@QuarkusTest
class HelloGrpcServiceTest {
    @GrpcClient
    HelloGrpc helloGrpc;

    @Test
    void testHello() {
        try {
            var unused = helloGrpc
                    .sayHello(HelloRequest.newBuilder().setName("IllegalArgument").build()).await().indefinitely();
            fail();
        } catch (StatusRuntimeException e) {
            assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatus().getCode());
        }
    }
}
