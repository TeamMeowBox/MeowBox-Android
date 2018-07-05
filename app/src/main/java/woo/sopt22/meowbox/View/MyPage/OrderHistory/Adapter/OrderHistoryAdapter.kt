package woo.sopt22.meowbox.View.MyPage.OrderHistory.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import woo.sopt22.meowbox.Model.OrderHistory
import woo.sopt22.meowbox.R



class OrderHistoryAdapter(var order_history_items : ArrayList<OrderHistory>, var context : Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val TYPE_HEADER : Int = 0
    private val TYPE_ITEM : Int = 1
    private lateinit var onItemClick: View.OnClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == TYPE_HEADER){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.order_history_header, parent, false)
            return OrderHeaderViewHolder(view)
        } else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.order_history_item, parent, false)
            return OrderHistoryViewHolder(view)
        }
    }

    fun setOnItemClickListener(l : View.OnClickListener){
        onItemClick = l
    }

    override fun getItemCount(): Int {
        return order_history_items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is OrderHistoryViewHolder){

            val itemHolder : OrderHistoryViewHolder = holder
            itemHolder.product_name_item.text = order_history_items[position].product
            itemHolder.product_date_item.text = order_history_items[position].payment_date
            itemHolder.product_ticket_type.text = order_history_items[position].flag.toString()
        } else if(holder is OrderHeaderViewHolder){
            val headerHolder : OrderHeaderViewHolder = holder
            headerHolder.header_name_item.text = order_history_items[position].product
            headerHolder.header_date_item.text = order_history_items[position].payment_date

        }


    }


    class OrderHistoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var product_name_item : TextView = itemView.findViewById(R.id.order_payment_name)
        var product_date_item : TextView = itemView.findViewById(R.id.order_payment_date)
        var product_ticket_type : TextView = itemView.findViewById(R.id.order_ticket_type)
    }

    class OrderHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var header_name_item : TextView = itemView.findViewById(R.id.header_payment_name)
        var header_date_item : TextView = itemView.findViewById(R.id.header_payment_date)
    }


    fun isPositionHeader(position: Int):Boolean{
        return position == TYPE_HEADER
    }
    fun isPositionItem(position: Int): Boolean{
        return position == TYPE_ITEM
    }


    override fun getItemViewType(position: Int): Int {
        if(isPositionHeader(position)){
            return TYPE_HEADER
        }else{
            return TYPE_ITEM
        }
        return 0
    }
}