package com.example.chatgptapp.viewmodel

import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatgptapp.module.GptMessage
import com.example.chatgptapp.module.GptRequest
import com.example.chatgptapp.module.GptResponse
import com.example.chatgptapp.module.RetrofitClient
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel(){

    var liveList:MutableLiveData<ArrayList<Pair<Int,String>>> = MutableLiveData()

    var msgList:ArrayList<Pair<Int,String>> = ArrayList<Pair<Int,String>>()

    var inputText:MutableLiveData<String> = MutableLiveData()

    var toastMsg:MutableLiveData<String> = MutableLiveData()

    fun submit(msg:String){
        msgList.add(Pair(0,msg))

        liveList.value = msgList

        inputText.value = ""
        var submitList = listOf(GptMessage("user",msg))
        var body = GptRequest("gpt-3.5-turbo",submitList)
        RetrofitClient.init().postTest(body).enqueue(object : Callback<GptResponse> {
            override fun onResponse(call: Call<GptResponse>, response: Response<GptResponse>) {
                if(response.isSuccessful){
                    Logger.d(response.body())
                    var output = response.body()!!.choices[0].message.content
                    msgList.add(Pair(1,output))
                    liveList.value = msgList
                    inputText.value = ""
                }else{
                    toastMsg.value = "통신 실패"
                }
            }
            override fun onFailure(call: Call<GptResponse>, t: Throwable) {
                toastMsg.value = "예기치 못한 에러"
            }
        })



    }
}