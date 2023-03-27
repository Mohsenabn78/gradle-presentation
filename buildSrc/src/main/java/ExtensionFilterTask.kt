import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction
import java.io.File
import javax.inject.Inject

open class ExtensionFilterTask @Inject constructor(
    private val resDir: String,
    taskEnabled: Boolean
) : DefaultTask() {

    init {
        enabled = taskEnabled
    }

    @TaskAction
    fun findJPGExtension() {
        val file = File(resDir)
        file.list()?.forEach { fileName ->
            if (fileName.contains(".JPG")) {
                throw GradleException("Invalid Extension")
            }
        }
    }

}