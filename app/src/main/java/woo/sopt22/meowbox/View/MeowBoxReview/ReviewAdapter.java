package woo.sopt22.meowbox.View.MeowBoxReview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zarinpal.libs.cardviwepager.BaseCardViewPagerItem;

import org.w3c.dom.Text;

import woo.sopt22.meowbox.R;

public class ReviewAdapter extends BaseCardViewPagerItem<ReviewModel> {
    @Override
    public int getLayout() {
        return R.layout.review_card_view;
    }



    @Override
    public void bindView(View view, ReviewModel item) {


        ImageView reviewCardViewImg = view.findViewById(R.id.review_card_view_img);
        TextView reviewCardViewTxt = view.findViewById(R.id.review_card_view_hash);
        TextView reviewCardVeiwId = view.findViewById(R.id.review_card_view_id);

        Glide.with(view.getContext()).load(item.getImgUrl()).into(reviewCardViewImg);
        reviewCardViewTxt.setText(item.getHashText());
        reviewCardVeiwId.setText(item.getHashId());
    }
}
