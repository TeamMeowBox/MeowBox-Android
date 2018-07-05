package woo.sopt22.meowbox.View.MyPage.Setting.Terms

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.terms2_fragment.view.*
import kotlinx.android.synthetic.main.terms2_frament2.view.*
import woo.sopt22.meowbox.R
import java.io.ByteArrayOutputStream
import java.io.IOException

class Terms2Fragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.terms2_fragment, container,false)
        view.terms2_txt1.setText(readTxt(1))

        return view
    }

    private fun readTxt(idx: Int): String? {
        var data: String? = null
        if(idx == 1){
            val inputStream = resources.openRawResource(R.raw.text_txt1)
            val byteArrayOutputStream = ByteArrayOutputStream()

            var i: Int
            try {
                i = inputStream.read()
                while (i != -1) {
                    byteArrayOutputStream.write(i)
                    i = inputStream.read()
                }

                data = String(byteArrayOutputStream.toByteArray())
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        return data

    }
}