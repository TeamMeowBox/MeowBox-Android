package woo.sopt22.meowbox.View.Order.OrderFragmentWithCatInfo

import android.app.Activity.RESULT_OK
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
import woo.sopt22.meowbox.Model.Order.*
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Order.Credit.CreditActivity
import woo.sopt22.meowbox.View.Order.OrderThirdActivity

class WithCatInfoFour : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            order_pay_previous->{

                (OrderThirdActivity.thirdContext as OrderThirdActivity).replaceFragment(WithCatInfoThird())
            }
            order_pay_next->{
                //oastMaker.makeLongToast(context, radio_button.text.trim().toString())
                Log.v("48",radio_button.text.toString())
                if(order_four_name.text.toString().length == 0 ||
                        order_four_address_one.text.toString().length == 0 ||
                        order_four_address_two.text.toString().length == 0 ||
                        order_four_email.text.toString().length == 0){
                    ToastMaker.makeShortToast(context, "필수 정보를 입력해주세요!")
                } else{
                    postOrder()
                }

                //postOrder()



            }
        }
    }

    var radio_id : Int=0
    lateinit var radio_button : RadioButton
    lateinit var cat_name : String
    lateinit var box_type : String
    lateinit var price : String
    lateinit var orderData: OrderData
    lateinit var orderChecking : OrderResult

    lateinit var orderTest : OrderTest

    lateinit var networkService: NetworkService
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_four_fragment, container, false)

        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(context!!)
        activity!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        view.order_pay_previous.setOnClickListener(this)
        view.order_pay_next.setOnClickListener(this)
        view.order_four_total_charge.text  = SharedPreference.instance!!.getPrefStringData("price")
        view.address_rg.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when(checkedId){
                    R.id.before_address->{
                        getBeforeAddress()
                    }
                    R.id.new_address->{
                        order_four_name.setText(" ")
                        order_four_address_one.setText(" ")
                        order_four_phone_number.setText(" ")
                        order_four_email.setText(" ")
                    }
                }
            }

        })

        price = SharedPreference.instance!!.getPrefStringData("price")!!
        box_type = SharedPreference.instance!!.getPrefStringData("box_type")!!
        Log.v("33 box_type",box_type)
        Log.v("33 price",price)
        radio_id = view.order_four_pay_method.checkedRadioButtonId
        radio_button = view.findViewById<View>(radio_id!!) as RadioButton
        return view
    }

    override fun onResume() {
        super.onResume()

    }


//    fun postORrderResult(){
//        orderChecking = OrderResult(SharedPreference.instance!!.getPrefStringData("merchant")!!)
//        Log.d("boolean", "어디까지 들어가냥")
//        val orderCheck = networkService.postOrderResult(SharedPreference.instance!!.getPrefStringData("token")!!,
//                orderChecking)
//        Log.d("boolean", "어디까지 들어가냥")
//        orderCheck.enqueue(object  : Callback<OrderResultResponse>{
//            override fun onFailure(call: Call<OrderResultResponse>?, t: Throwable?) {
//                Log.d("boolean", "어디까지 들어가냥")
//
//            }
//
//            override fun onResponse(call: Call<OrderResultResponse>?, response: Response<OrderResultResponse>?) {
//                if(response!!.isSuccessful){
//                    var orderCheckBoolean = response!!.body()!!.result.order_result
//                    Log.d("boolean", orderCheckBoolean.toString())
//                    if(orderCheckBoolean == true) {
//                        (OrderThirdActivity.thirdContext as OrderThirdActivity).replaceFragment(WithCatInfoFive())
//                    }
//                    else {
//                        Log.d("boolean", "어디까지 들어가냥")
//                        (OrderThirdActivity.thirdContext as OrderThirdActivity).replaceFragment(WithCatInfoThird())
//                    }
//                }
//            }
//
//        })
//
//    }


    fun postOrder(){
        if(this.arguments != null) {
            var bundle: Bundle = arguments!!
            cat_name = bundle.getString("cat_name")
            box_type = bundle.getString("box_type")
            price = bundle.getString("price")

        }
        orderData = OrderData(SharedPreference.instance!!.getPrefStringData("cat_name")!!
                , order_four_address_one.text.toString()+order_four_address_two.text.toString()
                , order_four_phone_number.text.toString(), box_type,
                price, order_four_email.text.toString(),radio_button.text.toString())
        val orderRespone = networkService.postOrder(SharedPreference.instance!!.getPrefStringData("token")!!,orderData)
        orderRespone.enqueue(object : Callback<OrderResponse>{
            override fun onFailure(call: Call<OrderResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<OrderResponse>?, response: Response<OrderResponse>?) {
                if(response!!.isSuccessful){
                    Log.v("412",response!!.message())
                    var orderIdx = response!!.body()!!.result.order_idx.toString()
                    SharedPreference.instance!!.setPrefData("merchant", orderIdx)
                    Log.d("ordererr",orderIdx)

                    var priceTmp : Int
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
                    intent.putExtra("orderIdx",orderJson)
                    startActivityForResult(intent, 1541);



                }
            }

        })
    }
    fun getBeforeAddress(){
        Log.v("310","어디까지 들어옴?")
        val addressResponse = networkService.getBeforeAddress(SharedPreference.instance!!.getPrefStringData("token")!!)
        addressResponse.enqueue(object : Callback<BeforeAddressResponse>{
            override fun onFailure(call: Call<BeforeAddressResponse>?, t: Throwable?) {
                Log.v("311",t!!.message)

            }

            override fun onResponse(call: Call<BeforeAddressResponse>?, response: Response<BeforeAddressResponse>?) {
                if(response!!.isSuccessful){
                    Log.v("312",response!!.message())
                    if(response!!.body()!!.result.order_idx == "-1"){
                        ToastMaker.makeLongToast(context, "이전 배송지가 없습니다.")
                    } else{
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
        if(resultCode == RESULT_OK){
            Log.d("체크5", "어디까지 들어가냥2")
            when(requestCode){
                1541 ->{
                    Log.d("체크3",data!!.getStringExtra("result"))
                    if(data!!.getStringExtra("result").equals("true")) {
                        (OrderThirdActivity.thirdContext as OrderThirdActivity).replaceFragment(WithCatInfoFive())
                    }
                    else {
                        Log.d("체크2", "어디까지 들어가냥")
                        (OrderThirdActivity.thirdContext as OrderThirdActivity).replaceFragment(WithCatInfoThird())
                    }

                }

            }
        }
    }



}