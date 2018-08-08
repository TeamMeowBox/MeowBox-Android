package woo.sopt22.meowbox.View.MyPage.Setting

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import kotlinx.android.synthetic.main.activity_my_page_setting.*
import woo.sopt22.meowbox.R
import android.text.style.UnderlineSpan
import android.view.View
import woo.sopt22.meowbox.Util.CustomDialog.DeleteUserCustomDialog
import woo.sopt22.meowbox.Util.CustomDialog.LogoutCustomDialog
import woo.sopt22.meowbox.View.MyPage.Setting.Notice.Notice2Activity
import woo.sopt22.meowbox.View.MyPage.Setting.Terms.Terms2Activity


class SettingActivity : AppCompatActivity(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!) {
            my_page_setting_x_btn -> {
                finish()
            }

            my_page_terms_btn->{
                startActivity(Intent(this, Terms2Activity::class.java))
            }

            my_page_setting_logout->{
                var logout_dialog = LogoutCustomDialog(this@SettingActivity)
                logout_dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                logout_dialog.setCanceledOnTouchOutside(false)
                logout_dialog.show()
            }

            my_page_setting_disjoin->{
                var dis_joing_dialog = DeleteUserCustomDialog(this@SettingActivity)
                dis_joing_dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dis_joing_dialog.setCanceledOnTouchOutside(false)
                dis_joing_dialog.show()

            }

            my_page_notice_btn->{
                startActivity(Intent(this, Notice2Activity::class.java))
            }
        }
    }

    fun init(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
        }

        my_page_setting_x_btn.setOnClickListener(this)
        my_page_terms_btn.setOnClickListener(this)
        my_page_setting_logout.setOnClickListener(this)
        my_page_setting_disjoin.setOnClickListener(this)
        my_page_notice_btn.setOnClickListener(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_setting)

        init()

        val content = SpannableString("여기")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        my_page_setting_disjoin.setText(content)

    }
}
