package com.example.chatgptapp.module

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

class App:Application() {
    override fun onCreate() {
        val format: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)//스레드 정보 표시 여부
            .methodCount(2)//표시할 메서드 라인 수
            .methodOffset(0)//설정한 오프셋까지 내부 메서드 호출을 숨김
            .tag("dotton95")// 글로벌 태그
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(format))
        super.onCreate()
    }
}