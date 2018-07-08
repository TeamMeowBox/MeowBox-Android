package woo.sopt22.meowbox.View.MyPage.Suggest

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_my_page_suggest.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Base.BaseModel
import woo.sopt22.meowbox.Model.Suggest.MeowBoxSuggest
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.View.MyPage.MyPageActivity

class MyPageSuggestActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            suggest_btn->{
                // 서버랑 통신
                postSuggest()
            }
            suggest_x_btn->{
                finish()
            }

        }
    }

    fun postSuggest(){
        meowBoxSuggest = MeowBoxSuggest(SharedPreference.instance!!.getPrefStringData("user_idx")!!
                ,suggest_opinion.text.toString(),suggest_detail_comment.text.toString())
        var suggestResponse = networkService.postSuggest(SharedPreference.instance!!.getPrefStringData("token")!!,meowBoxSuggest)
        suggestResponse.enqueue(object : Callback<BaseModel>{
            override fun onFailure(call: Call<BaseModel>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<BaseModel>?, response: Response<BaseModel>?) {
                if(response!!.isSuccessful){
                    Log.v("43",response!!.message())
                    finish()
                }
            }

        })
    }
    lateinit var networkService: NetworkService
    lateinit var meowBoxSuggest: MeowBoxSuggest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_suggest)

        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        suggest_btn.setOnClickListener(this)
        suggest_x_btn.setOnClickListener(this)


        // 제안하기
        suggest_opinion.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        // 자세히 알려주세요.
        suggest_detail_comment.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


    }
}
