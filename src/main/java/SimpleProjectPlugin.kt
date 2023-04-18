import org.gradle.api.Plugin
import org.gradle.api.Project


open class SimpleProjectPlugin : Plugin<Project> {

    //@Deprecated("im here",ReplaceWith(""))
    override fun apply(target: Project) {
        target.task("sayHelloFromPlugin") {
            it.doLast {
                println("Hello From CustomPlugin")
            }
        }
    }
}