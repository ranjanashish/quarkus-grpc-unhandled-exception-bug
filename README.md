# quarkus-grpc-unhandled-exception-bug

## In what cases does this bug occur?

|                                              | Mutiny gRPC API                      | Default gRPC API                     |
| -------------------------------------------- | ------------------------------------ | ------------------------------------ |
| Without `@Blocking` or `@RunOnVirtualThread` | *(expected)* client receives error   | *(expected)* client receives error   |
| `@Blocking`                                  | *(expected)* client receives error   | *(unexpected)* client hangs          |
| `@RunOnVirtualThread`                        | *(expected)* client receives error   | *(unexpected)* client hangs          |
