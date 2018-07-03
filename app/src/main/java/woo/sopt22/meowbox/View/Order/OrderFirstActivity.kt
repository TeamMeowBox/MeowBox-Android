package woo.sopt22.meowbox.View.Order

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_order_first.*
import kotlinx.android.synthetic.main.app_bar_order_first.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity
import android.support.v4.content.res.ResourcesCompat
import android.graphics.drawable.Drawable
import android.view.View
import android.R.attr.bitmap
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.support.v4.app.Fragment
import android.widget.FrameLayout
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.content_order_first.*
import woo.sopt22.meowbox.View.Order.Fragment.OrderFirstFragment


class OrderFirstActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            order_next_btn->{
                startActivity(Intent(this, OrderSecondActivity::class.java))
            }
        }
    }


    companion object {
        var mContext: Context?=null
    }
    lateinit var order_next_btn : RelativeLayout
    lateinit var container : FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_first)
        setSupportActionBar(toolbar)
        mContext = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        /*order_next_btn = order_name_next_btn as RelativeLayout
        order_next_btn.setOnClickListener(this)*/

        container = order_framge as FrameLayout

        replaceFragment(OrderFirstFragment())


        val toggle = ActionBarDrawerToggle(
                this, order_first_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)



        toggle.setDrawerIndicatorEnabled(false)
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_black, applicationContext!!.getTheme())

        val bitmap = (drawable as BitmapDrawable).bitmap
        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (order_first_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                    order_first_drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    order_first_drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })


        order_first_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        order_first_nav_view.setNavigationItemSelectedListener(this)
    }

    fun addFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.order_framge, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.order_framge, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (order_first_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            order_first_drawer_layout.closeDrawer(GravityCompat.START)
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

        order_first_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
