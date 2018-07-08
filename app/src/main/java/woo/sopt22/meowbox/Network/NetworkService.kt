package woo.sopt22.meowbox.Network

import retrofit2.Call
import retrofit2.http.*
import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Model.Login.LoginResponse
import woo.sopt22.meowbox.Model.Login.LoginUser
import woo.sopt22.meowbox.Model.Order.OrderHistory
import woo.sopt22.meowbox.Model.SignUp.SignUpUser
import woo.sopt22.meowbox.Model.RegisterCat.CatInformation

interface NetworkService {

    // 1. 회원가입
    @POST("user/signup")
    fun postSignUp(
            @Body singUser: SignUpUser
    ) : Call<LoginResponse>

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
    
}