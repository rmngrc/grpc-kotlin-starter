import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.ofSourceSet
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val grpcVersion = "1.34.0"
val grpcKotlinVersion = "1.0.0"
val protobufVersion = "3.14.0"
val pgvVersion = "0.4.1"
val coroutinesVersion = "1.4.2"
val jacksonVersion = "2.13.0"
val kotestVersion = "4.6.0"

plugins {
	application
	idea
	id("com.google.protobuf") version "0.8.18"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.springframework.boot") version "2.4.5"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.3.61"
}

group = "dev.rmngrc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	google()
	mavenCentral()
}

dependencies {
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
	implementation("com.google.protobuf:protobuf-java:$protobufVersion")
	implementation("com.google.protobuf:protobuf-java-util:$protobufVersion")
	implementation("io.envoyproxy.protoc-gen-validate:pgv-java-grpc:$pgvVersion")
	implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
	implementation("net.devh:grpc-server-spring-boot-starter:2.12.0.RELEASE")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter:2.4.5")
	testImplementation("io.grpc:grpc-testing:$grpcKotlinVersion")
	testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
	testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
	testImplementation("io.kotest:kotest-framework-datatest:$kotestVersion")
	testImplementation("io.mockk:mockk:1.11.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.4.5")
}

idea {
	module {
		generatedSourceDirs.add(file("build/generated/source/proto/main/grpc"))
		generatedSourceDirs.add(file("build/generated/source/proto/main/grpckt"))
		generatedSourceDirs.add(file("build/generated/source/proto/main/java"))
	}
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:$protobufVersion"
	}
	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
		}
		id("grpckt") {
			artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk7@jar"
		}
		id("javapgv") {
			artifact = "io.envoyproxy.protoc-gen-validate:protoc-gen-validate:$pgvVersion"
		}
	}
	generateProtoTasks {
		ofSourceSet("main").forEach {
			it.plugins {
				id("grpc")
				id("grpckt")
				id("javapgv") {
					option("lang=java")
				}
			}
		}
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
