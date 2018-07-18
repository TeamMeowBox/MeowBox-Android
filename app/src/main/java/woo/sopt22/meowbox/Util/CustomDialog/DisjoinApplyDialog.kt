package woo.sopt22.meowbox.Util.CustomDialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout

import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.SharedPreference


class DisjoinApplyDialog(context: Context) : Dialog(context), View.OnClickListener {

    lateinit var disjoinLogoutBtn: LinearLayout

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        SharedPreference.instance!!.load(context)

        disjoinLogoutBtn = findViewById<View>(R.id.disjoin_apply_apply) as LinearLayout
        disjoinLogoutBtn!!.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.disjoin_apply_apply -> {
                //deleteUser()
                cancel()
            }
        }
    }

    companion object {
        private val LAYOUT = R.layout.disjoin_apply_dialog
    }


}