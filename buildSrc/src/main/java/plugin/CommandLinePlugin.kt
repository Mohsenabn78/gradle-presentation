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
    }
}