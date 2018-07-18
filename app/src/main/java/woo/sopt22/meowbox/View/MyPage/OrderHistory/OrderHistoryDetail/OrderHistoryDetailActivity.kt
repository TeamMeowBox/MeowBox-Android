package woo.sopt22.meowbox.View.MyPage.OrderHistory.OrderHistoryDetail

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_order_history_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Order.OrderHistoryDetail.OrderHistoryDetail
import woo.sopt22.meowbox.Model.OrderObject
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.View.MyPage.OrderHistory.Adapter.HistoryDetailAdapter
import java.util.*

class OrderHistoryDetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            order_history_detail_x_btn->{
                finish()
            }
        }

    }

    lateinit var networkService: NetworkService
    val product_name = getIntent()
    val order_idx = getIntent()
    val term = getIntent()
    lateinit var orderObject: OrderObject
    lateinit var historyDetailAdapter: HistoryDetailAdapter
    lateinit var requestManager : RequestManager

    fun init(){
        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history_detail)

        requestManager = Glide.with(this)


        order_history_detail_x_btn.setOnClickListener(this)
        item_detail_name_tv.text = getIntent().getStringExtra("product")
        item_detail_term_tv.text = getIntent().getStringExtra("term")
        Log.v("기용1",getIntent().getStringExtra("product"))
        Log.v("기용2",getIntent().getStringExtra("term"))
        Log.v("기용3",getIntent().getIntExtra("order_idx",0).toString())

        postOrderHistoryDetailItem()
    }


    // 주문 상세 내역 - 통신
    fun postOrderHistoryDetailItem(){
        orderObject = OrderObject(getIntent().getIntExtra("order_idx",0).toString())
        val orderHistoryDetailItemResponse = networkService
                .postOrderDetail(SharedPreference.instance!!.getPrefStringData("token")!!,orderObject)
        orderHistoryDetailItemResponse.enqueue(object : Callback<OrderHistoryDetail>{
            override fun onFailure(call: Call<OrderHistoryDetail>?, t: Throwable?) {
                Log.v("상세내역 실패",t!!.message)
            }

            override fun onResponse(call: Call<OrderHistoryDetail>?, response: Response<OrderHistoryDetail>?) {
                if(response!!.isSuccessful){
                    // 받은 이미지 순서 반대로 돌림
                    Collections.reverse(response!!.body()!!.result)
                    historyDetailAdapter = HistoryDetailAdapter(response!!.body()!!.result,requestManager)
                    order_history_detail_rv.layoutManager = LinearLayoutManager(this@OrderHistoryDetailActivity)
                    order_history_detail_rv.adapter = historyDetailAdapter

                }
            }

        })
    }

}
