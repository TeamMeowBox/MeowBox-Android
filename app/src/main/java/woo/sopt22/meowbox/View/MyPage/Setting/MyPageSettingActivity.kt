package woo.sopt22.meowbox.View.MyPage.Setting

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import woo.sopt22.meowbox.R
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import woo.sopt22.meowbox.Util.CustomDialog.DeleteUserCustomDialog
import woo.sopt22.meowbox.View.MyPage.Setting.Notice.Notice2Activity
import woo.sopt22.meowbox.View.MyPage.Setting.Terms.Terms2Activity


class MyPageSettingActivity : AppCompatActivity(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!) {
            my_page_setting_x_btn -> {
                finish()
            }

            my_page_terms_btn->{
                startActivity(Intent(this, Terms2Activity::class.java))
            }

            my_page_setting_logout->{
                var logout_dialog = LogoutCustomDialog(this@MyPageSettingActivity)
                logout_dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                logout_dialog.setCanceledOnTouchOutside(false)
                logout_dialog.show()
            }

            my_page_setting_disjoin->{
                var dis_joing_dialog = DeleteUserCustomDialog(this@MyPageSettingActivity)
                dis_joing_dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dis_joing_dialog.setCanceledOnTouchOutside(false)
                dis_joing_dialog.show()

            }

            my_page_notice_btn->{
                startActivity(Intent(this, Notice2Activity::class.java))
            }
        }
    }

    // var tv1 = my_page_setting_disjoin as TextView
    lateinit var myPageSettingXBtn : ImageView
    lateinit var myPageSettingTerms : RelativeLayout
    lateinit var myPageSettingLogout : LinearLayout
    lateinit var myPageSettingDisjoin : TextView
    lateinit var myPageNoticeBtn : RelativeLayout


    fun dialog(){
        val inflater =  layoutInflater
        val dialogView = inflater.inflate(R.layout.logout_custom_dialog, null)


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_setting)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
        }

        val content = SpannableString("여기")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        my_page_setting_disjoin.setText(content)

        myPageSettingXBtn = my_page_setting_x_btn as ImageView
        my_page_setting_x_btn.setOnClickListener(this)

        myPageSettingTerms = my_page_terms_btn as RelativeLayout
        my_page_terms_btn.setOnClickListener(this)

        myPageSettingLogout = my_page_setting_logout as LinearLayout
        my_page_setting_logout.setOnClickListener(this)

        myPageSettingDisjoin = my_page_setting_disjoin as TextView
        my_page_setting_disjoin.setOnClickListener(this)

        myPageNoticeBtn = my_page_notice_btn as RelativeLayout
        my_page_notice_btn.setOnClickListener(this)


    }
}
