package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

import kotlinx.android.synthetic.main.activity_guide_caregiver.*

class GuideCaregiver : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_caregiver)

        val webView: WebView = findViewById(R.id.panel) //?
        val url = "https://github.com/mikunciek/PWR_Telemedicine_2022/raw/master/Poradnik%20dla%20opiekunow%20pacjent%C3%B3w%20z%20chorob%C4%85%20Alzheimera.pdf"

        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
        webView.isVerticalScrollBarEnabled = true
        webView.canGoBack()
        webView.canGoForward()
        webView.requestFocus()
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")

        backMainMenu.setOnClickListener {
            this.startActivity(Intent(this, MainActivity::class.java))
        }
    }

}