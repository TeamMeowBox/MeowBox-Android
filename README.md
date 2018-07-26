# MeowBox - Android

<img src="image/meow_box.png" width="70">


반려묘를 위한 수제 필수용품과 간식을 정기 배송하는 서비스 **MeowBox** 입니다.

## # 개요

수제 필수 용품(장난감, 스크래쳐 등등)과 간식과 같은 반려묘에게 꼭 필요한 것들을 정기 배송 해줌으로써 묘주의 구매력을 높이고 새로운 아이템을 찾아야 하는 번거로움을 해결하였고, 수제 간식 같은 건강식을 통해 반려묘의 건강을 관리 할 수 있습니다. 

매달 **컨셉**이 담긴 박스입니다.
반려묘를 키우는 사람을 대상으로 맞춤형 박스를 제공합니다.
1. 초보자 박스
2. 월별 컨셉 박스
3. 생일 박스



## # 워크 플로우

![](/image/meow_box_workflow.png)

## # 주요 기능

* 메인 화면
	* **Navigation Bar**를 이용하여 사용자가 어디서든 다른 화면으로 이동할 수 있도록 하였습니다. 
	* **Sliding Up Panel Layout**을 사용하여 아래에서 View를 끌어올릴 수 있도록 구현하였습니다. 
	* Viewpager에 들어가는 item의 Padding 값을 조절하여 카드 형식으로 화면을 넘겨 볼 수 있도록 구성하였습니다. 
	

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

            // 화면의 일부만 보이게 하기 위해서 ViewPager의 함수인 onpageScrolled에서 postion과 Offset을 건드렸다.
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






## # Develop Environment

* Language - **Kotlin**
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

