package com.example.client.domain

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.client.OnBoardingActivity
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


// 데이터 모델
data class KakaoLoginRequest(
    val authorizationCode: String // authorizationCode만 포함
)

// API 호출 -> POST request
interface ApiService {
    @POST("re-born.asia/api/auth/kakao")
    fun sendKakaoToken(@Body request: KakaoLoginRequest): Call<Void>
}

object RetrofitClient {
    private const val BASE_URL = "https://re-born.asia" // 서버 URL

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}


// [todo]: 카카오톡으로 로그인 요청(에뮬레이터에 카카오톡 어플 설치 및 로그인 필요)
fun loginWithKakao(context: Context) {
    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        if (error != null) {
            Log.e("Login", "Login Failed: ${error.message}")
        } else if (token != null) {
            Log.d("Login code", token.accessToken)
            sendTokenToServer(token.accessToken,context) // accessToken 대신 authorizationCode 사용
        }
    }
}

// [todo]: 서버에 authorizationCode 전송
private fun sendTokenToServer(authorizationCode: String, context: Context) {
    val request = KakaoLoginRequest(authorizationCode)

    RetrofitClient.instance.sendKakaoToken(request).enqueue(object : retrofit2.Callback<Void> {
        override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
            if (response.isSuccessful) {
                Log.d("TokenSend", "Token sent successfully!")

                val intent = Intent(context,OnBoardingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)

            } else {
                Log.e("TokenSend", "Failed to send token: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Log.e("TokenSend", "Network error: ${t.message}")
        }
    })
}