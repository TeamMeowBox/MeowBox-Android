package woo.sopt22.meowbox.View.Order

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.media.SoundPool
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
import android.widget.*
import kotlinx.android.synthetic.main.activity_order_second.*
import kotlinx.android.synthetic.main.app_bar_order_second.*
import kotlinx.android.synthetic.main.content_order_second.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity

class OrderSecondActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            order_second_previeous_btn->{
                finish()
            }
            order_second_next_btn->{
                startActivity(Intent(this, OrderThirdActivity::class.java))
            }
        }
    }

    lateinit var year_spinner : Spinner
    lateinit var month_spinner : Spinner
    lateinit var day_spinner : Spinner
    lateinit var order_second_previeous_btn : RelativeLayout
    lateinit var order_second_scroll : ScrollView
    lateinit var order_second_next_btn : RelativeLayout

    override fun onResume() {
        super.onResume()
        order_second_scroll = order_second_scrollview as ScrollView
        order_second_scroll.fullScroll(ScrollView.FOCUS_UP)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_second)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK


        year_spinner = order_second_year as Spinner
        month_spinner = order_second_month as Spinner
        day_spinner = order_second_day as Spinner

        order_second_previeous_btn = order_etc_previous as RelativeLayout
        order_second_next_btn = order_etc_next as RelativeLayout


        order_second_previeous_btn.setOnClickListener(this)
        order_second_next_btn.setOnClickListener(this)




        // 년도 스피너
        year_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var year = parent!!.getItemAtPosition(position) as String
                Toast.makeText(this@OrderSecondActivity, year, Toast.LENGTH_SHORT).show()
            }

        }

        // 월 스피너
        month_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var month = parent!!.getItemAtPosition(position) as String
                Toast.makeText(this@OrderSecondActivity, month, Toast.LENGTH_SHORT).show()
            }

        }

        day_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var day = parent!!.getItemAtPosition(position) as String
                Toast.makeText(this@OrderSecondActivity, day, Toast.LENGTH_SHORT).show()

            }

        }

        val toggle = ActionBarDrawerToggle(
                this, order_second_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)


        order_second_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        toggle.setDrawerIndicatorEnabled(false)
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_black, applicationContext!!.getTheme())

        val bitmap = (drawable as BitmapDrawable).bitmap
        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (order_second_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                    order_second_drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    order_second_drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })

        order_second_nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (order_second_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            order_second_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



/*    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }*/

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

        order_second_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
