package com.example.michaeledegware.theeploratory.teacherFragment;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.michaeledegware.theeploratory.R;
import com.example.michaeledegware.theeploratory.helperclass.ListviewContactAdapter;

import java.io.File;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class attendance extends Fragment {

    public attendance(){}
    ArrayList<String> contactlist;
    private static final int PERMISSION_REQUEST_CODE = 200;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.take_attendance, container, false);

        ArrayList<String> listContact = GetStudentList();
        ListView lv = (ListView)getActivity().findViewById(R.id.lt);
        contactlist = new ArrayList<String>();
        lv.setAdapter(new ListviewContactAdapter(getContext(), listContact));

        return rootView;
    }

    private ArrayList<String> GetStudentList(){
        ArrayList<String> contactlist = new ArrayList<String>();


        return contactlist;
    }

    private void requestPermissionAndContinue() {

        if (ContextCompat.checkSelfPermission(getActivity(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Teacher's request");
                alertBuilder.setMessage("Grant permission to write to device");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            readExcelFile("");
        }
    }

    private void readExcelFile(String filename) {
        try{
            File sdpath = Environment.getExternalStorageDirectory();
            String path = sdpath.getAbsolutePath() + "/The Exploratory/registrationInfo.xls";
            File file = new File(path);

            // Read Data from an Excel file using jxl api
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sh = workbook.getSheet(0);
            for (int rows = 0; rows < sh.getRows(); rows++) {
                contactlist.add(sh.getCell(0, rows).getContents() + " " + sh.getCell(1, rows).getContents());
            }

                    }catch (Exception e){e.printStackTrace(); }

        return;
    }
}