package woo.sopt22.meowbox.View.MyPage.FAQ.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.header_parent.view.*
import woo.sopt22.meowbox.R

class HeaderViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    var header_name : TextView = itemView.findViewById(R.id.header_text)
}