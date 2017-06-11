package testapp.example.com.testapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HomeTabAdapter mHomeTabAdapter;
    private ViewPager mHomePager;
    private ViewPager mTrandingMediaPager;
    private TrendingStoryAdapter mStoryAdapter;
    private TabLayout mHomeTabs;
    private List<MediaModel> mMediaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        createDummyMediaModel();
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mTrandingMediaPager = (ViewPager) findViewById(R.id.trending_media_pager);
        mHomePager = (ViewPager) findViewById(R.id.home_pager);
        mHomeTabs = (TabLayout) findViewById(R.id.home_tab);
        mStoryAdapter = new TrendingStoryAdapter(mMediaList);
        mTrandingMediaPager.setAdapter(mStoryAdapter);
        mHomeTabs.setSelectedTabIndicatorColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
        mHomeTabAdapter = new HomeTabAdapter(this, getSupportFragmentManager());
        PageIndicatorView pageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(mTrandingMediaPager);
        pageIndicatorView.setRadius(4);
        pageIndicatorView.setAnimationType(AnimationType.WORM);
        addHomeTabList();
        mHomePager.setAdapter(mHomeTabAdapter);
        mHomePager.addOnPageChangeListener(mOnPageChangeListener);
        mHomePager.setOffscreenPageLimit(mHomeTabAdapter.getCount());
        if (mHomeTabs != null && mHomePager != null) {
            mHomeTabs.setupWithViewPager(mHomePager);
            for (int index = 0; index < mHomeTabs.getTabCount(); index++) {
                TabLayout.Tab tab = mHomeTabs.getTabAt(index);
                if (tab != null) {
                    tab.setCustomView(mHomeTabAdapter.getTabView(this, index));
                }
            }
        }
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(0);
        }
    }

    private void addHomeTabList() {
        if (mHomeTabAdapter != null) {
            HomeTab imageTab = new HomeTab();
            imageTab.setHomeTabType(HomeTabType.IMAGE_TAB);
            imageTab.setHomeTabTitle(getString(R.string.image));

            HomeTab videoTab = new HomeTab();
            videoTab.setHomeTabType(HomeTabType.VIDEO_TAB);
            videoTab.setHomeTabTitle(getString(R.string.video));

            HomeTab milestoneTab = new HomeTab();
            milestoneTab.setHomeTabType(HomeTabType.MILESTONE_TAB);
            milestoneTab.setHomeTabTitle(getString(R.string.milestone));

            mHomeTabAdapter.addTab(videoTab);
            mHomeTabAdapter.addTab(imageTab);
            mHomeTabAdapter.addTab(milestoneTab);
        }
    }

    private void createDummyMediaModel() {
        for (int index = 0; index < 5; index++) {
            MediaModel model = new MediaModel();
            model.setAlbumTitle("BABY FT JUSTIN BABER");
            model.setAlbumDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
            model.setReleaseDate("20 HOURS AGO");
            model.setAlbumImage(R.drawable.mtv);
            mMediaList.add(model);
        }
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            if (mHomeTabAdapter != null)
                mHomeTabAdapter.getSelectedTab(mHomeTabs, position);
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
