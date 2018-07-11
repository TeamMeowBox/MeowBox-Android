package woo.sopt22.meowbox.View.MeowBoxReview

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.zarinpal.libs.cardviwepager.CardViewPager
import kotlinx.android.synthetic.main.activity_meow_box_review.*
import kotlinx.android.synthetic.main.app_bar_meow_box_review.*
import kotlinx.android.synthetic.main.content_meow_box_review.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Review.ReviewResponse
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.CustomDialog.CatCustomDialog
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.Login.LoginActivity
import woo.sopt22.meowbox.View.MeowBoxDetail.DetailAdapter
import woo.sopt22.meowbox.View.MeowBoxStory.MeowBoxStoryActivity
import woo.sopt22.meowbox.View.MyPage.MyPageActivity
import woo.sopt22.meowbox.View.Order.LoginCustomDialog
import woo.sopt22.meowbox.View.Order.OrderFirstActivity
import woo.sopt22.meowbox.View.Order.OrderThirdActivity

class MeowBoxReviewActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    //lateinit var reviewItem3 : ReviewAdapter
    lateinit var items1 : ArrayList<ReviewModel>
    lateinit var items2 : ArrayList<ReviewModel>
    lateinit var items3 : ArrayList<ReviewModel>
    //lateinit var items3 : ArrayList<ReviewModel>
    lateinit var networkService: NetworkService
    lateinit var mViewPager1 : ViewPager
    lateinit var mViewPager2 : ViewPager
    lateinit var mViewPager3: ViewPager
    lateinit var mIndicator1 : CircleAnimIndicator
    lateinit var mIndicator2: CircleAnimIndicator
    lateinit var mIndicator3: CircleAnimIndicator
    lateinit var title1 : TextView
    lateinit var title2 : TextView
    lateinit var title3 : TextView
    lateinit var comment1 : TextView
    lateinit var comment2 : TextView
    lateinit var comment3 : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meow_box_review)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        networkService = ApplicationController.instance!!.networkService

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        mViewPager1 = review_cardview1 as ViewPager
        mViewPager2 = review_cardview2 as ViewPager
        mViewPager3 = review_cardview3 as ViewPager
        mIndicator1 = review_cardview1_indicator as CircleAnimIndicator
        mIndicator2 = review_cardview2_indicator as CircleAnimIndicator
        mIndicator3 = review_cardview3_indicator as CircleAnimIndicator

        title1 = review_month_ment_head as TextView
        title2 = review_best_ment_head as TextView
        title3 = review_sub_ment_head as TextView
        comment1 = review_month_ment as TextView
        comment2 = review_best_ment as TextView
        comment3 = review_sub_ment as TextView


        reviewCardShow()



        val toggle = ActionBarDrawerToggle(
                this, review_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        toggle.setDrawerIndicatorEnabled(false)
        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.side_bar_btn_black, applicationContext!!.getTheme())

        val bitmap = (drawable as BitmapDrawable).bitmap
        val newdrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 30, 30, true))

        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (review_drawer_layout.isDrawerVisible(GravityCompat.START)) {
                    review_drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    review_drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        })
        review_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()



        review_nav_view.setNavigationItemSelectedListener(this)



    }

    override fun onBackPressed() {
        if (review_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            review_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun reviewCardShow(){
        val tmpResponse = networkService.getReview()

        Log.v("98888","들어오니?")
        tmpResponse.enqueue(object : Callback<ReviewResponse> {
            override fun onFailure(call: Call<ReviewResponse>?, t: Throwable?) {
                Log.v("98888", t!!.message)
            }

            override fun onResponse(call: Call<ReviewResponse>?, response: Response<ReviewResponse>?) {
                Log.v("9277","승우신")
                if (response!!.isSuccessful) {
                    Log.v("9377",response!!.body()!!.result.birthday.image_list.size.toString())
                    //Log.v("9377",response!!.body()!!.result.birthday.image_list.size.toString())
                    var birthday = response!!.body()!!.result.birthday
                    var best_image_7 = response!!.body()!!.result.best_image_7
                    var best_image_6 = response!!.body()!!.result.best_image_6

                    var imgUrls1 = ArrayList<String>()
                    var hashTag1 = ArrayList<String>()
                    var instaId1 = ArrayList<String>()

                    var imgUrls2 = ArrayList<String>(best_image_7.image_list.size)
                    var hashTag2 = ArrayList<String>(best_image_7.hashtag.size)
                    var instaId2 = ArrayList<String>(best_image_7.insta_id.size)

                    var imgUrls3 = ArrayList<String>(best_image_6.image_list.size)
                    var hashTag3 = ArrayList<String>(best_image_6.hashtag.size)
                    var instaId3 = ArrayList<String>(best_image_6.insta_id)

                    title1.setText(birthday.title)
                    title2.setText(best_image_7.title)
                    title3.setText(best_image_6.title)
                    comment1.setText(birthday.comment)
                    comment2.setText(best_image_7.comment)
                    comment3.setText(best_image_6.comment)



                    items1 = ArrayList();
                    items2 = ArrayList();
                    items3 = ArrayList();


                    for (i in 0..2) {
                        Log.d("test",response!!.body()!!.result.birthday.image_list[i])
                        imgUrls1.add(i, birthday.image_list[i])
                        hashTag1.add(i, birthday.hashtag[i])
                        instaId1.add(i, birthday.insta_id[i])

                        items1.add(ReviewModel(imgUrls1[i], hashTag1[i], instaId1[i]))

                        imgUrls2.add(i, best_image_7.image_list[i])
                        hashTag2.add(i, best_image_7.hashtag[i])
                        instaId2.add(i, best_image_7.insta_id[i])

                        items2.add(ReviewModel(imgUrls2[i], hashTag2[i], instaId2[i]))

                        imgUrls3.add(i, best_image_6.image_list[i])
                        hashTag3.add(i, best_image_6.hashtag[i])
                        instaId3.add(i, best_image_6.insta_id[i])

                        items3.add(ReviewModel(imgUrls3[i], hashTag3[i], instaId3[i]))
                    }

                    var mdapter1 = ReviewViewAdapter(layoutInflater, items1)
                    var mdapter2 = ReviewViewAdapter(layoutInflater, items2)
                    var mdapter3 = ReviewViewAdapter(layoutInflater, items3)

                    mViewPager1.adapter = mdapter1
                    mViewPager2.adapter = mdapter2
                    mViewPager3.adapter = mdapter3
                    mViewPager1.addOnPageChangeListener(mOnPageChangeListener1)
                    mViewPager2.addOnPageChangeListener(mOnPageChangeListener2)
                    mViewPager3.addOnPageChangeListener(mOnPageChangeListener3)

                    mIndicator1.setItemMargin(20)
                    mIndicator1.setAnimDuration(300)
                    mIndicator1.createDotPanel(items1.size,R.drawable.indicator_non, R.drawable.indicator_on )
                    mIndicator2.setItemMargin(20)
                    mIndicator2.setAnimDuration(300)
                    mIndicator2.createDotPanel(items2.size,R.drawable.indicator_non, R.drawable.indicator_on )
                    mIndicator3.setItemMargin(20)
                    mIndicator3.setAnimDuration(300)
                    mIndicator3.createDotPanel(items3.size,R.drawable.indicator_non, R.drawable.indicator_on )

                    mViewPager1.setClipToPadding(false);
                    mViewPager1.setPadding(80,0,80,0);
                    mViewPager1.pageMargin = 40
                    mViewPager2.setClipToPadding(false);
                    mViewPager2.setPadding(80,0,80,0);
                    mViewPager2.pageMargin = 40
                    mViewPager3.setClipToPadding(false);
                    mViewPager3.setPadding(80,0,80,0);
                    mViewPager3.pageMargin = 40


                    Log.d("hereis","here")






                } else {
                    Log.v("err", "hello")
                }
            }
        })


    }



    var mOnPageChangeListener1 = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            mIndicator1.selectDot(position)
        }

    }

    var mOnPageChangeListener2 = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            mIndicator2.selectDot(position)
        }

    }

    var mOnPageChangeListener3 = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            mIndicator3.selectDot(position)
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
                finish()
                var intent =  Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
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

        review_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
