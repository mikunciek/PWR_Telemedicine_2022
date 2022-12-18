package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGuideBinding
import com.example.myapplication.databinding.FragmentMenuCaregiverBinding
import kotlinx.android.synthetic.main.fragment_guide.*

class GuideFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentGuideBinding
    val webView: WebView = panel//?
    val url =
        "https://github.com/mikunciek/PWR_Telemedicine_2022/raw/master/pdf/Opieka%20nad%20chorym%20na%20alzheimera.pdf"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guide, container, false)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {  //właściwe wyświetlanie
        super.onViewCreated(view, savedInstanceState)

        init()

        backMainMenu.setOnClickListener() {
            findNavController().navigate(R.id.action_global_menuCaregiverFragment)
        }

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