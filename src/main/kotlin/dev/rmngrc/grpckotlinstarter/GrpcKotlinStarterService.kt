package dev.rmngrc.grpckolinstarter

import dev.rmngrc.grpckolinstarter.proto.HealthCheckReply
import dev.rmngrc.grpckolinstarter.proto.HealthCheckRequest
import net.devh.boot.grpc.server.service.GrpcService
import dev.rmngrc.grpckolinstarter.proto.GrpcKotlinStarterServiceGrpc
import io.grpc.stub.StreamObserver

@GrpcService
class GrpcKotlinStarterService : GrpcKotlinStarterServiceGrpc.GrpcKotlinStarterServiceImplBase() {
    override fun healthCheck(request: HealthCheckRequest, responseObserver: StreamObserver<HealthCheckReply>) {
        val reply = HealthCheckReply.newBuilder().setStatus("Hey there").build()
        responseObserver.onNext(reply)
        responseObserver.onCompleted()
    }
}