package testapp.example.com.testapp;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<MediaModel> mMediaModelList;

    public HomeAdapter(List<MediaModel> mediaModelList) {
        mMediaModelList = mediaModelList;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_media_item, null);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder viewHolder, int i) {
        if (viewHolder != null && mMediaModelList != null) {
            viewHolder.updateView(mMediaModelList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mMediaModelList.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        private TextView mAlbumName;
        private TextView mAlbumDetail;
        private TextView mAlbumReleaseDate;
        private ImageView mAlbumImage;

        public HomeViewHolder(View itemView) {
            super(itemView);
            mAlbumName = (TextView) itemView.findViewById(R.id.album_name);
            mAlbumDetail = (TextView) itemView.findViewById(R.id.album_detail);
            mAlbumReleaseDate = (TextView) itemView.findViewById(R.id.release_date);
            mAlbumImage = (ImageView) itemView.findViewById(R.id.album_image);
        }

        public void updateView(MediaModel mediaModel) {
            if (mediaModel == null) return;
            if (!TextUtils.isEmpty(mediaModel.getAlbumTitle())) {
                mAlbumName.setText(mediaModel.getAlbumTitle());
            }
            if (!TextUtils.isEmpty(mediaModel.getAlbumDescription())) {
                mAlbumDetail.setText(mediaModel.getAlbumDescription());
            }
            if (!TextUtils.isEmpty(mediaModel.getReleaseDate())) {
                mAlbumReleaseDate.setText(mediaModel.getReleaseDate());
            }
            mAlbumImage.setImageResource(mediaModel.getAlbumImage());
        }
    }
}
