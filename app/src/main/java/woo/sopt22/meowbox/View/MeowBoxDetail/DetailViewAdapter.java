package woo.sopt22.meowbox.View.MeowBoxDetail;

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

public class DetailViewAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private List<DetailModel> mPageList;
    private Context context;
    private View.OnClickListener onItemClick;

    public DetailViewAdapter(LayoutInflater inflater, List<DetailModel> mPageList) {
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
        View view = inflater.inflate(R.layout.detail_card_view, container, false);


        ImageView detailCardViewImg = (ImageView) view.findViewById(R.id.detail_card_view_img);
        TextView detailCardViewTxt = (TextView) view.findViewById(R.id.detail_card_view_txt);
        DetailModel item = mPageList.get(position);
        Glide.with(container.getContext()).load(item.getImgUrl()).into(detailCardViewImg);
        detailCardViewTxt.setText(item.getText());

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