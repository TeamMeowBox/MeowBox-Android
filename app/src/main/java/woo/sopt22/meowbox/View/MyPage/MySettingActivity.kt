package woo.sopt22.meowbox.View.MyPage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.Toast

import com.bumptech.glide.Glide

import woo.sopt22.meowbox.R

import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_my_setting.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class MySettingActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View) {
        when (v!!) {
            mysetting_profile -> {
                changeImage()
            }
            custom_button1->{
                customButton1!!.setBackgroundResource(R.drawable.custom_button)
                customButton2!!.isClickable = false
                customButton3!!.isClickable = false
            }

        }
    }

    private var customButton1: Button? = null
    private var customButton2: Button? = null
    private var customButton3: Button? = null

    lateinit var year: String
    lateinit var month: String
    lateinit var day: String
    private val REQ_CODE_SELECT_IMAGE = 100

    private var image : MultipartBody.Part?=null
    lateinit var data : Uri


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_my_setting)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.statusBarColor = Color.BLACK

        val profileImage = findViewById<View>(R.id.mysetting_profile) as ImageView

        val imgUrlex = "https://www.petmd.com/sites/default/files/petmd-cat-happy.jpg"
        Glide.with(profileImage).load(imgUrlex).into(profileImage)

        val myXButton = findViewById<View>(R.id.mysetting_x_btn) as ImageView
        myXButton.setOnClickListener {
            /*Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
            startActivity(intent);*/
            // 여기 finish()로 해도 될듯?
            finish()
        }

        val mySaveButton = findViewById<View>(R.id.mysetting_save) as RelativeLayout
        mySaveButton.setOnClickListener {
            val intent = Intent(applicationContext, MyPageActivity::class.java)
            startActivity(intent)

            Toast.makeText(this@MySettingActivity, year + month + day, LENGTH_SHORT).show()
        }

        mysetting_profile.setOnClickListener(this)

        customButton1 = findViewById<View>(R.id.custom_button1) as Button
        customButton1!!.setBackgroundResource(R.drawable.custom_button)

        customButton2 = findViewById<View>(R.id.custom_button2) as Button
        customButton2!!.setBackgroundResource(R.drawable.custom_button)

        customButton3 = findViewById<View>(R.id.custom_button3) as Button
        customButton3!!.setBackgroundResource(R.drawable.custom_button)

        val mySettingYear = findViewById<View>(R.id.mysetting_year) as Spinner
        mySettingYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                year = adapterView.getItemAtPosition(i) as String
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }

        val mySettingMonth = findViewById<View>(R.id.mysetting_month) as Spinner
        mySettingMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                month = adapterView.getItemAtPosition(i) as String
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }

        val mySettingDay = findViewById<View>(R.id.mysetting_day) as Spinner
        mySettingDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                day = adapterView.getItemAtPosition(i) as String
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }

    }

    fun changeImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQ_CODE_SELECT_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                try{
                    this.data = data!!.data
                    Log.v("이미지",this.data.toString())

                    val options = BitmapFactory.Options()

                    var input : InputStream?=null
                    try{
                        input = contentResolver.openInputStream(this.data)
                    } catch (e : FileNotFoundException){
                        e.printStackTrace()
                    }

                    // 여기까지 적당한 형태로 변환하고

                    val bitmap = BitmapFactory.decodeStream(input, null,options)
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"),baos.toByteArray())
                    val photo = File(this.data.toString()) // 파일의 이름을 알아내려고 한다.

                    // 통신할 때 이미지 사용 - 레트로핏 추가해서
                    image = MultipartBody.Part.createFormData("store_photo",photo.name, photoBody)
                    // postman으로 확인했을 때 이미지의 key이 photo로 같아야 한다.

                    Glide.with(this).load(data!!.data).into(mysetting_profile)

                    // 내가 가지고 온 이미지를 이미지 뷰에 가운데를 중심으로 잘라서 Glide를 통해서 넣는다.
                    //registerTv.text=""


                } catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }

    }


}
