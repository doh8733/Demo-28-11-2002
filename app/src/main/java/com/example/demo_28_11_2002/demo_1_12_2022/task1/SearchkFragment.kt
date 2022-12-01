package com.example.demo_28_11_2002.demo_1_12_2022.task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demo_28_11_2002.R
import kotlinx.android.synthetic.main.fragment_heart.*
import kotlinx.android.synthetic.main.fragment_heart.webView
import kotlinx.android.synthetic.main.fragment_searchk.*


class SearchkFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_searchk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mimeType = "text/html";
        val  encoding = "UTF-8";
        val html ="<br /><br />Read the handouts please for tomorrow.<br /><br /><!--homework help homework" +
                "help help with homework homework assignments elementary school high school middle school" +
                "// --><font color='#60c000' size='4'><strong>Please!</strong></font>"

        webView.loadDataWithBaseURL("",html,mimeType,encoding,"")
    }

}