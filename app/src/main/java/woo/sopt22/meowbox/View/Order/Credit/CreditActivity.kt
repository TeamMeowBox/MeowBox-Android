package woo.sopt22.meowbox.View.Order.Credit

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast

import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_credit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController

import woo.sopt22.meowbox.Model.Order.OrderData
import woo.sopt22.meowbox.Model.Order.OrderResult
import woo.sopt22.meowbox.Model.Order.OrderResultResponse
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference
import woo.sopt22.meowbox.View.Order.OrderFragmentWithCatInfo.WithCatInfoFour

class CreditActivity : Activity() {

    //AssetManager assetManager = getApplication().getAssets();

    private var mainWebView: WebView? = null
    lateinit var stringTmp: String
    lateinit var orderChecking : OrderResult
    lateinit var networkService: NetworkService

    @JavascriptInterface
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit)
        networkService = ApplicationController.instance!!.networkService
        SharedPreference.instance!!.load(this)




        mainWebView = main_web_view as WebView
        mainWebView!!.webViewClient = InicisWebViewClient(this)
        val settings = mainWebView!!.settings
        settings.javaScriptEnabled = true

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            val cookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)
            cookieManager.setAcceptThirdPartyCookies(mainWebView, true)
        }

        val intent = intent
        val intentData = intent.data

        val intentTmp = getIntent()
        stringTmp = intentTmp.getStringExtra("orderIdx")



        if (intentData == null) {
            Log.d("ton", "ton")
            mainWebView!!.loadUrl("file:///android_asset/your_own_scheme.js")
            Log.d("ton22", "ton22")


            val mHandler = Handler()
            mHandler.postDelayed({ mainWebView!!.loadUrl("javascript:myset('$stringTmp')") }, 3000)


            val tttmm = "안녕"


            //mainWebView.loadUrl("javascript:myset()");
            Log.d("ton33", "ton33")
            //mainWebView.loadUrl("javascript:IMP.request_pay('"+dataString+"')");


            //        	mainWebView.loadUrl("http://192.168.0.77:8888");
        } else {
            //isp 인증 후 복귀했을 때 결제 후속조치
            val url = intentData.toString()
            if (url.startsWith(APP_SCHEME)) {
                val redirectURL = url.substring(APP_SCHEME.length + 3)
                mainWebView!!.loadUrl(redirectURL)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        val url = intent.dataString
        if (url!!.startsWith(APP_SCHEME)) {
            val redirectURL = url.substring(APP_SCHEME.length + 3)
            mainWebView!!.loadUrl(redirectURL)
        }
    }

    override fun onStop() {
        super.onStop()
        val mHandler = Handler()
        mHandler.postDelayed({ postORrderResult() }, 20500)
    }


    inner class JavaScriptInterface internal constructor(aContext: Context) {
        internal var context: Context? = null

        init {
            context = aContext
        }

        //HTML 문서 내에서 JavaScript로 호출가능한 함수
        //브라우저에서 load가 완료되었을 때 호출하는 함수
        @JavascriptInterface
        fun onload() {
            Toast.makeText(applicationContext,
                    "JavaScript onLoad",
                    Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        private val APP_SCHEME = "iamporttest"
    }

    //    void trace() {
    //        String result = "";
    //        try {
    //            Log.d("errfind","hello");
    //            BufferedReader br = new BufferedReader(new InputStreamReader(assetManager.open("your_own_scheme.js")));
    //            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(assetManager.("your_own_scheme.js")));
    //
    //            String originalString = "myownjson";
    //            String replaceString = "hello";
    //            while ((result = br.readLine()) != null) {
    //                result = result.replaceAll(originalString, replaceString);
    //                bw.write(result + "\r\n");
    //                bw.flush();
    //            }
    //            bw.close();
    //            br.close();
    //        } catch (FileNotFoundException e) {
    //            e.printStackTrace();
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //    }



    fun postORrderResult(){
        orderChecking = OrderResult(SharedPreference.instance!!.getPrefStringData("merchant")!!)
        Log.d("boolan", "어디까지 들어가냥")
        val orderCheck = networkService.postOrderResult(SharedPreference.instance!!.getPrefStringData("token")!!,
                orderChecking)
        Log.d("boolan3", "어디까지 들어가냥")
        orderCheck.enqueue(object  : Callback<OrderResultResponse> {
            override fun onFailure(call: Call<OrderResultResponse>?, t: Throwable?) {
                Log.d("boolean3", "어디까지 들어가냥")

            }

            override fun onResponse(call: Call<OrderResultResponse>?, response: Response<OrderResultResponse>?) {
                if(response!!.isSuccessful){
                    var orderCheckBoolean = response!!.body()!!.result.order_result
                    Log.d("booln3", orderCheckBoolean.toString())

                    val resultIntent = Intent()
                    resultIntent.putExtra("result", orderCheckBoolean.toString())
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()

                }
            }

        })

    }


}