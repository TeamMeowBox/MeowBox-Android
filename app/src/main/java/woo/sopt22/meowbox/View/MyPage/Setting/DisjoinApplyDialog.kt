package woo.sopt22.meowbox.View.MyPage.Setting

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Network.NetworkService

import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.Login.LoginActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity


class DisjoinApplyDialog(context: Context) : Dialog(context), View.OnClickListener {

    lateinit var disjoinLogoutBtn: LinearLayout

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        SharedPreference.instance!!.load(context)

        disjoinLogoutBtn = findViewById<View>(R.id.disjoin_apply_apply) as LinearLayout
        disjoinLogoutBtn!!.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.disjoin_apply_apply -> {
                //deleteUser()
                cancel()
            }
        }
    }

    companion object {
        private val LAYOUT = R.layout.disjoin_apply_dialog
    }


}