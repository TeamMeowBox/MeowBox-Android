package woo.sopt22.meowbox.View.MyPage.OrderHistory

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import woo.sopt22.meowbox.R

class OrderViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {
    var product_name_item : TextView = itemView.findViewById(R.id.order_payment_name)
    var product_date_item : TextView = itemView.findViewById(R.id.order_payment_date)
    //var product_ticket_type : TextView = itemView.findViewById(R.id.order_ticket_type)
}