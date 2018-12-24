# MeowBox - Android

<img src="image/meow_box.png" width="80">


반려묘를 위한 수제 필수용품과 간식을 정기 배송하는 서비스 **MeowBox** 입니다.

## # 개요

필수 용품(장난감, 스크래쳐 등등)과 수제 간식과 같은 반려묘에게 꼭 필요한 것들을 정기 배송 해줌으로써 묘주의 구매력을 높이고 새로운 아이템을 찾아야 하는 번거로움을 해결하였고, 수제 간식 같은 건강식을 통해 반려묘의 건강을 관리 할 수 있습니다. 

매달 **컨셉**이 담긴 박스입니다.
반려묘를 키우는 사람을 대상으로 맞춤형 박스를 제공합니다.
1. 초보자 박스
2. 월별 컨셉 박스
3. 생일 박스


## # 워크 플로우

![](/image/meow_box_workflow.png)

## # Develop Environment

* Language - **Kotlin**, java
* Minimum SDK Version - 21
* Target SDK Version - 27
* Optimization Device - **Galaxy s8+**


## # Library

1. **Layout**
* 'com.sothree.slidinguppanel:library:3.4.0'
* 'com.android.support:cardview-v7:27.1.1'
* 'de.hdodenhof:circleimageview:2.2.0'
* 'com.android.support:recyclerview-v7:27.1.1'
* 'com.thoughtbot:expandablerecyclerview:1.3'
* 'com.zarinpal:cardviewpager:0.5.3'

2. **HTTP REST API**
* 'com.squareup.retrofit2:retrofit:2.4.0'
* 'com.squareup.retrofit2:converter-gson:2.1.0'

3. **Material design**
* 'com.android.support:design:27.1.1'

4. **Animation**
* 'com.airbnb.android:lottie:2.1.0'


## # 주요 기능

* 메인 화면

	* 사용자는 로그인 및 회원가입을 하지 않고도 앱을 둘러보고 사용할 수 있습니다.
	* 주문하기와 마이 페이지에 접근하기 위해서는 로그인을 해야 합니다.
	* **Navigation Bar**를 이용하여 사용자가 어디서든 다른 화면으로 이동할 수 있도록 하였습니다. 
	* **onPageScrolled**() 함수의 position과 **positionOffset**값을 이용하여 Viewpager에 들어가는 item의 **Padding** 값을 조절하여 카드 형식으로 화면을 넘겨 볼 수 있도록 구성하였습니다. 

```kotlin

    var items : ArrayList<CardData>
    items = ArrayList();
    items.add(CardData(R.drawable.home_main_one_img, 1))
    items.add(CardData(R.drawable.home_main_two_img,0))
    items.add(CardData(R.drawable.home_main_three_img,0))
    items.add(CardData(R.drawable.home_main_four_img,0))
    items.add(CardData(R.drawable.home_main_five_img,2))

    main_viewpager.setPadding(0,0,200,0)
    var madapter = CardViewAdapter(layoutInflater, items)
    main_viewpager.setCurrentItem(0)
    main_viewpager.adapter = madapter

    main_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {


            }

            // 이 함수를 통해서 선택된 position 값에 따른 네비게이션 바의 색깔과 툴바의 색을 변경할 수 있다.
            // 그리고 1,2에서의 상세보기와 3,4에서의 상세보기 버튼을 다른 기능을 할 수 있도록 구현할 수 있다.
            override fun onPageSelected(position: Int) {
           

            }

            // 화면의 일부만 보이게 하기 위해서 ViewPager의 함수인 onpageScrolled에서 postion과 Offset을 수정했습니다. 
            // 오른쪽 부분이 계속 보이다가 마지막 페이지에서는 왼쪽 페이지가 보입니다.
            // 결국에는 보여지는 화면의 position의 따라서 마지막과 마지막 전의 페이지만 padding 값을 조절하면 됩니다. 
            // items의 마지막 (items.size-1)은 왼쪽의 padding을 줘서 보이도록 하고 
            // 이 함수가 페이지가 스크롤 되는 동안에도 계속해서 호출이 되기 때문에 items의 마지막 전 (items.size-2) 페이지는
             
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                when(position){
                    (items.size-2)->{
                        main_viewpager.setPadding((200 * positionOffset).toInt(),0,200 - (200*positionOffset).toInt(),0)
                    }
                    (items.size-1)->{
                        main_viewpager.setPadding(200,0,0,0)
                    }

                }

            }

        })

```
* 메인 화면 - 추가 화면
	* **Sliding Up Panel Layout**을 사용하여 아래에서 View를 끌어올릴 수 있도록 구현하였습니다. 

```kotlin

	// 클릭 이벤트가 아닌 터치 제스처를 통해서 끌어올리면 통신과 뷰가 올라올 수 있도록 구현하였습니다. 
    bottom_up_relative_layout.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when(event!!.action){
                    MotionEvent.ACTION_UP,MotionEvent.ACTION_MOVE->{

                        getInsta() // 고양이 인스타 크롤링 정보를 받아오는 통신 함수
                        getCatCount() // 등록된 고양이의 수를 받아오는 통신 함수
                        main_sliding_scroll.fullScroll(ScrollView.FOCUS_UP)
                    }
                    MotionEvent.ACTION_DOWN->{

                        main_sliding_scroll.fullScroll(ScrollView.FOCUS_UP)
                    }

                }
                return true
            }


        })
```




* 미유 박스 이야기 화면
	* 미유 박스의 목표와 미유 박스가 무엇인지, 미유 박스는 어떤 구성품으로 구성되어있는지를 설명해줍니다.
	* 그리고 미유 박스를 주문할 수 있도록 유도합니다. 

* 생일 축하해 화면
	* 반려묘의 생일을 축하하는 이야기가 담겨 있는 화면입니다.
	* 자체 제작한 웹툰이 연재되어 있습니다. 

* 집사들의 후기 화면
	* 6월, 7월, 생일 총 3개 카테고리의 후기를 볼 수 있으며 카드 형식으로 Viewpager를 구성하였습니다. 

* 주문하기 화면
	* IamPort 모듈을 이용해서 결제 테스트를 구현했습니다.
	* PG사와 카드사가 제공하는 인증 및 프로세스는 웹을 통해서 이루어지므로 Webview를 활용하였습니다.
	* 내장된 Webview에서 아임포트 Javascript 코드가 포함된 페이지를 로드하며 이를 기반으로 결제가 이루어지게 됩니다. 


* 주문 페이지
	* 주문하기 버튼을 클릭했을 때 통신 함수를 호출합니다
	* 서버로부터 필요한 정보를 받아와 그 값을 Json 형태로 변환합니다.
	* 그리고 그 값을 가지고 CrediActivity로 넘어갑니다. 

```kotlin
    fun postOrder() {
        if (this.arguments != null) {
            var bundle: Bundle = arguments!!
            cat_name = bundle.getString("cat_name")
            box_type = bundle.getString("box_type")
            price = bundle.getString("price")

        }
        orderData = OrderData(order_four_name.text.toString()
                , order_four_address_one.text.toString() + order_four_address_two.text.toString()
                , order_four_phone_number.text.toString(), box_type,
                price, order_four_email.text.toString(), radio_button.text.toString())
        val orderRespone = networkService.postOrder(SharedPreference.instance!!.getPrefStringData("token")!!, orderData)
        orderRespone.enqueue(object : Callback<OrderResponse> {
            override fun onFailure(call: Call<OrderResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<OrderResponse>?, response: Response<OrderResponse>?) {
                if (response!!.isSuccessful) {
                    var orderIdx = response!!.body()!!.result.order_idx.toString()
                    // 해당 주문에 대한 id를 뜻하는 merchant 값입니다. 
                    SharedPreference.instance!!.setPrefData("merchant", orderIdx)

                    var priceTmp: Int
                    var re = Regex("[^0-9]")
                    priceTmp = re.replace(price, "").toInt()

                    if(box_type.equals("7")){

                        orderTest = OrderTest(orderIdx, "생일 축하해! 박스", priceTmp/100)

                    }
                    else{

                        orderTest = OrderTest(orderIdx, box_type+"개월 정기배송", priceTmp/100)
                    }

                    var gson = Gson()
                    var orderJson = gson.toJson(orderTest)
                    gson.toJson(orderTest)

                    // Json 형태로 바꾼 다음에 그 값을 CreditActivity로 보냅니다.
                    val intent = Intent(activity, CreditActivity::class.java)
                    intent.putExtra("orderIdx", orderJson)
                    startActivityForResult(intent, 1541); // 순서 : 1번


                }
            }

        })
    }

       // 순서 : 3번
       // startActivityForResult를 통해서 결제 페이지에 갔다가
       // 값을 받고 setResult 함수를 통해서 주문 페이지로 돌아오게 됩니다.
       // 그리고 data.result 가 true라면 주문이 완료된 것이므로 주문 완료 페이지로 넘어갑니다.
       // false라면 주문이 완료되지 않은 것이므로 그 전 페이지로 돌아갑니다.
       override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1541 -> {
                    if (data!!.getStringExtra("result").equals("true")) {
                        (OrderWithOutCatInfoActivity.mContext as OrderWithOutCatInfoActivity).replaceFragment(OrderFiveFragment())
                    } else {
                        (OrderWithOutCatInfoActivity.mContext as OrderWithOutCatInfoActivity).replaceFragment(OrderThirdFragment())
                    }

                }

            }
        }
    }

```
<!-- * 결제 페이지
	* 주문 페이지에서 넘어 온 정보를 전달 받고 Javascript 코드에 있는 함수를 통해 값을 넘깁니다. 
	* 그리고 내장된 Javascript 코드를 호출하여 결제 페이지를 로드합니다.

```kotlin

        mainWebView = main_web_view as WebView
        mainWebView!!.webViewClient = InicisWebViewClient(this)
        val settings = mainWebView!!.settings

        // Webview에서 Javascript 코드를 사용할 수 있도록 설정해줍니다. 
        settings.javaScriptEnabled = true

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            val cookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)
            cookieManager.setAcceptThirdPartyCookies(mainWebView, true)
        }

        val intent = intent
        val intentData = intent.data

        val intentTmp = getIntent()

        // merchant의 값인 orderIdx를 받습니다. 
        var stringTmp = intentTmp.getStringExtra("orderIdx")



        if (intentData == null) {

            // 내장된 Javascript 코드를 호출하여 페이지를 로드합니다.
            mainWebView!!.loadUrl("file:///android_asset/your_own_scheme.js")

            // 그리고 Handler를 이용하여 3초 정도 delay 시켜서
            // Javascript 함수 안에 있는 함수를 호출하여 stringTmp 즉, merchant 값을 넘깁니다. 
            // 그러면 iamport에 보낸 merchant값을 받습니다. 
            // 그리고 우리 서버로 redirectUrl을 설정해 놓습니다. 
            // 그러면 iamport에서 우리가 보낸 merchant를 우리 서버에 return 해줍니다. 
            val mHandler = Handler()
            mHandler.postDelayed({ mainWebView!!.loadUrl("javascript:myset('$stringTmp')") }, 3000)

        } else {
            //isp 인증 후 복귀했을 때 결제 후속조치
            val url = intentData.toString()
            if (url.startsWith(APP_SCHEME)) {
                val redirectURL = url.substring(APP_SCHEME.length + 3)
                mainWebView!!.loadUrl(redirectURL)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        val url = intent.dataString
        if (url!!.startsWith(APP_SCHEME)) {
            val redirectURL = url.substring(APP_SCHEME.length + 3)
            mainWebView!!.loadUrl(redirectURL)
        }
    }

    // 액티비티가 보이지 않을 때 Handler를 이용하여 통신 함수를 호출합니다.
    // Webview를 통해 카카오페이 결제에 들어가면 다른 Component들에 대한 통제권이 없기 때문에 handler를 통하여 지연시킵니다.
    override fun onStop() {
        super.onStop()
        val mHandler = Handler()
        mHandler.postDelayed({ postORrderResult() }, 22000)
    }

    // 서버는 주문자가 주문을 했을 때 발급해준 orderIdx 즉, merchant 값과 iamport에서 받은 merchant 값을 비교하여 true/false인지 반환해줍니다.
    // 그럼 boolean 값을 가지고 serResult를 통해서 다시 주문 페이지로 반환해줍니다. 

    fun postORrderResult(){ // 순서 : 2번
        orderChecking = OrderResult(SharedPreference.instance!!.getPrefStringData("merchant")!!)
        val orderCheck = networkService.postOrderResult(SharedPreference.instance!!.getPrefStringData("token")!!,
                orderChecking)
        orderCheck.enqueue(object  : Callback<OrderResultResponse> {
            override fun onFailure(call: Call<OrderResultResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<OrderResultResponse>?, response: Response<OrderResultResponse>?) {
                if(response!!.isSuccessful){
                    var orderCheckBoolean = response!!.body()!!.result.order_result
                    //var orderCheckBoolean = true

                    val resultIntent = Intent()
                    resultIntent.putExtra("result", orderCheckBoolean.toString())
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()

                }
            }

        })

    }
```



* 결제 페이지에 사용되는 Javascript 코드
	* 아래의 Javascript 코드를 추가하고 코드 안에 함수를 작성하였습니다.
	* myset 함수는 안드로이드에서 Javascript 쪽으로 정보를 전달받기 위한 함수입니다. 
	* 이 함수를 통해서 data라는 Json 객체에 저장된 데이터에 접근하여 정보를 가져옵니다. 

```Javascript
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pay document</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script type="text/javascript">
var IMP = window.IMP; // 생략가능
IMP.init('iamport'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

function myset(res){
var data = JSON.parse(res);
data;
//onclick, onload 등 원하는 이벤트에 호출합니다
IMP.request_pay(
{


    pg : 'inicis', // version 1.1.0부터 지원.
    pay_method : 'card',
    merchant_uid :data.merchant_uid,
    name :data.name,
    amount :data.amount,
    buyer_email : 'iamport@siot.do',
    buyer_name : '구매자이름',
    buyer_tel : '010-1234-5678',
    buyer_addr : '서울특별시 강남구 삼성동',
    buyer_postcode : '123-456',
    m_redirect_url : 'http://13.124.92.40:3000/order/order_result',
    app_scheme : 'iamportapp'
}
, function(rsp) {
    if ( rsp.success ) {
        var msg = '결제가 완료되었습니다.';
        msg += '고유ID : ' + rsp.imp_uid;
        msg += '상점 거래ID : ' + rsp.merchant_uid;
        msg += '결제 금액 : ' + rsp.paid_amount;
        msg += '카드 승인번호 : ' + rsp.apply_num;
    } else {
        var msg = '결제에 실패하였습니다.';
        msg += '에러내용 : ' + rsp.error_msg;
    }

    alert(msg);
});
}


</script>

</body>
</html>
``` -->

