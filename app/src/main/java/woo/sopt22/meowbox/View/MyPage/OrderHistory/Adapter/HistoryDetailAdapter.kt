package woo.sopt22.meowbox.View.MyPage.OrderHistory.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import woo.sopt22.meowbox.R

class HistoryDetailAdapter(var images : ArrayList<String>, var requestManager: RequestManager) : RecyclerView.Adapter<HistoryDetailAdapter.DetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.history_detail_item, parent, false)
        return DetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        requestManager.load(images[position]).into(holder.image)
    }


    class DetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.history_detail_image_item)
    }
}