package woo.sopt22.meowbox.Model.Order

data class OrderHistory (

        var idx : Int,
        var product : String,
        var payment_date : String,
        var flag : Int
)