package com.example.demo_28_11_2002.swingdata.fragmenttofragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demo_28_11_2002.R
import kotlinx.android.synthetic.main.fragment_2.*
import kotlinx.android.synthetic.main.fragment_2.view.*

class Fragment2 : Fragment() {
    private lateinit var  mIUpdate : IUpdateData
    interface IUpdateData{
        fun onUpDate(email: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mIUpdate = activity as IUpdateData
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnUpdate.setOnClickListener {
            updateDataToFragment1()
        }
    }

    private fun updateDataToFragment1() {
        val email = edtEmail2.text.toString().trim()
        mIUpdate.onUpDate(email)
    }
    //nhan data tu fragment 1
    fun receiveDataFromFragment1(email : String){
        edtEmail2.setText(email)
    }
}