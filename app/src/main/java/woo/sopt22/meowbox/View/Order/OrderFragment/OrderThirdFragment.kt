package woo.sopt22.meowbox.View.Order.OrderFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.order_third_fragment.*
import kotlinx.android.synthetic.main.order_third_fragment.view.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Order.OrderFirstActivity

class OrderThirdFragment : Fragment(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!){
            order_period_previous->{
                (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderSecondFragment())
            }
            order_period_next->{
                (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderFourFragment())
            }
            order_third_1_layout->{
                if(!order_third_1_layout.isSelected){
                    order_third_1_layout.isSelected = true
                }
            }
            order_third_3->{
                if(!order_third_3.isSelected){
                    order_third_3.isSelected = true
                } else{
                    order_third_3.isSelected = false
                }
            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_third_fragment, container, false)

        //order_third_1_layout.isSelected = true

        view.order_period_previous.setOnClickListener(this)
        view.order_period_next.setOnClickListener(this)
        view.order_third_1_layout.setOnClickListener(this)
        view.order_third_3.setOnClickListener(this)
        return view
    }
}