package woo.sopt22.meowbox.View.Login

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.activity_login.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.R.id.textView
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.View


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            login_to_sign_btn->{

            }
            loginBtn->{
                //clearSelected()


            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_to_sign_btn.setOnClickListener(this)
        loginBtn.setOnClickListener(this)

        if (login_email.text.toString().length >= 5 && login_password.text.toString().length >=5){
            loginBtn.isSelected = true
        }


        // login_email 입력 시 이메일 아이콘 색상 변경
        login_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s!!.toString().length == 0){
                    login_email_image.isSelected = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length !=null){
                    login_email_image.isSelected = true
                } else {
                    login_email_image.isSelected = false
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
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.length !=null){
                    login_password_image.isSelected = true
                } else if(s!!.length == null){
                    login_password_image.isSelected = false
                }
            }
        })


        val sp = SpannableStringBuilder("아직 아이디가 없으신가요? 회원가입")
//sp.setSpan(new ForegroundColorSpan(Color.rgb(255, 255, 255)), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(ForegroundColorSpan(resources.getColor(R.color.pink)), 15, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        login_to_sign_text.append(sp)


    }

    fun clearSelected(){
        loginBtn.isSelected = false
    }
}
