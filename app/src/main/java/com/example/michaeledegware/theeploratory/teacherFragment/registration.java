package com.example.michaeledegware.theeploratory.teacherFragment;



import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.Number;
import com.example.michaeledegware.theeploratory.R;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.ContentValues.TAG;



public class registration extends Fragment implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_CODE = 200;
    EditText first_name, last_name, age, other_information;
    private void saveFile(String Filename) {
       boolean append = true;
        try {
            File sdpath = Environment.getExternalStorageDirectory();
            String path = sdpath.getAbsolutePath() + "/The Exploratory/";
            File folder = new File(path);
            if(!folder.exists()){
                folder.mkdir();
                append = false;
            }
            File file = new File(folder, "registrationInfo.xls");

            // Write Data to an Excel file using jxl api
            if(!append) {
                WritableWorkbook wworkbook = Workbook.createWorkbook(file);
                WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
                /*titles*/
                wsheet.addCell(new Label(0, 0, "First Name"));
                wsheet.addCell(new Label(1, 0, "Last Name"));
                wsheet.addCell(new Label(2, 0, "Age"));
                wsheet.addCell(new Label(3, 0, "Other Information"));

                wsheet.addCell(new Label(0, 1, first_name.getText().toString()));
                wsheet.addCell(new Label(1, 1, last_name.getText().toString()));
                wsheet.addCell(new Number(2, 1, Integer.valueOf(age.getText().toString())));
                wsheet.addCell(new Label(3, 1, other_information.getText().toString()));

                wworkbook.write();
                wworkbook.close();
            }
            else {
                // Append Data to an Excel file using jxl api
                Workbook workbook1 = Workbook.getWorkbook(file);
                WritableWorkbook copy = Workbook.createWorkbook(file, workbook1);
                WritableSheet wsheet = copy.getSheet(0);
                Sheet sh2 = copy.getSheet(0);
                int nth_row = sh2.getRows();

                wsheet.addCell(new Label(0, nth_row, first_name.getText().toString()));
                wsheet.addCell(new Label(1, nth_row, last_name.getText().toString()));
                wsheet.addCell(new Number(2, nth_row, Integer.valueOf(age.getText().toString())));
                wsheet.addCell(new Label(3, nth_row, other_information.getText().toString()));
                copy.write();
                copy.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return;

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
             saveFile("");
         }
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_student, container, false);

       Button writeExcelButton = (Button) v.findViewById(R.id.save);
       first_name = (EditText) v.findViewById(R.id.first_name);
       last_name = (EditText) v.findViewById(R.id.last_name);
       age = (EditText) v.findViewById(R.id.age);
       other_information = (EditText) v.findViewById(R.id.other_info);
       writeExcelButton.setOnClickListener(this);

       return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                requestPermissionAndContinue();
                break;
        }
    }

}