package woo.sopt22.meowbox.View.Order

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
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
import kotlinx.android.synthetic.main.activity_order_third.*
import kotlinx.android.synthetic.main.app_bar_order_third.*
import kotlinx.android.synthetic.main.content_order_third.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity

class OrderThirdActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_third)
        setSupportActionBar(toolbar)


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

        // 아래 코드 해결해야함
        //order_third_nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (order_third_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            order_third_drawer_layout.closeDrawer(GravityCompat.START)
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
