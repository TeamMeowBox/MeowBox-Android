package woo.sopt22.meowbox.View.MyPage.Setting;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;

import woo.sopt22.meowbox.R;
import woo.sopt22.meowbox.View.Home.MainActivity;
import woo.sopt22.meowbox.View.Login.LoginActivity;
import woo.sopt22.meowbox.View.MyPage.MyPageActivity;


public class DisjoinCustomDialog extends Dialog implements View.OnClickListener{
    private static final int LAYOUT = R.layout.disjoin_custom_dialog;

    private Context context;

    private LinearLayout disjoinCancelBtn;
    private LinearLayout disjoinLoginBtn;

    public DisjoinCustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        disjoinCancelBtn = (LinearLayout) findViewById(R.id.disjoin_ask_cancel);
        disjoinCancelBtn.setOnClickListener(this);
        disjoinLoginBtn = (LinearLayout) findViewById(R.id.disjoin_ask_apply) ;
        disjoinLoginBtn.setOnClickListener(this);




    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.disjoin_ask_cancel:
                cancel();
                break;
            case R.id.disjoin_ask_apply:
                DisjoinApplyDialog dialog = new DisjoinApplyDialog(context);
                dialog.getWindow ().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                cancel();
                break;
        }
    }
}
