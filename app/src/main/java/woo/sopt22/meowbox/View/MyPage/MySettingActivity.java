package woo.sopt22.meowbox.View.MyPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import woo.sopt22.meowbox.R;

import static android.widget.Toast.LENGTH_SHORT;

public class MySettingActivity extends AppCompatActivity{

    private Button customButton1;
    private Button customButton2;
    private Button customButton3;

    String year, month, day;



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_setting);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(Color.BLACK);

        ImageView profileImage = (ImageView) findViewById(R.id.mysetting_profile);

        String imgUrlex = "https://www.petmd.com/sites/default/files/petmd-cat-happy.jpg";
        Glide.with(profileImage).load(imgUrlex).into(profileImage);

        ImageView myXButton = (ImageView) findViewById(R.id.mysetting_x_btn);
        myXButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
            /*Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
            startActivity(intent);*/
            // 여기 finish()로 해도 될듯?
                    finish();
        }
        });

        RelativeLayout mySaveButton = (RelativeLayout) findViewById(R.id.mysetting_save);
        mySaveButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
                        startActivity(intent);

                        Toast.makeText(MySettingActivity.this, year+month+day, LENGTH_SHORT).show();
                    }
                }
        );

        customButton1 = (Button) findViewById(R.id.custom_button1);
        customButton1.setBackgroundResource(R.drawable.custom_button);

        customButton2 = (Button) findViewById(R.id.custom_button2);
        customButton2.setBackgroundResource(R.drawable.custom_button);

        customButton3 = (Button) findViewById(R.id.custom_button3);
        customButton3.setBackgroundResource(R.drawable.custom_button);

        Spinner mySettingYear = (Spinner)findViewById(R.id.mysetting_year);
        mySettingYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner mySettingMonth = (Spinner)findViewById(R.id.mysetting_month);
        mySettingMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner mySettingDay = (Spinner)findViewById(R.id.mysetting_day);
        mySettingDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
