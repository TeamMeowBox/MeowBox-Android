package woo.sopt22.meowbox.Network

import retrofit2.Call
import retrofit2.http.*
import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Model.Login.LoginResponse
import woo.sopt22.meowbox.Model.Login.LoginUser
import woo.sopt22.meowbox.Model.MyPageMain.MyPageYes
import woo.sopt22.meowbox.Model.Order.OrderHistory
import woo.sopt22.meowbox.Model.QnA.QnAResponse
import woo.sopt22.meowbox.Model.SignUp.SignUpUser
import woo.sopt22.meowbox.Model.RegisterCat.CatInformation
import woo.sopt22.meowbox.Model.SignUp.SignUpResponse
import woo.sopt22.meowbox.Model.Suggest.MeowBoxSuggest

interface NetworkService {

    // 1. 회원가입 - 0
    @POST("user/signup")
    fun postSignUp(
            @Body singUser: SignUpUser
    ) : Call<SignUpResponse>

    // 2. 로그인 - 0
    @POST("user/signin")
    fun postSignIn(
            @Body loginUser: LoginUser
    ) : Call<LoginResponse>


    // 3. 고양이 등록 - 0
    //@FormUrlEncoded
    @POST("user/cat_signup")
    fun registerCat(
            @Header("authorization") authorization : String,
            @Body catInformation: CatInformation
    ) : Call<BaseModel>

    // 4. 회원탈퇴 - 0
    @HTTP(method = "DELETE", path = "user/account/{user_idx}", hasBody = false)
    fun deleteUser(
            @Header("token") token : String,
            @Path("user_idx") user_idx : String
    ):   Call<BaseModel>

    // 5. 주문 내역 - 0
    @GET("order/order_list/{user_idx}")
    fun getOrderHistory(
            @Header("authorization") authorization : String,
            @Path("user_idx") user_idx : String
    ) : Call<OrderHistory>

    // 6. 미유박스에 제안하기 - 0
    @POST("mypage/feedback")
    fun postSuggest(
            @Header("authorization") authorization : String,
            @Body meowBoxSuggest: MeowBoxSuggest
    ) : Call<BaseModel>

    //7. 마이페이지-1 - 0 손 좀 더 봐야함
    @GET("/mypage/mypageinfo/")
    fun getMyPageYes(
            @Header("authorization") authorization: String
    ) : Call<MyPageYes>

    // 8. Q&A 질문 불러오기
    @GET("mypage/qna")
    fun getQnA(
            @Header("authorization") authorization : String
    ) : Call<QnAResponse>


}