package woo.sopt22.meowbox.View.MyPage.OrderHistory.OrderHistoryDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_order_history_detail.*
import woo.sopt22.meowbox.R

class OrderHistoryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history_detail)

        getDate()
    }

    fun getDate(){
        val product_name = getIntent()
        text_tv.text = product_name.getStringExtra("product")
    }

}
