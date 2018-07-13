package woo.sopt22.meowbox.Model.Order

import woo.sopt22.meowbox.Model.Base.BaseModel

data class OrderResultResponse(
    var result : OrderResultResponseData
) : BaseModel()

data class OrderResultResponseData(
        var order_result : Boolean
)
