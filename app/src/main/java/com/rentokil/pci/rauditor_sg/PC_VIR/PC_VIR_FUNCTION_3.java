package com.rentokil.pci.rauditor_sg.PC_VIR;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.rentokil.pci.rauditor_sg.Category_Type_Activity;
import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.R;

public class PC_VIR_FUNCTION_3 extends AppCompatActivity {

    String key_id;

    customfonts.Button_SF_Pro_Display_Semibold back_title_1,sub_title_1;


    RadioGroup main_rd1,main_rd2,main_rd3,main_rd4,main_rd5,main_rd6,main_rd7,main_rd8,main_rd9,
            main_rd10,main_rd11,main_rd12,main_rd14;

    EditText et_client_13;

    String get_rd1,get_rd2,get_rd3,get_rd4,get_rd5,get_rd6,get_rd7,get_rd8,get_rd9,get_rd10,get_rd11,get_rd12,get_rd14;

    String get_et_client_13;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2,cv_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pc_vir_function_3);

        Toolbar mTopToolbar;

        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTopToolbar.setTitle("");
        mTopToolbar.setSubtitle("");
        setSupportActionBar(mTopToolbar);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.menuicon);
        mTopToolbar.setOverflowIcon(drawable);
        db = new DatabaseHelper(PC_VIR_FUNCTION_3.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv_off = new ContentValues();

        back_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.back_title_1);
        sub_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.sub_title_1);

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
        main_rd14 = (RadioGroup) findViewById(R.id.main_rd14);

        et_client_13 = (EditText) findViewById(R.id.et_client_13);

        Intent intent1=getIntent();
        key_id=intent1.getStringExtra("key_id");
        Log.e("DDDDDDX"," id 1 = "+key_id);

        back_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(PC_VIR_FUNCTION_3.this,PC_VIR_BODY_2.class);
                i.putExtra("key_id", key_id);
                startActivity(i);
            }
        });

        sub_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validation()) {

                    insert_offline("New");
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
                get_rd14 = ((RadioButton) findViewById(main_rd14.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_et_client_13 = et_client_13.getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }


            if(TextUtils.isEmpty(get_et_client_13)) {

                if(TextUtils.isEmpty(get_et_client_13 )){
                    et_client_13.setError("Required");
                }

                return false;
            }

            if(get_rd1.length()==0||get_rd2.length()==0||get_rd3.length()==0||get_rd4.length()==0||get_rd5.length()==0||get_rd6.length()==0
                    ||get_rd7.length()==0||get_rd8.length()==0||get_rd9.length()==0||get_rd10.length()==0||get_rd11.length()==0
                    ||get_rd12.length()==0||get_rd14.length()==0||get_et_client_13.length()==0){
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

    private void insert_offline(String Status) {


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
            get_rd14 = ((RadioButton) findViewById(main_rd14.getCheckedRadioButtonId())).getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            get_et_client_13 = et_client_13.getText().toString();
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
        cv_off.put(db.et13, "" + get_et_client_13);
        cv_off.put(db.et14, "" + get_rd14);



        if (check_data(key_id) == 0) {

            sd.insert(db.PC_VIR_DB_FUNCTION_3, null, cv_off);
            cv_off.clear();

            Intent intent = new Intent(PC_VIR_FUNCTION_3.this, PC_VIR_GENERAL_4.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);


        } else {
            Log.e("JJJJDDD", "e2");
            sd.update(db.PC_VIR_DB_FUNCTION_3,  cv_off,"KEY_ID = '" + key_id + "'",null);
            cv_off.clear();

            Intent intent = new Intent(PC_VIR_FUNCTION_3.this, PC_VIR_GENERAL_4.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);

        }

    }

    public int check_data(String key_id){
        String Query="select * from "+db.PC_VIR_DB_FUNCTION_3 +" where KEY_ID = '"+key_id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        return cursor.getCount();
    }

    public void get_offline(String id){

        Log.e("GGGRRRR","E2"  + id);
        String Query="select * from "+db.PC_VIR_DB_FUNCTION_3 +" where KEY_ID = '"+id+"'";
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


                et_client_13.setText(met13);

//////////////////////rd1//////////
                if(met1.equalsIgnoreCase("Pass")){
                    ((RadioButton) main_rd1.getChildAt(0)).setChecked(true);


                }
                else if(met1.equalsIgnoreCase("Fail")){

                    ((RadioButton) main_rd1.getChildAt(1)).setChecked(true);

                }
                else if(met1.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd1.getChildAt(2)).setChecked(true);

                }
//////////////////////rd2//////////
                if(met2.equalsIgnoreCase("Pass")){
                    ((RadioButton) main_rd2.getChildAt(0)).setChecked(true);


                }
                else if(met2.equalsIgnoreCase("Fail")){

                    ((RadioButton) main_rd2.getChildAt(1)).setChecked(true);

                }
                else if(met2.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd2.getChildAt(2)).setChecked(true);

                }

//////////////////////rd3//////////
                if(met3.equalsIgnoreCase("Pass")){
                    ((RadioButton) main_rd3.getChildAt(0)).setChecked(true);


                }
                else if(met3.equalsIgnoreCase("Fail")){

                    ((RadioButton) main_rd3.getChildAt(1)).setChecked(true);

                }
                else if(met3.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd3.getChildAt(2)).setChecked(true);

                }
//////////////////////rd4//////////
                if(met4.equalsIgnoreCase("Pass")){
                    ((RadioButton) main_rd4.getChildAt(0)).setChecked(true);


                }
                else if(met4.equalsIgnoreCase("Fail")){

                    ((RadioButton) main_rd4.getChildAt(1)).setChecked(true);

                }
                else if(met4.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd4.getChildAt(2)).setChecked(true);

                }
//////////////////////rd5//////////
                if(met5.equalsIgnoreCase("Pass")){
                    ((RadioButton) main_rd5.getChildAt(0)).setChecked(true);


                }
                else if(met5.equalsIgnoreCase("Fail")){

                    ((RadioButton) main_rd5.getChildAt(1)).setChecked(true);

                }
                else if(met5.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd5.getChildAt(2)).setChecked(true);

                }
/////////////////////rd6//////////
                if(met6.equalsIgnoreCase("Pass")){
                    ((RadioButton) main_rd6.getChildAt(0)).setChecked(true);


                }
                else if(met6.equalsIgnoreCase("Fail")){

                    ((RadioButton) main_rd6.getChildAt(1)).setChecked(true);

                }
                else if(met6.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd6.getChildAt(2)).setChecked(true);

                }
/////////////////////rd7//////////
                if(met7.equalsIgnoreCase("Pass")){
                    ((RadioButton) main_rd7.getChildAt(0)).setChecked(true);


                }
                else if(met7.equalsIgnoreCase("Fail")){

                    ((RadioButton) main_rd7.getChildAt(1)).setChecked(true);

                }
                else if(met7.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd7.getChildAt(2)).setChecked(true);

                }

/////////////////////rd8//////////
                if(met8.equalsIgnoreCase("Pass")){
                    ((RadioButton) main_rd8.getChildAt(0)).setChecked(true);


                }
                else if(met8.equalsIgnoreCase("Fail")){

                    ((RadioButton) main_rd8.getChildAt(1)).setChecked(true);

                }
                else if(met8.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd8.getChildAt(2)).setChecked(true);

                }
/////////////////////rd9//////////
                if(met9.equalsIgnoreCase("Pass")){
                    ((RadioButton) main_rd9.getChildAt(0)).setChecked(true);


                }
                else if(met9.equalsIgnoreCase("Fail")){

                    ((RadioButton) main_rd9.getChildAt(1)).setChecked(true);

                }
                else if(met9.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd9.getChildAt(2)).setChecked(true);

                }
/////////////////////rd10//////////
                if(met10.equalsIgnoreCase("Good")){
                    ((RadioButton) main_rd10.getChildAt(0)).setChecked(true);


                }
                else if(met10.equalsIgnoreCase("Fair")){

                    ((RadioButton) main_rd10.getChildAt(1)).setChecked(true);

                }
                else if(met10.equalsIgnoreCase("Poor")){

                    ((RadioButton) main_rd10.getChildAt(2)).setChecked(true);

                }
                else if(met10.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd10.getChildAt(3)).setChecked(true);

                }

/////////////////////rd11//////////
                if(met11.equalsIgnoreCase("Good")){
                    ((RadioButton) main_rd11.getChildAt(0)).setChecked(true);


                }
                else if(met11.equalsIgnoreCase("Fair")){

                    ((RadioButton) main_rd11.getChildAt(1)).setChecked(true);

                }
                else if(met11.equalsIgnoreCase("Poor")){

                    ((RadioButton) main_rd11.getChildAt(2)).setChecked(true);

                }
                else if(met11.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd11.getChildAt(3)).setChecked(true);

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
                if(met14.equalsIgnoreCase("Pass")){
                    ((RadioButton) main_rd14.getChildAt(0)).setChecked(true);


                }
                else if(met14.equalsIgnoreCase("Fail")){

                    ((RadioButton) main_rd14.getChildAt(1)).setChecked(true);

                }
                else if(met14.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd14.getChildAt(2)).setChecked(true);

                }




            } catch (Exception e) {

                Log.e("FFFFVV","error ="+e.getMessage());
                e.printStackTrace();
            }
        } else {

            Log.e("OOPPP","NNNN Ess");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu_vir, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.vir_home:
                Intent sir_home = new Intent(PC_VIR_FUNCTION_3.this, Category_Type_Activity.class);
                sir_home.putExtra("key_id", key_id);
                startActivity(sir_home);
                break;
            case R.id.vir_title_page_1:
                if (key_id != null) {
                    Intent sir_customer = new Intent(PC_VIR_FUNCTION_3.this, PC_VIR_TITLE_1.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.vir_body_2:

                if (key_id != null) {
                    Intent sir_customer = new Intent(PC_VIR_FUNCTION_3.this, PC_VIR_BODY_2.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }

                break;
            case R.id.vir_funct_3:
                Toast.makeText(getApplicationContext(),"Already,You Are in Same Page",Toast.LENGTH_SHORT).show();
                break;

            case R.id.vir_gen_4:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_FUNCTION_3.this, PC_VIR_GENERAL_4.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_ppe_5:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_FUNCTION_3.this, PC_VIR_PPE_5.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_stan_6:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_FUNCTION_3.this, PC_VIR_STANDARD_6.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_other_7:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_FUNCTION_3.this, PC_VIR_OTHER_7.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
