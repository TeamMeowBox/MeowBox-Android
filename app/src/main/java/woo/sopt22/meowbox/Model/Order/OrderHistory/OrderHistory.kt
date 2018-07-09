package woo.sopt22.meowbox.Model.Order.OrderHistory

import woo.sopt22.meowbox.Model.Base.BaseModel

data class OrderHistory (
        var result : OrderHistoryDate
) : BaseModel()

data class OrderHistoryDate(
        var ticket : ticketData,
        var ticketed : ArrayList<ticketData>
)
