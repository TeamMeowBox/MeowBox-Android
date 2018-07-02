package woo.sopt22.meowbox.View.MyPage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_my_page.*
import woo.sopt22.meowbox.R

class MyPageActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            myPageBackBtn->{
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        myPageBackBtn.setOnClickListener(this)
    }
}
