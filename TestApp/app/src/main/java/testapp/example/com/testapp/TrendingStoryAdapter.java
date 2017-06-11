package testapp.example.com.testapp;

import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TrendingStoryAdapter extends PagerAdapter {
    private List<MediaModel> mMediaModelList;

    public TrendingStoryAdapter(List<MediaModel> mediaModelList) {
        mMediaModelList = mediaModelList;
    }

    @Override
    public int getCount() {
        return mMediaModelList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        MediaModel mediaModel = mMediaModelList.get(position);
        LayoutInflater inflater = LayoutInflater.from(collection.getContext());
        View view = inflater.inflate(R.layout.view_trending_item, collection, false);
        ImageView albumImage = (ImageView) view.findViewById(R.id.trending_image);
        TextView albumName = (TextView) view.findViewById(R.id.album_name);
        if (mediaModel != null) {
            if (!TextUtils.isEmpty(mediaModel.getAlbumTitle())) {
                albumName.setText(mediaModel.getAlbumTitle());
            }
            albumImage.setImageResource(mediaModel.getAlbumImage());
        }
        collection.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }
}
