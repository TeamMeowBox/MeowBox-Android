package woo.sopt22.meowbox.View.MyPage.FAQ

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_question.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.MyPage.FAQ.Adapter.ParentAdapter
import woo.sopt22.meowbox.View.MyPage.FAQ.models.Child
import woo.sopt22.meowbox.View.MyPage.FAQ.models.Parent


class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            question_x_btn->{
                finish()
            }
        }
    }

    lateinit var parentAdapter: ParentAdapter
    lateinit var genre_items : ArrayList<Parent>

    var faq = arrayListOf<String>("Q. 간식과 사료의 성분을 알 수 있을까요?", "Q. 장난감은 같은 것으로 구성되어 있나요?", "Q. 크기는 어느정도 되나요?")
    var faq_content = arrayListOf<String>("간식, 사료","두 번째", "세 번쨰")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        question_x_btn.setOnClickListener(this)

        question_rv

        println(faq.size)
        println(faq_content.indexOf("간식, 사료"))

        for(i in 0..faq.size){
            println(i)
        }

        getGenres()
        parentAdapter = ParentAdapter(genre_items)
        question_rv.layoutManager = LinearLayoutManager(this)
        question_rv.adapter = parentAdapter
    }

    fun getGenres() {
        genre_items = ArrayList(3)

        for (i in 0..faq.size-1) {
            val contents : ArrayList<Child> = ArrayList<Child>(1)
            contents.add(Child(faq_content[i]))
            genre_items.add(Parent(faq[i], contents))
        }
    }

}
