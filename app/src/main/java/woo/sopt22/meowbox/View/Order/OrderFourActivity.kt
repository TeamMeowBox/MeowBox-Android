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
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order_four.*
import kotlinx.android.synthetic.main.app_bar_order_four.*
import kotlinx.android.synthetic.main.content_order_four.*
import woo.sopt22.meowbox.R
import android.widget.RadioButton
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity


class OrderFourActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            order_four_previeous_btn->{
                finish()
            }
            order_four_next_btn->{
                Toast.makeText(this, radio_button.text.trim(), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, OrderFiveActivity::class.java))
            }
        }
    }

    lateinit var radio_group : RadioGroup
    lateinit var radio_button : RadioButton

    lateinit var order_four_previeous_btn : RelativeLayout
    lateinit var order_four_next_btn : RelativeLayout
    var id : Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_four)
        setSupportActionBar(toolbar)

        // 타이틀바 없애기
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)


        radio_group = order_four_pay_method as RadioGroup
        id = radio_group.checkedRadioButtonId

        order_four_previeous_btn = order_pay_previous as RelativeLayout
        order_four_next_btn = order_pay_next as RelativeLayout

        order_four_previeous_btn.setOnClickListener(this)
        order_four_next_btn.setOnClickListener(this)

        radio_button = findViewById<View>(id!!) as RadioButton





        val toggle = ActionBarDrawerToggle(
                this, order_four_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        order_four_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        toggle.setDrawerIndicatorEnabled(false)
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_black, applicationContext!!.getTheme())

        val bitmap = (drawable as BitmapDrawable).bitmap
        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (order_four_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                    order_four_drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    order_four_drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })

        order_four_nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (order_four_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            order_four_drawer_layout.closeDrawer(GravityCompat.START)
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

        order_four_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
