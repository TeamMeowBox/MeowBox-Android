package woo.sopt22.meowbox.View.Login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.activity_login.*
import woo.sopt22.meowbox.R
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Model.Login.LoginResponse
import woo.sopt22.meowbox.Model.Login.LoginUser
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.Join.JoinActivity


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            login_to_sign_btn->{
                startActivity(Intent(applicationContext, JoinActivity::class.java))
            }
            loginBtn->{
                if(cnt_email == 1 && cnt_password == 1){
                    postLogin()
                    //ToastMaker.makeLongToast(this, "로그인할 수 있음")
                } else{
                    ToastMaker.makeLongToast(this, "정보를 입력해주세요!")
                }

            }
            login_back_btn->{
                finish()
            }
            login_activity_layout->{
                var imm : InputMethodManager
                imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromInputMethod(login_email.windowToken, 0)
            }
        }
    }

    var email_length : Int=0
    var password_length : Int=0
    var cnt_email : Int=0
    var cnt_password : Int=0
    var email_flag : Boolean = false
    var password_flag : Boolean = false



    // 통신
    lateinit var loginUser : LoginUser
    lateinit var networkService: NetworkService
    lateinit var token : String
    lateinit var email : String

    fun init(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
        }

        login_to_sign_btn.setOnClickListener(this)
        loginBtn.setOnClickListener(this)
        login_back_btn.setOnClickListener(this)
        login_activity_layout.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(this)
        email = SharedPreference.instance!!.getPrefStringData("user_name")!!

        init()



        email_length = login_email.text.toString().length
        password_length = login_password.text.toString().length


        // login_email 입력 시 이메일 아이콘 색상 변경
        login_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s!!.toString().length == 0){
                    login_email_image.isSelected = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(login_email.text.toString().length<1 && login_password.text.toString().length<1){
                    loginBtn.setImageResource(R.drawable.login_btn_gray)
                    cnt_email=0
                    cnt_password=0
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length !=null){
                    login_email_image.isSelected = true
                } else {
                    login_email_image.isSelected = false
                }

                if(login_email.text.toString().length>=1 && login_password.text.toString().length>=1){
                    loginBtn.setImageResource(R.drawable.login_btn_pink)
                    cnt_email=1
                    cnt_password=1
                }
            }
        })

        // login_password 입력 시 password 아이콘 색상 변경
        login_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s!!.toString().length == 0){
                    login_password_image.isSelected = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(login_email.text.toString().length<5 && login_password.text.toString().length<5){
                    loginBtn.setImageResource(R.drawable.login_btn_gray)
                    cnt_password=0
                    cnt_email=0
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length !=null){
                    login_password_image.isSelected = true
                } else if(s!!.length == null){
                    login_password_image.isSelected = false
                }

                if(login_email.text.toString().length>=1 && login_password.text.toString().length>=1){
                    loginBtn.setImageResource(R.drawable.login_btn_pink)
                    cnt_password = 1
                    cnt_email=1
                }
            }
        })


        val sp = SpannableStringBuilder("아직 아이디가 없으신가요? 회원가입")
//sp.setSpan(new ForegroundColorSpan(Color.rgb(255, 255, 255)), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(ForegroundColorSpan(resources.getColor(R.color.pink)), 15, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        login_to_sign_text.append(sp)


    }

    fun postLogin(){
        loginUser = LoginUser(login_email.text.toString(), login_password.text.toString())
        val loginResponse = networkService.postSignIn(loginUser)
        loginResponse.enqueue(object : Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                Log.v("login",t!!.message.toString())

            }

            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                if(response!!.isSuccessful){
                    Log.v("login",response!!.message())
                    token = response!!.body()!!.result!!.token!!.toString()
                    SharedPreference.instance!!.setPrefData("token",token)
                    SharedPreference.instance!!.setPrefData("user_email",response!!.body()!!.result!!.email)
                    SharedPreference.instance!!.setPrefData("name",response!!.body()!!.result!!.name)
                    SharedPreference.instance!!.setPrefData("flag",response!!.body()!!.result!!.flag)
                    SharedPreference.instance!!.setPrefData("phone_number",response!!.body()!!.result!!.phone_number)
                    SharedPreference.instance!!.setPrefData("cat_idx",response!!.body()!!.result!!.cat_idx)
                    SharedPreference.instance!!.setPrefData("image_profile", response!!.body()!!.result!!.image_profile!!)
                    Log.v("123",token)
                    Log.v("1233", response!!.body()!!.result!!.image_profile!!)
                    Log.v("1234",response.body()!!.result!!.cat_idx)
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }
            }

        })
    }
}
