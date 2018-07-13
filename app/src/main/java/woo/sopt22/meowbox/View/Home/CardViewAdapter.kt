package woo.sopt22.meowbox.View.Home

import android.content.Context
import android.content.Intent
import android.media.Image
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide

import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.MeowBoxBirthDay.MeowBoxtBirthDayStoryActivity
import woo.sopt22.meowbox.View.MeowBoxDetail.MeowBoxDetailActivity

class CardViewAdapter(private val inflater: LayoutInflater, private val mPageList: List<CardData>) : PagerAdapter() {
    private val context: Context? = null
    private var onItemClick: View.OnClickListener? = null

    override fun getCount(): Int {
        return mPageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = inflater.inflate(R.layout.cardview_adapter, container, false)


        val home_detail_btn = view.findViewById<View>(R.id.home_detail_btn) as ImageView
        home_detail_btn.setOnClickListener(onItemClick)
        val mImageView = view.findViewById<ImageView>(R.id.titleImageView)
        val item = mPageList[position]
        Glide.with(container.context).load(item.image).into(mImageView)
        if (item.tmp == 1) {
            home_detail_btn.setImageResource(R.drawable.home_detail_btn_white)
        } else if (item.tmp == 2) {
            home_detail_btn.visibility = View.INVISIBLE
        }
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
