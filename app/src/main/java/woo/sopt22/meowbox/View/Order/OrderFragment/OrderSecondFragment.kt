package woo.sopt22.meowbox.View.Order.OrderFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.order_second_fragment.*
import kotlinx.android.synthetic.main.order_second_fragment.view.*
import kotlinx.android.synthetic.main.sliding_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Model.RegisterCat.CatInformation
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Order.OrderFirstActivity


class OrderSecondFragment : Fragment(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!){
            order_etc_previous->{
                (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderFirstFragment())
            }
            order_etc_next->{
                // 고양이 등록 통신 진행
                registerCatInfo()
                //ToastMaker.makeLongToast(context, order_etc_cat_name.text.toString()+"고양이와"+year+month+day+"생일 정보와"+cat_type.toString()+"과"+cat_about_info.text.toString())
            }
            order_small_cat_image->{
                if(!order_small_cat_image.isSelected){
                    cat_type = 0
                    order_small_cat_image.isSelected = true
                    order_normal_cat_image.isClickable = false
                    order_large_cat_image.isClickable = false
                } else{
                    order_small_cat_image.isSelected = false
                    order_normal_cat_image.isClickable = true
                    order_large_cat_image.isClickable = true
                }

            }
            order_normal_cat_image->{
                if(!order_normal_cat_image.isSelected){
                    cat_type = 1
                    order_normal_cat_image.isSelected = true
                    order_small_cat_image.isClickable = false
                    order_large_cat_image.isClickable = false
                } else{
                    order_normal_cat_image.isSelected = false
                    order_small_cat_image.isClickable = true
                    order_large_cat_image.isClickable = true
                }

            }
            order_large_cat_image->{
                if(!order_large_cat_image.isSelected){
                    cat_type = 2
                    order_large_cat_image.isSelected = true
                    order_small_cat_image.isClickable = false
                    order_normal_cat_image.isClickable = false
                } else{
                    order_large_cat_image.isSelected = false
                    order_small_cat_image.isClickable = true
                    order_normal_cat_image.isClickable = true
                }

            }



        }
    }

    lateinit var year : String
    lateinit var month : String
    lateinit var day : String
    var cat_type : Int = 0

    // 통신
    lateinit var networkService: NetworkService
    lateinit var catInformation: CatInformation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_second_fragment, container, false)

        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(context!!)


        view.order_etc_previous.setOnClickListener(this)
        view.order_etc_next.setOnClickListener(this)
        view.order_small_cat_image.setOnClickListener(this)
        view.order_normal_cat_image.setOnClickListener(this)
        view.order_large_cat_image.setOnClickListener(this)

        val userId = arguments!!.getString("userId") // 전달한 key 값



        view.order_second_year.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                year = parent!!.getItemAtPosition(position) as String
                Toast.makeText(context, year, Toast.LENGTH_SHORT).show()
            }

        }

        // 월 스피너
        view.order_second_month.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                month = parent!!.getItemAtPosition(position) as String
                Toast.makeText(context, month, Toast.LENGTH_SHORT).show()
            }

        }

        view.order_second_day.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                day = parent!!.getItemAtPosition(position) as String
                Toast.makeText(context, day, Toast.LENGTH_SHORT).show()

            }

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if(this.arguments != null) {
            var bundle: Bundle = arguments!!
            order_etc_cat_name.text = bundle.getString("cat_name")
        }

    }

    fun registerCatInfo(){
        catInformation = CatInformation(order_etc_cat_name.text.toString(), cat_type,
                year+month+day,cat_about_info.text.toString())
        val registerResponse = networkService.registerCat(SharedPreference.instance!!.getPrefStringData("token")!!,catInformation)
        registerResponse.enqueue(object : Callback<BaseModel>{
            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {
                Log.v("0210",t!!.message)
            }

            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if(response!!.isSuccessful){
                    Log.v("0210",response!!.message())
                    (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderThirdFragment())
                }

            }

        })
    }


}