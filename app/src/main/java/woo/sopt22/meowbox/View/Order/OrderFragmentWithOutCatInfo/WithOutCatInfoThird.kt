package woo.sopt22.meowbox.View.Order.OrderFragmentWithOutCatInfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.order_first_fragment.*
import kotlinx.android.synthetic.main.order_third_fragment.*
import kotlinx.android.synthetic.main.order_third_fragment.view.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
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
                activity!!.finish()
            }
            order_period_next->{
                val without_four_fragment = WithOutCatInfoFour()
                val bundle = Bundle(3) // 파라미터는 전달할 데이터 개수
                bundle.putString("cat_name", SharedPreference.instance!!.getPrefStringData("cat_name")) // key , value
                bundle.putString("box_type",box_type)
                bundle.putString("price",price)
                without_four_fragment.setArguments(bundle)
                SharedPreference.instance!!.setPrefData("price",price)
                SharedPreference.instance!!.setPrefData("box_type",box_type)
                ToastMaker.makeLongToast(context, third_radio_button.text.toString())
                (OrderThirdActivity.thirdContext as OrderThirdActivity).replaceFragment(WithOutCatInfoFour())
            }
            order_third_1_layout->{
                if(!order_third_1_layout.isSelected){
                    order_third_1_layout.isSelected = true
                    order_third_pay_method.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
                        override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                            when(checkedId){
                                R.id.order_third_1->{
                                    ToastMaker.makeLongToast(context, order_third_1.text.toString())
                                    box_type = "1"
                                    price = "39,900원"
                                }
                                R.id.order_third_2->{
                                    ToastMaker.makeLongToast(context, order_third_2.text.toString())
                                    box_type = "2"
                                    price = "39,900원"
                                }
                            }
                        }

                    })

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
                    box_type = "3"
                    price = "37,700원"
                    ToastMaker.makeLongToast(context, box_type)

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
                    box_type = "6"
                    price = "35,000원"
                    ToastMaker.makeLongToast(context, box_type)

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
                    box_type = "7"
                    price = "60,000원"
                    ToastMaker.makeLongToast(context, box_type)

                    order_third_1_layout.isSelected = false
                    order_third_6.isSelected = false
                    order_third_3.isSelected = false
                } else{
                    order_third_7.isSelected = false
                }

            }

        }
    }
    lateinit var box_type : String
    var third_radio_id : Int=0
    lateinit var price : String
    lateinit var third_radio_button : RadioButton
    //lateinit var third_radig_group : RadioGroup
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_third_fragment, container, false)


        third_radio_id = view.order_third_pay_method.checkedRadioButtonId
        third_radio_button = view.findViewById<View>(third_radio_id!!) as RadioButton


        view.order_period_previous.setOnClickListener(this)
        view.order_period_next.setOnClickListener(this)
        view.order_third_1_layout.setOnClickListener(this)
        view.third_back_btn.text = "취소"
        view.order_third_3.setOnClickListener(this)
        view.order_third_6.setOnClickListener(this)
        view.order_third_7.setOnClickListener(this)
        view.order_third_1.setOnClickListener(this)
        view.order_third_2.setOnClickListener(this)
        view.order_third_1_layout.isSelected = true
        return view
    }
}