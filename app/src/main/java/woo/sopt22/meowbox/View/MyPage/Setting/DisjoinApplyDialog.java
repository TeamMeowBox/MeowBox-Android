package woo.sopt22.meowbox.View.MyPage.Setting;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;

import woo.sopt22.meowbox.R;
import woo.sopt22.meowbox.View.Home.MainActivity;
import woo.sopt22.meowbox.View.Login.LoginActivity;
import woo.sopt22.meowbox.View.MyPage.MyPageActivity;


public class DisjoinApplyDialog extends Dialog implements View.OnClickListener{
    private static final int LAYOUT = R.layout.disjoin_apply_dialog;

    private Context context;

    private LinearLayout disjoinLogoutBtn;

    public DisjoinApplyDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        disjoinLogoutBtn = (LinearLayout) findViewById(R.id.disjoin_apply_apply) ;
        disjoinLogoutBtn.setOnClickListener(this);




    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.disjoin_apply_apply:
                cancel();
                break;
        }
    }
}