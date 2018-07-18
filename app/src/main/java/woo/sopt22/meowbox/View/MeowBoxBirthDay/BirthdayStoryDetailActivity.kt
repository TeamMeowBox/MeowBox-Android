package woo.sopt22.meowbox.View.MeowBoxBirthDay

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import kotlinx.android.synthetic.main.activity_birthday_story_detail.*
import kotlinx.android.synthetic.main.content_meow_box_detail.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.CustomDialog.CatCustomDialog
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.View.MeowBoxDetail.DetailModel
import woo.sopt22.meowbox.View.MeowBoxDetail.DetailViewAdapter
import woo.sopt22.meowbox.View.Order.LoginCustomDialog
import woo.sopt22.meowbox.View.Order.OrderThirdActivity

class BirthdayStoryDetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            birthday_detail_order_btn->{
                if(SharedPreference.instance!!.getPrefStringData("token")!!.isEmpty()){
                    val dialog = LoginCustomDialog(this)
                    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dialog.show()
                } else{
                    if(SharedPreference.instance!!.getPrefStringData("cat_idx")!! == "-1"){
                        val dialog = CatCustomDialog(this)
                        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        dialog.show()
                    } else {
                        val intent = Intent(this, OrderThirdActivity::class.java)
                        intent.putExtra("cat_idx",SharedPreference.instance!!.getPrefStringData("cat_idx")!!)
                        startActivity(intent)
                    }
                }

            }
            birthday_detail_x_btn->{
                finish()
            }
        }
    }



    lateinit var items1 : ArrayList<DetailModel>
    lateinit var items2 : ArrayList<DetailModel>
    lateinit var items3 : ArrayList<DetailModel>
    lateinit var items4 : ArrayList<DetailModel>
    lateinit var items5 : ArrayList<DetailModel>

    fun init(){

        birthday_detail_order_btn.setOnClickListener(this)
        birthday_detail_x_btn.setOnClickListener(this)
        SharedPreference.instance!!.load(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
        }
    }

    fun dataSetting(){
        items1 = ArrayList(); // 선글라스 모자

        items1.add(DetailModel("2018 S/S 피서룩의 완성. 선글라스와 \n모자만 있으면, 이구역 힙냥이는 나야!",R.drawable.ribbon_two_img))
        items1.add(DetailModel("마음대로 길이조절이 가능한 밀짚모자! \n우리 냥이에게 딱 맞게 씌워주세요.",R.drawable.ribbon_three_img))
        items1.add(DetailModel("볼수록 더 귀여운 밀짚모자와 선글라스. 냥이에게 \n멋진 피서를 선물하는 법, 어렵지 않아요!",R.drawable.ribbon_four_img))

        items2 = ArrayList(); // 장난감

        items2.add(DetailModel("보기만 해도 시원해! 냥이가 씹고 뜯고 \n맛보고 즐길 올여름 최애 인형",R.drawable.cake_two_img))
        items2.add(DetailModel("세상 부드러운 양모볼에 캣닢가루를 솔솔~ \n하루 종일 갖고 놀아도 질리지 않아요.",R.drawable.cake_three_img))
        items2.add(DetailModel("냥이와 케미 폭발할 아기자기한 인형, \n가지고 놀기 딱 좋은 사이즈로 만들었어요. ",R.drawable.cake_four_img))

        items3 = ArrayList(); // 낚시대

        items3.add(DetailModel("숨어있던 사냥본능을 깨워라! \n자유자재 길이조절로 늘 새로운 3단 낚시대",R.drawable.toy_two_img))
        items3.add(DetailModel("길고 부드러운 깃털과 방울 소리로 \n냥이의 사냥본능을 자극해요.",R.drawable.toy_three_img))
        items3.add(DetailModel("엉키지 않는 긴 낚시줄과 길이 조절이 가능한 \n낚시대로 더 재밌고 편하게 놀아줄 수 있어요.",R.drawable.toy_four_img))

        items4 = ArrayList(); // 스크래쳐

        items4.add(DetailModel("스트레스를 해소해 줄 스크래쳐. \n한 번 긁으면, 멈출 수 없을 걸?",R.drawable.scratcher_two_img))
        items4.add(DetailModel("냥이가 스트레소를 해소하고, \n발톱을 정리할 스크래쳐. 마음껏 긁어주세요!" ,R.drawable.scratcher_three_img))
        items4.add(DetailModel("미유박스와 딱 맞는 크기의 스크래쳐, \n합체하면 아늑한 냥이만의 공간 완성!",R.drawable.scratcher_four_img))

        items5 = ArrayList(); // 간식

        items5.add(DetailModel("영양소를 그대로 담은 동결건조 닭가슴살, \n맛과 건강 둘 다 놓치지 않을 거예요!",R.drawable.snack_two_img))
        items5.add(DetailModel("까다로운 냥이도 무너뜨릴 향기, 갈매기살을 \n갈아 만들어 말랑한 식감의 포크 져키",R.drawable.snack_three_img))
        items5.add(DetailModel("미유가 만들고 수의사가 검사한 믿음직한 \n수제 간식. 영양소와 기호성 또한 최고!",R.drawable.snack_four_img))

        var madapter1 = DetailViewAdapter(layoutInflater, items1)
        var madapter2 = DetailViewAdapter(layoutInflater, items2)
        var madapter3 = DetailViewAdapter(layoutInflater, items3)
        var madapter4 = DetailViewAdapter(layoutInflater, items4)
        var madapter5 = DetailViewAdapter(layoutInflater, items5)


        birthday_detail_cardview_pager_one.adapter = madapter1
        birthday_detail_cardview_pager_two.adapter = madapter2
        birthday_detail_cardview_pager_three.adapter = madapter3
        birthday_detail_cardview_pager_four.adapter = madapter4
        birthday_detail_cardview_pager_five.adapter = madapter5


        birthday_detail_cardview_pager_one.addOnPageChangeListener(birthdayOnPageChangeListenter_one)
        birthday_detail_cardview_pager_two.addOnPageChangeListener(birthdayOnPageChangeListenter_two)
        birthday_detail_cardview_pager_three.addOnPageChangeListener(birthdayOnPageChangeListenter_three)
        birthday_detail_cardview_pager_four.addOnPageChangeListener(birthdayOnPageChangeListenter_four)
        birthday_detail_cardview_pager_five.addOnPageChangeListener(birthdayOnPageChangeListenter_five)
        //birthday_detail_cardview_pager_five.addOnPageChangeListener(birthdayOnPageChangeListenter_two)

        birthday_detail_cardview_one_indicator.setItemMargin(20)
        birthday_detail_cardview_one_indicator.setAnimDuration(300)
        birthday_detail_cardview_one_indicator.createDotPanel(items1.size,R.drawable.indicator_non, R.drawable.indicator_on)

        birthday_detail_cardview_two_indicator.setItemMargin(20)
        birthday_detail_cardview_two_indicator.setAnimDuration(300)
        birthday_detail_cardview_two_indicator.createDotPanel(items1.size,R.drawable.indicator_non, R.drawable.indicator_on)

        birthday_detail_cardview_three_indicator.setItemMargin(20)
        birthday_detail_cardview_three_indicator.setAnimDuration(300)
        birthday_detail_cardview_three_indicator.createDotPanel(items1.size,R.drawable.indicator_non, R.drawable.indicator_on)

        birthday_detail_cardview_four_indicator.setItemMargin(20)
        birthday_detail_cardview_four_indicator.setAnimDuration(300)
        birthday_detail_cardview_four_indicator.createDotPanel(items1.size,R.drawable.indicator_non, R.drawable.indicator_on)

        birthday_detail_cardview_five_indicator.setItemMargin(20)
        birthday_detail_cardview_five_indicator.setAnimDuration(300)
        birthday_detail_cardview_five_indicator.createDotPanel(items1.size,R.drawable.indicator_non, R.drawable.indicator_on)

        birthday_detail_cardview_pager_one.setClipToPadding(false)
        birthday_detail_cardview_pager_one.setPadding(80,0,80,0)
        birthday_detail_cardview_pager_one.pageMargin = 40

        birthday_detail_cardview_pager_two.setClipToPadding(false)
        birthday_detail_cardview_pager_two.setPadding(80,0,80,0)
        birthday_detail_cardview_pager_two.pageMargin = 40

        birthday_detail_cardview_pager_three.setClipToPadding(false)
        birthday_detail_cardview_pager_three.setPadding(80,0,80,0)
        birthday_detail_cardview_pager_three.pageMargin = 40

        birthday_detail_cardview_pager_four.setClipToPadding(false)
        birthday_detail_cardview_pager_four.setPadding(80,0,80,0)
        birthday_detail_cardview_pager_four.pageMargin = 40

        birthday_detail_cardview_pager_five.setClipToPadding(false)
        birthday_detail_cardview_pager_five.setPadding(80,0,80,0)
        birthday_detail_cardview_pager_five.pageMargin = 40

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday_story_detail)

        init()
        dataSetting()




    }

    var birthdayOnPageChangeListenter_one : ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            birthday_detail_cardview_one_indicator.selectDot(position)
        }

    }

    var birthdayOnPageChangeListenter_two : ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            birthday_detail_cardview_two_indicator.selectDot(position)
        }

    }

    var birthdayOnPageChangeListenter_three : ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            birthday_detail_cardview_three_indicator.selectDot(position)
        }

    }

    var birthdayOnPageChangeListenter_four : ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            birthday_detail_cardview_four_indicator.selectDot(position)
        }

    }
    var birthdayOnPageChangeListenter_five : ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            birthday_detail_cardview_five_indicator.selectDot(position)
        }

    }
}
