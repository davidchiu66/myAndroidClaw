package com.demo.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

data class KimiMessage(
    val role: String,
    val content: String,
    val imageBase64: String? = null
)

object KimiApiClient {

    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    /**
     * Anthropic messages 格式调用 Kimi Coding API。
     *
     * @param messages 对话历史（role: "user" / "assistant"）
     * @param system 系统提示（顶层字段，非 messages 数组）
     * @param apiKey x-api-key 认证密钥
     * @param baseUrl 基础地址，默认 https://api.kimi.com/coding
     * @param model 模型 ID
     * @param maxTokens 最大输出 token 数
     * @param temperature 采样温度
     * @return 模型回复的文本内容
     */
    suspend fun chat(
        messages: List<KimiMessage>,
        system: String? = null,
        apiKey: String,
        baseUrl: String = "https://api.kimi.com/coding",
        model: String = "kimi-k2.5",
        maxTokens: Int = 8192,
        temperature: Double = 0.0
    ): String = withContext(Dispatchers.IO) {
        val url = "${baseUrl.removeSuffix("/")}/v1/messages"

        val body = JSONObject().apply {
            put("model", model)
            put("max_tokens", maxTokens)
            put("temperature", temperature)
            if (!system.isNullOrEmpty()) {
                put("system", system)
            }
            put("messages", JSONArray().apply {
                messages.forEach { msg ->
                    put(JSONObject().apply {
                        put("role", msg.role)
                        if (msg.imageBase64 != null) {
                            put("content", JSONArray().apply {
                                put(JSONObject().apply {
                                    put("type", "image")
                                    put("source", JSONObject().apply {
                                        put("type", "base64")
                                        put("media_type", "image/jpeg")
                                        put("data", msg.imageBase64)
                                    })
                                })
                                put(JSONObject().apply {
                                    put("type", "text")
                                    put("text", msg.content)
                                })
                            })
                        } else {
                            put("content", msg.content)
                        }
                    })
                }
            })
        }

        val request = Request.Builder()
            .url(url)
            .post(body.toString().toRequestBody("application/json".toMediaType()))
            .header("x-api-key", apiKey)
            .header("anthropic-version", "2023-06-01")
            .build()

        client.newCall(request).execute().use { response ->
            val responseBody = response.body?.string() ?: ""
            if (!response.isSuccessful) {
                throw KimiApiException(response.code, responseBody)
            }

            val json = JSONObject(responseBody)
            val contentArray = json.getJSONArray("content")
            for (i in 0 until contentArray.length()) {
                val block = contentArray.getJSONObject(i)
                if (block.getString("type") == "text") {
                    return@withContext block.getString("text")
                }
            }
            throw KimiApiException(0, "No text content in response: $responseBody")
        }
    }
}

class KimiApiException(val code: Int, message: String) : Exception("Kimi API Error $code: $message")
