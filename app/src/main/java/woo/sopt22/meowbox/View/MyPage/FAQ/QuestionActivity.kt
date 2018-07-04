package woo.sopt22.meowbox.View.MyPage.FAQ

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_question.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.MyPage.FAQ.Adapter.GenreAdapter
import woo.sopt22.meowbox.View.MyPage.FAQ.models.Content
import woo.sopt22.meowbox.View.MyPage.FAQ.models.Genre


class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            question_x_btn->{
                finish()
            }
        }
    }

    lateinit var genreAdapter: GenreAdapter
    lateinit var genre_items : ArrayList<Genre>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        question_x_btn.setOnClickListener(this)

        question_rv

        getGenres()
        genreAdapter = GenreAdapter(genre_items)
        question_rv.layoutManager = LinearLayoutManager(this)
        question_rv.adapter = genreAdapter
    }

    fun getGenres() {
        genre_items = ArrayList(6)
        for (i in 0..5) {
            val contents : ArrayList<Content> = ArrayList<Content>(3)
            contents.add(Content("Paramore"))
            contents.add(Content("Flyleaf"))
            contents.add(Content("The Script"))
            genre_items.add(Genre("Rock_"+i,contents))
        }
    }

}
