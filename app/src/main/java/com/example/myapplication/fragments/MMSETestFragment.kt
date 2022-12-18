package com.example.myapplication.fragments

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_guide.*
import java.util.*


class MMSETestFragment : Fragment() {
    val webView: WebView = panel//?
    val url =
        "https://github.com/mikunciek/PWR_Telemedicine_2022/raw/master/pdf/MMSE.pdf"

    var mmseImageList = arrayListOf(R.drawable.mmse1,R.drawable.mmse2,
        R.drawable.mmse3,R.drawable.mmse4)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_m_s_etest, container, false)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {  //właściwe wyświetlanie
        super.onViewCreated(view, savedInstanceState)
        init()

       backMainMenu.setOnClickListener() { findNavController().navigate(R.id.action_global_menuCaregiverFragment) }

    }


    private fun init() {

        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
        webView.isVerticalScrollBarEnabled = true
        webView.canGoBack()
        webView.canGoForward()
        webView.requestFocus()
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")

    }


}