package woo.sopt22.meowbox.View.MyPage.OrderHistory

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_order_history.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Order.OrderHistory
import woo.sopt22.meowbox.Model.Order.OrderHistoryDate
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.View.MyPage.OrderHistory.Adapter.OrderHistoryAdapter
import woo.sopt22.meowbox.View.MyPage.OrderHistory.OrderHistoryDetail.OrderHistoryDetailActivity

class OrderHistoryActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            order_history_x_btn->{
                finish()
            }
            v!!->{
                val index : Int = order_history_rv.getChildAdapterPosition(v!!)
                //var product = order_history_items[index].product

                val intent = Intent(this, OrderHistoryDetailActivity::class.java)
                //intent.putExtra("product",product)
                startActivity(intent)
            }
        }
    }


    lateinit var order_history_items : OrderHistoryDate
    //lateinit var order_header_history_items : ArrayList<>
    lateinit var networkService: NetworkService
    lateinit var orderHistoryAdapter : OrderHistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        order_history_x_btn.setOnClickListener(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(this)


        //order_history_items

        getOrderHistroy()

        /*
        order_history_items.add(OrderHistory(1, "헤더", "2018.04.26 - 2018.06.26", 0))
        order_history_items.add(OrderHistory(2, "6개월 정기권", "2018.08.26 - 2018.01.26", 1))
        order_history_items.add(OrderHistory(3, "생일박", "2018.06.26", 2))
        order_history_items.add(OrderHistory(4, "3개월 정기권", "2018.04.26 - 2018.06.26", 3))
        order_history_items.add(OrderHistory(5, "6개월 정기권", "2018.08.26 - 2018.01.26", 4))
        order_history_items.add(OrderHistory(6, "생일박", "2018.06.26", 5))
        order_history_items.add(OrderHistory(7, "3개월 정기권", "2018.04.26 - 2018.06.26", 6))
        order_history_items.add(OrderHistory(8, "6개월 정기권", "2018.08.26 - 2018.01.26", 7))
        order_history_items.add(OrderHistory(9, "생일박", "2018.06.26", 1))*/

/*
        orderHistoryAdapter = OrderHistoryAdapter(order_history_items, context = this@OrderHistoryActivity)
        orderHistoryAdapter.setOnItemClickListener(this)
        order_history_rv.layoutManager = LinearLayoutManager(this@OrderHistoryActivity)
        order_history_rv.adapter = orderHistoryAdapter*/

    }

    fun getOrderHistroy(){
        val orderHistoryResponse = networkService.getOrderHistory(SharedPreference.instance!!.getPrefStringData("token")!!,
                SharedPreference.instance!!.getPrefStringData("user_idx")!!)
        orderHistoryResponse.enqueue(object : Callback<OrderHistory>{
            override fun onFailure(call: Call<OrderHistory>?, t: Throwable?) {
                Log.v("order",t!!.message)

            }

            override fun onResponse(call: Call<OrderHistory>?, response: Response<OrderHistory>?) {
                if(response!!.isSuccessful){
                    order_history_items = response!!.body()!!.result
                    SharedPreference.instance!!.setPrefData("idx", response!!.body()!!.result.ticketed)
                    Log.v("11",order_history_items.ticket.toString())
                    orderHistoryAdapter = OrderHistoryAdapter(order_history_items, context = this@OrderHistoryActivity)
                    orderHistoryAdapter.setOnItemClickListener(this@OrderHistoryActivity)
                    order_history_rv.layoutManager = LinearLayoutManager(this@OrderHistoryActivity)
                    order_history_rv.adapter = orderHistoryAdapter
                }
            }

        })

    }
}
