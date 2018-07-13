package woo.sopt22.meowbox.View.Splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Home.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        // 함수 하나와 시간이 들어감
        // Unit -> 반환형이 없는 함수
        // 이 함수를 몇초있다가 실행할 것인지를 지정함
        handler.postDelayed({
            // 얼마만큼의 시간 있다가 실행시킬 것인지 함수를 넣어주면 됨
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish() // 이 스플래쉬 화면으로 돌아오지 못하도록 finish()로 액티비티를 끈다.
        }, 3800) // 2초

        // 2초 있다가 위에 있는 함수를 실행시킨다. 즉, 2초 있다가 MainActivity로 넘어가게 된다.

    }
}
