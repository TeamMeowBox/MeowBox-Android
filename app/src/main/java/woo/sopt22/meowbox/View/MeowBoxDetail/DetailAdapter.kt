package woo.sopt22.meowbox.View.MeowBoxDetail

import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.shuhart.bubblepagerindicator.BubblePageIndicator
import com.zarinpal.libs.cardviwepager.BaseCardViewPagerItem
import com.zarinpal.libs.cardviwepager.ScrollViewPagerIndicator

import woo.sopt22.meowbox.R

class DetailAdapter : BaseCardViewPagerItem<DetailModel>() {

    override fun getLayout(): Int {
        return R.layout.detail_card_view
    }


    override fun bindView(view: View, item: DetailModel) {


        val detailCardViewImg = view.findViewById<ImageView>(R.id.detail_card_view_img)
        val detailCardViewTxt = view.findViewById<TextView>(R.id.detail_card_view_txt)

        Glide.with(view.context).load(item.imgUrl).into(detailCardViewImg)
        detailCardViewTxt.text = item.text
    }
}
