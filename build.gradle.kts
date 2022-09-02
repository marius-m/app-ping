buildscript {
    repositories {
        mavenCentral()
        jcenter()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.6.6")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
        classpath("org.jetbrains.kotlin:kotlin-noarg:1.6.20")
        classpath("gradle.plugin.org.flywaydb:gradle-plugin-publishing:6.4.3")
    }
}
