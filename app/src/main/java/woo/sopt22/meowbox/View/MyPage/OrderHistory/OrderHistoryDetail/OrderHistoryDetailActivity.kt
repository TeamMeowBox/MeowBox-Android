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
import woo.sopt22.meowbox.View.MeowBoxDetail.DetailAdapter
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history_detail)

        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(this)
        requestManager = Glide.with(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        order_history_detail_x_btn.setOnClickListener(this)
        item_detail_name_tv.text = getIntent().getStringExtra("product")
        item_detail_term_tv.text = getIntent().getStringExtra("term")
        Log.v("기용1",getIntent().getStringExtra("product"))
        Log.v("기용2",getIntent().getStringExtra("term"))
        Log.v("기용3",getIntent().getIntExtra("order_idx",0).toString())

       // getDate()
        postOrderHistoryDetailItem()
    }

    fun getDate(){

        item_detail_name_tv.text = getIntent().getStringExtra("product")
        //detail_text_tv2.text = order_idx.getIntExtra("order_idx",0).toString()
        item_detail_term_tv.text = getIntent().getStringExtra("term")

    }

    fun postOrderHistoryDetailItem(){
        orderObject = OrderObject(getIntent().getIntExtra("order_idx",0).toString())
        Log.v("기용7","겨이니ㅣㄴ")
        val orderHistoryDetailItemResponse = networkService
                .postOrderDetail(SharedPreference.instance!!.getPrefStringData("token")!!,orderObject)
        Log.v("기용9",getIntent().getIntExtra("order_idx",0).toString())
        Log.v("기용8","겨이니ㅣㄴ")
        orderHistoryDetailItemResponse.enqueue(object : Callback<OrderHistoryDetail>{
            override fun onFailure(call: Call<OrderHistoryDetail>?, t: Throwable?) {
                Log.v("기용6",t!!.message)
            }

            override fun onResponse(call: Call<OrderHistoryDetail>?, response: Response<OrderHistoryDetail>?) {
                if(response!!.isSuccessful){
                    Log.v("기용4",response!!.body()!!.result.size.toString())
                    Collections.reverse(response!!.body()!!.result)
                    historyDetailAdapter = HistoryDetailAdapter(response!!.body()!!.result,requestManager)
                    order_history_detail_rv.layoutManager = LinearLayoutManager(this@OrderHistoryDetailActivity)
                    order_history_detail_rv.adapter = historyDetailAdapter

                } else{
                    Log.v("기용5",response!!.message())
                }
            }

        })
    }

}
