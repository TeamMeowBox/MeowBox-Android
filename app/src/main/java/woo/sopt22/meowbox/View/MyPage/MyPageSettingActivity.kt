package woo.sopt22.meowbox.View.MyPage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*

import com.bumptech.glide.Glide

import woo.sopt22.meowbox.R

import kotlinx.android.synthetic.main.activity_my_setting.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.MyAccountSetting.MyAccountSettingGet
import woo.sopt22.meowbox.Model.MyAccountSetting.MyAccountSettingPost
import woo.sopt22.meowbox.Model.MyAccountSetting.MyAccountSettingPostResponse
import woo.sopt22.meowbox.Network.NetworkService
import woo.sopt22.meowbox.Util.SharedPreference
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.text.SimpleDateFormat

class MyPageSettingActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View) {
        when (v!!) {
            mysetting_profile -> {
                changeImage()
            }
            custom_button1 -> {
                buttonClicked("1")
            }
            custom_button2 -> {
                buttonClicked("2")
            }
            custom_button3 -> {
                buttonClicked("3")
            }
            mysetting_save -> {
                saveSettingAccount()
                //finish()


            }

        }
    }

    var userCatName: RequestBody? = null
    var userCatSize: RequestBody? = null
    var userBirthday: RequestBody? = null
    var userSuggest: RequestBody? = null

    lateinit var customButton1: ImageView
    lateinit var customButton2: ImageView
    lateinit var customButton3: ImageView

    lateinit var mySettingName: EditText
    lateinit var mySettingEmail: EditText
    lateinit var mySettingPhone: EditText
    lateinit var mySettingCatName: EditText
    lateinit var mySettingMySuggest: EditText

    lateinit var mysettingname: String
    lateinit var mysettingemail: String
    lateinit var mysettingphone: String
    var mysettingcatname: String? = null
    var mysettingmysuggest: String? = null
    var catsize: String? = null

    var year: String? = null
    var month: String? = null
    var day: String? = null
    private val REQ_CODE_SELECT_IMAGE = 100

    private var image: MultipartBody.Part? = null
    lateinit var data: Uri

    lateinit var networkService: NetworkService
    lateinit var myAccountSettingGet: MyAccountSettingGet
    lateinit var myAccountSettingPost: MyAccountSettingPost
    lateinit var profileImage: CircleImageView

    lateinit var mySettingYear: Spinner
    lateinit var mySettingMonth: Spinner
    lateinit var mySettingDay: Spinner

    lateinit var token: String


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_my_setting)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
        }

        networkService = ApplicationController.instance.networkService
        SharedPreference.instance!!.load(this)

        profileImage = findViewById<View>(R.id.mysetting_profile) as CircleImageView
        val imgUrlex = "https://www.petmd.com/sites/default/files/petmd-cat-happy.jpg"
        mySettingYear = findViewById<View>(R.id.mysetting_year) as Spinner
        mySettingMonth = findViewById<View>(R.id.mysetting_month) as Spinner
        mySettingDay = findViewById<View>(R.id.mysetting_day) as Spinner

        accountPreview()


        val myXButton = findViewById<View>(R.id.mysetting_x_btn) as ImageView
        myXButton.setOnClickListener {
            finish()
        }

        val mySaveButton = findViewById<View>(R.id.mysetting_save) as RelativeLayout
        mySaveButton.setOnClickListener(this)

        mysetting_profile.setOnClickListener(this)


        mySettingName = mysetting_my_name as EditText
        mySettingEmail = mysetting_my_email as EditText
        mySettingPhone = mysetting_my_phone as EditText
        mySettingCatName = mysetting_cat_name as EditText
        mySettingMySuggest = mysetting_my_suggest as EditText

        customButton1 = findViewById<View>(R.id.custom_button1) as ImageView
        customButton1!!.setOnClickListener(this)

        customButton2 = findViewById<View>(R.id.custom_button2) as ImageView
        customButton2!!.setOnClickListener(this)

        customButton3 = findViewById<View>(R.id.custom_button3) as ImageView
        customButton3!!.setOnClickListener(this)


        mySettingYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                year = adapterView.getItemAtPosition(i) as String
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }
        mySettingMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                month = adapterView.getItemAtPosition(i) as String
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }
        mySettingDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                day = adapterView.getItemAtPosition(i) as String
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }

    }

    override fun onResume() {
        super.onResume()
        accountPreview()
    }

    fun accountPreview() {
        val tmpResponse = networkService.getMyAccount(SharedPreference.instance!!.getPrefStringData("token")!!)

        Log.v("98", "들어오니?")
        tmpResponse.enqueue(object : Callback<MyAccountSettingGet> {
            override fun onFailure(call: Call<MyAccountSettingGet>?, t: Throwable?) {
                Log.v("98", "안됭")
            }

            override fun onResponse(call: Call<MyAccountSettingGet>?, response: Response<MyAccountSettingGet>?) {
                if (response!!.isSuccessful) {

                    mysettingname = response!!.body()!!.result.user_name
                    mysetting_my_name.setText(mysettingname)
                    mysettingemail = response!!.body()!!.result.email
                    mysetting_my_email.setText(mysettingemail)
                    mysettingphone = response!!.body()!!.result.phone_number
                    mysetting_my_phone.setText(mysettingphone)
                    mysettingcatname = response!!.body()!!.result.cat_name
                    mysetting_cat_name.setText(mysettingcatname)
                    mysettingmysuggest = response!!.body()!!.result.caution
                    mysetting_my_suggest.setText(mysettingmysuggest)

                    //SharedPreference.instance!!.removeData("name")


                    Glide.with(profileImage).load(response!!.body()!!.result.image_profile).into(profileImage)
                    Log.v("image 22", response!!.body()!!.result.image_profile)

                    buttonClicked(response!!.body()!!.result.size)

                    if (response!!.body()!!.result.birthday != null) {
                        var birthdays = response!!.body()!!.result.birthday.split("-")
                        var yearInt = birthdays[0].toInt() - 1980
                        //year = yearInt.toString()
                        if (yearInt == 0) {
                            year = (yearInt + 1980).toString()
                        } else {
                            year = yearInt.toString()
                        }
                        var monthInt = birthdays[1].toInt() - 1
                        //month = monthInt.toString()
                        if (monthInt == 0) {
                            month = (monthInt + 1).toString()
                        } else {
                            month = monthInt.toString()
                        }
                        var dayInt = birthdays[2].toInt() - 1
                        //day = dayInt.toString()
                        if (dayInt == 0) {
                            day = (dayInt + 1).toString()
                        } else {
                            day = dayInt.toString()
                        }


                        mySettingYear.setSelection(yearInt)
                        mySettingMonth.setSelection(monthInt)
                        mySettingDay.setSelection(dayInt)
                    }

                    //SharedPreference.instance!!.setPrefData("cat_idx",response!!.body()!!.result!!.cat_idx.toString())

                } else {
                    Log.v("96", response!!.message())
                }

            }

        })
    }

    fun buttonClicked(i: String?) {
        when (i) {
            "1" -> {
                customButton1!!.setImageResource(R.drawable.my_small_check_box_pink)
                customButton2!!.setImageResource(R.drawable.my_normal_check_box_gray)
                customButton3!!.setImageResource(R.drawable.my_large_check_box_gray)
                catsize = "1"
            }

            "2" -> {
                customButton1!!.setImageResource(R.drawable.my_small_check_box_gray)
                customButton2!!.setImageResource(R.drawable.my_normal_check_box_pink)
                customButton3!!.setImageResource(R.drawable.my_large_check_box_gray)
                catsize = "2"
            }

            "3" -> {
                customButton1!!.setImageResource(R.drawable.my_small_check_box_gray)
                customButton2!!.setImageResource(R.drawable.my_normal_check_box_gray)
                customButton3!!.setImageResource(R.drawable.my_large_check_box_pink)
                catsize = "3"
            }
        }


    }

    // 이미지 가져오기 위해 갤러리에 접근
    fun changeImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
        //Glide.with(this).load(intent).into(mysetting_profile)
    }

    fun saveSettingAccount() {

        //var birthdayString = year+"-"+month+"-"+day

        var simpleDataFormat = SimpleDateFormat("yyyy-MM-dd")
        //var birthdayString = year+"-"+month+"-"+day
        Log.d("date", year + ":" + ":" + month + ":" + day)
        var birthdayString: String
        if (day?.length == 1) {
            day = "0" + day
        }
        if (month?.length == 1) {
            month = "0" + month
        }

        birthdayString = year + "-" + month + "-" + day

        mysettingname = mySettingName.text.toString()
        mysettingemail = mySettingEmail.text.toString()
        mysettingphone = mySettingPhone.text.toString()
        mysettingcatname = mySettingCatName.text.toString()
        mysettingmysuggest = mySettingMySuggest.text.toString()

        SharedPreference.instance!!.setPrefData("name", mysettingname)
        SharedPreference.instance!!.setPrefData("cat_name", mysettingcatname!!)
        //Log.v("image 33",image!!.toString())
        //Log.v("image 44",data!!.toString())

        if (mySettingPhone.text.length == 0 || catsize == null || birthdayString.equals("1980-01-01")) {
            Log.d("nulllis", "nullyes")
            mysettingcatname = "-1"
        }


        val userName = RequestBody.create(MediaType.parse("multipart/form-data"), mysettingname)
        val userPhone = RequestBody.create(MediaType.parse("multipart/form-data"), mysettingphone)
        val userEmail = RequestBody.create(MediaType.parse("multipart/form-data"), mysettingemail)
        userCatName = RequestBody.create(MediaType.parse("multipart/form-data"), mysettingcatname)
        userCatSize = RequestBody.create(MediaType.parse("multipart/form-data"), catsize + "")
        userBirthday = RequestBody.create(MediaType.parse("multipart/form-data"), birthdayString)
        userSuggest = RequestBody.create(MediaType.parse("multipart/form-data"), mysettingmysuggest)

//        if(mysettingcatname.equals("")){ mysettingname? = null
//        }
        Log.v("경인", image.toString())
        val tmpResponse = networkService.postMyAccount(SharedPreference.instance!!.getPrefStringData("token")!!,
                userName, userPhone, userEmail, image, userCatName, userCatSize, userBirthday, userSuggest)
        tmpResponse.enqueue(object : Callback<MyAccountSettingPostResponse> {
            override fun onFailure(call: Call<MyAccountSettingPostResponse>?, t: Throwable?) {
                Log.v("01", t!!.message)
            }

            override fun onResponse(call: Call<MyAccountSettingPostResponse>?, response: Response<MyAccountSettingPostResponse>?) {
                if (response!!.isSuccessful) {
                    Log.v("장용범", response!!.body()!!.message)
                    Log.v("장용범", response!!.body()!!.result!!.toString())
                    //Log.v("11",response!!.body()!!.result!!.user_idx)
                    token = response!!.body()!!.result!!.token!!
                    Log.d("장용범", token)
                    SharedPreference.instance!!.setPrefData("name", mysettingname)
                    SharedPreference.instance!!.setPrefData("cat_name", mysettingcatname!!)
                    SharedPreference.instance!!.setPrefData("image_profile", image.toString())
                    Log.v("장용범 cat_idx", response!!.body()!!.result!!.cat_idx.toString())
                    SharedPreference.instance!!.setPrefData("cat_idx", response!!.body()!!.result!!.cat_idx)
                    SharedPreference.instance!!.setPrefData("token", response!!.body()!!.result!!.token)
                    finish()

                } else {
                    Log.v("장용범 실패", response!!.toString())
                    Log.v("장용범 실패", response!!.body()!!.message)
                }
            }

        })

    }

    // 갤러리 갔다 온 다음에 바로 호출되는 함수
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    this.data = data!!.data
                    Log.v("이미지", this.data.toString())
                    //SharedPreference.instance!!.setPrefData("image",this.data.toString())


                    val options = BitmapFactory.Options()

                    var input: InputStream? = null
                    try {
                        input = contentResolver.openInputStream(this.data)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    // 여기까지 적당한 형태로 변환하고

                    val bitmap = BitmapFactory.decodeStream(input, null, options)
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpeg"), baos.toByteArray())
                    val photo = File(this.data.toString()) // 파일의 이름을 알아내려고 한다.

                    // 통신할 때 이미지 사용 - 레트로핏 추가해서
                    image = MultipartBody.Part.createFormData("image_profile", photo.name, photoBody)
                    // postman으로 확인했을 때 이미지의 key이 photo로 같아야 한다.

                    println("장용범" + image)
                    println("장용범" + data!!.data)
                    //Glide.with(mysetting_profile).load(data!!.data.toString()).into(mysetting_profile)

//                    var bitmapTmp = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
//                    var drawbleTmp = BitmapDrawable(bitmapTmp) as Drawable
//                    mysetting_profile.setImageDrawable(drawbleTmp)

                    mysetting_profile.setImageURI(data!!.data)
                    mysetting_profile.background = data!!.data as Drawable


                    // 내가 가지고 온 이미지를 이미지 뷰에 가운데를 중심으로 잘라서 Glide를
                    // 통해서 넣는다.
                    //registerTv.text=""


                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }


}
