package woo.sopt22.meowbox.View.MeowBoxDetail

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_card_view.view.*

import woo.sopt22.meowbox.R

class DetailViewAdapter(private val inflater: LayoutInflater, private val mPageList: List<DetailModel>) : PagerAdapter() {
    private val context: Context? = null
    private var onItemClick: View.OnClickListener? = null

    override fun getCount(): Int {
        return mPageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = inflater.inflate(R.layout.detail_card_view, container, false)


        //val detailCardViewImg = view.findViewById<View>(R.id.detail_card_view_img) as ImageView
        //val detailCardViewTxt = view.findViewById<View>(R.id.detail_card_view_txt) as TextView
        val item = mPageList[position]
        Glide.with(container.context).load(item.imgUrl).into(view.detail_card_view_img)
        view.detail_card_view_txt.text = item.text

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