package woo.sopt22.meowbox.View.MyPage.OrderHistory.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import woo.sopt22.meowbox.Model.Order.OrderHistory.ticketData
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.MyPage.OrderHistory.OrderViewHolder

class OrderHistoryItemAdapter(var order_history_items : ArrayList<ticketData>, var context : Context) : RecyclerView.Adapter<OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.order_history_item,parent,false)
        view.setOnClickListener(onItemClick)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return order_history_items.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.product_name_item.text = order_history_items[position].product
        holder.product_date_item.text = order_history_items[position].term
    }

    private lateinit var onItemClick: View.OnClickListener

    fun setOnItemClickListener(l : View.OnClickListener){
        onItemClick = l
    }


}