package woo.sopt22.meowbox.Util.CustomDialog;

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


public class LogoutApplyDialog extends Dialog implements View.OnClickListener{
    private static final int LAYOUT = R.layout.logout_apply_dialog;

    private Context context;

    private LinearLayout popLogoutBtn;

    public LogoutApplyDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        popLogoutBtn = (LinearLayout) findViewById(R.id.logout_apply_apply) ;
        popLogoutBtn.setOnClickListener(this);




    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout_apply_apply:
                cancel();
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                break;
        }
    }
}
