package woo.sopt22.meowbox.View.MyPage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_my_page.*
import kotlinx.android.synthetic.main.app_bar_my_page.*

import woo.sopt22.meowbox.R
import kotlinx.android.synthetic.main.content_my_page.*
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.MeowBoxReview.MeowBoxReviewActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.Order.OrderFirstActivity

class MyPageActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0){
            mypage_setting -> {
                val intent = Intent(applicationContext, MySettingActivity::class.java)
                startActivity(intent);
            }


        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        var profileImage = mypage_profile_img as ImageView

        var imgUrlex = "https://www.petmd.com/sites/default/files/petmd-cat-happy.jpg" as String
        Glide.with(profileImage).load(imgUrlex).into(profileImage)








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
                } else {
                    mypage_drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })

        mypage_nav_view.setNavigationItemSelectedListener(this)

        mypage_setting.setOnClickListener(this)
    }

    override fun onBackPressed() {
        if (mypage_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            mypage_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.loginBtn -> {
                // Handle the camera action
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
                startActivity(Intent(this, OrderFirstActivity::class.java))
                finish()
            }
            R.id.reviewBtn -> {
                var intent = Intent(this, MeowBoxReviewActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.myPageBtn->{

            }
        }

        mypage_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
