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
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_third_fragment, container, false)

        view.order_period_previous.setOnClickListener(this)
        view.order_period_next.setOnClickListener(this)
        return view
    }
}