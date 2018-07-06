package woo.sopt22.meowbox.Model.Login

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import woo.sopt22.meowbox.Model.Base.BaseModel

data class LoginResponse(
        @SerializedName("result")
        @Expose
        open var result : result?=null
) : BaseModel()


data class result(
        var token : String,
        var user_idx : String,
        var cat_idx : String
)
