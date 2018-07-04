package woo.sopt22.meowbox.View.MyPage.FAQ.Adapter


import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.MyPage.FAQ.ViewHolder.ContentViewHolder
import woo.sopt22.meowbox.View.MyPage.FAQ.ViewHolder.ParentViewHolder
import woo.sopt22.meowbox.View.MyPage.FAQ.models.Child






class ParentAdapter(groups: List<ExpandableGroup<*>>)
    : ExpandableRecyclerViewAdapter<ParentViewHolder, ContentViewHolder>(groups) {
/*
    private val TYPE_HEADER : Int = 0
    private val TYPE_ITEM : Int = 1*/


    override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_parent, parent, false)
        return ParentViewHolder(view)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_child, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindChildViewHolder(holder: ContentViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?, childIndex: Int) {
        val content = group!!.getItems()[childIndex] as Child
        holder!!.content_name.text = content.content_name

    }

    override fun onBindGroupViewHolder(holder: ParentViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?) {
        if(flatPosition == 0){
            holder!!.genre_title.setTextSize(TypedValue.COMPLEX_UNIT_PX,60f)
        }
        holder!!.genre_title.text = group!!.title
    }

   /* override fun getItemViewType(position: Int): Int {
        if(isPositionHeader(position)){
            return TYPE_HEADER
        }else{
            return TYPE_ITEM
        }
        return 0
    }*/

 /*   fun isPositionHeader(position: Int):Boolean{
        return position == TYPE_HEADER
    }
    fun isPositionItem(position: Int): Boolean{
        return position == TYPE_ITEM
    }
*/

}



