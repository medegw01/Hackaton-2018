package com.example.michaeledegware.theeploratory.helperclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.michaeledegware.theeploratory.R;

import java.util.ArrayList;

/**
 * Created by Michael Edegware on 2/10/2018.
 */

public class ListviewContactAdapter  extends BaseAdapter {
        private static ArrayList<String> listContact;

        private LayoutInflater mInflater;

        public ListviewContactAdapter(Context photosFragment, ArrayList<String> results){
            listContact = results;
            mInflater = LayoutInflater.from(photosFragment);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return listContact.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return listContact.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }


    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.tv, null);
            holder = new ViewHolder();
            holder.txtname = (TextView) convertView.findViewById(R.id.kkkk);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

       /* holder.txtname.setText(listContact.get(position);*/


        return convertView;
    }

    static class ViewHolder{
        TextView txtname;
    }

}
