package woo.sopt22.meowbox.Model.Login

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import woo.sopt22.meowbox.Model.Base.BaseModel

data class LoginResponse(
        @SerializedName("result")
        @Expose
        open var result : LoginResult?=null
) : BaseModel()


data class LoginResult(
        var token : String,
        var email : String,
        var name : String,
        var phone_number : String,
        var image_background : Int?,
        var image_profile : Int?,
        var cat_idx : String
)
