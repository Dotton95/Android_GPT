package com.example.chatgptapp.module

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService

/**
 * 확장함수 관리 파일
 */

fun ArrayList<View>.viewAllChange(viewType:Int){ for(view in this) view.visibility = viewType }
