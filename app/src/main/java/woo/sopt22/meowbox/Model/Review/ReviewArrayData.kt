package woo.sopt22.meowbox.Model.Review

data class ReviewArrayData(
        var image_list : ArrayList<String>,
        var hashtag : ArrayList<String>,
        var insta_id : ArrayList<String>,
        var title : String,
        var comment : String
)