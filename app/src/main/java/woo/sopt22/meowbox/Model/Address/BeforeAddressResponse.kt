package woo.sopt22.meowbox.Model.Address

import woo.sopt22.meowbox.Model.Base.BaseModel

data class BeforeAddressResponse(
        var result : BeforeData
) : BaseModel()

data class BeforeData(
        var order_idx : String,
        var name : String,
        var address : String,
        var phone_number : String,
        var email : String,
        var payment_date : String
)
