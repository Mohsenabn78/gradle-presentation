
plugins {
    kotlin("jvm") version "1.5.10"
    groovy
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.codehaus.groovy:groovy-all:3.0.5")

    // okHttp
    implementation("com.squareup.okhttp3:logging-interceptor:3.8.1")
    implementation("com.squareup.okhttp3:okhttp:3.8.1")

    // sun mail
    implementation("com.sun.mail:android-mail:1.6.0")
    implementation("com.sun.mail:android-activation:1.6.0")

    //JGit
    //implementation("org.eclipse.jgit:org.eclipse.jgit:5.4.2.202011171130-r")
}