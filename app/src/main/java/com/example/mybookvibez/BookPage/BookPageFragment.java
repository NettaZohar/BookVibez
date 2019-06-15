package com.example.mybookvibez.BookPage;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.mybookvibez.BookItem;
import com.example.mybookvibez.R;

import java.util.ArrayList;
import java.util.List;

public class BookPageFragment extends Fragment {

    public static BookItem bookToDisplay = null;

    public static CollapsingToolbarLayout collapsingToolbar;
    public static ImageView bookmarkImg, bookImg;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.book_page_fragment, container, false);

        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        bookmarkImg = (ImageView) view.findViewById(R.id.bookmark);
        bookImg = (ImageView) view.findViewById(R.id.toolbar_image);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager_book_page);
        setupViewPager(viewPager);

        TabLayout tabs = (TabLayout) view.findViewById(R.id.tab_layout_book_page);
        tabs.setupWithViewPager(viewPager);


       return view;
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        BookPageFragment.Adapter adapter = new BookPageFragment.Adapter(getChildFragmentManager());

        adapter.addFragment(new BookPageTabDetails(), "Details");
        adapter.addFragment(new BookPageTabTimeline(), "Timeline");

        viewPager.setAdapter(adapter);
    }


    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
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