package woo.sopt22.meowbox.Util.CustomDialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.disjoin_apply_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.View.Home.MainActivity

class DeleteUserApplyCustomDialog(context : Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            disjoin_apply_apply->{
                deleteUser()
            }
        }
    }

    lateinit var networkService : NetworkService
    lateinit var disjoinApplyBtn : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(context)

        disjoinApplyBtn = disjoin_apply_apply as LinearLayout
        disjoin_apply_apply.setOnClickListener(this)

    }

    companion object {
        private val LAYOUT = R.layout.disjoin_apply_dialog
    }

    fun deleteUser(){
        Log.v("delete","들어옴?")
        val logoutUser = networkService.deleteUser(SharedPreference.instance!!.getPrefStringData("token")!!)
        Log.v("delete2","들어옴?2")
        logoutUser.enqueue(object : Callback<BaseModel> {
            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {
                Log.v("fail",t!!.toString())
            }

            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if(response!!.isSuccessful){
                    Log.v("success",response!!.body()!!.message)
                    SharedPreference.instance!!.removeData("user_email")
                    SharedPreference.instance!!.removeData("token")
                    SharedPreference.instance!!.removeData("name")
                    SharedPreference.instance!!.removeData("flag")
                    SharedPreference.instance!!.removeData("phone_number")
                    SharedPreference.instance!!.removeData("cat_idx")
                    cancel()
                    val intent = Intent(context, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            }
        })
    }
}