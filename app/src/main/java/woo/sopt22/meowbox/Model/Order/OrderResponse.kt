package woo.sopt22.meowbox.Model.Order

data class OrderResponse (
        var result : OrderResponseData
)
data class OrderResponseData(
        var flag : String,
        var order_idx : Int
)