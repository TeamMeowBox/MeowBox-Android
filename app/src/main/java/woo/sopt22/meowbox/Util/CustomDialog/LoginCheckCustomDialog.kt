package woo.sopt22.meowbox.Util.CustomDialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.login_dialog_custom.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Login.LoginActivity

/**
 * Created by VictoryWoo
 */
class LoginCheckCustomDialog(context: Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            popup_cancel_btn->{
                cancel()
            }
            popup_login_btn->{
                context.startActivity(Intent(context, LoginActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
        popup_cancel_btn.setOnClickListener(this)
        popup_login_btn.setOnClickListener(this)

    }

    companion object {
        private val LAYOUT = R.layout.login_dialog_custom
    }
}