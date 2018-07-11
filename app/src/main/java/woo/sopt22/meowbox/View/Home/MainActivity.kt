package woo.sopt22.meowbox.View.Home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_my_page.*
import kotlinx.android.synthetic.main.activity_order_first.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_my_page.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.sliding_layout.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.CustomDialog.CatCustomDialog
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Login.LoginActivity
import woo.sopt22.meowbox.View.Main2Activity
import woo.sopt22.meowbox.View.MeowBoxDetail.MeowBoxDetailActivity
import woo.sopt22.meowbox.View.MeowBoxReview.MeowBoxReviewActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity
import woo.sopt22.meowbox.View.Order.LoginCustomDialog
import woo.sopt22.meowbox.View.Order.OrderFirstActivity
import woo.sopt22.meowbox.View.Order.OrderThirdActivity
import java.util.jar.Manifest


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            home_stroy_btn->{
                startActivity(Intent(this, MeowBoxStoryActivity::class.java))
            }
            else->{
                startActivity(Intent(this, MeowBoxDetailActivity::class.java))
            }

        }
    }

    lateinit var mViewPager : ViewPager
    lateinit var mSlidingTextView: TextView
    lateinit var home_stroy_btn : ImageView
    //lateinit var home_detail_btn : ImageView
    var cag_flag : Boolean = false

    private val PermissionRequestCode = 123
    private lateinit var managePermissions : ManagePermissions
    lateinit var main_side_bar_btn : ImageView

    fun init(){
        mViewPager = viewpager as ViewPager
        mSlidingTextView = home_cat_count as TextView
        home_stroy_btn = home_to_story_btn as ImageView
        //home_detail_btn = home_to_detail_btn as ImageView
    }

    override fun onRestart() {
        Log.v("onRestart",SharedPreference.instance!!.getPrefStringData("user_name"))
        super.onRestart()
        SharedPreference.instance!!.load(this)


        var headerView : View = main_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)

        if(SharedPreference.instance!!.getPrefStringData("name")!!.isEmpty()){
            userName.text = "OO님!"
        } else {
            userName.text = SharedPreference.instance!!.getPrefStringData("name")
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        toolbar.bringToFront()

        var headerView : View = main_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)




        init()
        home_stroy_btn.setOnClickListener(this)
        //home_detail_btn.setOnClickListener(this)
        SharedPreference.instance!!.load(this)
        var result = SharedPreference.instance!!.getPrefStringData("token")
        println("11"+SharedPreference.instance!!.getPrefStringData("token"))
        println("cat_idx??"+SharedPreference.instance!!.getPrefStringData("cat_idx"))
        //println("11"+result!![0])

        var menu : Menu = main_nav_view.menu
        var login_menu_item : MenuItem = menu.findItem(R.id.loginBtn)
        var blank_menu_item : MenuItem = menu.findItem(R.id.blankBtn)
        var blank_menu_item2 : MenuItem = menu.findItem(R.id.blankBtn2)
        blank_menu_item.setEnabled(false)
        blank_menu_item2.setEnabled(false)

        if(SharedPreference.instance!!.getPrefStringData("name")!!.isEmpty()){
            userName.text = "OO님!"
            login_menu_item.setTitle("로그인")
        } else {
            userName.text = SharedPreference.instance!!.getPrefStringData("name")
            login_menu_item.setTitle("로그아웃")
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        window.statusBarColor = Color.BLACK


        //허가 요청
        val list = listOf<String>(
                android.Manifest.permission.INTERNET
        )

        managePermissions = ManagePermissions(this, list, PermissionRequestCode)

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                managePermissions.checkPermissions()



        mSlidingTextView.setOnClickListener {
            ToastMaker.makeLongToast(this,mSlidingTextView.text.toString())
        }

        // actionBar 타이틀 가리기




        //희현카드뷰


        var imgUrl1 = "https://post-phinf.pstatic.net/MjAxNzA0MjFfMTMx/MDAxNDkyNzAxMjI0NzA3.Q_bmK_EvjtxtFpT30CNtyBsJBfGkAieooME9VDfoKHYg.nrXNY37E18mt1g6nbwDpHN7kQAwmDr9Q2RPLKWkw_2wg.JPEG/1492696692724.jpg?type=w1200" as String
        var imgUrl2 = "https://post-phinf.pstatic.net/MjAxNzA0MjFfMTc2/MDAxNDkyNzAxMjI1MDA4.IS9AxBl-5bs1-h3PbJssvfm5xmcsUAkkLMg-qIJ9KVsg.h8_rW0zPTvO74wQ5yH_K3TRAJVJUcGT6Z_hldpv_GRgg.JPEG/1492696688049.jpg?type=w1200" as String
        var imgUrl3 = "https://post-phinf.pstatic.net/MjAxNzA0MjFfMTI1/MDAxNDkyNzAxMjI1MTY0.femsgEnFQWPK7szY4kZ0_6uSgXqCaDNyAPZt5Pp-ebMg.oRMiRH-aga5cGKJc8OSOabjZv1Nf0AO7XUFGe7sVa_cg.JPEG/1492696687282.jpg?type=w1200" as String

        var items : ArrayList<CardData>
        items = ArrayList();
        items.add(CardData(imgUrl1))
        items.add(CardData(imgUrl2))
        items.add(CardData(imgUrl3))
        items.add(CardData(imgUrl1))
        items.add(CardData(imgUrl2))

        /*home_detail_btn.setOnClickListener {
            ToastMaker.makeLongToast(this, "dd")
        }*/

        mViewPager.setPadding(0,0,100,0)
        var madapter = CardViewAdapter(layoutInflater, items)
        mViewPager.adapter = madapter
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {


            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                when(position){
                    (items.size-2)->{
                        mViewPager.setPadding((100 * positionOffset).toInt(),0,100 - (100*positionOffset).toInt(),0)
                    }
                    (items.size-1)->{
                        mViewPager.setPadding(100,0,0,0)
                    }

                }

            }

        })

        madapter.setOnItemClickListener(this)





        val toggle = ActionBarDrawerToggle(
                this, main_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

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
                    ToastMaker.makeLongToast(this,"로그인 해주세요.")
                } else{
                    startActivity(Intent(this, MyPageActivity::class.java))
                }
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


