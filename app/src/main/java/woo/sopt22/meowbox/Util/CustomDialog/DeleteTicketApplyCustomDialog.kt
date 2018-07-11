package woo.sopt22.meowbox.Util.CustomDialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.delete_custom_apply_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Model.Delete.DeleteTicket
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.View.MyPage.MyPageActivity

class DeleteTicketApplyCustomDialog(context: Context) : Dialog(context), View.OnClickListener {


    companion object {
        private val LAYOUT = R.layout.delete_custom_apply_dialog
    }
    lateinit var networkService: NetworkService

    override fun onClick(v: View?) {
        when(v!!){
            delete_apply_btn->{
                deleteTicekt()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(context)
        Log.v("077",SharedPreference.instance!!.getPrefStringData("order_idx")!!)

        delete_apply_btn.setOnClickListener(this)

    }
    fun deleteTicekt(){
        Log.v("078","티켓취소")
        val deleteResponse = networkService.deleteSeasonTicket(SharedPreference.instance!!.getPrefStringData("token")!!
                ,SharedPreference.instance!!.getPrefStringData("order_idx")!!)
        deleteResponse.enqueue(object : Callback<DeleteTicket>{
            override fun onFailure(call: Call<DeleteTicket>?, t: Throwable?) {
                Log.v("079",t!!.message)
            }

            override fun onResponse(call: Call<DeleteTicket>?, response: Response<DeleteTicket>?) {
                if(response!!.isSuccessful){
                    Log.v("080",response!!.message())

                    SharedPreference.instance!!.setPrefData("flag",response!!.body()!!.result!!.flag)
                    SharedPreference.instance!!.removeData("order_idx")
                    cancel()
                    val intent = Intent(context, MyPageActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            }

        })
    }

}