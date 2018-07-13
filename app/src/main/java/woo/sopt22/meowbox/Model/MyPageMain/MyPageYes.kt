package woo.sopt22.meowbox.Model.MyPageMain

import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Model.RegisterCat.CatInformation

data class MyPageYes (
    var result : MyPageYesData
) : BaseModel()

data class MyPageYesData(
        var catinfo : String,
        var flag : Int,
        var image_profile : String,
        var sendImage : String,
        var ticket : String,
        var use : String
)

// Edit by 승우 : 민형이꺼 보고 추가
