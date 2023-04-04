package com.example.chatgptapp.module

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.chatgptapp.R
import com.example.chatgptapp.databinding.ItemListBinding
import com.example.chatgptapp.module.RecyclerAdapter.MyViewHolder

@SuppressLint("ResourceAsColor")
class RecyclerAdapter(val context: Context,val msgList:ArrayList<Pair<Int,String>>) : RecyclerView.Adapter<MyViewHolder>() {
    inner class MyViewHolder(val binding:ItemListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(msg:Pair<Int,String>){

            if(msg.first==0) setView(Gravity.RIGHT,R.color.gpt_deep_color)
            else setView(Gravity.LEFT,R.color.gpt_color)

            binding.msg.text = msg.second
        }
        private fun setView(gravity: Int, color:Int){
            binding.msgBackgroud.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
            binding.msgLayout.gravity = gravity
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var binding = ItemListBinding.inflate(inflater, parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = msgList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(msgList[holder.adapterPosition])
    }
}