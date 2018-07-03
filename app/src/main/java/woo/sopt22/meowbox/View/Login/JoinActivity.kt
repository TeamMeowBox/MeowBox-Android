package woo.sopt22.meowbox.View.Login

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_join.*

import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Order.OrderFiveActivity

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

    override fun onClick(v: View?) {
        when(v!!){
            joinBtn->{
                startActivity(Intent(this, LoginActivity::class.java))

                jName = joinName.text.toString()
                jPhone = joinPhone.text.toString()
                jEmail = joinEmail.text.toString()
                jPwd = joinPwd.text.toString()

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
}
