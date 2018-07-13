package woo.sopt22.meowbox.Model.QnA

import woo.sopt22.meowbox.Model.Base.BaseModel

data class QnAResponse(
        var result : qnaResult

) : BaseModel()

data class qnaResult(
        var product : ArrayList<ResultData>,
        var delivery : ArrayList<ResultData>,
        var packing : ArrayList<ResultData>,
        var subscribe : ArrayList<ResultData>
)


