package woo.sopt22.meowbox.View.MyPage.OrderHistory.OrderHistoryDetail

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_order_history_detail_header.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Order.OrderHistoryDetail.OrderHistoryDetail
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker

class OrderHistoryDetailHeaderActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            order_history_detail_x_btn->{
                finish()
            }
        }
    }

    lateinit var networkService: NetworkService
    lateinit var image_list : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history_detail_header)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(this)
        order_history_detail_x_btn.setOnClickListener(this)

        getData()
        postOrderHistoryDetail()

    }
    fun getData()
    {
        val term = getIntent()
        val name = getIntent()
        header_detail_term_tv.text = term.getStringExtra("term")
        header_detail_name_tv.text = name.getStringExtra("name")

    }

    fun postOrderHistoryDetail(){
        val orderHistoryDetialResponse = networkService
                .postOrderDetail(SharedPreference.instance!!.getPrefStringData("token")!!
                        , SharedPreference.instance!!.getPrefStringData("order_idx")!!.toInt())

        Log.v("한승미","함수 안으로??")
        orderHistoryDetialResponse.enqueue(object : Callback<OrderHistoryDetail>{
            override fun onFailure(call: Call<OrderHistoryDetail>?, t: Throwable?) {
                Log.v("한승미",t!!.message+"실패 함수로")
            }

            override fun onResponse(call: Call<OrderHistoryDetail>?, response: Response<OrderHistoryDetail>?) {
                if(response!!.isSuccessful){
                    Log.v("한승미","성공 함수로??")
                    for(i in 0..response!!.body()!!.result!!.size-1){
                        image_list.add(i, response!!.body()!!.result[i])
                    }
                    Glide.with(this@OrderHistoryDetailHeaderActivity).load(image_list[0]).into(header_detail_image1)
                    Glide.with(this@OrderHistoryDetailHeaderActivity).load(image_list[1]).into(header_detail_image2)
                    Glide.with(this@OrderHistoryDetailHeaderActivity).load(image_list[2]).into(header_detail_image3)

                }
            }

        })
    }
}
