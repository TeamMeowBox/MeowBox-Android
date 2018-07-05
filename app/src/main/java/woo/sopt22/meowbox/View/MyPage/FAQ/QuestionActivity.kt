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
    lateinit var shippingAdapter: ParentAdapter
    lateinit var packingAdapter : ParentAdapter
    lateinit var subscribeAdapter : ParentAdapter


    lateinit var parent_items : ArrayList<Parent>
    lateinit var shipping_items : ArrayList<Parent>
    lateinit var packing_items : ArrayList<Parent>
    lateinit var subscribe_items : ArrayList<Parent>

    var faq = arrayListOf("Q. 간식과 사료의 성분을 알 수 있을까요?", "Q. 장난감은 같은 것으로 구성되어 있나요?", "Q. 크기는 어느정도 되나요?")
    var faq_content = arrayListOf("미유박스를 받으시면 간식/사료 포장 뒷면에 성분이 \n표기되어있습니다.","장난감은 매달 컨셉에 맞게 다른 상품으로 구성됩니다.", "미유박스는 350x240x150(mm)의 상자에 상품이 \n담겨 배송됩니다.")

    var shipping = arrayListOf("Q. 간식과 사료의 성분을 알 수 있을까요?")
    var shipping_content = arrayListOf("배송일은 주문/결제일 기준 다음주 월요일에 일괄 \n배송되며, 일반택배 기간인 2~3일 정도가 보통 \n소요됩니다.")

    var packing = arrayListOf("Q. 사료와 간식은 어떻게 포장되어 오나요?")
    var packing_content = arrayListOf("사료와 간식은 포장봉투에 싸여서 배송됩니다.")

    var subscribe = arrayListOf("Q. 매번 결제를 해야하는건가요?")
    var subscribe_content = arrayListOf("1회 구매의 경우 매번 결제를 하셔야합니다. 이것이 \n귀찮으시다면 정기구독(3개월, 6개월)을 선택하시면 \n매월초 자동결제가 되고 박스가 배송됩니다.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        question_x_btn.setOnClickListener(this)



        getGenres()
        getShipping()
        getPacking()
        getSubscribe()
    }

    fun getGenres() {
        parent_items = ArrayList(3)

        for (i in 0..faq.size-1) {
            val contents : ArrayList<Child> = ArrayList<Child>(1)
            contents.add(Child(faq_content[i]))
            if(i == 0){
                faq[i]
                faq_content
            }
            parent_items.add(Parent(faq[i], contents))
        }

        parentAdapter = ParentAdapter(parent_items)
        question_rv.layoutManager = LinearLayoutManager(this)
        question_rv.adapter = parentAdapter
    }

    fun getShipping() {
        shipping_items = ArrayList(1)

        for (i in 0..shipping.size-1) {
            val contents : ArrayList<Child> = ArrayList<Child>(1)
            contents.add(Child(shipping_content[i]))
            if(i == 0){
                shipping[i]
                shipping_content
            }
            shipping_items.add(Parent(shipping[i], contents))
        }
        shippingAdapter = ParentAdapter(shipping_items)
        shipping_rv.layoutManager = LinearLayoutManager(this)
        shipping_rv.adapter = shippingAdapter
        shipping_rv.setVerticalScrollBarEnabled(false)
    }

    fun getPacking(){
        packing_items = ArrayList(1)

        for (i in 0..packing.size-1) {
            val contents : ArrayList<Child> = ArrayList<Child>(1)
            contents.add(Child(packing_content[i]))
            packing_items.add(Parent(packing[i], contents))
        }
        packingAdapter = ParentAdapter(packing_items)
        packing_rv.layoutManager = LinearLayoutManager(this)
        packing_rv.adapter = packingAdapter
        packing_rv.setVerticalScrollBarEnabled(false)
    }

    fun getSubscribe(){
        subscribe_items = ArrayList(1)

        for (i in 0..subscribe.size-1) {
            val contents : ArrayList<Child> = ArrayList<Child>(1)
            contents.add(Child(subscribe_content[i]))
            subscribe_items.add(Parent(subscribe[i], contents))
        }
        subscribeAdapter = ParentAdapter(subscribe_items)
        subscribe_rv.layoutManager = LinearLayoutManager(this)
        subscribe_rv.adapter = subscribeAdapter
        subscribe_rv.setVerticalScrollBarEnabled(false)
    }

}

