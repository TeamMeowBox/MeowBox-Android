package woo.sopt22.meowbox.View.Order

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
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
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_order_third.*
import kotlinx.android.synthetic.main.app_bar_order_third.*
import kotlinx.android.synthetic.main.content_order_third.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity

class OrderThirdActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            order_third_previeous_btn->{
                finish()
            }
            order_third_next_btn->{
                startActivity(Intent(this, OrderFourActivity::class.java))
            }
        }
    }


    lateinit var order_third_previeous_btn : RelativeLayout
    lateinit var order_third_next_btn : RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_third)
        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)


        order_third_previeous_btn = order_period_previous as RelativeLayout
        order_third_next_btn = order_period_next as RelativeLayout

        order_third_previeous_btn.setOnClickListener(this)
        order_third_next_btn.setOnClickListener(this)


        val toggle = ActionBarDrawerToggle(
                this, order_third_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        order_third_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        toggle.setDrawerIndicatorEnabled(false)
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_black, applicationContext!!.getTheme())

        val bitmap = (drawable as BitmapDrawable).bitmap
        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (order_third_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                    order_third_drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    order_third_drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })

        order_third_nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (order_third_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            order_third_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }




    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.loginBtn -> {
                // Handle the camera action
            }
            R.id.homeBtn -> {
                var intent  =  Intent(this, MainActivity::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
            R.id.stroyBtn -> {
                startActivity(Intent(this, MeowBoxStoryActivity::class.java))
                finish()
            }
            R.id.orderBtn -> {
                /*  startActivity(Intent(this, OrderFirstActivity::class.java))
                  finish()*/
            }
            R.id.reviewBtn -> {
                //startActivity(Intent(this, MeowBoxReviewActivity::class.java))
            }
            R.id.myPageBtn->{
                startActivity(Intent(this, MyPageActivity::class.java))
                finish()
            }
        }

        order_third_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
