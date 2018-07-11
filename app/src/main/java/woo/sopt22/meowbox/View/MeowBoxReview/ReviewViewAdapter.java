package woo.sopt22.meowbox.View.MeowBoxReview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import woo.sopt22.meowbox.R;

public class ReviewViewAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private List<ReviewModel> mPageList;
    private Context context;
    private View.OnClickListener onItemClick;

    public ReviewViewAdapter(LayoutInflater inflater, List<ReviewModel> mPageList) {
        this.inflater = inflater;
        this.mPageList = mPageList;
    }

    @Override
    public int getCount() {
        return mPageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.review_card_view, container, false);


        ImageView reviewCardViewImg = (ImageView) view.findViewById(R.id.review_card_view_img);
        TextView reviewCardViewTxt = (TextView) view.findViewById(R.id.review_card_view_hash);
        TextView reviewCardVeiwId = (TextView) view.findViewById(R.id.review_card_view_id);
        ReviewModel item = mPageList.get(position);
        Glide.with(container.getContext()).load(item.getImgUrl()).into(reviewCardViewImg);
        reviewCardViewTxt.setText(item.getHashText());
        reviewCardVeiwId.setText(item.getHashId());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    public void setOnItemClickListener(View.OnClickListener l){
        onItemClick = l;
    }
}