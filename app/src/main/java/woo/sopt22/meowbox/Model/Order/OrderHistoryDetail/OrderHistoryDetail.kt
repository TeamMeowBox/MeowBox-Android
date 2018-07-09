package woo.sopt22.meowbox.Model.Order.OrderHistoryDetail

import woo.sopt22.meowbox.Model.Base.BaseModel

data class OrderHistoryDetail (
        var result : DetailImage
) : BaseModel()

data class DetailImage(
        var imageList : ArrayList<String>
)