package woo.sopt22.meowbox.View.MeowBoxBirthDay

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
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
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_meow_boxt_birth_day_story.*
import kotlinx.android.synthetic.main.app_bar_meow_boxt_birth_day_story.*
import kotlinx.android.synthetic.main.content_meow_boxt_birth_day_story.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.CustomDialog.CatCustomDialog
import woo.sopt22.meowbox.Util.CustomDialog.LoginToMyPageCustomDialog
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.Login.LoginActivity
import woo.sopt22.meowbox.View.MeowBoxReview.MeowBoxReviewActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity
import woo.sopt22.meowbox.View.Order.LoginCustomDialog
import woo.sopt22.meowbox.View.Order.OrderThirdActivity

class MeowBoxtBirthDayStoryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meow_boxt_birth_day_story)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        toolbar.bringToFront()


        //Glide.with(this).load(R.drawable.webtoon_img).into(birth_web)toon)
        //Glide.with(this).load(R.drawable.webtoon_img).apply(RequestOptions().fitCenter()).into(birth_webtoon)


        var headerView : View = birth_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)
        var userImage : ImageView = headerView.findViewById(R.id.imageView)
        userImage.setImageResource(R.drawable.side_bar_profile_img)


        SharedPreference.instance!!.load(this)
        Log.v("079",SharedPreference.instance!!.getPrefStringData("image"))

        if(SharedPreference.instance!!.getPrefStringData("image") == null){
            //userImage.setImageResource(R.drawable.side_bar_profile_img)
            Log.v("용범","123")
            Glide.with(this).load(R.drawable.side_bar_profile_img).into(userImage)
        } else{
            Log.v("용범","456")
            Glide.with(this).load(SharedPreference.instance!!.getPrefStringData("image")!!).into(userImage)
        }

        var menu : Menu = birth_nav_view.menu
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
            login_menu_item.setTitle("")
            login_menu_item.setEnabled(false)
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        val toggle = ActionBarDrawerToggle(
                this, birth_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        toggle.setDrawerIndicatorEnabled(false)
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_black, applicationContext!!.getTheme())

        val bitmap = (drawable as BitmapDrawable).bitmap
        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (birth_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                    birth_drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    birth_drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })
        birth_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        birth_nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (birth_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            birth_drawer_layout.closeDrawer(GravityCompat.START)
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
                ToastMaker.makeLongToast(this, "생일 축하! 페이지입니다.")
            }
        }

        birth_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
