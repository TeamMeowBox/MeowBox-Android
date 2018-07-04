package woo.sopt22.meowbox.View.MyPage.Setting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import woo.sopt22.meowbox.R
import android.text.style.UnderlineSpan



class MyPageSettingActivity : AppCompatActivity() {

    var tv1 = my_page_setting_disjoin as TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_setting)

        val content = SpannableString("Content")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        tv1.setText(content)


    }
}
