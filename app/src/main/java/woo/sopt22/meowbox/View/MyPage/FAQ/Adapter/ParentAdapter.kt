package woo.sopt22.meowbox.View.MyPage.FAQ.Adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.MyPage.FAQ.ViewHolder.ContentViewHolder
import woo.sopt22.meowbox.View.MyPage.FAQ.ViewHolder.HeaderViewHolder
import woo.sopt22.meowbox.View.MyPage.FAQ.ViewHolder.ParentViewHolder
import woo.sopt22.meowbox.View.MyPage.FAQ.models.Child




class ParentAdapter(groups: List<ExpandableGroup<*>>)
    : ExpandableRecyclerViewAdapter<ParentViewHolder, ContentViewHolder>(groups) {



    private val TYPE_HEADER : Int = 0
    private val TYPE_ITEM : Int = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_HEADER){
            var view = LayoutInflater.from(parent!!.context).inflate(R.layout.header_parent, parent, false)
            return HeaderViewHolder(view)
        } else{
            Log.v("123","123")
            println(groups.size)
            //println(parent)
            onCreateGroupViewHolder(parent, TYPE_ITEM) as GroupViewHolder
            return onCreateGroupViewHolder(parent, TYPE_ITEM) as GroupViewHolder


        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is HeaderViewHolder){
            val itemHolder : HeaderViewHolder = holder
            itemHolder.header_name.text = "제품"
            println("onBindViewHolder"+itemHolder.header_name.text.toString())
        }
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): ParentViewHolder {
            var view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_parent, parent, false)
            return ParentViewHolder(view)

    }

    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): ContentViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_child, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindChildViewHolder(holder: ContentViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?, childIndex: Int) {
        val content = group!!.getItems()[childIndex] as Child
        holder!!.content_name.text = content.content_name
        println("onBindChildViewHolder"+content.content_name)
    }

    override fun onBindGroupViewHolder(holder: ParentViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?) {
        holder!!.genre_title.text = group!!.title
        println("onBindGroupViewHolder"+group!!.title)
    }

    override fun getItemViewType(position: Int): Int {
        if(isPositionHeader(position)){
            return TYPE_HEADER
        }else if(isPositionItem(position)){
            return TYPE_ITEM
        }
        return 0

    }

    override fun getItemCount(): Int {
        return groups.size
        println(groups.size )
    }

    fun isPositionHeader(position: Int):Boolean{
        return position == TYPE_HEADER
    }
    fun isPositionItem(position: Int): Boolean{
        return position == TYPE_ITEM
    }
}



