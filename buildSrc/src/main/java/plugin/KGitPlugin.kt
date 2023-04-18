package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.impldep.org.eclipse.jgit.api.Git
import java.nio.file.Paths


class KGitPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("gitClone"){
           it.doLast {
               val localPath = Paths.get("C:/Users/mohsen/Desktop")
               val remoteUrl = "https://github.com/Mohsenabn78/teleport.git"
               val branchName = "main"
               val git = Git.cloneRepository()
                   .setURI(remoteUrl)
                   .setDirectory(localPath.toFile())
                   .setBranch(branchName)
                   .call()
           }
        }
    }
}