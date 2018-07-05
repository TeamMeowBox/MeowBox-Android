package woo.sopt22.meowbox.View.Order.OrderFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.order_second_fragment.*
import kotlinx.android.synthetic.main.order_second_fragment.view.*
import woo.sopt22.meowbox.R
import woo.sopt22.meowbox.Util.ToastMaker
import woo.sopt22.meowbox.View.Order.OrderFirstActivity


class OrderSecondFragment : Fragment(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v!!){
            order_etc_previous->{
                (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderFirstFragment())
            }
            order_etc_next->{
                (OrderFirstActivity.mContext as OrderFirstActivity).replaceFragment(OrderThirdFragment())
                ToastMaker.makeLongToast(context, order_etc_cat_name.text.toString()+"고양이와"+year+month+day+"생일 정보와"+cat_type.toString()+"과"+cat_about_info.text.toString())
            }
            order_small_cat_image->{
                if(!order_small_cat_image.isSelected){
                    cat_type = 0
                    order_small_cat_image.isSelected = true
                    order_normal_cat_image.isClickable = false
                    order_large_cat_image.isClickable = false
                } else{
                    order_small_cat_image.isSelected = false
                    order_normal_cat_image.isClickable = true
                    order_large_cat_image.isClickable = true
                }

            }
            order_normal_cat_image->{
                if(!order_normal_cat_image.isSelected){
                    cat_type = 1
                    order_normal_cat_image.isSelected = true
                    order_small_cat_image.isClickable = false
                    order_large_cat_image.isClickable = false
                } else{
                    order_normal_cat_image.isSelected = false
                    order_small_cat_image.isClickable = true
                    order_large_cat_image.isClickable = true
                }

            }
            order_large_cat_image->{
                if(!order_large_cat_image.isSelected){
                    cat_type = 2
                    order_large_cat_image.isSelected = true
                    order_small_cat_image.isClickable = false
                    order_normal_cat_image.isClickable = false
                } else{
                    order_large_cat_image.isSelected = false
                    order_small_cat_image.isClickable = true
                    order_normal_cat_image.isClickable = true
                }

            }



        }
    }

    lateinit var year : String
    lateinit var month : String
    lateinit var day : String
    var cat_type : Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_second_fragment, container, false)


        view.order_etc_previous.setOnClickListener(this)
        view.order_etc_next.setOnClickListener(this)
        view.order_small_cat_image.setOnClickListener(this)
        view.order_normal_cat_image.setOnClickListener(this)
        view.order_large_cat_image.setOnClickListener(this)

        val userId = arguments!!.getString("userId") // 전달한 key 값



        view.order_second_year.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                year = parent!!.getItemAtPosition(position) as String
                Toast.makeText(context, year, Toast.LENGTH_SHORT).show()
            }

        }

        // 월 스피너
        view.order_second_month.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                month = parent!!.getItemAtPosition(position) as String
                Toast.makeText(context, month, Toast.LENGTH_SHORT).show()
            }

        }

        view.order_second_day.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                day = parent!!.getItemAtPosition(position) as String
                Toast.makeText(context, day, Toast.LENGTH_SHORT).show()

            }

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if(this.arguments != null) {
            var bundle: Bundle = arguments!!
            order_etc_cat_name.text = bundle.getString("cat_name")
        }

    }


}