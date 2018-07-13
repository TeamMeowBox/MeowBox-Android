package woo.sopt22.meowbox.Util.CustomDialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.login_to_mypage_custom_dialog.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Login.LoginActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity

class LoginToMyPageCustomDialog(context : Context) : Dialog(context), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v!!){
            login_to_mypage_popup_cancel_btn->{
                dismiss()
            }
            login_to_mypage_popup_login_btn->{
                cancel()
                context.startActivity(Intent(context, LoginActivity::class.java))
            }
        }
    }

    companion object {
        private val LAYOUT = R.layout.login_to_mypage_custom_dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
        login_to_mypage_popup_cancel_btn.setOnClickListener(this)
        login_to_mypage_popup_login_btn.setOnClickListener(this)
    }


}