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

    lateinit var networkService: NetworkService
    lateinit var signUpUser : SignUpUser
    lateinit var token : String
    override fun onClick(v: View?) {
        when(v!!){
            join_btn->{
                if(join_name.text.toString().length == 0
                        || join_phone.text.toString().length == 0
                        || join_email.text.toString().length == 0
                        || join_password.text.toString().length == 0){
                    ToastMaker.makeShortToast(applicationContext, "정보를 정확히 입력해주세요.")
                } else{
                    sign()
                }

            }
            join_x_btn->{
                finish()
            }
        }
    }

    fun init(){
        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(this)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
        }

        // 클릭 리스너 달아줌
        join_btn.setOnClickListener(this)
        join_x_btn.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        init()

    }

    // 회원 가입 - 통신
    fun sign(){
        signUpUser = SignUpUser(join_email.text.toString(), join_password.text.toString()
                , join_name.text.toString(), join_phone.text.toString())

        var loginResponse = networkService.postSignUp(signUpUser)
        loginResponse.enqueue(object : Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                Log.v("회원가입 실패",t.toString())
            }

            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                if(response!!.isSuccessful){
                    Log.v("회원 가입 성공",response!!.body()!!.message)
                    token = response!!.body()!!.result!!.token!!.toString()

                    // 회원 가입하면서 받은 정보를 SharedPreference에 저장
                    SharedPreference.instance!!.setPrefData("token",token)
                    SharedPreference.instance!!.setPrefData("user_email",response!!.body()!!.result!!.email)
                    SharedPreference.instance!!.setPrefData("name",response!!.body()!!.result!!.name)
                    SharedPreference.instance!!.setPrefData("flag",response!!.body()!!.result!!.flag)
                    SharedPreference.instance!!.setPrefData("phone_number",response!!.body()!!.result!!.phone_number)
                    SharedPreference.instance!!.setPrefData("cat_idx",response!!.body()!!.result!!.cat_idx)
                    SharedPreference.instance!!.setPrefData("flag",response!!.body()!!.result!!.flag)
                    SharedPreference.instance!!.setPrefData("image_profile",response!!.body()!!.result!!.image_profile!!)

                    // MainActivity로 넘어가면서 스택에 있는 Activity 싹 다 날림
                    val intent = Intent(this@JoinActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }

        })
    }
}
