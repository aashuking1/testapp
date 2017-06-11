package testapp.example.com.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView mMediaRecyclerView;
    private HomeAdapter mHomeAdapter;
    private List<MediaModel> mMediaList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        createDummyMediaModel();
        return view;
    }

    private void initView(View view) {
        mMediaRecyclerView = (RecyclerView) view.findViewById(R.id.media_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        mMediaRecyclerView.setLayoutManager(manager);
        mHomeAdapter = new HomeAdapter(mMediaList);
        mMediaRecyclerView.setAdapter(mHomeAdapter);
    }

    private void createDummyMediaModel() {
        for (int index = 0; index < 20; index++) {
            MediaModel model = new MediaModel();
            model.setAlbumTitle("BABY FT JUSTIN BABER");
            model.setAlbumDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
            model.setReleaseDate("20 HOURS AGO");
            model.setAlbumImage(R.drawable.justin_album);
            mMediaList.add(model);
        }
        mHomeAdapter.notifyDataSetChanged();
    }

}
