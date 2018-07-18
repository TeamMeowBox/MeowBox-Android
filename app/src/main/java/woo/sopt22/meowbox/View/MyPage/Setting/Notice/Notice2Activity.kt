package woo.sopt22.meowbox.View.MyPage.Setting.Notice

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_notice2.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.MyPage.FAQ.Adapter.ParentAdapter
import woo.sopt22.meowbox.View.MyPage.FAQ.models.Child
import woo.sopt22.meowbox.View.MyPage.FAQ.models.Parent

class Notice2Activity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            notice_x_btn->{
                finish()
            }
        }
    }


    lateinit var parentAdapter: ParentAdapter
    lateinit var genre_items : ArrayList<Parent>

    var notice = arrayListOf<String>("드디어 미유박스가 출시되었습니다","미유박스 주문관련 공지입니다")
    var notice_content = arrayListOf<String>("안녕하세요, 미유박스 운영팀입니다. \n\n드디어 기다리고 기다리던 미유박스가 \n출시되었습니다. \n미유박스는 반려묘를 키우는 미유팀이 직접 \n반려묘를 키우면서 경험한 것을 녹여서 만든 \n서비스입니다. \n아이들의 정보를 바탕으로 맞춤형 놀이, 음식을 보내드립니다. \n기대되시죠!? \n\n냥이들을 위한 다양한 이벤트와 행사를 준비하였으니, \n많은 관심과 애정! \n부탁드리겠습니다. \n\n반려묘에게 아낌없는 사랑을 주고 싶은 Meow \nBox가 되겠습니다. \n\n미유박스 운영팀 드림."
    , "안녕하세요, 미유님들! \n\n주문해주신 미유박스를 취소/반품/환불 요청은 \n홈페이지 고객센터 1:1 문의에 접수해주시면 \n감사하겠습니다. \n\n다만, 주문취소(결제취소)의 경우 결제일로부터 \n매주 월요일 이전까지 가능합니다. 월요일 당일 오전 10시 이후 주문, \n결제건은 배송처리를 위해 배송정보 출력 및 포장/배송진행을 \n위한 작업을 바로 진행하기 때문에 취소처리가 \n어려운점 양해해주시면 감사하겠습니다. \n\n어려움이 있으실 경우, 고객센터로 연락주시면 \n자세하게 상담드리도록 하겠습니다. \n\n미유박스 운영팀 드림.")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice2)

        set_Staus_Navigation_bar()
        setClickListener()
        getGenres()
    }

    fun set_Staus_Navigation_bar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
        }
    }

    fun setClickListener(){
        notice_x_btn.setOnClickListener(this)

    }

    fun getGenres() {
        genre_items = ArrayList(3)

        for (i in 0..notice.size-1) {
            val contents : ArrayList<Child> = ArrayList<Child>(1)
            contents.add(Child(notice_content[i]))
            genre_items.add(Parent(notice[i], contents))
        }

        parentAdapter = ParentAdapter(genre_items)
        notice_rv.layoutManager = LinearLayoutManager(this)
        notice_rv.adapter = parentAdapter
    }
}
