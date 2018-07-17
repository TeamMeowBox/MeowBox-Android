package woo.sopt22.meowbox.Model.SignUp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import woo.sopt22.meowbox.Model.Base.BaseModel

data class SignUpResponse(
        @SerializedName("result")
        @Expose
        open var result : signResult?=null
) : BaseModel()

data class signResult(
        var token : String

)
