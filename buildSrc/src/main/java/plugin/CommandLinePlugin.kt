package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec

class CommandLinePlugin : Plugin<Project> {
    override fun apply(p0: Project) {

        p0.tasks.register("pingGoogle", Exec::class.java).get().apply {
            this.commandLine(
                "ping", "-n", "5", "8.8.8.8"
            )

            doLast {
                if (executionResult.get().exitValue != 0) {
                    println("Google is down!")
                }
            }
        }



        p0.tasks.register("detectOS", Exec::class.java).get().apply {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                this.commandLine("cmd", "/c", "ver")
            } else {
                this.commandLine("uname", "-a")
            }
        }

    }
}