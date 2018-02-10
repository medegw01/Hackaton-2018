package com.example.michaeledegware.theeploratory.MainFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import com.example.michaeledegware.theeploratory.helperclass.MyFragmentAdaptor;

import java.util.List;
import java.util.Vector;

import com.example.michaeledegware.theeploratory.R;
import com.example.michaeledegware.theeploratory.teacherFragment.attendance;
import com.example.michaeledegware.theeploratory.teacherFragment.registration;
import com.example.michaeledegware.theeploratory.teacherFragment.upload;

public class Teacher extends Fragment implements
        ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener  {

    ViewPager viewPager;
    TabHost tabHost;
    MyFragmentAdaptor myFragmentAdaptor;
    HorizontalScrollView horizontalScrollView;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the activated for this fragment
        //TabHostView goes here
        /******************* TabHostView**************************************************/
        v = inflater.inflate(R.layout.teacher, container, false);
        this.intViewPage();
        this.intTabHost(savedInstanceState);

      /********************************************************************************/
        return v;
    }
    public class FakeContent implements TabHost.TabContentFactory {
        Context context;

        public FakeContent(Context context) {
            this.context = context;
        }

        @Override
        public View createTabContent(String tag) {
            View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }

    private void intTabHost(Bundle savedInstanceState){
        tabHost = (TabHost) v.findViewById(R.id.tabHost);
        tabHost.setup();
        //modify according to the number of teacher's options
        String[] tabName = {"Register New Student","Take Attendance","Upload Student Info"};

        for(int  i = 0;i<tabName.length;i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabName[i]);
            tabSpec.setIndicator(tabName[i]);
            tabSpec.setContent(new FakeContent(getActivity()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
    }

    private void intViewPage(){
        List<Fragment> ListFragment = new Vector<Fragment>();
        //add fragments for each tool
        ListFragment.add(new registration());
        ListFragment.add(new attendance());
        ListFragment.add(new upload());

        
        this.myFragmentAdaptor = new MyFragmentAdaptor(
                getChildFragmentManager(),ListFragment);
        this.viewPager = (ViewPager) v.findViewById(R.id.view_Pager);
        this.viewPager.setAdapter(this.myFragmentAdaptor);
        this.viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);

    }
    //Viewpage Listener
    @Override
    public void onPageScrollStateChanged(int state) {


    }
    //tabhost Listener
    @Override
    public void onTabChanged(String tabId) {
        int tabselected = tabHost.getCurrentTab();
        viewPager.setCurrentItem(tabselected);

        horizontalScrollView = (HorizontalScrollView) v.findViewById(R.id.horizontalScroll);
        View tabview = tabHost.getCurrentTabView();
        int scrollposition = tabview.getLeft() -
                (horizontalScrollView.getWidth()-tabview.getWidth())/2;
        horizontalScrollView.smoothScrollTo(scrollposition, 0);

    }

}