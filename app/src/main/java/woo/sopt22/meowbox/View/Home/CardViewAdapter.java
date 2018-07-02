package woo.sopt22.meowbox.View.Home;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import woo.sopt22.meowbox.R;

public class CardViewAdapter extends PagerAdapter{
    private LayoutInflater inflater;
    private List<CardData> mPageList;

    public CardViewAdapter(LayoutInflater inflater, List<CardData> mPageList) {
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
        View view = inflater.inflate(R.layout.cardview_adapter, container, false);
        ImageView mImageView = view.findViewById(R.id.titleImageView);
        CardData item = mPageList.get(position);
        Glide.with(container.getContext()).load(item.getImage()).into(mImageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
