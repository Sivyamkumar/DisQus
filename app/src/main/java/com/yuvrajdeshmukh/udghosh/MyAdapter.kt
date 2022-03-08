package com.yuvrajdeshmukh.udghosh

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessController.getContext
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity

class MyAdapter(private val userlist: ArrayList<User> ) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val viewdata = inflater.inflate(R.layout.userrlayout,parent,false)
        return MyViewHolder(viewdata)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userlist[position]
        holder.username.text = currentItem.Name
        holder.useremail.text = currentItem.Email
       if(holder.question == null){
          return;
       }else{
           holder.question.text = currentItem.Question
       }

        holder.answer.setOnClickListener {
           it.setOnClickListener {
               val intent =Intent(it.context,Answer::class.java)
               it.context.startActivity(intent)
           }
        }

    }

    override fun getItemCount(): Int {
        return  userlist.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username : TextView = itemView.findViewById(R.id.username)
        val useremail : TextView = itemView.findViewById(R.id.useremail)
        val question : TextView = itemView.findViewById(R.id.QUESTION)
        val answer : ImageView = itemView.findViewById(R.id.comment)


    }

}