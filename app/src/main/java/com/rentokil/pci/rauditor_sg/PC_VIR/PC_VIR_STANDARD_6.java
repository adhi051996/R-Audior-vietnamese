package com.rentokil.pci.rauditor_sg.PC_VIR;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.R;

public class PC_VIR_STANDARD_6 extends AppCompatActivity {

    String key_id;
    customfonts.Button_SF_Pro_Display_Semibold back_title_1,sub_title_1;


    RadioGroup main_rd1,main_rd2,main_rd3,main_rd4,main_rd5,main_rd6,main_rd7,main_rd8,main_rd9,
            main_rd10,main_rd11,main_rd12,main_rd13,main_rd14,main_rd15,main_rd16,main_rd17,main_rd18,
            main_rd19,main_rd20,main_rd21,main_rd22;

    String get_rd1,get_rd2,get_rd3,get_rd4,get_rd5,get_rd6,get_rd7,get_rd8,get_rd9,get_rd10,get_rd11,get_rd12,get_rd13,get_rd14
            ,get_rd15,get_rd16,get_rd17,get_rd18,get_rd19,get_rd20,get_rd21,get_rd22;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2,cv_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pc_vir_ppe_standard_6);

        db = new DatabaseHelper(PC_VIR_STANDARD_6.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv_off = new ContentValues();


        Intent intent1=getIntent();
        key_id=intent1.getStringExtra("key_id");
        Log.e("DDDDDDX"," id 1 = "+key_id);


        main_rd1 = (RadioGroup) findViewById(R.id.main_rd1);
        main_rd2 = (RadioGroup) findViewById(R.id.main_rd2);
        main_rd3 = (RadioGroup) findViewById(R.id.main_rd3);
        main_rd4 = (RadioGroup) findViewById(R.id.main_rd4);
        main_rd5 = (RadioGroup) findViewById(R.id.main_rd5);
        main_rd6 = (RadioGroup) findViewById(R.id.main_rd6);
        main_rd7 = (RadioGroup) findViewById(R.id.main_rd7);
        main_rd8 = (RadioGroup) findViewById(R.id.main_rd8);
        main_rd9 = (RadioGroup) findViewById(R.id.main_rd9);
        main_rd10 = (RadioGroup) findViewById(R.id.main_rd10);
        main_rd11 = (RadioGroup) findViewById(R.id.main_rd11);
        main_rd12 = (RadioGroup) findViewById(R.id.main_rd12);
        main_rd13 = (RadioGroup) findViewById(R.id.main_rd13);
        main_rd14 = (RadioGroup) findViewById(R.id.main_rd14);
        main_rd15 = (RadioGroup) findViewById(R.id.main_rd15);
        main_rd16 = (RadioGroup) findViewById(R.id.main_rd16);
        main_rd17 = (RadioGroup) findViewById(R.id.main_rd17);
        main_rd18 = (RadioGroup) findViewById(R.id.main_rd18);
        main_rd19 = (RadioGroup) findViewById(R.id.main_rd19);
        main_rd20 = (RadioGroup) findViewById(R.id.main_rd20);
        main_rd21 = (RadioGroup) findViewById(R.id.main_rd21);
        main_rd22 = (RadioGroup) findViewById(R.id.main_rd22);


        back_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.back_title_1);
        sub_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.sub_title_1);

        back_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(PC_VIR_STANDARD_6.this,PC_VIR_PPE_5.class);
                i.putExtra("key_id", key_id);
                startActivity(i);

            }
        });

        sub_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(validation()) {

                    insert_offline();
                }
                else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT ).show();

                }

            }
        });

        if(key_id!=null){
            get_offline(key_id);
        }

    }

    public boolean validation() {



        try {
            try {
                get_rd1 = ((RadioButton) findViewById(main_rd1.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd2 = ((RadioButton) findViewById(main_rd2.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd3 = ((RadioButton) findViewById(main_rd3.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd4 = ((RadioButton) findViewById(main_rd4.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd5 = ((RadioButton) findViewById(main_rd5.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd6 = ((RadioButton) findViewById(main_rd6.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd7 = ((RadioButton) findViewById(main_rd7.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd8 = ((RadioButton) findViewById(main_rd8.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd9 = ((RadioButton) findViewById(main_rd9.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd10 = ((RadioButton) findViewById(main_rd10.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd11 = ((RadioButton) findViewById(main_rd11.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd12 = ((RadioButton) findViewById(main_rd12.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd13 = ((RadioButton) findViewById(main_rd13.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd14 = ((RadioButton) findViewById(main_rd14.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd15 = ((RadioButton) findViewById(main_rd15.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd16 = ((RadioButton) findViewById(main_rd16.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd17 = ((RadioButton) findViewById(main_rd17.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd18 = ((RadioButton) findViewById(main_rd18.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd19 = ((RadioButton) findViewById(main_rd19.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd20 = ((RadioButton) findViewById(main_rd20.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd21 = ((RadioButton) findViewById(main_rd21.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd22 = ((RadioButton) findViewById(main_rd22.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }



            if(get_rd1.length()==0||get_rd2.length()==0||get_rd3.length()==0||get_rd4.length()==0||get_rd5.length()==0||get_rd6.length()==0
                    ||get_rd7.length()==0||get_rd8.length()==0||get_rd9.length()==0||get_rd10.length()==0||get_rd11.length()==0
                    ||get_rd12.length()==0||get_rd13.length()==0||get_rd14.length()==0||get_rd15.length()==0||get_rd16.length()==0
                    ||get_rd17.length()==0||get_rd18.length()==0||get_rd19.length()==0||get_rd20.length()==0||get_rd21.length()==0
                    ||get_rd22.length()==0){
                return false;
            }
            else {
                return  true;
            }

            //wiruthuer
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("LLLLLL",""+e.getMessage());
            return false;
        }
        //return true;
    }


    public  void insert_offline(){
        try {
            get_rd1 = ((RadioButton) findViewById(main_rd1.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd2 = ((RadioButton) findViewById(main_rd2.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd3 = ((RadioButton) findViewById(main_rd3.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd4 = ((RadioButton) findViewById(main_rd4.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd5 = ((RadioButton) findViewById(main_rd5.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd6 = ((RadioButton) findViewById(main_rd6.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd7 = ((RadioButton) findViewById(main_rd7.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd8 = ((RadioButton) findViewById(main_rd8.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd9 = ((RadioButton) findViewById(main_rd9.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd10 = ((RadioButton) findViewById(main_rd10.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd11 = ((RadioButton) findViewById(main_rd11.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd12 = ((RadioButton) findViewById(main_rd12.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd13 = ((RadioButton) findViewById(main_rd13.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd14 = ((RadioButton) findViewById(main_rd14.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd15 = ((RadioButton) findViewById(main_rd15.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd16 = ((RadioButton) findViewById(main_rd16.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd17 = ((RadioButton) findViewById(main_rd17.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd18 = ((RadioButton) findViewById(main_rd18.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd19 = ((RadioButton) findViewById(main_rd19.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd20 = ((RadioButton) findViewById(main_rd20.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd21 = ((RadioButton) findViewById(main_rd21.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd22 = ((RadioButton) findViewById(main_rd22.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }




        cv_off.put(db.et1, "" + get_rd1);
        cv_off.put(db.et2, "" + get_rd2);
        cv_off.put(db.et3, "" + get_rd3);
        cv_off.put(db.et4, "" + get_rd4);
        cv_off.put(db.et5, "" + get_rd5);
        cv_off.put(db.et6, "" + get_rd6);
        cv_off.put(db.et7, "" + get_rd7);
        cv_off.put(db.et8, "" + get_rd8);
        cv_off.put(db.et9, "" + get_rd9);
        cv_off.put(db.et10, "" + get_rd10);
        cv_off.put(db.et11, "" + get_rd11);
        cv_off.put(db.et12, "" + get_rd12);
        cv_off.put(db.et13, "" + get_rd13);
        cv_off.put(db.et14, "" + get_rd14);
        cv_off.put(db.et15, "" + get_rd15);
        cv_off.put(db.et16, "" + get_rd16);
        cv_off.put(db.et17, "" + get_rd17);
        cv_off.put(db.et18, "" + get_rd18);
        cv_off.put(db.et19, "" + get_rd19);
        cv_off.put(db.et20, "" + get_rd20);
        cv_off.put(db.et21, "" + get_rd21);
        cv_off.put(db.et22, "" + get_rd22);


        if (check_data(key_id) == 0) {

            sd.insert(db.PC_VIR_DB_STANDARD_6, null, cv_off);
            cv_off.clear();

            Intent intent = new Intent(PC_VIR_STANDARD_6.this, PC_VIR_OTHER_7.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);


        } else {
            Log.e("JJJJDDD", "e2");
            sd.update(db.PC_VIR_DB_STANDARD_6,  cv_off,"KEY_ID = '" + key_id + "'",null);
            cv_off.clear();

            Intent intent = new Intent(PC_VIR_STANDARD_6.this, PC_VIR_OTHER_7.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);

        }


    }

    public int check_data(String key_id){

        String Query="select * from "+db.PC_VIR_DB_STANDARD_6 +" where KEY_ID = '"+key_id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        return cursor.getCount();
    }

    public void get_offline(String id){

        Log.e("GGGRRRR","E2"  + id);
        String Query="select * from "+db.PC_VIR_DB_STANDARD_6 +" where KEY_ID = '"+id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        Log.e("KKKKKKKGG","E3 = "+cursor.getCount());

        if (cursor.getCount()!=0) {

            Log.e("YYYYRRR","E9");
            try {


                String met1 = cursor.getString(cursor.getColumnIndex(db.et1));
                String met2 = cursor.getString(cursor.getColumnIndex(db.et2));
                String met3 = cursor.getString(cursor.getColumnIndex(db.et3));
                String met4 = cursor.getString(cursor.getColumnIndex(db.et4));
                String met5 = cursor.getString(cursor.getColumnIndex(db.et5));
                String met6 = cursor.getString(cursor.getColumnIndex(db.et6));
                String met7 = cursor.getString(cursor.getColumnIndex(db.et7));
                String met8 = cursor.getString(cursor.getColumnIndex(db.et8));
                String met9 = cursor.getString(cursor.getColumnIndex(db.et9));
                String met10 = cursor.getString(cursor.getColumnIndex(db.et10));
                String met11 = cursor.getString(cursor.getColumnIndex(db.et11));
                String met12 = cursor.getString(cursor.getColumnIndex(db.et12));
                String met13 = cursor.getString(cursor.getColumnIndex(db.et13));
                String met14 = cursor.getString(cursor.getColumnIndex(db.et14));
                String met15 = cursor.getString(cursor.getColumnIndex(db.et15));
                String met16 = cursor.getString(cursor.getColumnIndex(db.et16));
                String met17 = cursor.getString(cursor.getColumnIndex(db.et17));
                String met18 = cursor.getString(cursor.getColumnIndex(db.et18));
                String met19 = cursor.getString(cursor.getColumnIndex(db.et19));
                String met20 = cursor.getString(cursor.getColumnIndex(db.et20));
                String met21 = cursor.getString(cursor.getColumnIndex(db.et21));
                String met22 = cursor.getString(cursor.getColumnIndex(db.et22));




//////////////////////rd1//////////
                if(met1.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd1.getChildAt(0)).setChecked(true);


                }
                else if(met1.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd1.getChildAt(1)).setChecked(true);

                }
                else if(met1.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd1.getChildAt(2)).setChecked(true);

                }
//////////////////////rd2//////////
                if(met2.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd2.getChildAt(0)).setChecked(true);


                }
                else if(met2.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd2.getChildAt(1)).setChecked(true);

                }
                else if(met2.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd2.getChildAt(2)).setChecked(true);

                }

//////////////////////rd3//////////
                if(met3.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd3.getChildAt(0)).setChecked(true);


                }
                else if(met3.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd3.getChildAt(1)).setChecked(true);

                }
                else if(met3.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd3.getChildAt(2)).setChecked(true);

                }
//////////////////////rd4//////////
                if(met4.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd4.getChildAt(0)).setChecked(true);


                }
                else if(met4.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd4.getChildAt(1)).setChecked(true);

                }
                else if(met4.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd4.getChildAt(2)).setChecked(true);

                }
//////////////////////rd5//////////
                if(met5.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd5.getChildAt(0)).setChecked(true);


                }
                else if(met5.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd5.getChildAt(1)).setChecked(true);

                }
                else if(met5.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd5.getChildAt(2)).setChecked(true);

                }
/////////////////////rd6//////////
                if(met6.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd6.getChildAt(0)).setChecked(true);


                }
                else if(met6.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd6.getChildAt(1)).setChecked(true);

                }
                else if(met6.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd6.getChildAt(2)).setChecked(true);

                }
/////////////////////rd7//////////
                if(met7.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd7.getChildAt(0)).setChecked(true);


                }
                else if(met7.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd7.getChildAt(1)).setChecked(true);

                }
                else if(met7.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd7.getChildAt(2)).setChecked(true);

                }

/////////////////////rd8//////////
                if(met8.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd8.getChildAt(0)).setChecked(true);


                }
                else if(met8.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd8.getChildAt(1)).setChecked(true);

                }
                else if(met8.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd8.getChildAt(2)).setChecked(true);

                }
/////////////////////rd9//////////
                if(met9.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd9.getChildAt(0)).setChecked(true);


                }
                else if(met9.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd9.getChildAt(1)).setChecked(true);

                }
                else if(met9.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd9.getChildAt(2)).setChecked(true);

                }
/////////////////////rd10//////////
                if(met10.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd10.getChildAt(0)).setChecked(true);


                }
                else if(met10.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd10.getChildAt(1)).setChecked(true);

                }
                else if(met10.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd10.getChildAt(2)).setChecked(true);

                }

/////////////////////rd11//////////
                if(met11.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd11.getChildAt(0)).setChecked(true);


                }
                else if(met11.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd11.getChildAt(1)).setChecked(true);

                }
                else if(met11.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd11.getChildAt(2)).setChecked(true);

                }
/////////////////////rd12//////////
                if(met12.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd12.getChildAt(0)).setChecked(true);


                }
                else if(met12.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd12.getChildAt(1)).setChecked(true);

                }
                else if(met12.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd12.getChildAt(2)).setChecked(true);

                }
/////////////////////rd13//////////
                if(met13.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd13.getChildAt(0)).setChecked(true);


                }
                else if(met13.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd13.getChildAt(1)).setChecked(true);

                }
                else if(met13.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd13.getChildAt(2)).setChecked(true);

                }
/////////////////////rd14//////////
                if(met14.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd14.getChildAt(0)).setChecked(true);


                }
                else if(met14.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd14.getChildAt(1)).setChecked(true);

                }
                else if(met14.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd14.getChildAt(2)).setChecked(true);

                }

                /////////////////////rd15//////////
                if(met15.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd15.getChildAt(0)).setChecked(true);


                }
                else if(met15.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd15.getChildAt(1)).setChecked(true);

                }
                else if(met15.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd15.getChildAt(2)).setChecked(true);

                }
                /////////////////////rd16//////////
                if(met16.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd16.getChildAt(0)).setChecked(true);


                }
                else if(met16.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd16.getChildAt(1)).setChecked(true);

                }
                else if(met16.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd16.getChildAt(2)).setChecked(true);

                }
                /////////////////////rd17//////////
                if(met17.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd17.getChildAt(0)).setChecked(true);


                }
                else if(met17.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd17.getChildAt(1)).setChecked(true);

                }
                else if(met17.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd17.getChildAt(2)).setChecked(true);

                }
/////////////////////rd18//////////
                if(met18.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd18.getChildAt(0)).setChecked(true);


                }
                else if(met18.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd18.getChildAt(1)).setChecked(true);

                }
                else if(met18.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd18.getChildAt(2)).setChecked(true);

                }

                /////////////////////rd19//////////
                if(met19.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd19.getChildAt(0)).setChecked(true);


                }
                else if(met19.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd19.getChildAt(1)).setChecked(true);

                }
                else if(met19.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd19.getChildAt(2)).setChecked(true);

                }
/////////////////////rd20//////////
                if(met20.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd20.getChildAt(0)).setChecked(true);


                }
                else if(met20.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd20.getChildAt(1)).setChecked(true);

                }
                else if(met20.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd20.getChildAt(2)).setChecked(true);

                }

/////////////////////rd21//////////
                if(met21.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd21.getChildAt(0)).setChecked(true);


                }
                else if(met21.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd21.getChildAt(1)).setChecked(true);

                }
                else if(met21.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd21.getChildAt(2)).setChecked(true);

                }



/////////////////////rd22//////////
                if(met22.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd22.getChildAt(0)).setChecked(true);


                }
                else if(met22.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd22.getChildAt(1)).setChecked(true);

                }
                else if(met22.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd22.getChildAt(2)).setChecked(true);

                }



            } catch (Exception e) {

                Log.e("FFFFVV","error ="+e.getMessage());
                e.printStackTrace();
            }
        } else {

            Log.e("OOPPP","NNNN Ess");
        }
    }


}