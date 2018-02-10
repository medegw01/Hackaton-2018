package com.example.michaeledegware.theeploratory.helperclass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michaeledegware.theeploratory.R;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Michael on 2/9/2018.
 */
public class NavigateAdaptor extends ArrayAdapter<NavigateTools> {
    Context context;
    int reLayout;
    List<NavigateTools> listToolNav;


    public NavigateAdaptor(Context context, int reLayout, List<NavigateTools> listToolNav) {
        super(context, reLayout, listToolNav);
        this.context = context;
        this.listToolNav = listToolNav;
        this.reLayout = reLayout;
    }
    //to modify content....

    @SuppressLint("Viewholder")@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, reLayout, null);
        TextView title = (TextView) v.findViewById(R.id.tool_title);

        ImageView navIcon = (ImageView)v.findViewById(R.id.tool_image);

        NavigateTools navigateTools = listToolNav.get(position);
        title.setText(navigateTools.getToolName());
        navIcon.setImageResource(navigateTools.getResIcon());

        return v;
    }
}
