package woo.sopt22.meowbox.Util.CustomDialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.cat_information_custom_dialog.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.Order.OrderFragment.OrderWithOutCatInfoActivity


class CatCustomDialog(context: Context) : Dialog(context), View.OnClickListener {

    lateinit var catDialogCancelBtn : LinearLayout
    lateinit var catDialogApplyBtn : LinearLayout

    override fun onClick(v: View?) {
        when(v!!){
            cat_question_cancel->{
                dismiss()
            }
            cat_question_apply_btn->{
                context.startActivity(Intent(context, OrderWithOutCatInfoActivity::class.java))
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        catDialogCancelBtn = cat_question_cancel as LinearLayout
        catDialogApplyBtn = cat_question_apply_btn as LinearLayout


        catDialogApplyBtn.setOnClickListener(this)
        catDialogCancelBtn.setOnClickListener(this)

    }

    companion object {
        private val LAYOUT = R.layout.cat_information_custom_dialog
    }


}
