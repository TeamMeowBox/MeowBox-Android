package woo.sopt22.meowbox.Model.MyAccountSetting

import woo.sopt22.meowbox.Model.Base.BaseModel

data class MyAccountSettingGet (
        var result : MyAccountSettingGetData
) : BaseModel()

data class MyAccountSettingGetData(
        var user_name : String,
        var email : String,
        var phone_number : String,
        var image_profile : String,
        var cat_name : String,
        var size : String,
        var birthday : String,
        var caution : String
)