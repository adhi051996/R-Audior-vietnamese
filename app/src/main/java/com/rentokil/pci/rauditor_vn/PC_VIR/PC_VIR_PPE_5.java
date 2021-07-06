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

public class PC_VIR_PPE_5 extends AppCompatActivity {

    String key_id;
    customfonts.Button_SF_Pro_Display_Semibold back_title_1,sub_title_1;


    RadioGroup main_rd1,main_rd2,main_rd3,main_rd4,main_rd5,main_rd6,main_rd7,main_rd8,main_rd9,
            main_rd10,main_rd11,main_rd12,main_rd13,main_rd14,main_rd15,main_rd16,main_rd17,main_rd18,
            main_rd19,main_rd20,main_rd21,main_rd22,main_rd23,main_rd24,main_rd25,main_rd26,main_rd27,main_rd28,
            main_rd29,main_rd30,main_rd31,main_rd32,main_rd33,main_rd34,main_rd35,main_rd36,main_rd37,main_rd38,
            main_rd39,main_rd40;

    String get_rd1,get_rd2,get_rd3,get_rd4,get_rd5,get_rd6,get_rd7,get_rd8,get_rd9,get_rd10,get_rd11,get_rd12,get_rd13,get_rd14
            ,get_rd15,get_rd16,get_rd17,get_rd18,get_rd19,get_rd20,get_rd21,get_rd22,get_rd23,get_rd24,get_rd25,get_rd26,get_rd27,get_rd28,get_rd29,get_rd30
            ,get_rd31,get_rd32,get_rd33,get_rd34,get_rd35,get_rd36,get_rd37,get_rd38,get_rd39,get_rd40,get_et_client_41;

    EditText et_client_41;
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2,cv_off;


    private  int PC_VIR_5_counter_PASS=0;
    private  int PC_VIR_5_counter_FAIL=0;
    private  double PC_VIR_5_counter_FAIR_1=0.0;
    private  double PC_VIR_5_counter_FAIR_2=0.0;
    private  double PC_VIR_5_counter_FAIR_3=0.0;
    private  double PC_VIR_5_counter_FAIR_4=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pc_vir_ppe_equip_5);

        Toolbar mTopToolbar;

        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTopToolbar.setTitle("");
        mTopToolbar.setSubtitle("");
        setSupportActionBar(mTopToolbar);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.menuicon);
        mTopToolbar.setOverflowIcon(drawable);

        db = new DatabaseHelper(PC_VIR_PPE_5.this);
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
        main_rd23 = (RadioGroup) findViewById(R.id.main_rd23);
        main_rd24 = (RadioGroup) findViewById(R.id.main_rd24);
        main_rd25 = (RadioGroup) findViewById(R.id.main_rd25);
        main_rd26 = (RadioGroup) findViewById(R.id.main_rd26);
        main_rd27 = (RadioGroup) findViewById(R.id.main_rd27);
        main_rd28 = (RadioGroup) findViewById(R.id.main_rd28);
        main_rd29 = (RadioGroup) findViewById(R.id.main_rd29);
        main_rd30 = (RadioGroup) findViewById(R.id.main_rd30);
        main_rd31 = (RadioGroup) findViewById(R.id.main_rd31);
        main_rd32 = (RadioGroup) findViewById(R.id.main_rd32);
        main_rd33 = (RadioGroup) findViewById(R.id.main_rd33);
        main_rd34 = (RadioGroup) findViewById(R.id.main_rd34);
        main_rd35 = (RadioGroup) findViewById(R.id.main_rd35);
        main_rd36 = (RadioGroup) findViewById(R.id.main_rd36);
        main_rd37 = (RadioGroup) findViewById(R.id.main_rd37);
        main_rd38 = (RadioGroup) findViewById(R.id.main_rd38);
        main_rd39 = (RadioGroup) findViewById(R.id.main_rd39);
        main_rd40 = (RadioGroup) findViewById(R.id.main_rd40);
        et_client_41 = (EditText) findViewById(R.id.et_client_41);

        back_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.back_title_1);
        sub_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.sub_title_1);

        back_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(PC_VIR_PPE_5.this,PC_VIR_GENERAL_4.class);
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

            try {
                get_rd23 = ((RadioButton) findViewById(main_rd23.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd24 = ((RadioButton) findViewById(main_rd24.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd25 = ((RadioButton) findViewById(main_rd25.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd26 = ((RadioButton) findViewById(main_rd26.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd27 = ((RadioButton) findViewById(main_rd27.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd28 = ((RadioButton) findViewById(main_rd28.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd29 = ((RadioButton) findViewById(main_rd29.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd30 = ((RadioButton) findViewById(main_rd30.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd31 = ((RadioButton) findViewById(main_rd31.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd32 = ((RadioButton) findViewById(main_rd32.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd33 = ((RadioButton) findViewById(main_rd33.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd34 = ((RadioButton) findViewById(main_rd34.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd35 = ((RadioButton) findViewById(main_rd35.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd36 = ((RadioButton) findViewById(main_rd36.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd37 = ((RadioButton) findViewById(main_rd37.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd38 = ((RadioButton) findViewById(main_rd38.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                get_rd39 = ((RadioButton) findViewById(main_rd39.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                get_rd40 = ((RadioButton) findViewById(main_rd40.getCheckedRadioButtonId())).getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }



            try {
                get_et_client_41 = et_client_41.getText().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }


            if(TextUtils.isEmpty(get_et_client_41)) {

                if(TextUtils.isEmpty(get_et_client_41 )){
                    et_client_41.setError("Required");
                }

                return false;
            }

            if(get_rd1.length()==0||get_rd2.length()==0||get_rd3.length()==0||get_rd4.length()==0||get_rd5.length()==0||get_rd6.length()==0
                    ||get_rd7.length()==0||get_rd8.length()==0||get_rd9.length()==0||get_rd10.length()==0||get_rd11.length()==0
                    ||get_rd12.length()==0||get_rd13.length()==0||get_rd14.length()==0||get_rd15.length()==0||get_rd16.length()==0
                    ||get_rd17.length()==0||get_rd18.length()==0||get_rd19.length()==0||get_rd20.length()==0||get_rd21.length()==0
                    ||get_rd22.length()==0||get_rd23.length()==0||get_rd24.length()==0||get_rd25.length()==0||get_rd26.length()==0
                    ||get_rd27.length()==0||get_rd28.length()==0||get_rd29.length()==0||get_rd30.length()==0||get_rd31.length()==0
                    ||get_rd32.length()==0||get_rd33.length()==0||get_rd34.length()==0||get_rd35.length()==0||get_rd36.length()==0
                    ||get_rd37.length()==0||get_rd38.length()==0||get_rd39.length()==0||get_rd40.length()==0||get_et_client_41.length()==0){
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

            if(get_rd1.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd1.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd2 = ((RadioButton) findViewById(main_rd2.getCheckedRadioButtonId())).getText().toString();

            if(get_rd2.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd2.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd3 = ((RadioButton) findViewById(main_rd3.getCheckedRadioButtonId())).getText().toString();

            if(get_rd3.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd3.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd4 = ((RadioButton) findViewById(main_rd4.getCheckedRadioButtonId())).getText().toString();

            if(get_rd4.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd4.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd5 = ((RadioButton) findViewById(main_rd5.getCheckedRadioButtonId())).getText().toString();

            if(get_rd5.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd5.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd6 = ((RadioButton) findViewById(main_rd6.getCheckedRadioButtonId())).getText().toString();

            if(get_rd6.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd6.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd7 = ((RadioButton) findViewById(main_rd7.getCheckedRadioButtonId())).getText().toString();

            if(get_rd7.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd7.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd8 = ((RadioButton) findViewById(main_rd8.getCheckedRadioButtonId())).getText().toString();

            if(get_rd8.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd8.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd9 = ((RadioButton) findViewById(main_rd9.getCheckedRadioButtonId())).getText().toString();

            if(get_rd9.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd9.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd10 = ((RadioButton) findViewById(main_rd10.getCheckedRadioButtonId())).getText().toString();

            if(get_rd10.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd10.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd11 = ((RadioButton) findViewById(main_rd11.getCheckedRadioButtonId())).getText().toString();

            if(get_rd11.equalsIgnoreCase("Compliant")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd11.equalsIgnoreCase("Non-Compliant")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd12 = ((RadioButton) findViewById(main_rd12.getCheckedRadioButtonId())).getText().toString();

            if(get_rd12.equalsIgnoreCase("Compliant")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd12.equalsIgnoreCase("Non-Compliant")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd13 = ((RadioButton) findViewById(main_rd13.getCheckedRadioButtonId())).getText().toString();

            if(get_rd13.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd13.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd14 = ((RadioButton) findViewById(main_rd14.getCheckedRadioButtonId())).getText().toString();

            if(get_rd14.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd14.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd15 = ((RadioButton) findViewById(main_rd15.getCheckedRadioButtonId())).getText().toString();

            if(get_rd15.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd15.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd16 = ((RadioButton) findViewById(main_rd16.getCheckedRadioButtonId())).getText().toString();

            if(get_rd16.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd16.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd17 = ((RadioButton) findViewById(main_rd17.getCheckedRadioButtonId())).getText().toString();

            if(get_rd17.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd17.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd18 = ((RadioButton) findViewById(main_rd18.getCheckedRadioButtonId())).getText().toString();

            if(get_rd18.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd18.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd19 = ((RadioButton) findViewById(main_rd19.getCheckedRadioButtonId())).getText().toString();

            if(get_rd19.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd19.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd20 = ((RadioButton) findViewById(main_rd20.getCheckedRadioButtonId())).getText().toString();

            if(get_rd20.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd20.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd21 = ((RadioButton) findViewById(main_rd21.getCheckedRadioButtonId())).getText().toString();

            if(get_rd21.equalsIgnoreCase("Good")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd21.equalsIgnoreCase("Fair")){


                PC_VIR_5_counter_FAIR_1++;

                Log.e("AASXCVAA","before = "+PC_VIR_5_counter_FAIR_1);

                PC_VIR_5_counter_FAIR_1 = PC_VIR_5_counter_FAIR_1 / 2;



                Log.e("AASXCVAA","afters 2 = "+PC_VIR_5_counter_FAIR_1);
            }
            else if (get_rd21.equalsIgnoreCase("Poor")){
                PC_VIR_5_counter_FAIL++;
            }

            else if (get_rd21.equalsIgnoreCase("Not Present")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd22 = ((RadioButton) findViewById(main_rd22.getCheckedRadioButtonId())).getText().toString();

            if(get_rd22.equalsIgnoreCase("Good")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd22.equalsIgnoreCase("Fair")){


                PC_VIR_5_counter_FAIR_2++;

                Log.e("AASXCVAA","before = "+PC_VIR_5_counter_FAIR_2);

                PC_VIR_5_counter_FAIR_2 = PC_VIR_5_counter_FAIR_2 / 2;



                Log.e("AASXCVAA","afters 2 = "+PC_VIR_5_counter_FAIR_2);
            }
            else if (get_rd22.equalsIgnoreCase("Poor")){
                PC_VIR_5_counter_FAIL++;
            }

            else if (get_rd22.equalsIgnoreCase("Not Present")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd23 = ((RadioButton) findViewById(main_rd23.getCheckedRadioButtonId())).getText().toString();

            if(get_rd23.equalsIgnoreCase("Good")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd23.equalsIgnoreCase("Fair")){


                PC_VIR_5_counter_FAIR_3++;

                Log.e("AASXCVAA","before = "+PC_VIR_5_counter_FAIR_3);

                PC_VIR_5_counter_FAIR_3 = PC_VIR_5_counter_FAIR_3 / 2;



                Log.e("AASXCVAA","afters 2 = "+PC_VIR_5_counter_FAIR_3);
            }
            else if (get_rd23.equalsIgnoreCase("Poor")){
                PC_VIR_5_counter_FAIL++;
            }

            else if (get_rd23.equalsIgnoreCase("Not Present")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd24 = ((RadioButton) findViewById(main_rd24.getCheckedRadioButtonId())).getText().toString();

            if(get_rd24.equalsIgnoreCase("Good")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd24.equalsIgnoreCase("Fair")){


                PC_VIR_5_counter_FAIR_4++;

                Log.e("AASXCVAA","before = "+PC_VIR_5_counter_FAIR_4);

                PC_VIR_5_counter_FAIR_4 = PC_VIR_5_counter_FAIR_4 / 2;



                Log.e("AASXCVAA","afters 2 = "+PC_VIR_5_counter_FAIR_4);
            }
            else if (get_rd24.equalsIgnoreCase("Poor")){
                PC_VIR_5_counter_FAIL++;
            }

            else if (get_rd24.equalsIgnoreCase("Not Present")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd25 = ((RadioButton) findViewById(main_rd25.getCheckedRadioButtonId())).getText().toString();

            if(get_rd25.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd25.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd26 = ((RadioButton) findViewById(main_rd26.getCheckedRadioButtonId())).getText().toString();

            if(get_rd26.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd26.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd27 = ((RadioButton) findViewById(main_rd27.getCheckedRadioButtonId())).getText().toString();

            if(get_rd27.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd27.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd28 = ((RadioButton) findViewById(main_rd28.getCheckedRadioButtonId())).getText().toString();

            if(get_rd28.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd28.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd29 = ((RadioButton) findViewById(main_rd29.getCheckedRadioButtonId())).getText().toString();

            if(get_rd29.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd29.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd30 = ((RadioButton) findViewById(main_rd30.getCheckedRadioButtonId())).getText().toString();

            if(get_rd30.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd30.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd31 = ((RadioButton) findViewById(main_rd31.getCheckedRadioButtonId())).getText().toString();

            if(get_rd31.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd31.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd32 = ((RadioButton) findViewById(main_rd32.getCheckedRadioButtonId())).getText().toString();

            if(get_rd32.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd32.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd33 = ((RadioButton) findViewById(main_rd33.getCheckedRadioButtonId())).getText().toString();

            if(get_rd33.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd33.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd34 = ((RadioButton) findViewById(main_rd34.getCheckedRadioButtonId())).getText().toString();

            if(get_rd34.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd34.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd35 = ((RadioButton) findViewById(main_rd35.getCheckedRadioButtonId())).getText().toString();

            if(get_rd35.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd35.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd36 = ((RadioButton) findViewById(main_rd36.getCheckedRadioButtonId())).getText().toString();


            if(get_rd36.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd36.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd37 = ((RadioButton) findViewById(main_rd37.getCheckedRadioButtonId())).getText().toString();

            if(get_rd37.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd37.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd38 = ((RadioButton) findViewById(main_rd38.getCheckedRadioButtonId())).getText().toString();

            if(get_rd38.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd38.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            get_rd39 = ((RadioButton) findViewById(main_rd39.getCheckedRadioButtonId())).getText().toString();

            if(get_rd39.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd39.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_rd40 = ((RadioButton) findViewById(main_rd40.getCheckedRadioButtonId())).getText().toString();

            if(get_rd40.equalsIgnoreCase("Yes")){
                PC_VIR_5_counter_PASS++;
            }
            else if (get_rd40.equalsIgnoreCase("No")){
                PC_VIR_5_counter_FAIL++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            get_et_client_41 = et_client_41.getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        double Fair=PC_VIR_5_counter_FAIR_1+PC_VIR_5_counter_FAIR_2+PC_VIR_5_counter_FAIR_3+PC_VIR_5_counter_FAIR_4;

        Log.e("AASXCVAAE","Fair total 51 = " + PC_VIR_5_counter_FAIR_1);
        Log.e("AASXCVAAE","Fair total 52 = " + PC_VIR_5_counter_FAIR_2);
        Log.e("AASXCVAAE","Fair total 53 = " + PC_VIR_5_counter_FAIR_3);
        Log.e("AASXCVAAE","Fair total 54 = " + PC_VIR_5_counter_FAIR_4);
        Log.e("AASXCVAAE","total5 = " + Fair);

        Log.e("AASXCVAAE","Pass5 =  " + PC_VIR_5_counter_PASS);
        Log.e("AASXCVAAE","fail5 =  " + PC_VIR_5_counter_FAIL);


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
        cv_off.put(db.et23, "" + get_rd23);
        cv_off.put(db.et24, "" + get_rd24);
        cv_off.put(db.et25, "" + get_rd25);
        cv_off.put(db.et26, "" + get_rd26);
        cv_off.put(db.et27, "" + get_rd27);
        cv_off.put(db.et28, "" + get_rd28);
        cv_off.put(db.et29, "" + get_rd29);
        cv_off.put(db.et30, "" + get_rd30);
        cv_off.put(db.et31, "" + get_rd31);
        cv_off.put(db.et32, "" + get_rd32);
        cv_off.put(db.et33, "" + get_rd33);
        cv_off.put(db.et34, "" + get_rd34);
        cv_off.put(db.et35, "" + get_rd35);
        cv_off.put(db.et36, "" + get_rd36);
        cv_off.put(db.et37, "" + get_rd37);
        cv_off.put(db.et38, "" + get_rd38);
        cv_off.put(db.et39, "" + get_rd39);
        cv_off.put(db.et40, "" + get_rd40);
        cv_off.put(db.et41, "" + get_et_client_41);
        cv_off.put(db.PASS_CHECK, "" + PC_VIR_5_counter_PASS);
        cv_off.put(db.FAIL_CHECK, "" + PC_VIR_5_counter_FAIL);
        cv_off.put(db.FAIR_CHECK, "" + Fair);
        cv_off.put(db.MAIN_ID, "" + key_id);

        if (check_data(key_id) == 0) {

            sd.insert(db.PC_VIR_DB_PPE_5, null, cv_off);
            cv_off.clear();

            Intent intent = new Intent(PC_VIR_PPE_5.this, PC_VIR_STANDARD_6.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);


        } else {
            Log.e("JJJJDDD", "e2");
            sd.update(db.PC_VIR_DB_PPE_5,  cv_off,"KEY_ID = '" + key_id + "'",null);
            cv_off.clear();

            Intent intent = new Intent(PC_VIR_PPE_5.this, PC_VIR_STANDARD_6.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);

        }


    }

    public int check_data(String key_id){
        String Query="select * from "+db.PC_VIR_DB_PPE_5 +" where MAIN_ID = '"+key_id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        return cursor.getCount();
    }

    public void get_offline(String id){

        Log.e("GGGRRRR","E2"  + id);
        String Query="select * from "+db.PC_VIR_DB_PPE_5 +" where MAIN_ID = '"+id+"'";
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
                String met23 = cursor.getString(cursor.getColumnIndex(db.et23));
                String met24 = cursor.getString(cursor.getColumnIndex(db.et24));
                String met25 = cursor.getString(cursor.getColumnIndex(db.et25));
                String met26 = cursor.getString(cursor.getColumnIndex(db.et26));
                String met27 = cursor.getString(cursor.getColumnIndex(db.et27));
                String met28 = cursor.getString(cursor.getColumnIndex(db.et28));
                String met29 = cursor.getString(cursor.getColumnIndex(db.et29));
                String met30 = cursor.getString(cursor.getColumnIndex(db.et30));
                String met31 = cursor.getString(cursor.getColumnIndex(db.et31));
                String met32 = cursor.getString(cursor.getColumnIndex(db.et32));
                String met33 = cursor.getString(cursor.getColumnIndex(db.et33));
                String met34 = cursor.getString(cursor.getColumnIndex(db.et34));
                String met35 = cursor.getString(cursor.getColumnIndex(db.et35));
                String met36 = cursor.getString(cursor.getColumnIndex(db.et36));
                String met37 = cursor.getString(cursor.getColumnIndex(db.et37));
                String met38 = cursor.getString(cursor.getColumnIndex(db.et38));
                String met39 = cursor.getString(cursor.getColumnIndex(db.et39));
                String met40 = cursor.getString(cursor.getColumnIndex(db.et40));
                String met41 = cursor.getString(cursor.getColumnIndex(db.et41));


                et_client_41.setText(met41);

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
                if(met11.equalsIgnoreCase("Compliant")){
                    ((RadioButton) main_rd11.getChildAt(0)).setChecked(true);


                }
                else if(met11.equalsIgnoreCase("Non-Compliant")){

                    ((RadioButton) main_rd11.getChildAt(1)).setChecked(true);

                }
                else if(met11.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd11.getChildAt(2)).setChecked(true);

                }
/////////////////////rd12//////////
                if(met12.equalsIgnoreCase("Compliant")){
                    ((RadioButton) main_rd12.getChildAt(0)).setChecked(true);


                }
                else if(met12.equalsIgnoreCase("Non-Compliant")){

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
                if(met21.equalsIgnoreCase("Good")){
                    ((RadioButton) main_rd21.getChildAt(0)).setChecked(true);


                }
                else if(met21.equalsIgnoreCase("Fair")){

                    ((RadioButton) main_rd21.getChildAt(1)).setChecked(true);

                }
                else if(met21.equalsIgnoreCase("Poor")){

                    ((RadioButton) main_rd21.getChildAt(2)).setChecked(true);

                }

                else if(met21.equalsIgnoreCase("Not Present")){

                    ((RadioButton) main_rd21.getChildAt(3)).setChecked(true);

                }  else if(met21.equalsIgnoreCase("Not Present")){

                    ((RadioButton) main_rd21.getChildAt(3)).setChecked(true);

                }


/////////////////////rd22//////////
                if(met22.equalsIgnoreCase("Good")){
                    ((RadioButton) main_rd22.getChildAt(0)).setChecked(true);


                }
                else if(met22.equalsIgnoreCase("Fair")){

                    ((RadioButton) main_rd22.getChildAt(1)).setChecked(true);

                }
                else if(met22.equalsIgnoreCase("Poor")){

                    ((RadioButton) main_rd22.getChildAt(2)).setChecked(true);

                }

                else if(met22.equalsIgnoreCase("Not Present")){

                    ((RadioButton) main_rd22.getChildAt(3)).setChecked(true);

                }

/////////////////////rd23//////////
                if(met23.equalsIgnoreCase("Good")){
                    ((RadioButton) main_rd23.getChildAt(0)).setChecked(true);


                }
                else if(met23.equalsIgnoreCase("Fair")){

                    ((RadioButton) main_rd23.getChildAt(1)).setChecked(true);

                }
                else if(met23.equalsIgnoreCase("Poor")){

                    ((RadioButton) main_rd23.getChildAt(2)).setChecked(true);

                }

                else if(met23.equalsIgnoreCase("Not Present")){

                    ((RadioButton) main_rd23.getChildAt(3)).setChecked(true);

                }

/////////////////////rd24//////////
                if(met24.equalsIgnoreCase("Good")){
                    ((RadioButton) main_rd24.getChildAt(0)).setChecked(true);


                }
                else if(met24.equalsIgnoreCase("Fair")){

                    ((RadioButton) main_rd24.getChildAt(1)).setChecked(true);

                }
                else if(met24.equalsIgnoreCase("Poor")){

                    ((RadioButton) main_rd24.getChildAt(2)).setChecked(true);

                }

                else if(met24.equalsIgnoreCase("Not Present")){

                    ((RadioButton) main_rd24.getChildAt(3)).setChecked(true);

                }

/////////////////////rd25//////////
                if(met25.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd25.getChildAt(0)).setChecked(true);


                }
                else if(met25.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd25.getChildAt(1)).setChecked(true);

                }
                else if(met25.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd25.getChildAt(2)).setChecked(true);

                }

 /////////////////////rd26//////////
                if(met26.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd26.getChildAt(0)).setChecked(true);


                }
                else if(met26.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd26.getChildAt(1)).setChecked(true);

                }
                else if(met26.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd26.getChildAt(2)).setChecked(true);

                }
 /////////////////////rd27//////////

                if(met27.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd27.getChildAt(0)).setChecked(true);


                }
                else if(met27.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd27.getChildAt(1)).setChecked(true);

                }
                else if(met27.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd27.getChildAt(2)).setChecked(true);

                }
 /////////////////////rd28//////////

                if(met28.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd28.getChildAt(0)).setChecked(true);


                }
                else if(met28.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd28.getChildAt(1)).setChecked(true);

                }
                else if(met28.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd28.getChildAt(2)).setChecked(true);

                }
 /////////////////////rd29//////////

                if(met29.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd29.getChildAt(0)).setChecked(true);


                }
                else if(met29.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd29.getChildAt(1)).setChecked(true);

                }
                else if(met29.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd29.getChildAt(2)).setChecked(true);

                }
/////////////////////rd30//////////

                if(met30.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd30.getChildAt(0)).setChecked(true);


                }
                else if(met30.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd30.getChildAt(1)).setChecked(true);

                }
                else if(met30.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd30.getChildAt(2)).setChecked(true);

                }
/////////////////////rd31//////////

                if(met31.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd31.getChildAt(0)).setChecked(true);


                }
                else if(met31.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd31.getChildAt(1)).setChecked(true);

                }
                else if(met31.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd31.getChildAt(2)).setChecked(true);

                }
/////////////////////rd32//////////
                if(met32.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd32.getChildAt(0)).setChecked(true);


                }
                else if(met32.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd32.getChildAt(1)).setChecked(true);

                }
                else if(met32.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd32.getChildAt(2)).setChecked(true);

                }
/////////////////////rd33//////////
                if(met33.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd33.getChildAt(0)).setChecked(true);


                }
                else if(met33.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd33.getChildAt(1)).setChecked(true);

                }
                else if(met33.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd33.getChildAt(2)).setChecked(true);

                }
/////////////////////rd34//////////

                if(met34.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd34.getChildAt(0)).setChecked(true);


                }
                else if(met34.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd34.getChildAt(1)).setChecked(true);

                }
                else if(met34.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd34.getChildAt(2)).setChecked(true);

                }
/////////////////////rd35//////////

                if(met35.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd35.getChildAt(0)).setChecked(true);


                }
                else if(met35.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd35.getChildAt(1)).setChecked(true);

                }
                else if(met35.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd35.getChildAt(2)).setChecked(true);

                }
/////////////////////rd36//////////

                if(met36.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd36.getChildAt(0)).setChecked(true);


                }
                else if(met36.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd36.getChildAt(1)).setChecked(true);

                }
                else if(met36.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd36.getChildAt(2)).setChecked(true);

                }
/////////////////////rd37//////////

                if(met37.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd37.getChildAt(0)).setChecked(true);


                }
                else if(met37.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd37.getChildAt(1)).setChecked(true);

                }
                else if(met37.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd37.getChildAt(2)).setChecked(true);

                }
/////////////////////rd38//////////


                if(met38.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd38.getChildAt(0)).setChecked(true);


                }
                else if(met38.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd38.getChildAt(1)).setChecked(true);

                }
                else if(met38.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd38.getChildAt(2)).setChecked(true);

                }
/////////////////////rd39//////////

                if(met39.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd39.getChildAt(0)).setChecked(true);


                }
                else if(met39.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd39.getChildAt(1)).setChecked(true);

                }
                else if(met39.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd39.getChildAt(2)).setChecked(true);

                }
/////////////////////rd40//////////

                if(met40.equalsIgnoreCase("Yes")){
                    ((RadioButton) main_rd40.getChildAt(0)).setChecked(true);


                }
                else if(met40.equalsIgnoreCase("No")){

                    ((RadioButton) main_rd40.getChildAt(1)).setChecked(true);

                }
                else if(met40.equalsIgnoreCase("N/A")){

                    ((RadioButton) main_rd40.getChildAt(2)).setChecked(true);

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
                Intent sir_home = new Intent(PC_VIR_PPE_5.this, Category_Type_Activity.class);
                sir_home.putExtra("key_id", key_id);
                startActivity(sir_home);
                break;
            case R.id.vir_title_page_1:
                if (key_id != null) {
                    Intent sir_customer = new Intent(PC_VIR_PPE_5.this, PC_VIR_TITLE_1.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.vir_body_2:
                if (key_id != null) {
                    Intent sir_customer = new Intent(PC_VIR_PPE_5.this, PC_VIR_BODY_2.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.vir_funct_3:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_PPE_5.this, PC_VIR_FUNCTION_3.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_gen_4:

                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_PPE_5.this, PC_VIR_GENERAL_4.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_ppe_5:
                Toast.makeText(getApplicationContext(),"Already,You Are in Same Page",Toast.LENGTH_SHORT).show();
                break;

            case R.id.vir_stan_6:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_PPE_5.this, PC_VIR_STANDARD_6.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_other_7:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_PPE_5.this, PC_VIR_OTHER_7.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }



}
