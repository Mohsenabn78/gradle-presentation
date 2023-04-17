package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Delete
import java.io.File

class FileManagerPlugin : Plugin<Project> {
    override fun apply(p0: Project) {

        p0.task("createLogFile") {
            it.doLast {
                val targetDir = p0.layout.projectDirectory.dir("/generate").toString()
                File(targetDir).mkdirs()
                File(targetDir, "log.html").writeText("version = 1.0.0")
            }
            it.finalizedBy("copyLogFile")
        }

        p0.tasks.register("copyLogFile", Copy::class.java).get().apply {
            val sourceDir = p0.layout.projectDirectory.dir("/generate/log.html").toString()
            val targetDir = p0.layout.projectDirectory.dir("/src/main/resources").toString()
            from(sourceDir)
            into(targetDir)
            rename("log.html", "ConfLog.html")

            this.finalizedBy("deleteLogFile")
        }

        p0.tasks.register("deleteLogFile", Delete::class.java).get().apply {
            val targetDir = p0.layout.projectDirectory.dir("/generate/log.html").toString()
            delete(targetDir)
        }
    }
}