package com.example.demo_28_11_2002.swingdata

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.demo_28_11_2002.R
import com.example.demo_28_11_2002.swingdata.AuthenListener
import kotlinx.android.synthetic.main.activity_instagram.*

class InstagramActivity(context: Context,private val listener: AuthenListener):Dialog(context){

    companion object Token{
        private const val ACCESS_TOKEN ="access_token="
    }
    private val requestUrl :String
    val redirectUrl :String = context.resources.getString(R.string.request_url)
    init {
        requestUrl = String.format(
            context.resources.getString(R.string.request_url),
            context.resources.getString(R.string.client_id),
            redirectUrl
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instagram)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        webView.loadUrl(requestUrl)
        webView.webViewClient = object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url!!.startsWith(requestUrl)){
                    dismiss()
                    return true
                }
                return super.shouldOverrideUrlLoading(view, url)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url!!.contains(ACCESS_TOKEN)){
                    val accessToken =
                        url.substring(url.lastIndexOf(ACCESS_TOKEN) + ACCESS_TOKEN.length)
                    listener.onAuthenVerified(accessToken)
                    dismiss();
                } else if (url.contains("?error")) {
                    Log.e("access_token", "getting error fetching access token");
                    dismiss();
                }
            }
        }

    }
}