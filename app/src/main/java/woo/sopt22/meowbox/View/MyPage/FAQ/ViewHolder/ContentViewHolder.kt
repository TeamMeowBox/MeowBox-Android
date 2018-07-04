package woo.sopt22.meowbox.View.MyPage.FAQ.ViewHolder

import android.view.View
import android.widget.TextView
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import woo.sopt22.meowbox.R

class ContentViewHolder(itemView : View) : ChildViewHolder(itemView){

    var content_name : TextView = itemView.findViewById(R.id.list_item_content_name)
}