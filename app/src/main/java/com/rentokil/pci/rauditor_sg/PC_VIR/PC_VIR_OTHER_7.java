package com.rentokil.pci.rauditor_sg.PC_VIR;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.rentokil.pci.rauditor_sg.Category_Type_Activity;
import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PC_VIR_OTHER_7 extends AppCompatActivity {

    String key_id;
    customfonts.Button_SF_Pro_Display_Semibold back_title_1,sub_title_1;


    RadioGroup main_rd1,main_rd2,main_rd3,main_rd4,main_rd5,main_rd6,main_rd7,main_rd8;

    String get_rd1,get_rd2,get_rd3,get_rd4,get_rd5,get_rd6,get_rd7,get_rd8;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv_main,cv_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pc_vir_other_7);

        Toolbar mTopToolbar;

        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTopToolbar.setTitle("");
        mTopToolbar.setSubtitle("");
        setSupportActionBar(mTopToolbar);


        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.menuicon);
        mTopToolbar.setOverflowIcon(drawable);

        db = new DatabaseHelper(PC_VIR_OTHER_7.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv_main = new ContentValues();
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


        back_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.back_title_1);
        sub_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.sub_title_1);

        back_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(PC_VIR_OTHER_7.this,PC_VIR_STANDARD_6.class);
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


            if(get_rd1.length()==0||get_rd2.length()==0||get_rd3.length()==0||get_rd4.length()==0||get_rd5.length()==0||get_rd6.length()==0
                    ||get_rd7.length()==0||get_rd8.length()==0){
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_time = sdf.format(new Date());
        try {
            cv_main.put(db.COMPLETED_DATE,date_time);
            cv_main.put(db.STATUS,"Completed");
            sd.update( db.PC_VIR_DB_TITLE_1, cv_main, db.KEY_ID + "=" + key_id + "", null );
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(),"Report Completed Successfully,Please Sync",Toast.LENGTH_SHORT).show();

        cv_off.put(db.et1, "" + get_rd1);
        cv_off.put(db.et2, "" + get_rd2);
        cv_off.put(db.et3, "" + get_rd3);
        cv_off.put(db.et4, "" + get_rd4);
        cv_off.put(db.et5, "" + get_rd5);
        cv_off.put(db.et6, "" + get_rd6);
        cv_off.put(db.et7, "" + get_rd7);
        cv_off.put(db.et8, "" + get_rd8);
        cv_off.put(db.BRANCH_END_DATE,date_time);


        if (check_data(key_id) == 0) {

            sd.insert(db.PC_VIR_DB_OTHER_7, null, cv_off);
            cv_off.clear();

            Intent intent = new Intent(PC_VIR_OTHER_7.this, Category_Type_Activity.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);


        } else {
            Log.e("JJJJDDD", "e2");
            sd.update(db.PC_VIR_DB_OTHER_7,  cv_off,"KEY_ID = '" + key_id + "'",null);
            cv_off.clear();

            Intent intent = new Intent(PC_VIR_OTHER_7.this, Category_Type_Activity.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);

        }


    }

    public int check_data(String key_id){

        String Query="select * from "+db.PC_VIR_DB_OTHER_7 +" where KEY_ID = '"+key_id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        return cursor.getCount();
    }

    public void get_offline(String id){

        Log.e("GGGRRRR","E2"  + id);
        String Query="select * from "+db.PC_VIR_DB_OTHER_7 +" where KEY_ID = '"+id+"'";
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


//////////////////////rd1//////////
                if(met1.equalsIgnoreCase("complaint")){
                    ((RadioButton) main_rd1.getChildAt(0)).setChecked(true);


                }
                else if(met1.equalsIgnoreCase("Non-complaint")){

                    ((RadioButton) main_rd1.getChildAt(1)).setChecked(true);

                }
                else if(met1.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd1.getChildAt(2)).setChecked(true);

                }
//////////////////////rd2//////////
                if(met2.equalsIgnoreCase("complaint")){
                    ((RadioButton) main_rd2.getChildAt(0)).setChecked(true);


                }
                else if(met2.equalsIgnoreCase("Non-complaint")){

                    ((RadioButton) main_rd2.getChildAt(1)).setChecked(true);

                }
                else if(met2.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd2.getChildAt(2)).setChecked(true);

                }

//////////////////////rd3//////////
                if(met3.equalsIgnoreCase("complaint")){
                    ((RadioButton) main_rd3.getChildAt(0)).setChecked(true);


                }
                else if(met3.equalsIgnoreCase("Non-complaint")){

                    ((RadioButton) main_rd3.getChildAt(1)).setChecked(true);

                }
                else if(met3.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd3.getChildAt(2)).setChecked(true);

                }
//////////////////////rd4//////////
                if(met4.equalsIgnoreCase("complaint")){
                    ((RadioButton) main_rd4.getChildAt(0)).setChecked(true);


                }
                else if(met4.equalsIgnoreCase("Non-complaint")){

                    ((RadioButton) main_rd4.getChildAt(1)).setChecked(true);

                }
                else if(met4.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd4.getChildAt(2)).setChecked(true);

                }
//////////////////////rd5//////////
                if(met5.equalsIgnoreCase("complaint")){
                    ((RadioButton) main_rd5.getChildAt(0)).setChecked(true);


                }
                else if(met5.equalsIgnoreCase("Non-complaint")){

                    ((RadioButton) main_rd5.getChildAt(1)).setChecked(true);

                }
                else if(met5.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd5.getChildAt(2)).setChecked(true);

                }
/////////////////////rd6//////////
                if(met6.equalsIgnoreCase("complaint")){
                    ((RadioButton) main_rd6.getChildAt(0)).setChecked(true);


                }
                else if(met6.equalsIgnoreCase("Non-complaint")){

                    ((RadioButton) main_rd6.getChildAt(1)).setChecked(true);

                }
                else if(met6.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd6.getChildAt(2)).setChecked(true);

                }
/////////////////////rd7//////////
                if(met7.equalsIgnoreCase("complaint")){
                    ((RadioButton) main_rd7.getChildAt(0)).setChecked(true);


                }
                else if(met7.equalsIgnoreCase("Non-complaint")){

                    ((RadioButton) main_rd7.getChildAt(1)).setChecked(true);

                }
                else if(met7.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd7.getChildAt(2)).setChecked(true);

                }

/////////////////////rd8//////////
                if(met8.equalsIgnoreCase("complaint")){
                    ((RadioButton) main_rd8.getChildAt(0)).setChecked(true);


                }
                else if(met8.equalsIgnoreCase("Non-complaint")){

                    ((RadioButton) main_rd8.getChildAt(1)).setChecked(true);

                }
                else if(met8.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd8.getChildAt(2)).setChecked(true);

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
                Intent sir_home = new Intent(PC_VIR_OTHER_7.this, Category_Type_Activity.class);
                sir_home.putExtra("key_id", key_id);
                startActivity(sir_home);
                break;
            case R.id.vir_title_page_1:
                if (key_id != null) {
                    Intent sir_customer = new Intent(PC_VIR_OTHER_7.this, PC_VIR_TITLE_1.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.vir_body_2:
                if (key_id != null) {
                    Intent sir_customer = new Intent(PC_VIR_OTHER_7.this, PC_VIR_BODY_2.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.vir_funct_3:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_OTHER_7.this, PC_VIR_FUNCTION_3.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_gen_4:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_OTHER_7.this, PC_VIR_GENERAL_4.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_ppe_5:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_OTHER_7.this, PC_VIR_PPE_5.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_stan_6:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_OTHER_7.this, PC_VIR_STANDARD_6.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_other_7:
                Toast.makeText(getApplicationContext(),"Already,You Are in Same Page",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    

}