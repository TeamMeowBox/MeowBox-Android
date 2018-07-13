package woo.sopt22.meowbox.Model.Home

import woo.sopt22.meowbox.Model.Base.BaseModel

data class InstaCrawlingResponse (
        var result : ArrayList<CatData>
) : BaseModel()

data class CatData(
        var nickname : String,
        var profile : String,
        var picture : String
)