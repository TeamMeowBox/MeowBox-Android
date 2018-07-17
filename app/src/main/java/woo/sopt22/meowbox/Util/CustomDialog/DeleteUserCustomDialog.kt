package woo.sopt22.meowbox.Util.CustomDialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.disjoin_custom_dialog.*
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference

class DeleteUserCustomDialog(context : Context) : Dialog(context), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!){
            disjoin_ask_cancel->{
                cancel()
            }
            disjoin_ask_apply->{
                val dialog = DeleteUserApplyCustomDialog(context)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()
                cancel()
            }
        }

    }

    companion object {
        private val LAYOUT = R.layout.disjoin_custom_dialog
    }

    lateinit var deleteCancelBtn: LinearLayout
    lateinit var deleteApplyBtn: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        SharedPreference.instance!!.load(context)

        deleteCancelBtn = disjoin_ask_cancel as LinearLayout
        deleteApplyBtn = disjoin_ask_apply as LinearLayout
        disjoin_ask_cancel.setOnClickListener(this)
        disjoin_ask_apply.setOnClickListener(this)
    }


}