package com.yuvrajdeshmukh.udghosh

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class QnaFragment : Fragment() {




    var recyclearView: RecyclerView? = null


    var layoutManager: RecyclerView.LayoutManager? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var questionListMA:ArrayList<Question>? = null
        val view = inflater.inflate(R.layout.fragment_qna, container, false)
        questionListMA = ArrayList()
        questionListMA?.add(Question(1,"sivyamK@gmail.com","Sivyam Kumar","this my question 1","2"))

        recyclearView = view?.findViewById(R.id.recyclearView)

        layoutManager = LinearLayoutManager(activity as Context)

        //creating our adapter
        val adapter = QnaAdapter(activity as Context, questionListMA!!)

        //now adding the adapter to recyclerview
        recyclearView?.adapter = adapter
        recyclearView?.layoutManager = layoutManager

        return view
    }


}