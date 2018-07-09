package woo.sopt22.meowbox.Network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Model.Login.LoginResponse
import woo.sopt22.meowbox.Model.Login.LoginUser
import woo.sopt22.meowbox.Model.MyAccountSetting.MyAccountSettingGet
import woo.sopt22.meowbox.Model.MyAccountSetting.MyAccountSettingPost
import woo.sopt22.meowbox.Model.MyAccountSetting.MyAccountSettingPostResponse
import woo.sopt22.meowbox.Model.MyPageMain.MyPageYes
import woo.sopt22.meowbox.Model.Order.OrderHistory
import woo.sopt22.meowbox.Model.SignUp.SignUpUser
import woo.sopt22.meowbox.Model.RegisterCat.CatInformation
import woo.sopt22.meowbox.Model.SignUp.SignUpResponse
import woo.sopt22.meowbox.Model.Suggest.MeowBoxSuggest

interface NetworkService {

    // 1. 회원가입
    @POST("user/signup")
    fun postSignUp(
            @Body singUser: SignUpUser
    ) : Call<SignUpResponse>

    // 2. 로그인
    @POST("user/signin")
    fun postSignIn(
            @Body loginUser: LoginUser
    ) : Call<LoginResponse>


    // 3. 고양이 등록
    //@FormUrlEncoded
    @POST("user/cat_signup")
    fun registerCat(
            @Header("authorization") authorization : String,
            @Body catInformation: CatInformation
    ) : Call<BaseModel>

    // 4. 회원탈퇴
    @HTTP(method = "DELETE", path = "user/account/{user_idx}", hasBody = false)
    fun deleteUser(
            @Header("token") token : String,
            @Path("user_idx") user_idx : String
    ):   Call<BaseModel>

    // 5. 주문 내역
    @GET("order/order_list/{user_idx}")
    fun getOrderHistory(
            @Header("authorization") authorization : String,
            @Path("user_idx") user_idx : String
    ) : Call<OrderHistory>

    // 6. 미유박스에 제안하기
    @POST("mypage/feedback")
    fun postSuggest(
            @Header("authorization") authorization : String,
            @Body meowBoxSuggest: MeowBoxSuggest
    ) : Call<BaseModel>

    //7. 마이페이지-1
    @GET("/mypage/mypageinfo/")
    fun getMyPageYes(
            @Header("authorization") authorization: String
    ) : Call<MyPageYes>

    //8h. 계정설정화면
    @GET("mypage/account_setting/account")
    fun getMyAccount(
            @Header("authorization") authorization: String
    ) : Call<MyAccountSettingGet>

    //9h. 계정설정화면-2
    @Multipart
    @POST("mypage/account_setting/account")
    fun postMyAccount(
            @Header("authorization") authorization: String,
            @Part("user_name") user_name : RequestBody,
            @Part("user_phone") user_phone : RequestBody,
            @Part("user_email") user_email : RequestBody,
            @Part image_profile : MultipartBody.Part?,
            @Part("cat_name") cat_name : RequestBody?,
            @Part("cat_size") cat_size : RequestBody?,
            @Part("cat_birthday") cat_birthday : RequestBody?,
            @Part("cat_caution") cat_caution : RequestBody?
            ) : Call<MyAccountSettingPostResponse>
}