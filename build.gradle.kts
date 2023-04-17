import org.gradle.kotlin.dsl.support.serviceOf
import java.util.Date

plugins {
    kotlin("jvm") version "1.5.10"
    groovy
    java
}

//apply<SimpleProjectPlugin>()
//apply<plugin.FileManagerPlugin>()
apply<plugin.CommandLinePlugin>()


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation(kotlin("stdlib"))
    implementation("org.codehaus.groovy:groovy-all:3.0.5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}


/** custom script tasks **/

task("printHello1") {
    println("hello from task1 in configuration phase")
}

tasks.register("printHello2") {
    doFirst {
        println("hello")
    }

    doLast {
        println("from task2 in execution phase")
    }

    dependsOn("printHello1")
}


task("printAllTask") {
    doLast {
        tasks.names.forEach { name ->
            logger.quiet(name)
//        logger.quiet(name,Throwable("OOOOOOPS!"))
//        logger.error(name)
        }
    }
}

task("createLogFile") {
    doLast {
        val targetDir = layout.projectDirectory.dir("/generate").toString()
        File(targetDir).mkdirs()
        File(targetDir, "log.html").writeText("version = 1.0.0")
    }
}

tasks.whenTaskAdded {
    if (this.name == "copyLogFile") {
        this.finalizedBy("deleteLogFile")
    }
}

task("copyLogFile", Copy::class) {
    val sourceDir = layout.projectDirectory.dir("/generate/log.html").toString()
    val targetDir = layout.projectDirectory.dir("/src/main/resources").toString()
    from(sourceDir)
    into(targetDir)
    rename("log.html", "ConfLog.html")
}

task("deleteLogFile", Delete::class) {
    val targetDir = layout.projectDirectory.dir("/generate/log.html").toString()
    delete(targetDir)
}

task("sendSlackMessage", SlackMessagingTask::class) {
    messageText = "hello from gradle!"
    webhookUrl = "https://hooks.slack.com/services/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"
}

task("sendEmail", EmailManagerTask::class) {
    val clock = project.gradle.serviceOf<org.gradle.initialization.BuildRequestMetaData>().startTime
    val convTime = Date(clock)
    subject = "Gradle Custom Task"
    text = "hi, this message sent from gradle EmailManagerTask at ${convTime.hours}:${convTime.minutes}"
}

tasks.whenTaskAdded {
    if (this.name == "findJPGExtension") {
        this.finalizedBy("sendEmailAfterInvalidExt")
    }
}

val jpgExtTask by tasks.register(
    "findJPGExtension",
    ExtensionFilterTask::class,
    layout.projectDirectory.dir("/src/main/resources/drawable").toString(),
    true
)

task("sendEmailAfterInvalidExt", EmailManagerTask::class) {
    subject = "Gradle Custom Task"
    text = "OOPS! i found an invalid extension format in drawable path"
    onlyIf {
        jpgExtTask.state.failure is Throwable
    }
}
