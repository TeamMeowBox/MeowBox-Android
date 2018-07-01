package woo.sopt22.meowbox.View.MeowBoxStory

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_meow_box_story.*
import kotlinx.android.synthetic.main.app_bar_meow_box_story.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Home.MainActivity
import woo.sopt22.meowbox.View.MeowBoxReview.MeowBoxReviewActivity

class MeowBoxStoryActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meow_box_story)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)



        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
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
                var intent : Intent =  Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            R.id.stroyBtn -> {
                startActivity(Intent(this, MeowBoxStoryActivity::class.java))
            }
            R.id.orderBtn -> {

            }
            R.id.reviewBtn -> {
                startActivity(Intent(this, MeowBoxReviewActivity::class.java))
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
