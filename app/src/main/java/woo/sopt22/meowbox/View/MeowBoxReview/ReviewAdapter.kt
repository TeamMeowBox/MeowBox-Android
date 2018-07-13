package woo.sopt22.meowbox.View.MeowBoxReview

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.zarinpal.libs.cardviwepager.BaseCardViewPagerItem

import org.w3c.dom.Text

import woo.sopt22.meowbox.R

class ReviewAdapter : BaseCardViewPagerItem<ReviewModel>() {
    override fun getLayout(): Int {
        return R.layout.review_card_view
    }


    override fun bindView(view: View, item: ReviewModel) {


        val reviewCardViewImg = view.findViewById<ImageView>(R.id.review_card_view_img)
        val reviewCardViewTxt = view.findViewById<TextView>(R.id.review_card_view_hash)
        val reviewCardVeiwId = view.findViewById<TextView>(R.id.review_card_view_id)

        Glide.with(view.context).load(item.imgUrl).into(reviewCardViewImg)
        reviewCardViewTxt.text = item.hashText
        reviewCardVeiwId.text = item.hashId
    }
}
