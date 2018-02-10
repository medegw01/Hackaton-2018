package com.example.michaeledegware.theeploratory.MainFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michaeledegware.theeploratory.R;


/**
 * Created by Michael on 2/9/2018.
 */
public class Admin extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return  inflater.inflate(R.layout.admin, container, false);
    }
}
