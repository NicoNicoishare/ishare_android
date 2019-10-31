package com.example.yang.iShare;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    private TabLayout tabs;
    View view;
    ViewPager vp;
    FragmentPagerAdapter adapter;
    private AboutFollowFragment aboutFollowFragment;
    private AboutMeFragment aboutMeFragment;
    List<String> list_title;

    public static AboutFragment newInstance(String param1) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public AboutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("已关注"), true);//添加 Tab,默认选中
        tabLayout.addTab(tabLayout.newTab().setText("你"), false);//添加 Tab,默认不选中
        vp = (ViewPager) view.findViewById(R.id.pager);
        vp.setOffscreenPageLimit(1);
        list_title = new ArrayList<>();
        list_title.add("已关注");
        list_title.add("你");

        return view;
    }

    
}