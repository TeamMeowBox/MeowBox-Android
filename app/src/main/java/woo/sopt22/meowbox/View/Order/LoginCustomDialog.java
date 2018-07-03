package woo.sopt22.meowbox.View.Order;

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

public class LoginCustomDialog extends Dialog implements View.OnClickListener{
    private static final int LAYOUT = R.layout.login_dialog_custom;

    private Context context;

    private LinearLayout popCancelBtn;
    private LinearLayout popLoginBtn;

    public LoginCustomDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        popCancelBtn = (LinearLayout) findViewById(R.id.popup_cancel_btn);
        popCancelBtn.setOnClickListener(this);
        popLoginBtn = (LinearLayout) findViewById(R.id.popup_login_btn) ;
        popLoginBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.popup_cancel_btn:
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                break;
            case R.id.popup_login_btn:
                intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
