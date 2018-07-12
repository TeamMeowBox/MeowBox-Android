package woo.sopt22.meowbox.Model.MyAccountSetting

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import woo.sopt22.meowbox.Model.Base.BaseModel

data class MyAccountSettingPostResponse(
        @SerializedName("result")
        @Expose
        open var result : MyAccountSettingResult?=null
) : BaseModel()


data class MyAccountSettingResult(
        var cat_idx : Int,
        var token : String
)