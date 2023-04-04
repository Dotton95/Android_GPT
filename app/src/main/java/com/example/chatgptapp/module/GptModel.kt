package com.example.chatgptapp.module

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GptRequest (
    @SerializedName(value="model")
    @Expose
    val model:String,
    @SerializedName(value="messages")
    @Expose
    val messages:List<GptMessage>
)
data class GptMessage(
    @SerializedName(value="role")
    @Expose
    val role:String,
    @SerializedName(value="content")
    @Expose
    val content:String
)

data class GptResponse(
    @SerializedName(value="id")
    @Expose
    val id:String,
    @SerializedName(value="object")
    @Expose
    val _object:String,
    @SerializedName(value="created")
    @Expose
    val created:Int,
    @SerializedName(value="choices")
    @Expose
    val choices:List<Choices>,
    @SerializedName(value="usage")
    @Expose
    val usage: Usage
)
data class Choices(
    @SerializedName(value="index")
    @Expose
    val index:Int,
    @SerializedName(value="message")
    @Expose
    val message: GptMessage,
    @SerializedName(value="finish_reason")
    @Expose
    val finish_reason:String
)
data class Usage(
    @SerializedName(value="prompt_tokens")
    @Expose
    val prompt_tokens:Int,
    @SerializedName(value="completion_tokens")
    @Expose
    val completion_tokens:Int,
    @SerializedName(value="total_tokens")
    @Expose
    val total_tokens:Int
)
