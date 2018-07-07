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
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Network.NetworkService

import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.Login.LoginActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity


class DisjoinCustomDialog(context: Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.disjoin_ask_cancel -> {
                cancel()
            }
            R.id.disjoin_ask_apply -> {
                //deleteUser()
            }
        }
    }

    companion object {
        private val LAYOUT = R.layout.disjoin_custom_dialog
    }


    lateinit var disjoinCancelBtn: LinearLayout
    lateinit var disjoinLoginBtn: LinearLayout

    lateinit var networkService : NetworkService
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        SharedPreference.instance!!.load(context)
        networkService = ApplicationController.instance!!.networkService

        disjoinCancelBtn = findViewById<View>(R.id.disjoin_ask_cancel) as LinearLayout
        disjoinCancelBtn!!.setOnClickListener(this)
        disjoinLoginBtn = findViewById<View>(R.id.disjoin_ask_apply) as LinearLayout
        disjoinLoginBtn!!.setOnClickListener(this)


    }




   /* fun deleteUser(){
        //val logoutUser = networkService.deleteUser(SharedPreference.instance!!.getPrefStringData("user_idx")!!.toInt())
        //logoutUser.enqueue(object : Callback<BaseModel> {
            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if(response!!.isSuccessful){
                    val dialog = DisjoinApplyDialog(context)
                    SharedPreference.instance!!.removeData("user_idx")
                    SharedPreference.instance!!.removeData("token")
                    SharedPreference.instance!!.removeData("cat_idx")
                    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dialog.setCanceledOnTouchOutside(false)
                    dialog.show()
                    cancel()
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            }
        })
    }*/
}
