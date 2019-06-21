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

	* 사용자는 로그인 및 회원가입을 하지 않아도 앱을 둘러보고 사용할 수 있습니다.
	* 주문하기와 마이 페이지에 접근하기 위해서는 로그인을 해야 합니다.
	* **Navigation Bar**를 이용하여 사용자가 어디서든 다른 화면으로 이동할 수 있도록 접근성을 높였습니다.
	* **onPageScrolled**() 함수의 position과 **positionOffset**값을 이용하여 Viewpager에 들어가는 item의 **Padding** 값을 조절하여 카드 형식으로 화면을 넘겨 볼 수 있도록 구성하였습니다. 

```kotlin
    main_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }
	    
            override fun onPageSelected(position: Int) {
           

            }
	    // position과 positionOffset을 조절
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
