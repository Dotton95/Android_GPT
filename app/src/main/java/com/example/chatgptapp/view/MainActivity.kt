package com.example.chatgptapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager

import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatgptapp.R
import com.example.chatgptapp.databinding.ActivityMainBinding
import com.example.chatgptapp.module.RecyclerAdapter
import com.example.chatgptapp.module.viewAllChange
import com.example.chatgptapp.viewmodel.MainViewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private val model:MainViewModel by viewModels()
    private var mainViewList = arrayListOf<View>()

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainBinding.lifecycleOwner = this
        mainBinding.mainViewModel = model

        var manager = LinearLayoutManager(this)
        manager.stackFromEnd = true
        mainBinding.recycler.layoutManager = manager

        mainViewList.apply {
            add(mainBinding.logoTitle)
            add(mainBinding.recycler)
            add(mainBinding.bottomLayout)
            add(mainBinding.view)
        }

        mainViewList.viewAllChange(View.INVISIBLE)
        loadingAnimation()

        model.liveList.observe(this, Observer {
            var adapter = RecyclerAdapter(this,model.msgList)
            mainBinding.recycler.adapter = adapter
        })
        mainBinding.recycler.setOnTouchListener { view, motionEvent ->
            if(currentFocus!=null){
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(this@MainActivity.currentFocus!!.windowToken, 0)
            }
            false
        }
        model.toastMsg.observe(this, Observer {
            Toast.makeText(this, "${model.toastMsg.value}", Toast.LENGTH_SHORT).show()
        })

    }
    private fun loadingAnimation(){
        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            var anim = AnimationUtils.loadAnimation(this@MainActivity,R.anim.logo_anim)
            mainBinding.logoAnim.startAnimation(anim)
            mainBinding.loading.visibility = View.GONE
            delay(1000)
            mainViewList.viewAllChange(View.VISIBLE)
            delay(50)
            mainBinding.logoAnim.visibility = View.GONE
        }
    }
}