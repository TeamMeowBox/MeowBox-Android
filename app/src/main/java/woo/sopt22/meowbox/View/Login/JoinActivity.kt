package woo.sopt22.meowbox.View.Login

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_join.*

import woo.sopt22.meowbox.R

class JoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        var joinBtn = join_btn as RelativeLayout

        //joinBtn.setOnClickListener(this)
    }
}
