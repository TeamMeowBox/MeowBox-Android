package woo.sopt22.meowbox.View.MyPage.FAQ.Adapter

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.MyPage.FAQ.ViewHolder.ContentViewHolder
import woo.sopt22.meowbox.View.MyPage.FAQ.ViewHolder.GenreViewHolder
import woo.sopt22.meowbox.View.MyPage.FAQ.models.Content




class GenreAdapter(groups: List<ExpandableGroup<*>>) : ExpandableRecyclerViewAdapter<GenreViewHolder, ContentViewHolder>(groups) {
    override fun onCreateGroupViewHolder(parent: ViewGroup?, viewType: Int): GenreViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup?, viewType: Int): ContentViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_content, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindChildViewHolder(holder: ContentViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?, childIndex: Int) {
        val content = group!!.getItems()[childIndex] as Content
        holder!!.content_name.text = content.content_name
    }

    override fun onBindGroupViewHolder(holder: GenreViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?) {
        holder!!.genre_title.text = group!!.title
    }
}
