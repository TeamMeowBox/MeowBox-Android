package woo.sopt22.meowbox.View.Order.OrderFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.order_first_fragment.*
import kotlinx.android.synthetic.main.order_second_fragment.*
import kotlinx.android.synthetic.main.order_third_fragment.*
import kotlinx.android.synthetic.main.order_third_fragment.view.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Order.OrderFirstActivity

class OrderThirdFragment : Fragment(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!){
            order_period_previous->{
                (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderSecondFragment())
            }
            order_period_next->{
                SharedPreference.instance!!.setPrefData("price",price)
                SharedPreference.instance!!.setPrefData("box_type",box_type)
                (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderFourFragment())
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
    lateinit var third_radio_button : RadioButton
    lateinit var price : String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_third_fragment, container, false)
        activity!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        box_type = "1"
        order_third_pay_method.id =  R.id.order_third_1

        //order_third_1_layout.isSelected = true

        third_radio_id = view.order_third_pay_method.checkedRadioButtonId
        third_radio_button = view.findViewById<View>(third_radio_id!!) as RadioButton
        view.order_period_previous.setOnClickListener(this)
        view.order_period_next.setOnClickListener(this)
        view.order_third_1_layout.setOnClickListener(this)
        view.order_third_3.setOnClickListener(this)
        view.order_third_6.setOnClickListener(this)
        view.order_third_7.setOnClickListener(this)
        view.order_third_1.setOnClickListener(this)
        view.order_third_2.setOnClickListener(this)
        view.order_third_1_layout.isSelected = true
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}