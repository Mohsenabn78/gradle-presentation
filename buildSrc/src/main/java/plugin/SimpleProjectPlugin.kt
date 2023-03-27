package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class SimpleProjectPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.task("sayHelloFromPlugin") {
            it.doLast {
                println("Hello From CustomPlugin")
            }
        }
    }
}