# grpc-kotlin-starter

## Introduction

This is a starter project written in Kotlin with Gradle as a dependency management system
and using gRPC for the communication layer.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/gradle-plugin/reference/html/)
* [Introduction to gRPC](https://www.grpc.io/docs/what-is-grpc/introduction/)

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

## Getting Started

### Prerequisites

* Install the JDK for Java 11. As an example you can download this from the
  [Amazon Coretto](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
  webpage or install it using [Sdkman](https://sdkman.io/).
* Install an IDE such as [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=mac
  with the Kotlin and Gradle plug-ins.

### Run the project

Follow these steps to start developing:

* Open the `src/main/kotlin/dev.rmngrc.grpckotlinstarter/Application.kt` file.
* Click on the green Play icon next to the `main` function.
* A server will start on port `9090`.
* Open a new terminal and execute the following command:

```bash
grpcurl -plaintext localhost:9090 \
  dev.rmngrc.grpckotlinstarter.proto.GrpcKotlinStarterService/HealthCheck
```

* The server should reply with:

```bash
{
  "status": "Hello World!"
}
```
