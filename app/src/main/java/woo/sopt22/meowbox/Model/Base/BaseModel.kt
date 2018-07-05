package woo.sopt22.meowbox.Model.Base

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseModel(
        @SerializedName("status")
        @Expose
        open var status : Boolean?=null,
        @SerializedName("message")
        @Expose
        open var message : String?=null

)