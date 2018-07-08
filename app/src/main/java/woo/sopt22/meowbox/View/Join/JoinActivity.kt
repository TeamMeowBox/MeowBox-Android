package woo.sopt22.meowbox.View.Join

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_join.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Login.LoginResponse
import woo.sopt22.meowbox.Model.SignUp.SignUpResponse
import woo.sopt22.meowbox.Model.SignUp.SignUpUser
import woo.sopt22.meowbox.Network.NetworkService

import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Home.MainActivity

class JoinActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var joinBtn : RelativeLayout
    lateinit var joinCloseBtn : ImageView
    lateinit var joinName : EditText
    lateinit var joinPhone : EditText
    lateinit var joinEmail : EditText
    lateinit var joinPwd : EditText

    lateinit var jName : String
    lateinit var jPhone : String
    lateinit var jEmail : String
    lateinit var jPwd : String

    lateinit var networkService: NetworkService
    lateinit var signUpUser : SignUpUser
    lateinit var token : String
    override fun onClick(v: View?) {
        when(v!!){
            joinBtn->{
                //startActivity(Intent(this, LoginActivity::class.java))

                jName = joinName.text.toString()
                jPhone = joinPhone.text.toString()
                jEmail = joinEmail.text.toString()
                jPwd = joinPwd.text.toString()
                sign()

                Toast.makeText(this, jName+" "+jPhone+" "+jEmail+" "+jPwd, Toast.LENGTH_SHORT).show()
            }
            joinCloseBtn->{
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(this)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        joinBtn = join_btn as RelativeLayout
        joinBtn.setOnClickListener(this)

        joinCloseBtn = join_x_btn as ImageView
        joinCloseBtn.setOnClickListener(this)

        joinName = join_name as EditText
        joinPhone = join_phone as EditText
        joinEmail = join_email as EditText
        joinPwd = join_password as EditText

    }

    fun sign(){
        signUpUser = SignUpUser(jEmail, jPwd, jName, jPhone)
        var loginResponse = networkService.postSignUp(signUpUser)
        loginResponse.enqueue(object : Callback<SignUpResponse>{
            override fun onFailure(call: Call<SignUpResponse>?, t: Throwable?) {
                Log.v("12",t.toString())
            }

            override fun onResponse(call: Call<SignUpResponse>?, response: Response<SignUpResponse>?) {
                if(response!!.isSuccessful){
                    Log.v("11",response!!.body()!!.message)
                    //Log.v("11",response!!.body()!!.result!!.user_idx)
                    token = response!!.body()!!.result!!.token!!.toString()
                    ToastMaker.makeLongToast(this@JoinActivity, token)
                    SharedPreference.instance!!.setPrefData("token",token)
                    SharedPreference.instance!!.setPrefData("user_email",join_email.text.toString())
                    //SharedPreference.instance!!.setPrefData("user_idx",response.body()!!.result!!.user_idx)
                    //SharedPreference.instance!!.setPrefData("cat_idx",response.body()!!.result!!.cat_idx)
                    startActivity(Intent(this@JoinActivity, MainActivity::class.java))
                }
            }

        })
    }
}
