package woo.sopt22.meowbox.Model.Review

import woo.sopt22.meowbox.Model.Base.BaseModel

data class ReviewResponse (
        var result : ReviewData
) : BaseModel()

data class ReviewData(
        var birthday : ReviewArrayData,
        var best_image_7 : ReviewArrayData,
        var best_image_6 : ReviewArrayData
)