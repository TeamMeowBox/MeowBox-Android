package woo.sopt22.meowbox.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import woo.sopt22.meowbox.ApplicationController
import woo.sopt22.meowbox.Model.Review.ReviewResponse
import woo.sopt22.meowbox.Network.NetworkService

import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.View.MeowBoxReview.ReviewModel

class Main2Activity : AppCompatActivity() {


    lateinit var networkService: NetworkService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        networkService = ApplicationController.instance!!.networkService
        reviewCardShow()

    }
    fun reviewCardShow(){
        val tmpResponse = networkService.getReview()

        Log.v("98888","들어오니?")
        tmpResponse.enqueue(object : Callback<ReviewResponse> {
            override fun onFailure(call: Call<ReviewResponse>?, t: Throwable?) {
                Log.v("98888", t!!.message)
            }

            override fun onResponse(call: Call<ReviewResponse>?, response: Response<ReviewResponse>?) {
                Log.v("977","승우신")
                if (response!!.isSuccessful) {
                   /* var birthday = response!!.body()!!.result.birthday
                    var best_image_7 = response!!.body()!!.result.best_image_7
                    var best_image_6 = response!!.body()!!.result.best_image_6

                    var imgUrls1 = ArrayList<String>(birthday.image_list.size)
                    var hashTag1 = ArrayList<String>(birthday.hashtag.size)
                    var instaId1 = ArrayList<String>(birthday.insta_id)

                    var imgUrls2 = ArrayList<String>(best_image_7.image_list.size)
                    var hashTag2 = ArrayList<String>(best_image_7.hashtag.size)
                    var instaId2 = ArrayList<String>(best_image_7.insta_id)

                    var imgUrls3 = ArrayList<String>(best_image_6.image_list.size)
                    var hashTag3 = ArrayList<String>(best_image_6.hashtag.size)
                    var instaId3 = ArrayList<String>(best_image_6.insta_id)


                    items1 = ArrayList();

                    for (i in 0..birthday.image_list.size) {
                        imgUrls1[i] = birthday.image_list[i]
                        hashTag1[i] = birthday.hashtag[i]
                        instaId1[i] = birthday.insta_id[i]

                        items1.add(ReviewModel(imgUrls1[i], hashTag1[i], instaId1[i]))

                        imgUrls2[i] = best_image_7.image_list[i]
                        hashTag2[i] = best_image_7.hashtag[i]
                        instaId2[i] = best_image_7.insta_id[i]

                        items2.add(ReviewModel(imgUrls2[i], hashTag2[i], instaId2[i]))

                        imgUrls3[i] = best_image_6.image_list[i]
                        hashTag3[i] = best_image_6.hashtag[i]
                        instaId3[i] = best_image_6.insta_id[i]

                        items3.add(ReviewModel(imgUrls3[i], hashTag3[i], instaId3[i]))
                    }*/


                    /*var reviewViewPager1 = review_cardview1 as CardViewPager
                    var reviewViewPager2 = review_cardview2 as CardViewPager
                    //var reviewViewPager3 = review_cardview3 as CardViewPager


                    reviewItem1 = ReviewAdapter()
                    reviewItem2 = ReviewAdapter()
                    //reviewItem3 = ReviewAdapter()

                    for (i in 0..items1.size - 1) {
                        reviewItem1.addCardItem(items1[i])
                        reviewItem2.addCardItem(items2[i])
                        //reviewItem3.addCardItem(items3[i])
                    }

                    reviewItem1.setElevation(-0f)

                    reviewViewPager1.setAdapter(reviewItem1)
                    reviewViewPager2.setAdapter(reviewItem1)
                    reviewViewPager1.isShowShadowTransformer(false)

                    reviewViewPager1.setAdapter(reviewItem2)
                    reviewViewPager2.setAdapter(reviewItem2)
                    reviewViewPager1.isShowShadowTransformer(false)*/


                } else {
                    Log.v("err", "hello")
                }
            }
        })


    }
}
