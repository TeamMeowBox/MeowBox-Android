package woo.sopt22.meowbox.View.MyPage.Setting

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Network.NetworkService

import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.Login.LoginActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity


class LogoutCustomDialog(context: Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.logout_ask_cancel -> cancel()
            R.id.logout_ask_apply -> {
                logoutUser()
            }
        }
    }

    private var popCancelBtn: LinearLayout? = null
    private var popLoginBtn: LinearLayout? = null


    lateinit var networkService: NetworkService

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(context)
        //Log.v("user_idx : ",SharedPreference.instance!!.getPrefIntegerData("user_idx",0).toString())

        popCancelBtn = findViewById<View>(R.id.logout_ask_cancel) as LinearLayout
        popCancelBtn!!.setOnClickListener(this)
        popLoginBtn = findViewById<View>(R.id.logout_ask_apply) as LinearLayout
        popLoginBtn!!.setOnClickListener(this)
    }



    companion object {
        private val LAYOUT = R.layout.logout_custom_dialog
    }

    fun logoutUser(){
        val dialog = LogoutApplyDialog(context)
        SharedPreference.instance!!.removeData("user_email")
        SharedPreference.instance!!.removeData("token")
        SharedPreference.instance!!.removeData("name")
        SharedPreference.instance!!.removeData("flag")
        SharedPreference.instance!!.removeData("phone_number")
        SharedPreference.instance!!.removeData("cat_idx")
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        cancel()


    }


}
