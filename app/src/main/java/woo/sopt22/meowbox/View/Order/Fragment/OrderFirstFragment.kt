package woo.sopt22.meowbox.View.Order.Fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.order_first_fragment.*
import kotlinx.android.synthetic.main.order_first_fragment.view.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.Order.LoginCustomDialog
import woo.sopt22.meowbox.View.Order.OrderFirstActivity
import android.graphics.drawable.ColorDrawable




class OrderFirstFragment : Fragment(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!){
            order_name_next_btn->{
                // OrderFirstActivity의 함수 이용하기
               // (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderSecondFragment())

                var dialog = LoginCustomDialog(OrderFirstActivity.mContext as OrderFirstActivity)
                dialog.getWindow ().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()
            }
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_first_fragment, container, false)


        view.order_name_next_btn.setOnClickListener(this)
        return view
    }
}