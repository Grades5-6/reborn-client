package com.example.client

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent

class OAuthRedirectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Kakao OAuth URL 설정
        val clientId = "5098a2cd0f077065a8f1ff9a677b1c26"
        val redirectUri = "myapp://oauth"
        val kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?client_id=$clientId&redirect_uri=$redirectUri&response_type=code"

        // Custom Tabs로 Kakao 로그인 페이지로 리디렉션
        val customTabIntent = CustomTabsIntent.Builder().build()
        customTabIntent.launchUrl(this, Uri.parse(kakaoAuthUrl))

        // Kakao 로그인 후 인가 코드 받으면 처리
        handleOAuthRedirect()
    }

    private fun handleOAuthRedirect() {
        val uri: Uri? = intent?.data
        uri?.let {
            val code = it.getQueryParameter("code")
            if (code != null) {
                Log.d("OAuthRedirect", "Authorization Code: $code")
                // 인가 코드 처리 로직
            } else {
                val error = it.getQueryParameter("error")
                Log.e("OAuthRedirect", "Error: $error")
            }
        }

        // 인가 코드 확인 후 액티비티 종료
        finish()
    }
}