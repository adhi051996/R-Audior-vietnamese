package com.rentokil.pci.rauditor_vn.PC_VIR;

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

import com.rentokil.pci.rauditor_vn.Category_Type_Activity;
import com.rentokil.pci.rauditor_vn.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_vn.R;

public class PC_VIR_GENERAL_4 extends AppCompatActivity {

    String key_id;

    customfonts.Button_SF_Pro_Display_Semibold back_title_1,sub_title_1;

    RadioGroup main_rd1,main_rd2,main_rd3,main_rd4,main_rd5,main_rd6;

    EditText et_remarks_7;

    String get_rd1,get_rd2,get_rd3,get_rd4,get_rd5,get_rd6;

    String get_remarks_7;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2,cv_off;

    private  int PC_VIR_4_counter_PASS=0;
    private  int PC_VIR_4_counter_FAIL=0;
    private  double PC_VIR_4_counter_FAIR_1=0.0;
    private  double PC_VIR_4_counter_FAIR_2=0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pc_vir_general_4);

        Toolbar mTopToolbar;

        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTopToolbar.setTitle("");
        mTopToolbar.setSubtitle("");
        setSupportActionBar(mTopToolbar);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.menuicon);
        mTopToolbar.setOverflowIcon(drawable);

        db = new DatabaseHelper(PC_VIR_GENERAL_4.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv_off = new ContentValues();


        Intent intent1=getIntent();
        key_id=intent1.getStringExtra("key_id");
        Log.e("DDDDDDX"," id 1 = "+key_id);

        back_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.back_title_1);
        sub_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.sub_title_1);

        main_rd1 = (RadioGroup) findViewById(R.id.main_rd1);
        main_rd2 = (RadioGroup) findViewById(R.id.main_rd2);
        main_rd3 = (RadioGroup) findViewById(R.id.main_rd3);
        main_rd4 = (RadioGroup) findViewById(R.id.main_rd4);
        main_rd5 = (RadioGroup) findViewById(R.id.main_rd5);
        main_rd6 = (RadioGroup) findViewById(R.id.main_rd6);

        et_remarks_7 = (EditText) findViewById(R.id.et_remarks_7);



        back_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(PC_VIR_GENERAL_4.this,PC_VIR_FUNCTION_3.class);
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
                get_remarks_7 = et_remarks_7.getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }


            if(TextUtils.isEmpty(get_remarks_7)) {

                if(TextUtils.isEmpty(get_remarks_7 )){
                    et_remarks_7.setError("Required");
                }

                return false;
            }

            if(get_rd1.length()==0||get_rd2.length()==0||get_rd3.length()==0||get_rd4.length()==0||get_rd5.length()==0||get_rd6.length()==0
                    ||get_remarks_7.length()==0 ){
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

            if(get_rd1.equalsIgnoreCase("Yes")){
                PC_VIR_4_counter_PASS++;
            }
            else if (get_rd1.equalsIgnoreCase("No")){
                PC_VIR_4_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd2 = ((RadioButton) findViewById(main_rd2.getCheckedRadioButtonId())).getText().toString();

            if(get_rd2.equalsIgnoreCase("Good")){
                PC_VIR_4_counter_PASS++;
            }
            else if (get_rd2.equalsIgnoreCase("Fair")){


                PC_VIR_4_counter_FAIR_1++;

                Log.e("AASXCVAA","before = "+PC_VIR_4_counter_FAIR_1);

                PC_VIR_4_counter_FAIR_1 = PC_VIR_4_counter_FAIR_1 / 2;



                Log.e("AASXCVAA","afters 2= "+PC_VIR_4_counter_FAIR_1);
            }
            else if (get_rd2.equalsIgnoreCase("Poor")){
                PC_VIR_4_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd3 = ((RadioButton) findViewById(main_rd3.getCheckedRadioButtonId())).getText().toString();

            if(get_rd3.equalsIgnoreCase("Pass")){
                PC_VIR_4_counter_PASS++;
            }
            else if (get_rd3.equalsIgnoreCase("Fail")){
                PC_VIR_4_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd4 = ((RadioButton) findViewById(main_rd4.getCheckedRadioButtonId())).getText().toString();

            if(get_rd4.equalsIgnoreCase("Pass")){
                PC_VIR_4_counter_PASS++;
            }
            else if (get_rd4.equalsIgnoreCase("Fail")){
                PC_VIR_4_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd5 = ((RadioButton) findViewById(main_rd5.getCheckedRadioButtonId())).getText().toString();

            if(get_rd5.equalsIgnoreCase("Compliant")){
                PC_VIR_4_counter_PASS++;
            }
            else if (get_rd5.equalsIgnoreCase("Non-Compliant")){
                PC_VIR_4_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd6 = ((RadioButton) findViewById(main_rd6.getCheckedRadioButtonId())).getText().toString();

            if(get_rd6.equalsIgnoreCase("Pass")){
                PC_VIR_4_counter_PASS++;
            }
            else if (get_rd6.equalsIgnoreCase("Fail")){
                PC_VIR_4_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_remarks_7 = et_remarks_7.getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        double Fair=PC_VIR_4_counter_FAIR_1+PC_VIR_4_counter_FAIR_2;

        Log.e("AASXCVAAE","Fair total 41 " + PC_VIR_4_counter_FAIR_1);
        Log.e("AASXCVAAE","Fair total 42  " + PC_VIR_4_counter_FAIR_2);
        Log.e("AASXCVAAE","total 43 " + Fair);

        Log.e("AASXCVAAE","Pass4 =  " + PC_VIR_4_counter_PASS);
        Log.e("AASXCVAAE","fail4 =  " + PC_VIR_4_counter_FAIL);



        cv_off.put(db.et1, "" + get_rd1);
        cv_off.put(db.et2, "" + get_rd2);
        cv_off.put(db.et3, "" + get_rd3);
        cv_off.put(db.et4, "" + get_rd4);
        cv_off.put(db.et5, "" + get_rd5);
        cv_off.put(db.et6, "" + get_rd6);
        cv_off.put(db.et7, "" + get_remarks_7);
        cv_off.put(db.PASS_CHECK, "" + PC_VIR_4_counter_PASS);
        cv_off.put(db.FAIL_CHECK, "" + PC_VIR_4_counter_FAIL);
        cv_off.put(db.FAIR_CHECK, "" + Fair);
        cv_off.put(db.MAIN_ID, "" + key_id);



        if (check_data(key_id) == 0) {

            sd.insert(db.PC_VIR_DB_GENERAL_4, null, cv_off);
            cv_off.clear();

            Intent intent = new Intent(PC_VIR_GENERAL_4.this, PC_VIR_PPE_5.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);


        } else {
            Log.e("JJJJDDD", "e2");
            sd.update(db.PC_VIR_DB_GENERAL_4,  cv_off,"KEY_ID = '" + key_id + "'",null);
            cv_off.clear();

            Intent intent = new Intent(PC_VIR_GENERAL_4.this, PC_VIR_PPE_5.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);

        }

    }

    public int check_data(String key_id){
        String Query="select * from "+db.PC_VIR_DB_GENERAL_4 +" where MAIN_ID = '"+key_id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        return cursor.getCount();
    }

    public void get_offline(String id){

        Log.e("GGGRRRR","E2"  + id);
        String Query="select * from "+db.PC_VIR_DB_GENERAL_4 +" where MAIN_ID = '"+id+"'";
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

                et_remarks_7.setText(met7);

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
                if(met2.equalsIgnoreCase("Good")){
                    ((RadioButton) main_rd2.getChildAt(0)).setChecked(true);


                }
                else if(met2.equalsIgnoreCase("Fair")){

                    ((RadioButton) main_rd2.getChildAt(1)).setChecked(true);

                }
                else if(met2.equalsIgnoreCase("Poor")){

                    ((RadioButton) main_rd2.getChildAt(2)).setChecked(true);

                }
                else if(met2.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd2.getChildAt(3)).setChecked(true);

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
                if(met5.equalsIgnoreCase("Compliant")){
                    ((RadioButton) main_rd5.getChildAt(0)).setChecked(true);


                }
                else if(met5.equalsIgnoreCase("Non-Compliant")){

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
                Intent sir_home = new Intent(PC_VIR_GENERAL_4.this, Category_Type_Activity.class);
                sir_home.putExtra("key_id", key_id);
                startActivity(sir_home);
                break;
            case R.id.vir_title_page_1:
                if (key_id != null) {
                    Intent sir_customer = new Intent(PC_VIR_GENERAL_4.this, PC_VIR_TITLE_1.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.vir_body_2:
                if (key_id != null) {
                    Intent sir_customer = new Intent(PC_VIR_GENERAL_4.this, PC_VIR_BODY_2.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.vir_funct_3:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_GENERAL_4.this, PC_VIR_FUNCTION_3.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_gen_4:
                Toast.makeText(getApplicationContext(),"Already,You Are in Same Page",Toast.LENGTH_SHORT).show();
                break;

            case R.id.vir_ppe_5:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_GENERAL_4.this, PC_VIR_PPE_5.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_stan_6:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_GENERAL_4.this, PC_VIR_STANDARD_6.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_other_7:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_GENERAL_4.this, PC_VIR_OTHER_7.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    


}
