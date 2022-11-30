package com.example.demo_28_11_2002.swingdata.fragmenttofragment.p2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.FragmentTransaction
import com.example.demo_28_11_2002.R
import com.example.demo_28_11_2002.swingdata.fragmenttofragment.Fragment1
import com.example.demo_28_11_2002.swingdata.fragmenttofragment.Fragment2
import kotlinx.android.synthetic.main.activity_ato_bactivity.view.*
import kotlinx.android.synthetic.main.fragment_3.*
import kotlinx.android.synthetic.main.fragment_3.view.*
import kotlinx.android.synthetic.main.fragment_4.*


class Fragment3 : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    val name: String = Fragment3::class.java.name

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //nhan giu lieu cua fragment 4 chuyen vao
        parentFragmentManager.setFragmentResultListener(
            "dataFrom4", this
        ) { _, result ->
            val data = result.getString("data4")
            editSendTo4.setText(data)
        }

        //gui du lieu fragment 3 truyen di
        view.btnSendTo4.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("data", editSendTo4.text.toString())
            fragmentManager?.setFragmentResult("dataFrom3", bundle)
            val fragment = Fragment4()
            val fragmentTransaction: FragmentTransaction = fragmentManager?.beginTransaction()!!
            fragmentTransaction.replace(R.id.layoutContainer, fragment)
            fragmentTransaction.addToBackStack(name)
            fragmentTransaction.commit()
            editSendTo4.setText("")
        }
    }

}