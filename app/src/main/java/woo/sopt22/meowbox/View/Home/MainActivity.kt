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
import woo.sopt22.meowbox.View.Order.OrderThirdActivity


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            home_stroy_btn->{
                Log.v("073","073")
                startActivity(Intent(this, MeowBoxStoryActivity::class.java))
            }
            home_to_detail_btn->{
                Log.v("만혁","detail")
                Log.v("만혁","묘박스 디테일")
                startActivity(Intent(this, MeowBoxDetailActivity::class.java))

                Log.v("만혁","아예 엘스")
            }
            else->{
                if(current_number == 2 || current_number == 3){
                    Log.v("만혁",current_number.toString())
                    startActivity(Intent(this, BirthdayStoryDetailActivity::class.java))
                } else{
                    Log.v("만혁","묘박스 디테일")
                    startActivity(Intent(this, MeowBoxDetailActivity::class.java))
                }
                /*Log.v("만혁","전체 엘스")
                startActivity(Intent(this, MeowBoxDetailActivity::class.java))*/
            }
        }
    }

    lateinit var mViewPager : ViewPager
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
    lateinit var main_side_bar_btn : ImageView
    lateinit var main_side_back_btn : ImageView
    lateinit var networkService: NetworkService
    var current_number : Int = 0

    fun init(){
        mViewPager = viewpager as ViewPager
        mSlidingTextView = home_cat_count as TextView
        home_stroy_btn = home_to_story_btn as ImageView
        //main_side_back_btn = side_bar_back_btn as ImageView

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
        sliding_toolbar = re as RelativeLayout
    }

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

    override fun onResume() {
        super.onResume()

        SharedPreference.instance!!.load(this)


        var headerView : View = main_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)
        var userImage : ImageView = headerView.findViewById(R.id.imageView)


        if(SharedPreference.instance!!.getPrefStringData("name")!!.isEmpty()){
            userName.text = "OO님!"
        } else {
            userName.text = SharedPreference.instance!!.getPrefStringData("name")
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
    override fun onRestart() {
        Log.v("onRestart",SharedPreference.instance!!.getPrefStringData("user_name"))
        super.onRestart()
        SharedPreference.instance!!.load(this)


        var headerView : View = main_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)
        var userImage : ImageView = headerView.findViewById(R.id.imageView)


        if(SharedPreference.instance!!.getPrefStringData("name")!!.isEmpty()){
            userName.text = "OO님!"
        } else {
            userName.text = SharedPreference.instance!!.getPrefStringData("name")
        }

        if(SharedPreference.instance!!.getPrefStringData("image_profile") == null){
            //userImage.setImageResource(R.drawable.side_bar_profile_img)
            Log.v("용범 onRestart","123")
            Glide.with(this).load(R.drawable.side_bar_profile_img).into(userImage)
        } else{
            Log.v("용범 onRestart","456")
            userImage.setImageURI(Uri.parse(SharedPreference.instance!!.getPrefStringData("image_profile")))
            Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image_profile")!!).into(userImage)
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        toolbar.bringToFront()

        //main_side_back_btn = side_bar_back_btn as ImageView
        //side_bar_back_btn.setOnClickListener(this@MainActivity)


        var headerView : View = main_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)
        var userImage : ImageView = headerView.findViewById(R.id.imageView)
        //userImage.setImageResource(R.drawable.side_bar_profile_img)

        networkService = ApplicationController.instance!!.networkService

        SharedPreference.instance!!.load(this)
        Log.v("079",SharedPreference.instance!!.getPrefStringData("image_profile"))

        if(SharedPreference.instance!!.getPrefStringData("image_profile") == null){
            Log.v("용범 onCreate","123")
            Glide.with(this).load(R.drawable.side_bar_profile_img).into(userImage)
        } else{
            Log.v("용범 onCreate","456")
            Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image_profile")!!).into(userImage)
        }
        Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image_profile")!!).into(userImage)
        home_to_detail_btn.setOnClickListener(this)

        init()
        re.setOnTouchListener(object : View.OnTouchListener{
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





        home_stroy_btn.setOnClickListener(this)
        //main_side_back_btn.setOnClickListener(this)
        var result = SharedPreference.instance!!.getPrefStringData("token")
        println("11"+SharedPreference.instance!!.getPrefStringData("token"))
        //println("cat_idx??"+SharedPreference.instance!!.getPrefStringData("cat_idx"))
        //println("11"+result!![0])

        var menu : Menu = main_nav_view.menu
        var login_menu_item : MenuItem = menu.findItem(R.id.loginBtn)
        var blank_menu_item : MenuItem = menu.findItem(R.id.blankBtn)
        var blank_menu_item2 : MenuItem = menu.findItem(R.id.blankBtn2)
        blank_menu_item.setEnabled(false)
        blank_menu_item2.setEnabled(false)
        //home_detail_btn.setImageResource(R.drawable.home_detail_btn_white)

        if(SharedPreference.instance!!.getPrefStringData("name")!!.isEmpty()){
            userName.text = "OO님!"
            login_menu_item.setTitle("로그인")
        } else {
            userName.text = SharedPreference.instance!!.getPrefStringData("name")
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



      /*  mSlidingTextView.setOnClickListener {
            ToastMaker.makeLongToast(this,mSlidingTextView.text.toString())
        }*/

        // actionBar 타이틀 가리기




        //희현카드뷰


        var imgUrl1 = "https://post-phinf.pstatic.net/MjAxNzA0MjFfMTMx/MDAxNDkyNzAxMjI0NzA3.Q_bmK_EvjtxtFpT30CNtyBsJBfGkAieooME9VDfoKHYg.nrXNY37E18mt1g6nbwDpHN7kQAwmDr9Q2RPLKWkw_2wg.JPEG/1492696692724.jpg?type=w1200" as String
        var imgUrl2 = "https://post-phinf.pstatic.net/MjAxNzA0MjFfMTc2/MDAxNDkyNzAxMjI1MDA4.IS9AxBl-5bs1-h3PbJssvfm5xmcsUAkkLMg-qIJ9KVsg.h8_rW0zPTvO74wQ5yH_K3TRAJVJUcGT6Z_hldpv_GRgg.JPEG/1492696688049.jpg?type=w1200" as String
        var imgUrl3 = "https://post-phinf.pstatic.net/MjAxNzA0MjFfMTI1/MDAxNDkyNzAxMjI1MTY0.femsgEnFQWPK7szY4kZ0_6uSgXqCaDNyAPZt5Pp-ebMg.oRMiRH-aga5cGKJc8OSOabjZv1Nf0AO7XUFGe7sVa_cg.JPEG/1492696687282.jpg?type=w1200" as String

        var items : ArrayList<CardData>
        items = ArrayList();
        items.add(CardData(R.drawable.home_main_one_img, 1))
        items.add(CardData(R.drawable.home_main_two_img,0))
        items.add(CardData(R.drawable.home_main_three_img,0))
        items.add(CardData(R.drawable.home_main_four_img,0))
        items.add(CardData(R.drawable.home_main_five_img,2))

        /*home_detail_btn.setOnClickListener {
            ToastMaker.makeLongToast(this, "dd")
        }*/

        mViewPager.setPadding(0,0,200,0)
        var madapter = CardViewAdapter(layoutInflater, items)
        mViewPager.setCurrentItem(0)
        mViewPager.adapter = madapter
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {


            }

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

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                when(position){
                    (items.size-2)->{
                        mViewPager.setPadding((200 * positionOffset).toInt(),0,200 - (200*positionOffset).toInt(),0)
                    }
                    (items.size-1)->{
                        mViewPager.setPadding(200,0,0,0)
                    }

                }

            }

        })

        madapter.setOnItemClickListener(this)





        val toggle = ActionBarDrawerToggle(
                this, main_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

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

        main_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()




        main_nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (main_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            main_drawer_layout.closeDrawer(GravityCompat.START)
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
                        val intent = Intent(this, OrderThirdActivity::class.java)
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


