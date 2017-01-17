package mf.andorid.com.mfinfo.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import mf.andorid.com.mfinfo.Fragments.listMutualFund;
import mf.andorid.com.mfinfo.Fragments.mfInfoFragment;
import mf.andorid.com.mfinfo.Fragments.userPortfolioFragment;
import mf.andorid.com.mfinfo.Fragments.userWishlistFragment;
import mf.andorid.com.mfinfo.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new mfInfoFragment(), "MF");
        adapter.addFragment(new listMutualFund(), "List");
        adapter.addFragment(new userPortfolioFragment(), "Portfolio");
        adapter.addFragment(new userWishlistFragment(), "Watchlist");
        adapter.addFragment(new userPortfolioFragment(), "Feedback");

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(myOnPageChangeListener);
    }
    private OnPageChangeListener myOnPageChangeListener = new OnPageChangeListener(){
        @Override
        public void onPageScrollStateChanged(int state) {
            //Called when the scroll state changes.
        }

        @Override
        public void onPageScrolled(int position,
                                   float positionOffset, int positionOffsetPixels) {
            //This method will be invoked when the current page is scrolled,
            //either as part of a programmatically initiated smooth scroll
            //or a user initiated touch scroll.
        }

        @Override
        public void onPageSelected(int position) {
            //This method will be invoked when a new page becomes selected.
            System.out.print("ONPageSelected");

        }


    };




    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
