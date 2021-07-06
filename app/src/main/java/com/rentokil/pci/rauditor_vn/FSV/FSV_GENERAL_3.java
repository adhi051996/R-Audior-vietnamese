package com.rentokil.pci.rauditor_vn.FSV;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rentokil.pci.rauditor_vn.Category_Type_Activity;
import com.rentokil.pci.rauditor_vn.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_vn.R;

public class FSV_GENERAL_3 extends AppCompatActivity {

    CheckBox checkbox_1,checkbox_2,checkbox_3,checkbox_4,checkbox_5,checkbox_6,checkbox_7,
            checkbox_8,checkbox_9,checkbox_10,checkbox_11,checkbox_12,checkbox_13,checkbox_14,checkbox_15,
            checkbox_16,checkbox_17,checkbox_18;

    EditText remarks;

    String get_check1,get_check2,get_check3,get_check4,get_check5,get_check6,get_check7,get_check8,get_check9,
            get_check10,get_check11,get_check12,get_check13,get_check14,get_check15,get_check16,get_check17,get_check18,get_remarks;
    customfonts.Button_SF_Pro_Display_Semibold back_title_1,sub_title_1;
    String key_id;
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv_off;

    private  int counter_check_yes=0;
    private  int counter_check_no=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fsv__general_3);

        db = new DatabaseHelper(FSV_GENERAL_3.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv_off = new ContentValues();



        Intent intent1=getIntent();
        key_id=intent1.getStringExtra("key_id");
        Log.e("DDDDDDX"," id 1 = "+key_id);

        checkbox_1=(CheckBox) findViewById(R.id.checkbox_1);
        checkbox_2=(CheckBox) findViewById(R.id.checkbox_2);
        checkbox_3=(CheckBox) findViewById(R.id.checkbox_3);
        checkbox_4=(CheckBox) findViewById(R.id.checkbox_4);
        checkbox_5=(CheckBox) findViewById(R.id.checkbox_5);
        checkbox_6=(CheckBox) findViewById(R.id.checkbox_6);
        checkbox_7=(CheckBox) findViewById(R.id.checkbox_7);
        checkbox_8=(CheckBox) findViewById(R.id.checkbox_8);
        checkbox_9=(CheckBox) findViewById(R.id.checkbox_9);
        checkbox_10=(CheckBox) findViewById(R.id.checkbox_10);
        checkbox_11=(CheckBox) findViewById(R.id.checkbox_11);
        checkbox_12=(CheckBox) findViewById(R.id.checkbox_12);
        checkbox_13=(CheckBox) findViewById(R.id.checkbox_13);
        checkbox_14=(CheckBox) findViewById(R.id.checkbox_14);
        checkbox_15=(CheckBox) findViewById(R.id.checkbox_15);
        checkbox_16=(CheckBox) findViewById(R.id.checkbox_16);
        checkbox_17=(CheckBox) findViewById(R.id.checkbox_17);
        checkbox_18=(CheckBox) findViewById(R.id.checkbox_18);
        sub_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.sub_title_1);
        remarks=(EditText) findViewById(R.id.et_remarks);
        back_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.back_title_1);

        back_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(FSV_GENERAL_3.this, FSV_BODY_2.class);
                i.putExtra("key_id", key_id);
                startActivity(i);
            }
        });
        checkbox_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_1.isChecked()) {
                    get_check1 = "Yes";
                } else {
                    get_check1 = "";
                }
            }
        });

        checkbox_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_2.isChecked()) {

                    get_check2 = "Yes";
                } else {
                    get_check2 = "";
                }
            }
        });

        checkbox_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_3.isChecked()) {

                    get_check3 = "Yes";
                } else {
                    get_check3 = "";
                }
            }
        });

        checkbox_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_4.isChecked()) {
                    get_check4 = "Yes";
                } else {
                    get_check4 = "";
                }
            }
        });

        checkbox_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_5.isChecked()) {

                    get_check5 = "Yes";
                } else {
                    get_check5 = "";
                }
            }
        });

        checkbox_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_6.isChecked()) {

                    get_check6 = "Yes";

                } else {
                    get_check6 = "";
                }
            }
        });

        checkbox_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_7.isChecked()) {


                    get_check7 = "Yes";
                } else {
                    get_check7 = "";
                }
            }
        });

        checkbox_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_8.isChecked()) {

                    get_check8 = "Yes";
                } else {
                    get_check8 = "";
                }
            }
        });

        checkbox_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_9.isChecked()) {

                    get_check9 = "Yes";
                } else {
                    get_check9 = "";
                }
            }
        });

        checkbox_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_10.isChecked()) {

                    get_check10 = "Yes";
                } else {
                    get_check10 = "";
                }
            }
        });

        checkbox_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_11.isChecked()) {


                    get_check11 = "Yes";
                } else {
                    get_check11 = "";
                }
            }
        });

        checkbox_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_12.isChecked()) {

                    get_check12 = "Yes";
                } else {
                    get_check12 = "";
                }
            }
        });

        checkbox_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_13.isChecked()) {

                    get_check13 = "Yes";
                } else {
                    get_check13 = "";
                }
            }
        });

        checkbox_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_14.isChecked()) {

                    get_check14 = "Yes";
                } else {
                    get_check14 = "";
                }
            }
        });

        checkbox_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_15.isChecked()) {


                    get_check15 = "Yes";
                } else {
                    get_check15 = "";
                }
            }
        });

        checkbox_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_16.isChecked()) {


                    get_check16 = "Yes";
                } else {
                    get_check16 = "";
                }
            }
        });

        checkbox_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_17.isChecked()) {


                    get_check17 = "Yes";
                } else {
                    get_check17 = "";
                }
            }
        });

        checkbox_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_18.isChecked()) {

                    get_check18 = "Yes";
                } else {
                    get_check18 = "";
                }
            }
        });

        sub_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insert_offline("New");
            }
        });

        if(key_id!=null){
            get_offline(key_id);
        }

    }

    private void insert_offline(String Status) {

        get_remarks=remarks.getText().toString();

        if (checkbox_1.isChecked()) {
            counter_check_yes++;

            Log.e("AHHXBGGSA","check = "+get_check1);
            cv_off.put(db.et1, "" + get_check1);
        } else {
            counter_check_no++;

            Log.e("AHHXBGGSA","uncheck = ");
            cv_off.put(db.et1, "No");
        }

        if (checkbox_2.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et2, "" + get_check2);
        } else {
            counter_check_no++;
            cv_off.put(db.et2, "No");
        }

        if (checkbox_3.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et3, "" + get_check3);
        } else {
            counter_check_no++;
            cv_off.put(db.et3, "No");
        }

        if (checkbox_4.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et4, "" + get_check4);
        } else {
            counter_check_no++;
            cv_off.put(db.et4, "No");
        }
        if (checkbox_5.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et5, "" + get_check5);
        } else {
            counter_check_no++;
            cv_off.put(db.et5, "No");
        }
        if (checkbox_6.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et6, "" + get_check6);
        } else {
            counter_check_no++;
            cv_off.put(db.et6, "No");
        }

        if (checkbox_7.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et7, "" + get_check7);
        } else {
            counter_check_no++;
            cv_off.put(db.et7, "No");
        }

        if (checkbox_8.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et8, "" + get_check8);
        } else {
            counter_check_no++;
            cv_off.put(db.et8, "No");
        }

        if (checkbox_9.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et9, "" + get_check9);
        } else {
            counter_check_no++;
            cv_off.put(db.et9, "No");
        }
        if (checkbox_10.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et10, "" + get_check10);
        } else {
            counter_check_no++;
            cv_off.put(db.et10, "No");
        }

        if (checkbox_11.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et11, "" + get_check11);
        } else {
            counter_check_no++;
            cv_off.put(db.et11, "No");
        }
        if (checkbox_12.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et12, "" + get_check12);
        } else {
            counter_check_no++;
            cv_off.put(db.et12, "No");
        }

        if (checkbox_13.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et13, "" + get_check13);
        } else {
            counter_check_no++;
            cv_off.put(db.et13, "No");
        }

        if (checkbox_14.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et14, "" + get_check14);
        } else {
            counter_check_no++;
            cv_off.put(db.et14, "No");
        }

        if (checkbox_15.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et15, "" + get_check15);
        } else {
            counter_check_no++;
            cv_off.put(db.et15, "No");
        }

        if (checkbox_16.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et16, "" + get_check16);
        } else {
            counter_check_no++;
            cv_off.put(db.et16, "No");
        }

        if (checkbox_17.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et17, "" + get_check17);
        } else {
            counter_check_no++;
            cv_off.put(db.et17, "No");
        }

        if (checkbox_18.isChecked()) {
            counter_check_yes++;
            cv_off.put(db.et18, "" + get_check18);
        } else {
            counter_check_no++;
            cv_off.put(db.et18, "No");
        }


 Log.e("HHGSGTTQQXC","check yes = "+counter_check_yes);
 Log.e("HHGSGTTQQXC","check no = "+counter_check_no);

        cv_off.put(db.et19,""+get_remarks);

        cv_off.put(db.CHECK_COUNT_YES,""+counter_check_yes);

        cv_off.put(db.CHECK_COUNT_NO,""+counter_check_no);
        cv_off.put(db.MAIN_ID, ""+key_id);

        if (check_data(key_id) == 0) {

            sd.insert(db.FSV_GENERAL_3, null, cv_off);
            cv_off.clear();

            Intent intent = new Intent(FSV_GENERAL_3.this, FSV_STANDARD_TOOL_4.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);


        } else {
            Log.e("JJJJDDD", "e2");
            sd.update(db.FSV_GENERAL_3,  cv_off,"KEY_ID = '" + key_id + "'",null);
            cv_off.clear();

            Intent intent = new Intent(FSV_GENERAL_3.this, FSV_STANDARD_TOOL_4.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);

        }

    }

    public int check_data(String key_id){

        String Query="select * from "+db.FSV_GENERAL_3 +" where MAIN_ID = '"+key_id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();
        return cursor.getCount();
    }

    public void get_offline(String id){

        Log.e("GGGRRRR","E2"  + id);
        String Query="select * from "+db.FSV_GENERAL_3 +" where MAIN_ID = '"+id+"'";
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


                Log.e("JJHAHSTTE","met1 = "+met1);
                Log.e("JJHAHSTTE","met2 = "+met2);
                Log.e("JJHAHSTTE","met3 = "+met3);

                try {
                    if (met1.equalsIgnoreCase("No")) {
                    } else {

                        get_check1 = "Yes";


                        checkbox_1.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (met2.equalsIgnoreCase("No")) {

                } else {

                    get_check2 = "Yes";

                    checkbox_2.setChecked(true);
                }
                try {
                    if (met3.equalsIgnoreCase("No")) {
                    } else {

                        get_check3 = "Yes";

                        checkbox_3.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (met4.equalsIgnoreCase("No")) {

                    } else {

                        get_check4 = "Yes";

                        checkbox_4.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (met5.equalsIgnoreCase("No")) {
                    } else {
                        get_check5 = "Yes";

                        checkbox_5.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (met6.equalsIgnoreCase("No")) {
                    } else {
                        get_check6 = "Yes";

                        checkbox_6.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (met7.equalsIgnoreCase("No")) {
                    } else {
                        get_check7 = "Yes";

                        checkbox_7.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (met8.equalsIgnoreCase("No")) {
                    } else {
                        get_check8 = "Yes";

                        checkbox_8.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (met9.equalsIgnoreCase("No")) {
                    } else {
                        get_check9 = "Yes";

                        checkbox_9.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (met10.equalsIgnoreCase("No")) {
                    } else {
                        get_check10 = "Yes";

                        checkbox_10.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (met11.equalsIgnoreCase("No")) {
                    } else {
                        get_check11 = "Yes";

                        checkbox_11.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (met12.equalsIgnoreCase("No")) {
                    } else {
                        get_check12 = "Yes";

                        checkbox_12.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (met13.equalsIgnoreCase("No")) {
                    } else {
                        get_check13 = "Yes";

                        checkbox_13.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (met14.equalsIgnoreCase("No")) {
                    } else {

                        get_check14 = "Yes";

                        checkbox_14.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (met15.equalsIgnoreCase("No")) {
                    } else {
                        get_check15 = "Yes";

                        checkbox_15.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (met16.equalsIgnoreCase("No")) {
                    } else {
                        get_check16 = "Yes";

                        checkbox_16.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (met17.equalsIgnoreCase("No")) {
                    } else {
                        get_check17 = "Yes";

                        checkbox_17.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (met18.equalsIgnoreCase("No")) {
                    } else {
                        get_check18 = "Yes";
                        checkbox_18.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }




                remarks.setText(met19);





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
        inflater.inflate(R.menu.popup_menu_fsv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.vir_home:
                Intent sir_home = new Intent(FSV_GENERAL_3.this, Category_Type_Activity.class);
                sir_home.putExtra("key_id", key_id);
                startActivity(sir_home);
                break;
            case R.id.vir_title_page_1:
                if (key_id != null) {
                    Intent sir_customer = new Intent(FSV_GENERAL_3.this, FSV_TITLE_1.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.vir_body_2:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_GENERAL_3.this, FSV_BODY_2.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;
            case R.id.vir_funct_3:

                Toast.makeText(getApplicationContext(),"Already,You Are in Same Page",Toast.LENGTH_SHORT).show();

                break;

            case R.id.vir_gen_4:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_GENERAL_3.this, FSV_STANDARD_TOOL_4.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_ppe_5:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_GENERAL_3.this, FSV_STANDARD_ITEM_5.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_stan_6:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_GENERAL_3.this, FSV_EMERGENCY_6.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_other_7:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_GENERAL_3.this, FSV_SIGN_7.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
