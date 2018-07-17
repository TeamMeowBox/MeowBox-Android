package woo.sopt22.meowbox.Util.CustomDialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.delete_custom_dialog.*
import woo.sopt22.meowbox.R

class DeleteTicketDialog(context : Context) : Dialog(context) , View.OnClickListener {

    companion object {
        private val LAYOUT = R.layout.delete_custom_dialog
    }
    override fun onClick(v: View?) {
        when(v!!){
            delete_ticket_cancel->{
                cancel()
            }
            delete_ticket_apply->{
                val delete_ticket_apply_dialog = DeleteTicketApplyCustomDialog(context)
                delete_ticket_apply_dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                delete_ticket_apply_dialog.setCanceledOnTouchOutside(false)
                delete_ticket_apply_dialog.show()
                cancel()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        delete_ticket_cancel.setOnClickListener(this)
        delete_ticket_apply.setOnClickListener(this)

    }
}