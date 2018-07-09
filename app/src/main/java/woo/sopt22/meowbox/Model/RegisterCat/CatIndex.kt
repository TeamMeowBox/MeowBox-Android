package woo.sopt22.meowbox.Model.RegisterCat

import woo.sopt22.meowbox.Model.Base.BaseModel

data class CatIndex (
        var result : CatResult
) : BaseModel()

data class CatResult(
        var cat_idx : String
)