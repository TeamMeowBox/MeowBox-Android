package woo.sopt22.meowbox.View.Home

import android.content.Intent

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.cardview_adapter.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.sliding_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Home.CatCountResponse
import woo.sopt22.meowbox.Model.Home.InstaCrawlingResponse
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.CustomDialog.CatCustomDialog
import woo.sopt22.meowbox.Util.CustomDialog.LoginToMyPageCustomDialog
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Login.LoginActivity
import woo.sopt22.meowbox.View.MeowBoxBirthDay.BirthdayStoryDetailActivity
import woo.sopt22.meowbox.View.MeowBoxBirthDay.MeowBoxtBirthDayStoryActivity
import woo.sopt22.meowbox.View.MeowBoxDetail.MeowBoxDetailActivity
import woo.sopt22.meowbox.View.MeowBoxReview.MeowBoxReviewActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.CircleImageView
import woo.sopt22.meowbox.View.MyPage.MyPageActivity
import woo.sopt22.meowbox.View.Order.LoginCustomDialog
import woo.sopt22.meowbox.View.Order.OrderFragmentWithCatInfo.OrderWithCatInfoActivity


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            home_to_story_btn->{
                startActivity(Intent(this, MeowBoxStoryActivity::class.java))
            }
            home_to_detail_btn->{
                Log.v("만혁","detail")
                Log.v("만혁","묘박스 디테일")
                startActivity(Intent(this, MeowBoxDetailActivity::class.java))

                Log.v("만혁","아예 엘스")
            }
            main_back_btn->{
                main_drawer_layout.closeDrawer(GravityCompat.START)
            }
            else->{
                if(current_number == 2 || current_number == 3){
                    Log.v("만혁",current_number.toString())
                    startActivity(Intent(this, BirthdayStoryDetailActivity::class.java))
                } else{
                    Log.v("만혁","묘박스 디테일")
                    startActivity(Intent(this, MeowBoxDetailActivity::class.java))
                }
            }
        }
    }

    lateinit var mSlidingTextView: TextView

    lateinit var mSlidingInstaProfile1 : CircleImageView
    lateinit var mSlidingInstaUserName1 : TextView
    lateinit var mSlidingInstaCatProfile1 : ImageView

    lateinit var mSlidingInstaProfile2 : CircleImageView
    lateinit var mSlidingInstaUserName2 : TextView
    lateinit var mSlidingInstaCatProfile2 : ImageView

    lateinit var mSlidingInstaProfile3 : CircleImageView
    lateinit var mSlidingInstaUserName3 : TextView
    lateinit var mSlidingInstaCatProfile3 : ImageView

    lateinit var mSlidingInstaProfile4 : CircleImageView
    lateinit var mSlidingInstaUserName4 : TextView
    lateinit var mSlidingInstaCatProfile4 : ImageView
    lateinit var home_stroy_btn : ImageView

    lateinit var slidinViewMain : RelativeLayout
    lateinit var sliding_toolbar : RelativeLayout
    private val PermissionRequestCode = 123
    private lateinit var managePermissions : ManagePermissions
    lateinit var networkService: NetworkService
    var current_number : Int = 0

    fun init(){
        mSlidingTextView = home_cat_count as TextView

        mSlidingInstaProfile1 = insta_profile_image1 as CircleImageView
        mSlidingInstaUserName1 = insta_user_name1 as TextView
        mSlidingInstaCatProfile1 = insta_cat_profile1 as ImageView

        mSlidingInstaProfile2 = insta_profile_image2 as CircleImageView
        mSlidingInstaUserName2 = insta_user_name2 as TextView
        mSlidingInstaCatProfile2 = insta_cat_profile2 as ImageView

        mSlidingInstaProfile3 = insta_profile_image3 as CircleImageView
        mSlidingInstaUserName3 = insta_user_name3 as TextView
        mSlidingInstaCatProfile3 = insta_cat_profile3 as ImageView

        mSlidingInstaProfile4 = insta_profile_image4 as CircleImageView
        mSlidingInstaUserName4 = insta_user_name4 as TextView
        mSlidingInstaCatProfile4 = insta_cat_profile4 as ImageView

        slidinViewMain = sliding_view_main as RelativeLayout
        sliding_toolbar = bottom_up_relative_layout as RelativeLayout

        home_to_detail_btn.setOnClickListener(this)
        home_to_story_btn.setOnClickListener(this)
    }


    // 고양이 인스타 크롤링 사진 받아오기 - 통신
    fun getInsta(){
        val instaResponse = networkService.getInstaCrawling()
        instaResponse.enqueue(object : Callback<InstaCrawlingResponse>{
            override fun onFailure(call: Call<InstaCrawlingResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<InstaCrawlingResponse>?, response: Response<InstaCrawlingResponse>?) {
                if(response!!.isSuccessful){
                    mSlidingInstaUserName1.text = response!!.body()!!.result[0].nickname
                    Glide.with(this@MainActivity).load(response!!.body()!!.result[0].profile).into(mSlidingInstaProfile1)
                    Glide.with(this@MainActivity).load(response!!.body()!!.result[0].picture).into(mSlidingInstaCatProfile1)

                    mSlidingInstaUserName2.text = response!!.body()!!.result[1].nickname
                    Glide.with(this@MainActivity).load(response!!.body()!!.result[1].profile).into(mSlidingInstaProfile2)
                    Glide.with(this@MainActivity).load(response!!.body()!!.result[1].picture).into(mSlidingInstaCatProfile2)

                    mSlidingInstaUserName3.text = response!!.body()!!.result[2].nickname
                    Glide.with(this@MainActivity).load(response!!.body()!!.result[2].profile).into(mSlidingInstaProfile3)
                    Glide.with(this@MainActivity).load(response!!.body()!!.result[2].picture).into(mSlidingInstaCatProfile3)

                    mSlidingInstaUserName4.text = response!!.body()!!.result[3].nickname
                    Glide.with(this@MainActivity).load(response!!.body()!!.result[3].profile).into(mSlidingInstaProfile4)
                    Glide.with(this@MainActivity).load(response!!.body()!!.result[3].picture).into(mSlidingInstaCatProfile4)
                }
            }

        })
    }

    // 고양이 Count 받아오기 - 통신
    fun getCatCount(){
        val countResponse = networkService.getCatCount()
        countResponse.enqueue(object : Callback<CatCountResponse>{
            override fun onFailure(call: Call<CatCountResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<CatCountResponse>?, response: Response<CatCountResponse>?) {
                if(response!!.isSuccessful){
                    mSlidingTextView.text = response!!.body()!!.result+"냥이"
                    Log.v("7979",response!!.body()!!.result)
                }

            }

        })
    }


    // 처음 들어오거나 다른 액티비티로 갔다가 올 때 정보를 확인해서 SharedPreference에 저장된 데이터 불러오도록!!
    fun checkInfo(){

        SharedPreference.instance!!.load(this)

        var headerView : View = main_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)
        var userImage : ImageView = headerView.findViewById(R.id.imageView)


        if(SharedPreference.instance!!.getPrefStringData("name")!!.isEmpty()){
            userName.text = "OO님!"
        } else {
            userName.text = SharedPreference.instance!!.getPrefStringData("name") + " 님"
        }

        if(SharedPreference.instance!!.getPrefStringData("image_profile") == null){
            //userImage.setImageResource(R.drawable.side_bar_profile_img)
            Log.v("용범 onResume","123")
            Glide.with(this).load(R.drawable.side_bar_profile_img).into(userImage)
        } else{
            Log.v("용범 onResume","456")
            userImage.setImageURI(Uri.parse(SharedPreference.instance!!.getPrefStringData("image_profile")))
            Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image_profile")!!).into(userImage)
        }

    }

    override fun onResume() {
        super.onResume()
        checkInfo()

    }
    override fun onRestart() {
        Log.v("onRestart",SharedPreference.instance!!.getPrefStringData("user_name"))
        super.onRestart()
        checkInfo()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // toolbar의 타이을틀 가리고 기능도 없애는 부분
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        toolbar.bringToFront()

        /*FIXME
         * 기본 NavigationBar를 지우고 내가 원하는 이미지를 넣기 위해서 감추고 원하는 이미지를 넣는다.
         * 그리고 내가 넣은 이미지가 원래 NavigationBar처럼 똑같이 동작하도록 코드를 넣어주었다.
         * ActionBarDrawerToggle 이라는 함수를 통해 main_drawer_layout을 toggle이라는 이름의 객체로 가져온다.
         * 그리고 IndicatorEnabled를 false로 줌으로써 보이지 않게 하고
         * */
        val toggle = ActionBarDrawerToggle(
                this, main_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        toggle.setDrawerIndicatorEnabled(false)
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_white, applicationContext!!.getTheme())

        //val bitmap = (drawable as BitmapDrawable).bitmap
        //val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (main_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                    main_drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    main_drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })

        main_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        SharedPreference.instance!!.load(this)
        networkService = ApplicationController.instance!!.networkService

        var headerView : View = main_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)
        var userImage : ImageView = headerView.findViewById(R.id.imageView)
        var main_back_button : ImageView = headerView.findViewById(R.id.main_back_btn)
        userImage.setImageResource(R.drawable.side_bar_profile_img)
        main_back_button.setOnClickListener(this)


        checkInfo()

        init()
        // 터치 리스너를 달아준다.
        bottom_up_relative_layout.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when(event!!.action){
                    MotionEvent.ACTION_UP,MotionEvent.ACTION_MOVE->{
                        //Log.v("8989","8989")
                        getInsta()
                        getCatCount()
                        main_sliding_scroll.fullScroll(ScrollView.FOCUS_UP)
                    }
                    MotionEvent.ACTION_DOWN->{
                        //Log.v("9898","9898")
                        //getInsta()
                        //ToastMaker.makeLongToast(this@MainActivity, "dd")
                        main_sliding_scroll.fullScroll(ScrollView.FOCUS_UP)
                    }

                }
                return true
            }


        })


        //main_side_back_btn.setOnClickListener(this)
        var result = SharedPreference.instance!!.getPrefStringData("token")
        println("11"+SharedPreference.instance!!.getPrefStringData("token"))
        //println("cat_idx??"+SharedPreference.instance!!.getPrefStringData("cat_idx"))
        //println("11"+result!![0])

        var menu : Menu = main_nav_view.menu
        var login_menu_item : MenuItem = menu.findItem(R.id.loginBtn)
        var blank_menu_item : MenuItem = menu.findItem(R.id.blankBtn)
        var blank_menu_item2 : MenuItem = menu.findItem(R.id.blankBtn2)
        var home_menu_item : MenuItem = menu.findItem(R.id.homeBtn)
        home_menu_item.setEnabled(false)
        blank_menu_item.setEnabled(false)
        blank_menu_item2.setEnabled(false)
        //home_detail_btn.setImageResource(R.drawable.home_detail_btn_white)

        if(SharedPreference.instance!!.getPrefStringData("name")!!.isEmpty()){
            userName.text = "OO님!"
            login_menu_item.setTitle("로그인")
        } else {
            userName.text = SharedPreference.instance!!.getPrefStringData("name") + "님"
            login_menu_item.setTitle("")
            login_menu_item.setEnabled(false)
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
        }


        //허가 요청
        val list = listOf<String>(
                android.Manifest.permission.INTERNET
        )

        managePermissions = ManagePermissions(this, list, PermissionRequestCode)

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                managePermissions.checkPermissions()



        //희현카드뷰


        var items : ArrayList<CardData>
        items = ArrayList();
        items.add(CardData(R.drawable.home_main_one_img, 1))
        items.add(CardData(R.drawable.home_main_two_img,0))
        items.add(CardData(R.drawable.home_main_three_img,0))
        items.add(CardData(R.drawable.home_main_four_img,0))
        items.add(CardData(R.drawable.home_main_five_img,2))

        main_viewpager.setPadding(0,0,200,0)
        var madapter = CardViewAdapter(layoutInflater, items)
        main_viewpager.setCurrentItem(0)
        main_viewpager.adapter = madapter
        main_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {


            }

            // 이 함수를 통해서 선택된 position 값에 따른 네비게이션 바의 색깔과 툴바의 색을 변경할 수 있다.
            // 그리고 1,2에서의 상세보기와 3,4에서의 상세보기 버튼을 다른 기능을 할 수 있도록 구현할 수 있다.
            override fun onPageSelected(position: Int) {
                when(position){
                    0->{
                        current_number = position
                        main_toolbar_image.setImageResource(R.drawable.logo_white)
                        home_detail_btn.setImageResource(R.drawable.home_detail_btn_white)
                        val toggle = ActionBarDrawerToggle(
                                this@MainActivity, main_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

                        toggle.setDrawerIndicatorEnabled(false)
                        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_white, applicationContext!!.getTheme())

                        val bitmap = (drawable as BitmapDrawable).bitmap
                        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

                        toggle.setHomeAsUpIndicator(drawable)
                        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
                            override fun onClick(v: View?) {
                                if (main_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                                    main_drawer_layout.closeDrawer(GravityCompat.START)
                                } else {
                                    main_drawer_layout.openDrawer(GravityCompat.START)
                                }
                            }
                        })

                    }
                    1,2,3->{
                        current_number = position
                        main_toolbar_image.setImageResource(R.drawable.logo_pink)
                        home_detail_btn.setImageResource(R.drawable.home_detail_btn_gray)
                        val toggle = ActionBarDrawerToggle(
                                this@MainActivity, main_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

                        toggle.setDrawerIndicatorEnabled(false)
                        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_black, applicationContext!!.getTheme())

                        val bitmap = (drawable as BitmapDrawable).bitmap
                        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

                        toggle.setHomeAsUpIndicator(drawable)
                        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
                            override fun onClick(v: View?) {
                                if (main_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                                    main_drawer_layout.closeDrawer(GravityCompat.START)
                                } else {
                                    main_drawer_layout.openDrawer(GravityCompat.START)
                                }
                            }
                        })
                    }
                    4->{
                        current_number = position
                        main_toolbar_image.setImageResource(R.drawable.logo_white)
                        home_detail_btn.setImageResource(R.drawable.home_detail_btn_gray)
                        val toggle = ActionBarDrawerToggle(
                                this@MainActivity, main_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

                        toggle.setDrawerIndicatorEnabled(false)
                        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_black, applicationContext!!.getTheme())

                        val bitmap = (drawable as BitmapDrawable).bitmap
                        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

                        toggle.setHomeAsUpIndicator(drawable)
                        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
                            override fun onClick(v: View?) {
                                if (main_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                                    main_drawer_layout.closeDrawer(GravityCompat.START)
                                } else {
                                    main_drawer_layout.openDrawer(GravityCompat.START)
                                }
                            }
                        })
                    }
                }

            }

            // 화면의 일부만 보이게 하기 위해서 ViewPager의 함수인 onpageScrolled에서 postion과 Offset을 건드렸다.
            /*FIXME
            * positionOffset이란 해당 포지션의 page의 변위차를 의미한다.
            * */
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                when(position){
                    (items.size-2)->{
                        //Log.v("12",positionOffset.toString())
                        Log.v("12",(200*positionOffset).toString())
                        main_viewpager.setPadding((200*positionOffset).toInt(),0,200 - (200*positionOffset).toInt(),0)
                        Log.v("33",(200-(200*positionOffset)).toString())
                    }
                    (items.size-1)->{
                        Log.v("44",positionOffset.toString())
                        main_viewpager.setPadding(200,0,0,0)
                    }

                }

            }

        })

        madapter.setOnItemClickListener(this)
        main_nav_view.setNavigationItemSelectedListener(this)
    }

    // 뒤로가기 버튼
    override fun onBackPressed() {
        if (main_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            main_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    // Navigation Bar에 있는 아이템을 선택했을 때의 클릭 리스너 부분
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
                ToastMaker.makeLongToast(this, "홈 화면입니다.")
            }
            R.id.stroyBtn -> {
                startActivity(Intent(this, MeowBoxStoryActivity::class.java))
            }
            R.id.orderBtn -> {
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
                        val intent = Intent(this, OrderWithCatInfoActivity::class.java)
                        intent.putExtra("cat_idx",SharedPreference.instance!!.getPrefStringData("cat_idx")!!)
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
                startActivity(Intent(this,MeowBoxtBirthDayStoryActivity::class.java))
            }


        }
        main_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode){
            PermissionRequestCode ->{
                val isPermissionsGranted = managePermissions.processPermissionsResult(requestCode, permissions as Array<String>, grantResults)
                if(isPermissionsGranted){
                    // Do the task now
                    //ToastMaker.makeLongToast(this,"Permissions granted.")
                }else{
                    ToastMaker.makeLongToast(this,"Permissions denied.")
                }
                return
            }
        }
    }
}


