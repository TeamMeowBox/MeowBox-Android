package woo.sopt22.meowbox.View.MyPage.OrderHistory.OrderHistoryDetail

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_order_history_detail.*
import woo.sopt22.meowbox.R

class OrderHistoryDetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            order_history_detail_x_btn->{
                finish()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history_detail)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        order_history_detail_x_btn.setOnClickListener(this)

        getDate()
    }

    fun getDate(){
        val product_name = getIntent()
        text_tv.text = product_name.getStringExtra("product")
    }

}
