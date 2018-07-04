package woo.sopt22.meowbox.View.MyPage.FAQ

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_question.*
import woo.sopt22.meowbox.R

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            question_x_btn->{
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        question_x_btn.setOnClickListener(this)
    }
}
