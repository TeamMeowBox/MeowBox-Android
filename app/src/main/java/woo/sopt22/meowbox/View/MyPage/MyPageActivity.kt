package woo.sopt22.meowbox.View.MyPage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_my_page.*
import kotlinx.android.synthetic.main.app_bar_my_page.*

import woo.sopt22.meowbox.R
import kotlinx.android.synthetic.main.content_my_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.MyPageMain.MyPageYes
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.Util.CustomDialog.CatCustomDialog
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.Login.LoginActivity
import woo.sopt22.meowbox.View.MeowBoxBirthDay.MeowBoxtBirthDayStoryActivity
import woo.sopt22.meowbox.View.MeowBoxReview.MeowBoxReviewActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.FAQ.QuestionActivity
import woo.sopt22.meowbox.View.MyPage.OrderHistory.OrderHistoryActivity
import woo.sopt22.meowbox.View.MyPage.ProgressBar.StateProgressBar
import woo.sopt22.meowbox.View.MyPage.Setting.MyPageSettingActivity
import woo.sopt22.meowbox.View.MyPage.Suggest.MyPageSuggestActivity
import woo.sopt22.meowbox.View.Order.LoginCustomDialog
import woo.sopt22.meowbox.View.Order.OrderThirdActivity

class MyPageActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            mypage_setting -> {
                val intent = Intent(applicationContext, MySettingActivity::class.java)
                startActivity(intent);
            }
            mypage_suggest_btn->{
                startActivity(Intent(this, MyPageSuggestActivity::class.java))
            }

            mypage_setting_btn->{
                startActivity(Intent(this, MyPageSettingActivity::class.java))
            }
            mypage_qna_btn->{
                startActivity(Intent(this, QuestionActivity::class.java))
            }

            mypage_order_btn->{

                startActivity(Intent(this, OrderHistoryActivity::class.java))
            }
            story_order_btn->{
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
        }

    }

    //var descriptionData = arrayOf("1box")

    var tmpMaxNum : Int = 0
    var tmpCurrentNum : Int = 0
    lateinit var tmpStringMax : String
    lateinit var tmpStringCurrent : String
    lateinit var myPageTextImg : ImageView
    lateinit var myPageTextImgString : String
    lateinit var storyOrderBtn : ImageView

    lateinit var mypageVisibleProgess : RelativeLayout
    lateinit var mypageVisibleText : RelativeLayout

    lateinit var mypage_to_suggest_btn : LinearLayout
    lateinit var mypage_to_setting_btn : LinearLayout
    lateinit var mypage_to_qna_btn : LinearLayout
    lateinit var mypage_to_history_btn : LinearLayout
    lateinit var mypageVisibleBoxLeftBox : TextView
    lateinit var mypageVisibleBoxGetBox : TextView
    lateinit var re : Regex
    lateinit var profileImage : ImageView
    // Edit by 승우 : 민형이꺼 코드 추가




    lateinit var networkService: NetworkService
    lateinit var myPageYes: MyPageYes
    companion object {
        lateinit var stateProgressBar : StateProgressBar
    }

    fun init(){
        Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image")).into(mypage_profile_img)
        Log.v("윤선",SharedPreference.instance!!.getPrefStringData("ima\nge"))
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        networkService = ApplicationController.instance.networkService
        SharedPreference.instance!!.load(this)

        init()




        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
        }

        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)


        var headerView : View = mypage_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)
        var userImage : ImageView = headerView.findViewById(R.id.imageView)
        userImage.setImageResource(R.drawable.side_bar_profile_img)

        if(SharedPreference.instance!!.getPrefStringData("image_profile") == null){
            //userImage.setImageResource(R.drawable.side_bar_profile_img)
            Log.v("용범","123")
            Glide.with(this).load(R.drawable.side_bar_profile_img).into(userImage)
        } else{
            Log.v("용범","456")
            //userImage.setImage(SharedPreference.instance!!.getPrefStringData("image"))
            Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image_profile")!!).into(userImage)
        }



        profileImage = mypage_profile_img as ImageView

        //var imgUrlex = "https://www.petmd.com/sites/default/files/petmd-cat-happy.jpg" as String
        //Glide.with(profileImage).load(imgUrlex).into(profileImage)

        mypage_to_suggest_btn = mypage_suggest_btn as LinearLayout
        mypage_suggest_btn.setOnClickListener(this)

        mypage_to_setting_btn = mypage_setting_btn as LinearLayout
        mypage_setting_btn.setOnClickListener(this)

        mypage_to_qna_btn = mypage_qna_btn as LinearLayout
        mypage_qna_btn.setOnClickListener(this)

        mypage_to_history_btn = mypage_order_btn as LinearLayout
        mypage_order_btn.setOnClickListener(this)


        mypageVisibleProgess = mypage_visiblebox_progress as RelativeLayout
        mypageVisibleText = mypage_visiblebox_text as RelativeLayout




        tmpStringMax = "6박스"
        tmpStringCurrent = "6박스"
        stateProgressBar = your_state_progress_bar_id as StateProgressBar
        getMyPageYes();




        /*re = Regex("[^0-9]")
        tmpMaxNum = re.replace(tmpStringMax, "").toInt()
        tmpCurrentNum = re.replace(tmpStringCurrent, "").toInt()


        stateProgressBar.setMaxStateNumber(tmpMaxNum);
        stateProgressBar.setCurrentStateNumber(tmpCurrentNum);

        mypageVisibleBoxLeftBox = mypage_visiblebox_progress_leftbox as TextView
        mypageVisibleBoxGetBox = mypage_visiblebox_progress_getbox as TextView

        mypageVisibleBoxLeftBox.setText(tmpStringMax)
        mypageVisibleBoxGetBox.setText(tmpStringCurrent)*/



        val toggle = ActionBarDrawerToggle(
                this, mypage_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mypage_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        toggle.setDrawerIndicatorEnabled(false)
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_black, applicationContext!!.getTheme())

        val bitmap = (drawable as BitmapDrawable).bitmap
        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (mypage_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                    mypage_drawer_layout.closeDrawer(GravityCompat.START)
                    getMyPageYes()
                } else {
                    mypage_drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })

        var menu : Menu = mypage_nav_view.menu
        var login_menu_item : MenuItem = menu.findItem(R.id.loginBtn)
        var blank_menu_item : MenuItem = menu.findItem(R.id.blankBtn)
        var blank_menu_item2 : MenuItem = menu.findItem(R.id.blankBtn2)
        blank_menu_item.setEnabled(false)
        login_menu_item.setEnabled(false)
        blank_menu_item2.setEnabled(false)
        login_menu_item.setTitle("")

        mypage_nav_view.setNavigationItemSelectedListener(this)

        mypage_setting.setOnClickListener(this)
    }


    override fun onResume() {
        super.onResume()

        getMyPageYes()

        SharedPreference.instance!!.load(this)


        var headerView : View = mypage_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)
        var userImage : ImageView = headerView.findViewById(R.id.imageView)
        //userImage.setImageResource(R.drawable.side_bar_profile_img)
        Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image_profile")).into(userImage)

        if(SharedPreference.instance!!.getPrefStringData("image_profile") == null){
            //userImage.setImageResource(R.drawable.side_bar_profile_img)
            Log.v("승우 onResume","123")
            Glide.with(this).load(R.drawable.side_bar_profile_img).into(userImage)
        } else{
            Log.v("승우 onResume","456")
            //Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image_profile")).into(mypage_profile_img)
            Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image_profile")!!).into(userImage)
        }

        if(SharedPreference.instance!!.getPrefStringData("name")!!.isEmpty()){
            userName.text = "OO님!"
        } else {
            userName.text = SharedPreference.instance!!.getPrefStringData("name")
            if(SharedPreference.instance!!.getPrefStringData("cat_name")!!.isEmpty()){
                mypage_name_text1.text = SharedPreference.instance!!.getPrefStringData("name")+" 님"
            } else{
                if(SharedPreference.instance!!.getPrefStringData("cat_idx") == "-1")
                {
                    mypage_name_text1.text = " 집사 "+SharedPreference.instance!!.getPrefStringData("name")+" 님"

                } else{
                    mypage_name_text1.text = SharedPreference.instance!!.getPrefStringData("cat_name")!!+" 집사 "+SharedPreference.instance!!.getPrefStringData("name")+" 님"
                }
            }

        }

    }

    fun getMyPageYes(){
        val tmpResponse = networkService.getMyPageYes(SharedPreference.instance!!.getPrefStringData("token")!!)

        //val tmpResponse = networkService.getMyPageYes("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IuyEnOyXsOydtCDstZzqs6Ag6riw7Jqp7J2064qUIOqwgGRkIiwidXNlcl9pZHgiOjE5MywiaWF0IjoxNTMxMDI1NzQ5LCJleHAiOjE1MzM2MTc3NDl9.OlyBgwTWCeG76qAi1f8sV37MzluNJXe4PPqvUpK2mzA")
        Log.v("98","들어오니?")
        tmpResponse.enqueue(object : Callback<MyPageYes>{
            override fun onFailure(call: Call<MyPageYes>?, t: Throwable?) {
                Log.v("98","안됭")
            }

            override fun onResponse(call: Call<MyPageYes>?, response: Response<MyPageYes>?) {
                if(response!!.isSuccessful){

                    var tmpFlag = response!!.body()!!.result.flag
                    if(tmpFlag == 1){
                        mypageVisibleProgess.show()
                        mypageVisibleText.hide()





                        tmpStringMax = response!!.body()!!.result.ticket
                        tmpStringCurrent = response!!.body()!!.result.use
                        Log.v("93",tmpStringCurrent)
                        Log.v("94", tmpStringMax)

                        re = Regex("[^0-9]")
                        tmpMaxNum = re.replace(tmpStringMax, "").toInt()
                        tmpCurrentNum = re.replace(tmpStringCurrent, "").toInt()
                        Log.v("95", tmpMaxNum.toString())

                        stateProgressBar.setMaxStateNumber(tmpMaxNum);
                        stateProgressBar.setCurrentStateNumber(tmpCurrentNum);

                        mypageVisibleBoxLeftBox = mypage_visiblebox_progress_leftbox as TextView
                        mypageVisibleBoxGetBox = mypage_visiblebox_progress_getbox as TextView

                        mypageVisibleBoxLeftBox.setText(tmpStringMax)
                        mypageVisibleBoxGetBox.setText(tmpStringCurrent)
                        var descriptionData = Array(tmpMaxNum,{ i -> (i+1).toString()})
                        stateProgressBar.setStateDescriptionData(descriptionData)

                        Glide.with(this@MyPageActivity).load(response!!.body()!!.result.image_profile).into(profileImage)
                        SharedPreference.instance!!.setPrefData("image_profile",response!!.body()!!.result.image_profile)
                        var headerView : View = mypage_nav_view.getHeaderView(0)
                        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)
                        var userImage : ImageView = headerView.findViewById(R.id.imageView)
                        userImage.setImageURI(Uri.parse(response!!.body()!!.result.image_profile))
                        Glide.with(this@MyPageActivity).load(response!!.body()!!.result.image_profile).into(userImage)




                    }
                    else{
                        mypageVisibleProgess.hide()
                        mypageVisibleText.show()
                        myPageTextImgString = response!!.body()!!.result.sendImage

                        storyOrderBtn = story_order_btn as ImageView
                        storyOrderBtn.setOnClickListener(this@MyPageActivity)

                        myPageTextImg = mypage_visiblebox_text_img as ImageView
                        Glide.with(this@MyPageActivity).load(myPageTextImgString).into(myPageTextImg);
                        Glide.with(this@MyPageActivity).load(response!!.body()!!.result.image_profile).into(profileImage)
                        SharedPreference.instance!!.setPrefData("image_profile",response!!.body()!!.result.image_profile)

                    }
                } else{
                    Log.v("96",response!!.message())
                }

            }

        })

    }

    override fun onBackPressed() {
        if (mypage_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            mypage_drawer_layout.closeDrawer(GravityCompat.START)
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
                    ToastMaker.makeLongToast(this, "설정에서 로그아웃 해주세요!")
                }
            }
            R.id.homeBtn -> {
                var intent =  Intent(this, MainActivity::class.java)
                /*intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)*/
                startActivity(intent)
                finish()
            }
            R.id.stroyBtn -> {
                startActivity(Intent(this, MeowBoxStoryActivity::class.java))
                finish()
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
                var intent = Intent(this, MeowBoxReviewActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.myPageBtn->{

            }
            R.id.birthDayBtn->{
                startActivity(Intent(this, MeowBoxtBirthDayStoryActivity::class.java))
            }
        }

        mypage_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun View.show(){
        visibility = View.VISIBLE
    }

    fun View.hide(){
        visibility = View.GONE
    }



}
