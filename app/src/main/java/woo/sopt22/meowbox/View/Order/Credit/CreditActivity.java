package woo.sopt22.meowbox.View.Order.Credit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import woo.sopt22.meowbox.Model.Order.OrderData;
import woo.sopt22.meowbox.R;
import woo.sopt22.meowbox.Util.SharedPreference;
import woo.sopt22.meowbox.View.Order.OrderFragmentWithCatInfo.WithCatInfoFour;

public class CreditActivity extends Activity {

    private WebView mainWebView;
    private static final String APP_SCHEME = "iamporttest://";
    //OrderData orderData;
    @JavascriptInterface
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        Intent intenttmp = getIntent();
        String stringTmp = intenttmp.getStringExtra("myJson");
        Gson gson = new Gson();
        final OrderData orderData = gson.fromJson(stringTmp, OrderData.class);





        mainWebView = (WebView) findViewById(R.id.mainWebView);
        mainWebView.setWebViewClient(new InicisWebViewClient(this));
        WebSettings settings = mainWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.setAcceptThirdPartyCookies(mainWebView, true);
        }

        Intent intent = getIntent();
        Uri intentData = intent.getData();

        if ( intentData == null ) {
            mainWebView.loadUrl("file:///android_asset/your_own_scheme.js");
            Log.d("errfidn","heell");

            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mainWebView.loadUrl("javascript:myset('"+ orderData.getProduct() +"')");

                }
            }, 10000);
        } else {
            //isp 인증 후 복귀했을 때 결제 후속조치
            String url = intentData.toString();
            if ( url.startsWith(APP_SCHEME) ) {
                String redirectURL = url.substring(APP_SCHEME.length()+3);
                mainWebView.loadUrl(redirectURL);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String url = intent.getDataString();
        if ( url.startsWith(APP_SCHEME) ) {
            String redirectURL = url.substring(APP_SCHEME.length()+3);
            mainWebView.loadUrl(redirectURL);
        }
    }

}
