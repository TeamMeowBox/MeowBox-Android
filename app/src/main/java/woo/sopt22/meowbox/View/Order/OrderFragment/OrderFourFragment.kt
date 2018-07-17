package woo.sopt22.meowbox.View.Order.OrderFragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.order_four_fragment.*
import kotlinx.android.synthetic.main.order_four_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Address.BeforeAddressResponse
import woo.sopt22.meowbox.Model.Order.OrderData
import woo.sopt22.meowbox.Model.Order.OrderResponse
import woo.sopt22.meowbox.Model.Order.OrderTest
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Order.Credit.CreditActivity
import woo.sopt22.meowbox.View.Order.OrderFirstActivity
import woo.sopt22.meowbox.View.Order.OrderFragmentWithCatInfo.WithCatInfoFive
import woo.sopt22.meowbox.View.Order.OrderFragmentWithCatInfo.WithCatInfoThird
import woo.sopt22.meowbox.View.Order.OrderThirdActivity

class OrderFourFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {
            order_pay_previous -> {
                (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderThirdFragment())
            }
            order_pay_next -> {
                //ToastMaker.makeLongToast(context, radio_button.text.trim().toString())
                Log.v("48", radio_button.text.toString())
                if(order_four_name.text.toString().length == 0 ||
                        order_four_address_one.text.toString().length == 0 ||
                        order_four_address_two.text.toString().length == 0 ||
                        order_four_email.text.toString().length == 0){
                    ToastMaker.makeShortToast(context, "필수 정보를 입력해주세요!")
                } else{
                    postOrder()
                }
            }
        }
    }

    var radio_id: Int = 0
    lateinit var radio_button: RadioButton
    lateinit var box_type: String
    lateinit var orderData: OrderData
    lateinit var networkService: NetworkService
    lateinit var price: String
    lateinit var payment_method: String
    lateinit var cat_name: String
    lateinit var orderTest: OrderTest

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_four_fragment, container, false)
        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(context!!)
        payment_method = "1"

        activity!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        view.order_pay_previous.setOnClickListener(this)
        view.order_pay_next.setOnClickListener(this)
        view.address_rg.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.before_address -> {
                        getBeforeAddress()
                    }
                    R.id.new_address -> {
                        //ToastMaker.makeLongToast(context, new_address.text.toString())
                    }
                }
            }

        })

        view.order_four_pay_method.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.order_four_credit_pay -> {
                        payment_method = "1"
                    }
                    R.id.order_four_phone_pay -> {
                        payment_method = "2"
                    }
                }
            }

        })
        price = SharedPreference.instance!!.getPrefStringData("price")!!
        view.order_four_total_charge.text = price
        box_type = SharedPreference.instance!!.getPrefStringData("box_type")!!

        radio_id = view.order_four_pay_method.checkedRadioButtonId

        radio_button = view.findViewById<View>(radio_id!!) as RadioButton
        Log.v("56", radio_button.text.toString())
        Log.v("56", radio_id.toString())
        radio_button.setOnClickListener(this)
        return view
    }

/*    fun postOrder(){
        orderData = OrderData(order_four_name.text.toString(), order_four_address_one.text.toString()+order_four_address_two.text.toString(), order_four_phone_number.text.toString(), box_type,
                price, order_four_email.text.toString(),payment_method)
        val orderRespone = networkService.postOrder(SharedPreference.instance!!.getPrefStringData("token")!!,orderData)
        orderRespone.enqueue(object : Callback<OrderResponse> {
            override fun onFailure(call: Call<OrderResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<OrderResponse>?, response: Response<OrderResponse>?) {
                if(response!!.isSuccessful){
                    Log.v("412",response!!.message())
                    var orderIdx = response!!.body()!!.result.order_idx.toString()
                    Log.d("ordererr",orderIdx)

                    val intent = Intent(activity, CreditActivity::class.java)
                    intent.putExtra("orderIdx",orderIdx)
                    startActivity(intent);

                    (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderFiveFragment())
                }
            }

        })
    }*/

    fun postOrder() {
        if (this.arguments != null) {
            var bundle: Bundle = arguments!!
            cat_name = bundle.getString("cat_name")
            box_type = bundle.getString("box_type")
            price = bundle.getString("price")

        }
        orderData = OrderData(order_four_name.text.toString()
                , order_four_address_one.text.toString() + order_four_address_two.text.toString()
                , order_four_phone_number.text.toString(), box_type,
                price, order_four_email.text.toString(), radio_button.text.toString())
        val orderRespone = networkService.postOrder(SharedPreference.instance!!.getPrefStringData("token")!!, orderData)
        orderRespone.enqueue(object : Callback<OrderResponse> {
            override fun onFailure(call: Call<OrderResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<OrderResponse>?, response: Response<OrderResponse>?) {
                if (response!!.isSuccessful) {
                    Log.v("412", response!!.message())
                    var orderIdx = response!!.body()!!.result.order_idx.toString()
                    SharedPreference.instance!!.setPrefData("merchant", orderIdx)
                    Log.d("ordererr", orderIdx)

                    var priceTmp: Int
                    var re = Regex("[^0-9]")
                    priceTmp = re.replace(price, "").toInt()

                    if(box_type.equals("7")){

                        orderTest = OrderTest(orderIdx, "생일 축하해! 박스", priceTmp/100)

                    }
                    else{

                        orderTest = OrderTest(orderIdx, box_type+"개월 정기배송", priceTmp/100)
                    }

                    //orderTest = OrderTest(orderIdx, box_type+"개월 정기배송", priceTmp/100)
                    var gson = Gson()
                    var orderJson = gson.toJson(orderTest)
                    gson.toJson(orderTest)

                    val intent = Intent(activity, CreditActivity::class.java)
                    intent.putExtra("orderIdx", orderJson)
                    startActivityForResult(intent, 1541);


                }
            }

        })
    }

    fun getBeforeAddress() {
        Log.v("210", "어디까지 들어옴?")
        val addressResponse = networkService.getBeforeAddress(SharedPreference.instance!!.getPrefStringData("token")!!)
        addressResponse.enqueue(object : Callback<BeforeAddressResponse> {
            override fun onFailure(call: Call<BeforeAddressResponse>?, t: Throwable?) {
                Log.v("211", t!!.message)

            }

            override fun onResponse(call: Call<BeforeAddressResponse>?, response: Response<BeforeAddressResponse>?) {
                if (response!!.isSuccessful) {
                    Log.v("212", response!!.message())
                    if (response!!.body()!!.result.order_idx == "-1") {
                        ToastMaker.makeLongToast(context, "이전 배송지가 없습니다.")
                    } else {
                        order_four_name.setText(response!!.body()!!.result!!.name)
                        order_four_address_one.setText(response!!.body()!!.result!!.address)
                        order_four_phone_number.setText(response!!.body()!!.result!!.phone_number)
                        order_four_email.setText(response!!.body()!!.result!!.email)
                    }
                }

            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            Log.d("체크5", "어디까지 들어가냥2")
            when (requestCode) {
                1541 -> {
                    Log.d("체크3", data!!.getStringExtra("result"))
                    if (data!!.getStringExtra("result").equals("true")) {
                        (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderFiveFragment())
                    } else {
                        Log.d("체크2", "어디까지 들어가냥")
                        (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderThirdFragment())
                    }

                }

            }
        }
    }
}