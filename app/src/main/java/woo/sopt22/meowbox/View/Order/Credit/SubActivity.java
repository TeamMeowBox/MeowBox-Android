package woo.sopt22.meowbox.View.Order.Credit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import woo.sopt22.meowbox.R;

public class SubActivity extends Activity {

    private WebView mainWebView;

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

//        mainWebView = (WebView) findViewById(R.id.mainWebView);
//        mainWebView.loadUrl("http://www.naver.com");

        Intent intent = getIntent();
        String url = intent.getDataString();
        System.out.println();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String url = intent.getDataString();
        System.out.println(url);
    }

}