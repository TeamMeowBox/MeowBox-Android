package woo.sopt22.meowbox.View.MeowBoxDetail

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_meow_box_detail.*
import kotlinx.android.synthetic.main.app_bar_meow_box_detail.*
import kotlinx.android.synthetic.main.content_meow_box_detail.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.CustomDialog.CatCustomDialog
import woo.sopt22.meowbox.Util.CustomDialog.LoginCheckCustomDialog
import woo.sopt22.meowbox.Util.CustomDialog.LoginToMyPageCustomDialog
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.Login.LoginActivity
import woo.sopt22.meowbox.View.MeowBoxBirthDay.MeowBoxtBirthDayStoryActivity
import woo.sopt22.meowbox.View.MeowBoxReview.MeowBoxReviewActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity
import woo.sopt22.meowbox.View.Order.LoginCustomDialog
import woo.sopt22.meowbox.View.Order.OrderFragmentWithCatInfo.OrderWithCatInfoActivity

class MeowBoxDetailActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    override fun onClick(p0: View?) {
        when (p0) {
            detail_order_btn -> {
                if (SharedPreference.instance!!.getPrefStringData("token")!!.isEmpty()) {
                    val dialog = LoginCustomDialog(this)
                    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dialog.show()
                } else {
                    if (SharedPreference.instance!!.getPrefStringData("cat_idx")!!.toInt() == -1) {
                        val dialog = CatCustomDialog(this)
                        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        dialog.show()
                    } else {
                        val intent = Intent(this, OrderWithCatInfoActivity::class.java)
                        intent.putExtra("cat_idx", SharedPreference.instance!!.getPrefStringData("cat_idx")!!)
                        startActivity(intent)

                    }
                }

            }

        }
    }

    lateinit var items1 : ArrayList<DetailModel>
    lateinit var items2 : ArrayList<DetailModel>
    lateinit var items3 : ArrayList<DetailModel>
    lateinit var items4 : ArrayList<DetailModel>
    lateinit var items5 : ArrayList<DetailModel>

    lateinit var detailOrderBtn : RelativeLayout

    fun init(){

        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        toolbar.bringToFront()

        SharedPreference.instance!!.load(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
        }

        detail_order_btn.setOnClickListener(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meow_box_detail)
        setSupportActionBar(toolbar)

        init()
        dataSetting()


        var headerView : View = detail_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)
        var userImage : ImageView = headerView.findViewById(R.id.imageView)

        Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image")!!).into(userImage)


        var menu : Menu = detail_nav_view.menu
        var menu_item : MenuItem = menu.findItem(R.id.loginBtn)
        var blank_menu_item : MenuItem = menu.findItem(R.id.blankBtn)
        var blank_menu_item2 : MenuItem = menu.findItem(R.id.blankBtn2)
        blank_menu_item.setEnabled(false)
        blank_menu_item2.setEnabled(false)

        if(SharedPreference.instance!!.getPrefStringData("name")!!.isEmpty()){
            userName.text = "OO님!"
            menu_item.setTitle("로그인")
        } else {
            userName.text = SharedPreference.instance!!.getPrefStringData("name") + "님"
            menu_item.setTitle("")
            menu_item.setEnabled(false)
        }

        if(SharedPreference.instance!!.getPrefStringData("image_profile") == null){
            Glide.with(this).load(R.drawable.side_bar_profile_img).into(userImage)
        } else{
            Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image_profile")!!).into(userImage)
        }


        val toggle = ActionBarDrawerToggle(
                this, detail_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.setDrawerIndicatorEnabled(false)
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_black, applicationContext!!.getTheme())

        val bitmap = (drawable as BitmapDrawable).bitmap
        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (detail_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                    detail_drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    detail_drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })

        detail_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        detail_nav_view.setNavigationItemSelectedListener(this)
    }

    fun dataSetting(){
        items1 = ArrayList(); // 선글라스 모자

        items1.add(DetailModel("2018 S/S 피서룩의 완성. 선글라스와 \n모자만 있으면, 이구역 힙냥이는 나야!",R.drawable.sunglass_two_img))
        items1.add(DetailModel("마음대로 길이조절이 가능한 밀짚모자! \n우리 냥이에게 딱 맞게 씌워주세요.",R.drawable.sunglass_three_img))
        items1.add(DetailModel("볼수록 더 귀여운 밀짚모자와 선글라스. 냥이에게 \n멋진 피서를 선물하는 법, 어렵지 않아요!",R.drawable.sunglass_four_img))


        items2 = ArrayList(); // 장난감

        items2.add(DetailModel("보기만 해도 시원해! 냥이가 씹고 뜯고 \n맛보고 즐길 올여름 최애 인형",R.drawable.toy_two_img))
        items2.add(DetailModel("세상 부드러운 양모볼에 캣닢가루를 솔솔~ \n하루 종일 갖고 놀아도 질리지 않아요.",R.drawable.toy_three_img))
        items2.add(DetailModel("냥이와 케미 폭발할 아기자기한 인형, \n가지고 놀기 딱 좋은 사이즈로 만들었어요. ",R.drawable.toy_four_img))

        items3 = ArrayList(); // 낚시대

        items3.add(DetailModel("숨어있던 사냥본능을 깨워라! \n자유자재 길이조절로 늘 새로운 3단 낚시대",R.drawable.fishing_rod_two_img))
        items3.add(DetailModel("길고 부드러운 깃털과 방울 소리로 \n냥이의 사냥본능을 자극해요.",R.drawable.fishing_rod_three_img))
        items3.add(DetailModel("엉키지 않는 긴 낚시줄과 길이 조절이 가능한 \n낚시대로 더 재밌고 편하게 놀아줄 수 있어요.",R.drawable.fishing_rod_four_img))

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


        detail_cardview_pager_one.adapter = madapter1
        detail_cardview_pager_two.adapter = madapter2
        detail_cardview_pager_three.adapter = madapter3
        detail_cardview_pager_four.adapter = madapter4
        detail_cardview_pager_five.adapter = madapter5


        detail_cardview_pager_one.addOnPageChangeListener(mOnPageChangeListener_one)
        detail_cardview_pager_two.addOnPageChangeListener(mOnPageChangeListener_two)
        detail_cardview_pager_three.addOnPageChangeListener(mOnPageChangeListener_three)
        detail_cardview_pager_four.addOnPageChangeListener(mOnPageChangeListener_four)
        detail_cardview_pager_five.addOnPageChangeListener(mOnPageChangeListener_five)

        detail_cardview_one_indicator.setItemMargin(20)
        detail_cardview_one_indicator.setAnimDuration(400)
        detail_cardview_one_indicator.createDotPanel(items1.size,R.drawable.indicator_non, R.drawable.indicator_on)

        detail_cardview_two_indicator.setItemMargin(20)
        detail_cardview_two_indicator.setAnimDuration(300)
        detail_cardview_two_indicator.createDotPanel(items1.size,R.drawable.indicator_non, R.drawable.indicator_on)

        detail_cardview_three_indicator.setItemMargin(20)
        detail_cardview_three_indicator.setAnimDuration(300)
        detail_cardview_three_indicator.createDotPanel(items1.size,R.drawable.indicator_non, R.drawable.indicator_on)

        detail_cardview_four_indicator.setItemMargin(20)
        detail_cardview_four_indicator.setAnimDuration(300)
        detail_cardview_four_indicator.createDotPanel(items1.size,R.drawable.indicator_non, R.drawable.indicator_on)

        detail_cardview_five_indicator.setItemMargin(20)
        detail_cardview_five_indicator.setAnimDuration(300)
        detail_cardview_five_indicator.createDotPanel(items1.size,R.drawable.indicator_non, R.drawable.indicator_on)

        detail_cardview_pager_one.setClipToPadding(false)
        detail_cardview_pager_one.setPadding(80,0,80,0)
        detail_cardview_pager_one.pageMargin = 40

        detail_cardview_pager_two.setClipToPadding(false)
        detail_cardview_pager_two.setPadding(80,0,80,0)
        detail_cardview_pager_two.pageMargin = 40

        detail_cardview_pager_three.setClipToPadding(false)
        detail_cardview_pager_three.setPadding(80,0,80,0)
        detail_cardview_pager_three.pageMargin = 40

        detail_cardview_pager_four.setClipToPadding(false)
        detail_cardview_pager_four.setPadding(80,0,80,0)
        detail_cardview_pager_four.pageMargin = 40

        detail_cardview_pager_five.setClipToPadding(false)
        detail_cardview_pager_five.setPadding(80,0,80,0)
        detail_cardview_pager_five.pageMargin = 40


    }


    var mOnPageChangeListener_one = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            detail_cardview_one_indicator.selectDot(position)
        }

    }

    var mOnPageChangeListener_two = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            detail_cardview_two_indicator.selectDot(position)
        }

    }

    var mOnPageChangeListener_three = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            detail_cardview_three_indicator.selectDot(position)
        }

    }

    var mOnPageChangeListener_four = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            detail_cardview_four_indicator.selectDot(position)
        }

    }

    var mOnPageChangeListener_five = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            detail_cardview_five_indicator.selectDot(position)
        }

    }


    override fun onBackPressed() {
        if (detail_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            detail_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }




    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.loginBtn -> {
                if(SharedPreference.instance!!.getPrefStringData("token")!!.isEmpty()){
                    startActivity(Intent(this, LoginActivity::class.java))
                } else{
                    ToastMaker.makeLongToast(this, "마이페이지에서 로그아웃 해주세요.")
                }
            }
            R.id.blankBtn->{
                item.isChecked = false
            }
            R.id.homeBtn -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.stroyBtn -> {
                startActivity(Intent(this, MeowBoxStoryActivity::class.java))
            }
            R.id.orderBtn -> {
                if(SharedPreference.instance!!.getPrefStringData("token")!!.isEmpty()){
                    val dialog = LoginCheckCustomDialog(this)
                    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dialog.show()
                } else{
                    if(SharedPreference.instance!!.getPrefStringData("cat_idx")!! == "-1"){
                        val dialog = CatCustomDialog(this)
                        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        dialog.show()
                    } else {
                        val intent = Intent(this, OrderWithCatInfoActivity::class.java)
                        intent.putExtra("cat_idx", SharedPreference.instance!!.getPrefStringData("cat_idx")!!)
                        startActivity(intent)
                    }
                }
            }
            R.id.reviewBtn -> {
                startActivity(Intent(this, MeowBoxReviewActivity::class.java))
            }
            R.id.myPageBtn->{
                if(SharedPreference.instance!!.getPrefStringData("token")!!.isEmpty()){
                    val dialog = LoginToMyPageCustomDialog(this)
                    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dialog.show()
                } else{
                    startActivity(Intent(this, MyPageActivity::class.java))
                }
            }
            R.id.birthDayBtn->{
                startActivity(Intent(this, MeowBoxtBirthDayStoryActivity::class.java))
            }
        }

        detail_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
