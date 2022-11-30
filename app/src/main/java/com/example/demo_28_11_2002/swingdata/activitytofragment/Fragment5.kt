package com.example.demo_28_11_2002.swingdata.activitytofragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demo_28_11_2002.R
import com.example.demo_28_11_2002.swingdata.fragmenttofragment.Fragment1
import kotlinx.android.synthetic.main.fragment_5.*
import kotlinx.android.synthetic.main.fragment_5.view.*


class Fragment5 : Fragment() {
    interface ISendDataListener2{
        fun sendDataFromFragment(data : String)
    }
    private lateinit var iSendDataListener2 : ISendDataListener2

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iSendDataListener2 = activity as ISendDataListener2
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_5, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.edtData.setText(this.arguments?.getString("message"))
        view.btnSendData.setOnClickListener {
            sendDataToActivity()
        }
    }

    private fun sendDataToActivity() {
        val data = edtData.text.toString()
        iSendDataListener2.sendDataFromFragment(data)
    }
}