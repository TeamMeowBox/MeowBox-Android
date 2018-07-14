package woo.sopt22.meowbox.View.Order.OrderFragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import kotlinx.android.synthetic.main.order_first_fragment.*
import kotlinx.android.synthetic.main.order_first_fragment.view.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.CustomDialog.CatNameCheckCustomDialog
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Order.OrderFirstActivity


class OrderFirstFragment : Fragment(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!){
            order_name_next_btn->{
                // OrderFirstActivity의 함수 이용하기
                if(order_cat_name.text.toString().length == 0){
                    var dialog = CatNameCheckCustomDialog(OrderFirstActivity.mContext as OrderFirstActivity)
                    dialog.getWindow ().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dialog.setCanceledOnTouchOutside(false)
                    dialog.show()
                    //ToastMaker.makeLongToast(context, "고양이 이름을 입력해주세요.")

                } else{
                    val second_fragment = OrderSecondFragment() // Fragment 생성
                    val bundle = Bundle(1) // 파라미터는 전달할 데이터 개수
                    bundle.putString("cat_name", order_cat_name.text.toString()) // key , value
                    second_fragment.setArguments(bundle)
                    (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(second_fragment)

                }

            }
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_first_fragment, container, false)

        activity!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        view.order_name_next_btn.setOnClickListener(this)
        return view
    }
}