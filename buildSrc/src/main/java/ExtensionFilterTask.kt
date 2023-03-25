import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class ExtensionFilterTask : DefaultTask() {

    @TaskAction
    fun findJPGExtension(){
        val resDir = project.projectDir.path + "/src/main/resources/drawable"
        val file = File(resDir)
        file.list()?.forEach { fileName ->
            if (fileName.contains(".JPG")) {

                logger.error(fileName,Throwable("Oh"))

//                project.tasks.getByName("sendEmail") {
//                    (it as EmailManagerTask).apply {
//                        subject = "Gradle Custom Task"
//                        text = "Oh No I Find Out A JPG Fie With Name $fileName At $resDir"
//                    }
//                }
            }
        }
    }

}