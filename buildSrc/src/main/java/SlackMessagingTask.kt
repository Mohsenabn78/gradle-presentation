import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

open class SlackMessagingTask : DefaultTask() {

    @Input
    var messageText: String? = null

    @Input
    var webhookUrl: String? = null

    @TaskAction
    fun sendMessage() {
        val logger = HttpLoggingInterceptor { m -> logger.debug(m) }
        logger.level = HttpLoggingInterceptor.Level.BODY
        val okhttp = OkHttpClient.Builder().addInterceptor(logger).build()
        val body = RequestBody.create(
            MediaType.parse("application/json"),
            "{\"text\":\"${messageText!!.replace("\"", "\\\"")}\"}"
        )
        val request = Request.Builder()
            .url(webhookUrl!!)
            .post(body)
            .build()
        okhttp.newCall(request).execute()
    }

}