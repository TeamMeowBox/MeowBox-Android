package woo.sopt22.meowbox.View.MyPage.Setting;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import woo.sopt22.meowbox.R;

public class TermsActivity extends AppCompatActivity {

    TabHost tabHost;

    TextView tv1, tv2;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(Color.BLACK);

        tabHost = (TabHost) findViewById(R.id.tabhost1);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("1").setContent(R.id.tab1).setIndicator("이용약관");

        TabHost.TabSpec tab2 = tabHost.newTabSpec("2").setContent(R.id.tab2).setIndicator("개인정보 취급방침");

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);

        ImageView myXButton = (ImageView) findViewById(R.id.terms_x_btn);
        myXButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        init();
    }

    void init(){

        tv1 = (TextView) findViewById(R.id.terms_txt1);
        tv2 = (TextView) findViewById(R.id.terms_txt2);

        frameLayout = (FrameLayout) findViewById(android.R.id.tabcontent);


        tv1.setText(readTxt(1));
        tv2.setText(readTxt(2));

        /*tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                switch(s){
                    case "1":
                        frameLayout.setBackgroundColor(Color.parseColor("#FFFFbb33"));
                        break;
                    case "2":
                        frameLayout.setBackgroundColor(Color.parseColor("#ff00ddff"));
                        break;
                }
            }
        });*/

    }

    private String readTxt(int idx){
        String data = null;
        if(idx == 1){
            InputStream inputStream = getResources().openRawResource(R.raw.test_txt);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int i;
            try {
                i = inputStream.read();
                while (i != -1) {
                    byteArrayOutputStream.write(i);
                    i = inputStream.read();
                }

                data = new String(byteArrayOutputStream.toByteArray());
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        else {
            InputStream inputStream = getResources().openRawResource(R.raw.text_txt2);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int i;
            try {
                i = inputStream.read();
                while (i != -1) {
                    byteArrayOutputStream.write(i);
                    i = inputStream.read();
                }

                data = new String(byteArrayOutputStream.toByteArray());
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return data;

    }

}
