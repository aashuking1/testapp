package testapp.example.com.testapp;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeTabAdapter extends FragmentPagerAdapter {

    private List<HomeTab> mHomeTabList;
    private final List<String> mChildIdList = new ArrayList<>();
    private HomeFragment mHomeFragment;

    public HomeTabAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mHomeTabList = new ArrayList<>();
        mChildIdList.clear();
    }

    public void addTab(HomeTab homeTab) {
        if (mHomeTabList != null) {
            mHomeTabList.add(homeTab);
        }
    }

    public View getTabView(Context context, int position) {
        HomeTab homeTab = mHomeTabList.get(position);
        View tabView = LayoutInflater.from(context).inflate(R.layout.view_home_tab, null);
        if (homeTab != null && tabView != null) {
            tabView.setTag(position);
            TextView tabText = (TextView) tabView.findViewById(R.id.tab_text);
            if (tabText != null && !TextUtils.isEmpty(homeTab.getHomeTabTitle())) {
                tabText.setText(homeTab.getHomeTabTitle());
            }
            ImageView tabIcon = (ImageView) tabView.findViewById(R.id.tab_icon);
            if (tabIcon != null && homeTab.getHomeTabType() != null) {
                switch (homeTab.getHomeTabType()) {
                    case IMAGE_TAB:
                        tabIcon.setImageResource(R.drawable.image);
                        break;

                    case VIDEO_TAB:
                        tabIcon.setImageResource(R.drawable.video);
                        break;

                    case MILESTONE_TAB:
                        tabIcon.setImageResource(R.drawable.milestone);
                        break;
                }
            }
        }
        return tabView;
    }


    @Override
    public Fragment getItem(int position) {
        HomeTab homeTab = getHomeTab(position);
        if (homeTab == null || homeTab.getHomeTabType() == null) {
            return null;
        }
        switch (homeTab.getHomeTabType()) {
            case IMAGE_TAB:
            case VIDEO_TAB:
            case MILESTONE_TAB:
                return new HomeFragment();
            default:
                // This should never happen. Always account for each position above.
                return null;
        }
    }

    @Override
    public int getCount() {
        if (mHomeTabList != null) {
            return mHomeTabList.size();
        }
        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        // save the appropriate reference depending on position
        HomeTab homeTab = getHomeTab(position);
        if (createdFragment == null || homeTab == null || homeTab.getHomeTabType() == null) {
            return createdFragment;
        }
        switch (homeTab.getHomeTabType()) {
            case IMAGE_TAB:
            case VIDEO_TAB:
            case MILESTONE_TAB:
                mHomeFragment = (HomeFragment) createdFragment;
                break;
        }
        return createdFragment;
    }

    private HomeTab getHomeTab(int position) {
        if (mHomeTabList != null && position < mHomeTabList.size()) {
            return mHomeTabList.get(position);
        }
        return null;
    }


    public HomeTab getSelectedTab(final TabLayout tabLayout, int position) {
        if (mHomeTabList != null && tabLayout != null) {
            for (int index = 0; index < mHomeTabList.size(); ++index) {
                HomeTab homeTab = mHomeTabList.get(index);
                if (homeTab != null && homeTab.getHomeTabType() != null && tabLayout.getTabAt(index) != null
                        && tabLayout.getTabAt(index).getCustomView() != null) {
                    View tabView = tabLayout.getTabAt(index).getCustomView();
                    TextView tabText = (TextView) tabView.findViewById(R.id.tab_text);
                    ImageView tabIcon = (ImageView) tabView.findViewById(R.id.tab_icon);
                    switch (homeTab.getHomeTabType()) {
                        case IMAGE_TAB:

                            if (position == index) {
                                tabIcon.setImageResource(R.drawable.select_image);
                                tabText.setTextColor(tabText.getResources().getColor(android.R.color.holo_orange_dark));
                            } else {
                                tabIcon.setImageResource(R.drawable.image);
                                tabText.setTextColor(tabText.getResources().getColor(android.R.color.black));
                            }

                            break;

                        case VIDEO_TAB:
                            if (position == index) {
                                tabIcon.setImageResource(R.drawable.select_video);
                                tabText.setTextColor(tabText.getResources().getColor(android.R.color.holo_orange_dark));
                            } else {
                                tabIcon.setImageResource(R.drawable.video);
                                tabText.setTextColor(tabText.getResources().getColor(android.R.color.black));
                            }
                            break;

                        case MILESTONE_TAB:
                            if (position == index) {
                                tabIcon.setImageResource(R.drawable.select_milestone);
                                tabText.setTextColor(tabText.getResources().getColor(android.R.color.holo_orange_dark));
                            } else {
                                tabIcon.setImageResource(R.drawable.milestone);
                                tabText.setTextColor(tabText.getResources().getColor(android.R.color.black));
                            }
                            break;
                    }
                }
            }
            if (position < mHomeTabList.size()) {
                return mHomeTabList.get(position);
            }
        }
        return null;
    }
}
