import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("org.jetbrains.kotlin.plugin.noarg")
    id("org.flywaydb.flyway")
}

val appProps = Properties().apply {
    file("${projectDir}/src/main/resources/application.properties")
            .inputStream()
            .use { load(it) }
}
group = "lt.markmerkk"
version = appProps.getProperty("version")

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-noarg:1.6.20")
    implementation("org.jetbrains.kotlin:kotlin-maven-noarg:1.6.20")

    // Other
    implementation("commons-io:commons-io:2.6")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.7")
    implementation("joda-time:joda-time:2.10.10")

    // Database
    implementation("org.flywaydb:flyway-core:6.4.3")
    implementation("mysql:mysql-connector-java:8.0.23")

    // Spring
    implementation("org.springframework.boot:spring-boot-starter:2.6.6")
    implementation("org.springframework.boot:spring-boot-starter-data-rest:2.6.6")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.6")
    developmentOnly("org.springframework.boot:spring-boot-devtools:2.6.6")
    implementation("javax.xml.bind:jaxb-api:2.3.0")

    // Firebase
    implementation("com.google.firebase:firebase-admin:9.0.0")

    // Logging
    implementation("org.slf4j:jul-to-slf4j:1.7.12")
    implementation("org.slf4j:log4j-over-slf4j:1.7.12")
    implementation("org.slf4j:slf4j-api:1.7.12")
    implementation("ch.qos.logback:logback-classic:1.2.11")

    testImplementation("junit:junit:4.12")
    testImplementation("org.hamcrest:hamcrest-library:1.3")
    testImplementation("org.mockito:mockito-core:2.23.0")
    testImplementation("org.assertj:assertj-core:3.8.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.6")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "11"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "11"
}

extensions.getByType(org.springframework.boot.gradle.dsl.SpringBootExtension::class.java).apply {
    buildInfo()
}

extensions.getByType(org.flywaydb.gradle.FlywayExtension::class.java).apply {
    val appProps = Properties().apply {
        file("${projectDir}/src/main/resources/creds.properties")
            .inputStream()
            .use { load(it) }
    }
    url = "jdbc:mysql://localhost:3306/${appProps.getProperty("db.primary.relativeUrl")}"
    user = appProps.getProperty("db.primary.username")
    password = appProps.getProperty("db.primary.password")
}
