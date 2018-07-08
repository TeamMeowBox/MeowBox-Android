package woo.sopt22.meowbox.View.MeowBoxDetail

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Adapter
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.zarinpal.libs.cardviwepager.CardViewPager
import kotlinx.android.synthetic.main.activity_meow_box_detail.*
import kotlinx.android.synthetic.main.activity_my_page.*
import kotlinx.android.synthetic.main.app_bar_meow_box_detail.*
import kotlinx.android.synthetic.main.content_meow_box_detail.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.CustomDialog.CatCustomDialog
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Login.LoginActivity
import woo.sopt22.meowbox.View.MeowBoxReview.MeowBoxReviewActivity
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity
import woo.sopt22.meowbox.View.Order.LoginCustomDialog
import woo.sopt22.meowbox.View.Order.OrderFirstActivity
import woo.sopt22.meowbox.View.Order.OrderThirdActivity

class MeowBoxDetailActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    override fun onClick(p0: View?) {
        when (p0) {
            detail_order_btn -> {
                if (SharedPreference.instance!!.getPrefStringData("token")!!.isEmpty()) {
                    val dialog = LoginCustomDialog(this)
                    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dialog.show()
                } else {
                    if (SharedPreference.instance!!.getPrefStringData("cat_idx")!!.toInt() == -1) {
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
        }
    }

    lateinit var detailItem1 : DetailAdapter
    lateinit var items1 : ArrayList<DetailModel>

    lateinit var detailOrderBtn : RelativeLayout
    lateinit var detailFirstImg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meow_box_detail)
        setSupportActionBar(toolbar)

        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        toolbar.bringToFront()

        SharedPreference.instance!!.load(this)


        var headerView : View = detail_nav_view.getHeaderView(0)
        var userName : TextView = headerView.findViewById<TextView>(R.id.header_name)

        if(SharedPreference.instance!!.getPrefStringData("user_email")!!.isEmpty()){
            userName.text = "OO님!"
        } else {
            userName.text = SharedPreference.instance!!.getPrefStringData("user_email")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK


        val toggle = ActionBarDrawerToggle(
                this, detail_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.setDrawerIndicatorEnabled(false)
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_black, applicationContext!!.getTheme())

        val bitmap = (drawable as BitmapDrawable).bitmap
        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (detail_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                    detail_drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    detail_drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })
        detail_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        detail_nav_view.setNavigationItemSelectedListener(this)

        detailOrderBtn = detail_order_btn as RelativeLayout
        detail_order_btn.setOnClickListener(this)

        var imgUrl1 = "https://post-phinf.pstatic.net/MjAxNzA0MjFfMTMx/MDAxNDkyNzAxMjI0NzA3.Q_bmK_EvjtxtFpT30CNtyBsJBfGkAieooME9VDfoKHYg.nrXNY37E18mt1g6nbwDpHN7kQAwmDr9Q2RPLKWkw_2wg.JPEG/1492696692724.jpg?type=w1200" as String
        var imgUrl2 = "https://post-phinf.pstatic.net/MjAxNzA0MjFfMTc2/MDAxNDkyNzAxMjI1MDA4.IS9AxBl-5bs1-h3PbJssvfm5xmcsUAkkLMg-qIJ9KVsg.h8_rW0zPTvO74wQ5yH_K3TRAJVJUcGT6Z_hldpv_GRgg.JPEG/1492696688049.jpg?type=w1200" as String
        var imgUrl3 = "https://post-phinf.pstatic.net/MjAxNzA0MjFfMTI1/MDAxNDkyNzAxMjI1MTY0.femsgEnFQWPK7szY4kZ0_6uSgXqCaDNyAPZt5Pp-ebMg.oRMiRH-aga5cGKJc8OSOabjZv1Nf0AO7XUFGe7sVa_cg.JPEG/1492696687282.jpg?type=w1200" as String


        items1 = ArrayList();
        items1.add(DetailModel("ex1",imgUrl1))
        items1.add(DetailModel("ex2",imgUrl2))
        items1.add(DetailModel("ex3",imgUrl3))

        detailFirstImg = detail_first_img as ImageView

        Glide.with(this).load("http://t1.daumcdn.net/liveboard/petxlab/a5d83a4064744faea244e8ac7667fb90.gif").into(detailFirstImg);


       var detailViewPager1 = detail_cardview_pager1 as CardViewPager
       var detailViewPager2 = detail_cardview_pager2 as CardViewPager


        detailItem1 = DetailAdapter()
        
        for (i in 0..items1.size-1){
            detailItem1.addCardItem(items1[i])
        }
        println("333"+detailItem1.getItem(0).text)

        detailItem1.setElevation(0.0f)
        
        detailViewPager1.setAdapter(detailItem1)
        detailViewPager2.setAdapter(detailItem1)

    }

    override fun onBackPressed() {
        if (detail_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            detail_drawer_layout.closeDrawer(GravityCompat.START)
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
                if(SharedPreference.instance!!.getPrefStringData("token")!!.isEmpty()){
                    startActivity(Intent(this, LoginActivity::class.java))
                } else{
                    ToastMaker.makeLongToast(this, "이미 로그인 하셨습니다.")
                }
            }
            R.id.blankBtn->{
                item.isChecked = false
            }
            R.id.homeBtn -> {

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
                    if(SharedPreference.instance!!.getPrefStringData("cat_idx")!!.toInt() == -1){
                        val dialog = CatCustomDialog(this)
                        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        dialog.show()
                    } else {
                        val intent = Intent(this, OrderFirstActivity::class.java)
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
                    ToastMaker.makeLongToast(this,"로그인 해주세요.")
                } else{
                    startActivity(Intent(this, MyPageActivity::class.java))
                }
            }
        }

        detail_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
