package woo.sopt22.meowbox.Model.Delete

import woo.sopt22.meowbox.Model.Base.BaseModel

data class DeleteTicket(
        var result : DeleteTicketData
) : BaseModel()

data class DeleteTicketData(
        var flag: String
)