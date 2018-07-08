package woo.sopt22.meowbox.View.Order.OrderFragmentWithOutCatInfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.order_third_fragment.*
import kotlinx.android.synthetic.main.order_third_fragment.view.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Order.OrderFirstActivity
import woo.sopt22.meowbox.View.Order.OrderFragment.OrderFourFragment
import woo.sopt22.meowbox.View.Order.OrderFragment.OrderSecondFragment
import woo.sopt22.meowbox.View.Order.OrderThirdActivity

class WithOutCatInfoThird : Fragment(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!){
            order_period_previous->{
                //(OrderThirdActivity.thirdContext as OrderThirdActivity).replaceFragment(OrderSecondFragment())
                ToastMaker.makeLongToast(context, "이전으로 돌아가지 못합니다. ")
            }
            order_period_next->{
                (OrderThirdActivity.thirdContext as OrderThirdActivity).replaceFragment(WithOutCatInfoFour())
            }
            order_third_1_layout->{
                if(!order_third_1_layout.isSelected){
                    order_third_1_layout.isSelected = true

                    order_third_3.isSelected = false
                    order_third_6.isSelected = false
                    order_third_7.isSelected = false
                } else{
                    order_third_1_layout.isSelected = false
                }
            }
            order_third_3->{
                if(!order_third_3.isSelected){
                    order_third_3.isSelected = true

                    order_third_1_layout.isSelected = false
                    order_third_6.isSelected = false
                    order_third_7.isSelected = false
                } else{
                    order_third_3.isSelected = false
                }
            }
            order_third_6->{
                if(!order_third_6.isSelected){
                    order_third_6.isSelected = true

                    order_third_1_layout.isSelected = false
                    order_third_3.isSelected = false
                    order_third_7.isSelected = false
                } else{
                    order_third_6.isSelected = false
                }

            }
            order_third_7->{
                if(!order_third_7.isSelected){
                    order_third_7.isSelected = true

                    order_third_1_layout.isSelected = false
                    order_third_6.isSelected = false
                    order_third_3.isSelected = false
                } else{
                    order_third_7.isSelected = false
                }

            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_third_fragment, container, false)


        view.order_period_previous.setOnClickListener(this)
        view.order_period_next.setOnClickListener(this)
        view.order_third_1_layout.setOnClickListener(this)
        view.order_third_3.setOnClickListener(this)
        view.order_third_6.setOnClickListener(this)
        view.order_third_7.setOnClickListener(this)
        view.order_third_1_layout.isSelected = true
        return view
    }
}