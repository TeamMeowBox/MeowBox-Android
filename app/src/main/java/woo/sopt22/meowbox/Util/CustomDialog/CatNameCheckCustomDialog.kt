package woo.sopt22.meowbox.Util.CustomDialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.cat_name_check_custom_dialog.*
import woo.sopt22.meowbox.R

class CatNameCheckCustomDialog(context : Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            cat_name_check_apply_btn->{
                dismiss()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
        cat_name_check_apply_btn.setOnClickListener(this)

    }

    companion object {
        private val LAYOUT = R.layout.cat_name_check_custom_dialog
    }
}