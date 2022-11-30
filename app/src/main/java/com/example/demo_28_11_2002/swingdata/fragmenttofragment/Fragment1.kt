package com.example.demo_28_11_2002.swingdata.fragmenttofragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.demo_28_11_2002.R
import kotlinx.android.synthetic.main.activity_ato_bactivity.*
import kotlinx.android.synthetic.main.activity_ato_bactivity.btnSendData
import kotlinx.android.synthetic.main.fragment_1.*
import kotlinx.android.synthetic.main.fragment_1.view.*


class Fragment1 : Fragment() {
    interface ISendDataListener{
        fun sendData(email : String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mISendDataListener = activity as ISendDataListener
    }
    private lateinit var mISendDataListener : ISendDataListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        view.btnSendData.setOnClickListener {
            sendDataToFragment2()

        }
        return view
    }

    private fun sendDataToFragment2() {
        val email : String = edtEmail.text.toString().trim()
        mISendDataListener.sendData(email)

    }
    //nhan data tu fragment 2 thong qua interface taij main
    fun receiveDataFromFragment2(email: String){
        edtEmail.setText(email)
    }
}