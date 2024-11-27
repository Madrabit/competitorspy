plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "ru.madrabit"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation ("org.seleniumhq.selenium:selenium-java:4.26.0")
	implementation ("org.springframework.data:spring-data-mongodb:4.4.0")
	implementation ("io.github.bonigarcia:webdrivermanager:5.5.3")
	implementation ("jakarta.validation:jakarta.validation-api:3.0.2")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
