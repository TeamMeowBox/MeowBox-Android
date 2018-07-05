package woo.sopt22.meowbox.View.MyPage.OrderHistory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import woo.sopt22.meowbox.R;

public class HistoryActivity extends AppCompatActivity {

    RelativeLayout itemBuyOne;
    RelativeLayout itemBuyThree;
    RelativeLayout itemBuySix;
    ImageView historyXButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        itemBuyOne = (RelativeLayout) findViewById(R.id.history_buy_one);
        itemBuyThree = (RelativeLayout) findViewById(R.id.history_buy_three);
        itemBuySix = (RelativeLayout) findViewById(R.id.history_buy_six);

        historyXButton = (ImageView) findViewById(R.id.history_x_btn);
        historyXButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        int tmp = 1;
        if(tmp == 1){
            itemBuyThree.setVisibility(View.VISIBLE);
        }
    }
}
