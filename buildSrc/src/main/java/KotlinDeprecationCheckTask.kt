import org.gradle.api.DefaultTask

import org.gradle.api.tasks.TaskAction


open class KotlinDeprecationCheckTask : DefaultTask() {

    @TaskAction
    fun checkDeprecation() {
        val deprecatedAnnotations = listOf("@Deprecated", "@java.lang.Deprecated")
        val files = project.fileTree(project.projectDir).matching {
            it.include("**/*.kt")
        }.files
        files.forEach { file ->
            if (file.nameWithoutExtension != "KotlinDeprecationCheckTask") {
                val source = file.readText()
                val deprecatedUsages = deprecatedAnnotations.filter { source.contains(it) }
                if (deprecatedUsages.isNotEmpty()) {
                    logger.error("Deprecated declarations found in file: $file")
                    logger.error(deprecatedUsages.joinToString("\n"))
                }
            }
        }
    }

}
