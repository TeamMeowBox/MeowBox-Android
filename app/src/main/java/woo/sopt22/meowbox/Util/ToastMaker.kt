package woo.sopt22.meowbox.Util

import android.content.Context
import android.widget.Toast

object ToastMaker {

    fun makeLongToast(context: Context, id: Int) {
        makeLongToast(context, context.getResources().getString(id))
    }

    fun makeShortToast(context: Context, id: Int) {
        makeShortToast(context, context.getResources().getString(id))
    }

    fun makeLongToast(context: Context?, text: String?) {
        if (context != null && text != null && !text.isEmpty()) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }
    }

    fun makeShortToast(context: Context?, text: String?) {
        if (context != null && text != null && !text.isEmpty()) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }
}