package woo.sopt22.meowbox.Network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import woo.sopt22.meowbox.Model.Login.LoginResponse
import woo.sopt22.meowbox.Model.Login.LoginUser

interface NetworkService {

    // 1.회원가입
    @POST("user/signup")
    fun postSignUp(
            @Body loginUser: LoginUser
    ) : Call<LoginResponse>
}