package woo.sopt22.meowbox.View.MeowBoxDetail;

import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zarinpal.libs.cardviwepager.BaseCardViewPagerItem;

import woo.sopt22.meowbox.R;

public class DetailAdapter extends BaseCardViewPagerItem<DetailModel>{

    @Override
    public int getLayout() {
        return R.layout.detail_card_view;
    }

    @Override
    public void bindView(View view, DetailModel item) {

        ImageView detailCardViewImg = view.findViewById(R.id.detail_card_view_img);
        TextView detailCardViewTxt = view.findViewById(R.id.detail_card_view_txt);

        Glide.with(view.getContext()).load(item.getImgUrl()).into(detailCardViewImg);
        detailCardViewTxt.setText(item.getText());
    }
}
