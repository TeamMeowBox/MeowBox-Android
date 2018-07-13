package woo.sopt22.meowbox.View.MeowBoxReview

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import woo.sopt22.meowbox.R

class ReviewViewAdapter(private val inflater: LayoutInflater, private val mPageList: List<ReviewModel>) : PagerAdapter() {
    private val context: Context? = null
    private var onItemClick: View.OnClickListener? = null

    override fun getCount(): Int {
        return mPageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = inflater.inflate(R.layout.review_card_view, container, false)


        val reviewCardViewImg = view.findViewById<View>(R.id.review_card_view_img) as ImageView
        val reviewCardViewTxt = view.findViewById<View>(R.id.review_card_view_hash) as TextView
        val reviewCardVeiwId = view.findViewById<View>(R.id.review_card_view_id) as TextView
        val item = mPageList[position]
        Glide.with(container.context).load(item.imgUrl).into(reviewCardViewImg)
        reviewCardViewTxt.text = item.hashText
        reviewCardVeiwId.text = item.hashId

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


    fun setOnItemClickListener(l: View.OnClickListener) {
        onItemClick = l
    }
}