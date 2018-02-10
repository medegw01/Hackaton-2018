package com.example.michaeledegware.theeploratory;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.michaeledegware.theeploratory.MainFragments.About;
import com.example.michaeledegware.theeploratory.MainFragments.Admin;
import com.example.michaeledegware.theeploratory.MainFragments.Home;
import com.example.michaeledegware.theeploratory.MainFragments.Teacher;
import com.example.michaeledegware.theeploratory.helperclass.NavigateAdaptor;
import com.example.michaeledegware.theeploratory.helperclass.NavigateTools;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    RelativeLayout drawerpane;
    ListView lvNav;
    List<NavigateTools> listNavTools;
    List<Fragment> listFragment;

    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;}
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_slider);
        String name = getIntent().getStringExtra("name");
        TextView txt = (TextView) findViewById(R.id.tool_nameX);
        txt.setText(name);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerpane = (RelativeLayout) findViewById(R.id.drawer_pane);
        lvNav = (ListView) findViewById(R.id.nav_list);


        listNavTools = new ArrayList<NavigateTools>();
        listNavTools.add(new NavigateTools("Home",R.drawable.home));
        listNavTools.add(new NavigateTools("Teachers",R.drawable.teacher));
        listNavTools.add(new NavigateTools("Admin",R.drawable.admin));
        listNavTools.add(new NavigateTools("About",R.drawable.about));


        NavigateAdaptor navigateAdaptor = new NavigateAdaptor(getApplicationContext(), R.layout.navigator, listNavTools);
        lvNav.setAdapter(navigateAdaptor);

        listFragment = new ArrayList<Fragment>();
        listFragment.add(new Home());
        listFragment.add(new Teacher());
        listFragment.add(new Admin());
        listFragment.add(new About());


        //load first fragment as default
        FragmentManager fragmentManager =  getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, listFragment.get(0)).commit();

        setTitle(listNavTools.get(0).getToolName());
        lvNav.setItemChecked(0, true);
        drawerLayout.closeDrawer(drawerpane);
        lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //replace fragment with the selected fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_content, listFragment.get(position)).commit();

                setTitle(listNavTools.get(position).getToolName());
                lvNav.setItemChecked(position, true);
                drawerLayout.closeDrawer(drawerpane);
            }
        });
        //create listener for drawer activated
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,
                R.string.drawer_opened, R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }
}