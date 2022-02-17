package com.yuvrajdeshmukh.udghosh

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class QnaAdapter(private val context: Context, val question:ArrayList<Question>): RecyclerView.Adapter<QnaAdapter.QnaViewHolder>() {

    class QnaViewHolder(view: View): RecyclerView.ViewHolder(view)
    {


        val txtUserEmail : TextView = view.findViewById(R.id.userEmail)
        val name : TextView = view.findViewById(R.id.username)
        val singleQuestion : TextView = view.findViewById(R.id.userQuestion)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):QnaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_row_question,parent,false)
        return QnaViewHolder(view)

    }

    override fun onBindViewHolder(holder: QnaViewHolder, position: Int) {
        val theQuestion = question[position]
        holder.txtUserEmail.text = theQuestion.user_email
        holder.name.text = theQuestion.time_Stamp
        holder.singleQuestion.text = theQuestion.askedQuestion

    }

    override fun getItemCount(): Int {
        return question.size
    }


}