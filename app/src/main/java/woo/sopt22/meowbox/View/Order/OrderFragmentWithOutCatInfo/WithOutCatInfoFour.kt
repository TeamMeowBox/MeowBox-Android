package woo.sopt22.meowbox.View.Order.OrderFragmentWithOutCatInfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import kotlinx.android.synthetic.main.order_four_fragment.*
import kotlinx.android.synthetic.main.order_four_fragment.view.*
import kotlinx.android.synthetic.main.order_third_fragment.*
import kotlinx.android.synthetic.main.order_third_fragment.view.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Order.OrderFirstActivity
import woo.sopt22.meowbox.View.Order.OrderFragment.OrderFiveFragment
import woo.sopt22.meowbox.View.Order.OrderFragment.OrderFourFragment
import woo.sopt22.meowbox.View.Order.OrderFragment.OrderSecondFragment
import woo.sopt22.meowbox.View.Order.OrderFragment.OrderThirdFragment
import woo.sopt22.meowbox.View.Order.OrderThirdActivity

class WithOutCatInfoFour : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            order_pay_previous->{
                (OrderThirdActivity.thirdContext as OrderThirdActivity).replaceFragment(WithOutCatInfoThird())
            }
            order_pay_next->{
                ToastMaker.makeLongToast(context, radio_button.text.trim().toString())
                (OrderThirdActivity.thirdContext as OrderThirdActivity).replaceFragment(WithOutCatInfoFive())
            }
        }
    }

    var radio_id : Int=0
    lateinit var radio_button : RadioButton
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_four_fragment, container, false)

        view.order_pay_previous.setOnClickListener(this)
        view.order_pay_next.setOnClickListener(this)

        radio_id = view.order_four_pay_method.checkedRadioButtonId


        radio_button = view.findViewById<View>(radio_id!!) as RadioButton
        return view
    }
}