package com.example.chatgptapp.module

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GptAPI {
    @POST("completions")
    fun postTest(@Body body:GptRequest) : Call<GptResponse>
}