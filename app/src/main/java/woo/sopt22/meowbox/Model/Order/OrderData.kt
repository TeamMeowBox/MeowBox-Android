package woo.sopt22.meowbox.Model.Order

data class OrderData(
        var name : String,
        var address : String,
        var phone_number : String,
        var product : String,
        var price : String,
        var email : String,
        var payment_method : String
)
