package com.example.client.data.api

import com.example.client.data.model.request.JobOnBoardingRequest
import com.example.client.data.model.request.KakaoLoginRequest
import com.example.client.data.model.request.MainOnBoardingRequest
import com.example.client.data.model.response.LicensesGetResponse
import com.example.client.data.model.request.mypage.EditInterestedRequest
import com.example.client.data.model.request.mypage.EditProfileRequest
import com.example.client.data.model.request.mypage.EditRegionRequest
import com.example.client.data.model.response.JobPostResponse
import com.example.client.data.model.response.MyPageResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {
    /** 카카오 로그인 */
    @POST("auth/kakao")
    fun sendKakaoToken(@Body request: KakaoLoginRequest): Call<Void>

    /** 온보딩 */
    @POST("users/onboarding")
    suspend fun submitOnboarding(@Body request: MainOnBoardingRequest): Response<Void>

    @POST("users/onboarding/jobs")
    suspend fun submitJobOnboarding(@Body request: JobOnBoardingRequest): Response<Void>

    /** 일자리 */
    @GET("jobs/posts")
    suspend fun getJob(): Response<List<JobPostResponse>>

    @GET("jobs/posts/search")
    suspend fun searchJob(): Response<Void>

    @GET("jobs/posts/licenses") //사용자가 클릭한 자격증 이름으로 request
    suspend fun getJobLicenses(): Response<Void>

    /** 자격증 리스트 소환 */
    @GET("licenses")
    suspend fun getLicenses(): Response<List<LicensesGetResponse>>

    /** 마이페이지 */
    @GET("users/mypage")
    suspend fun getUser(): Response<MyPageResponse>

    @PATCH("users/mypage") //닉네임, 프로필이미지, 재직여부 수정
    suspend fun setUserProfile(@Body request: EditProfileRequest): Response<Void>

    @PATCH("users/mypage/region") //동네 수정
    suspend fun setUserRegion(@Body request: EditRegionRequest): Response<Void>

    @PATCH("users/mypage/interests") //관심분야 수정
    suspend fun setUserInterests(@Body request: EditInterestedRequest): Response<Void>

    //자격증 수정 추가

    /** 커뮤니티 */
    @GET("community/posts") //커뮤니티 더미데이터 불러오기
    suspend fun getCommunity(): Response<Void>

}