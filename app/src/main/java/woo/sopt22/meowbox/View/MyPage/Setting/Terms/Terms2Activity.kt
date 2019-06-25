package woo.sopt22.meowbox.View.MyPage.Setting.Terms

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_terms2.*
import woo.sopt22.meowbox.R
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import woo.sopt22.meowbox.View.MyPage.Setting.Terms.Terms2Fragment
import woo.sopt22.meowbox.View.MyPage.Setting.Terms.Terms2Fragment2
import java.io.ByteArrayOutputStream
import java.io.IOException


class Terms2Activity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            terms2_x_btn->{
                finish()
            }
        }
    }

    fun replaceFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.terms2_frame, fragment)
        transaction.commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms2)

        terms2_x_btn.setOnClickListener(this)

        terms2_tab.addTab(terms2_tab.newTab().setText("이용약관"))
        terms2_tab.addTab(terms2_tab.newTab().setText("개인정보 취급방침"))


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK


        replaceFragment(Terms2Fragment())

        terms2_tab.setTabGravity(TabLayout.GRAVITY_FILL)
        //terms2_txt1.setText(readTxt(0))
        terms2_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0->{
                        replaceFragment(Terms2Fragment())
                    }
                    1->{
                        replaceFragment(Terms2Fragment2())
                    }
                }

            }

        })


    }

}
