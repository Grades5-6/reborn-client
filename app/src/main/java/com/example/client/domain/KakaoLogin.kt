package com.example.client.domain

import android.content.Context
import android.util.Log
import com.example.client.data.api.RetrofitClient
import com.example.client.data.model.request.KakaoLoginRequest
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
//
//// [todo]: 카카오톡으로 로그인 요청(에뮬레이터에 카카오톡 어플 설치 및 로그인 필요)
//fun loginWithKakao(context: Context) {
//    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
//        if (error != null) {
//            Log.e("Login", "Login Failed: ${error.message}")
//        } else if (token != null) {
//            Log.d("Login code", token.accessToken)
//            sendTokenToServer(token.accessToken)
//            UserInfo.fetchUserInfo { user ->
//                if (user != null) {
//                    Log.d("UserInfo", "User ID: ${user.userId}, Nickname: ${user.nickname}")
//                }
//            }
//        }
//    }
//}
//
//// [todo]: 서버에 accessToken 전송
//private fun sendTokenToServer(authorizationCode: String) {
//    val request = KakaoLoginRequest(authorizationCode)
//
//    RetrofitClient.instance.sendKakaoToken(request).enqueue(object : retrofit2.Callback<Void> {
//        override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
//            if (response.isSuccessful) {
//                Log.d("TokenSend", "Token sent successfully!")
//            } else {
//                Log.e("TokenSend", "Failed to send token: ${response.code()}")
//            }
//        }
//
//        override fun onFailure(call: Call<Void>, t: Throwable) {
//            Log.e("TokenSend", "Network error: ${t.message}")
//        }
//    })
//}
