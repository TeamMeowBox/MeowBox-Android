package woo.sopt22.meowbox.View.Order.OrderFragmentWithOutCatInfo

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.order_five_fragment.*
import kotlinx.android.synthetic.main.order_five_fragment.view.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Home.MainActivity

class WithOutCatInfoFive : Fragment(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!){
            order_five_home_btn->{
                var intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_five_fragment, container, false)

        view.order_five_home_btn.setOnClickListener(this)

        return view
    }
}