package com.example.demo_28_11_2002.swingdata.fragmenttofragment.p2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.FragmentTransaction
import com.example.demo_28_11_2002.R
import kotlinx.android.synthetic.main.fragment_3.view.*
import kotlinx.android.synthetic.main.fragment_4.*
import kotlinx.android.synthetic.main.fragment_4.view.*

class Fragment4 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //nhan giu lieu cua fragment 3 chuyen vao
        parentFragmentManager.setFragmentResultListener("dataFrom3",this,object :FragmentResultListener{
            override fun onFragmentResult(requestKey: String, result: Bundle) {
                val data = result.getString("data")
                editSendTo3.setText(data)

            }

        })

        // truyen du lieu tu fragment 4 truyen di
        view.btnSendTo3.setOnClickListener {
            val data = view.editSendTo3
            val bundle = Bundle()
            bundle.putString("data4", editSendTo3.text.toString())
            parentFragmentManager.setFragmentResult("dataFrom4",bundle)
            fragmentManager?.popBackStack()
        }
    }

}