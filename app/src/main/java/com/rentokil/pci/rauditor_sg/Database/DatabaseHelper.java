package com.rentokil.pci.rauditor_sg.Database;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String RENTOKIL_SIGN = "RENTOKIL_SIGN";
    public static final String CUSTOMER_SIGN = "CUSTOMER_SIGN";
    public static final String RENTOKIL_NAME = "RENTOKIL_NAME";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";


    public static final String DATABASE_NAME = "IAUDITOR.db", ACTIVITY_SELECT = "ACTIVITY_SELECT", BRANCH_AND_TECH_MSOT = "BRANCH_AND_TECH_MSOT", INSERT_MAIN_ID = "INSERT_MAIN_ID";
    public static final String USER_PROFILE_TABLE = "USER_PROFILE_TABLE", AUDIT_ACCESS_TB = "AUDIT_ACCESS_TB",
            BRANCH_AUDITBY_TABLE = "BRANCH_AUDITBY_TABLE", DASHBOARD_TB = "DASHBOARD_TB",
            USER_COMPLETE_STATUS = "USER_COMPLETE_STATUS", CHECK_STATUS_TABLE = "CHECK_STATUS_TABLE", ACTIVITIES_TABLE = "ACTIVITIES_TABLE", ACTIVITIES_SELECTED_STRING = "ACTIVITIES_SELECTED_STRING", ACTIVITIES_PAGE_1 = "ACTIVITIES_PAGE_1", ACTIVITIES_PAGE_2 = "ACTIVITIES_PAGE_2", AREA = "AREA", MSOT_LIST_SELECT = "MSOT_LIST_SELECT", MSOT_ACTIVITY_MAS_DB = "MSOT_ACTIVITY_MAS_DB", MSOT_QUESTION_MAS_DB = "MSOT_QUESTION_MAS_DB",
            MSOT_ACT_QUS_DB = "MSOT_ACT_QUS_DB", MSOT_TYPE_TABLE = "MSOT_TYPE_TABLE", MSOT_QUESTION_ID_TABLE = "MSOT_QUESTION_ID_TABLE",
            KEY_ID = "KEY_ID", DELETED = "DELETED", STATE = "STATE", USER_NAME = "USER_NAME", USER_MAIL = "USER_MAIL", COUNTRY = "COUNTRY", BRANCH = "BRANCH", STATUS = "STATUS", MAIN_ID = "MAIN_ID", STATUS_1 = "STATUS_1", STATUS_2 = "STATUS_2", COMPLETE_STATUS = "COMPLETE_STATUS", QSR_COMPLETE_STATUS = "QSR_COMPLETE_STATUS", PMCA_COMPLETE_STATUS = "PMCA_COMPLETE_STATUS", IPM_COMPLETE_STATUS = "IPM_COMPLETE_STATUS", AIB_COMPLETE_STATUS = "AIB_COMPLETE_STATUS", AUDIT_BY_NAME = "AUDIT_BY_NAME", JOB_TITLE = "JOB_TITLE", REP_BRANCH = "REP_BRANCH", POSITION_ID = "POSITION_ID", AUDIT_NAME = "AUDIT_NAME", COMPLETED = "COMPLETED", IN_PROGRESS = "IN_PROGRESS", YEAR = "YEAR", MONTH = "MONTH", ACCESS = "ACCESS", WEEK = "WEEK", COUNT = "COUNT", DATE = "DATE", MSOT_MAIN_DB = "MSOT_MAIN_DB", MSOT_PAGE_A_DB = "MSOT_PAGE_A_DB", MSOT_PAGE_B_DB = "MSOT_PAGE_B_DB", MSOT_PAGE_C_DB = "MSOT_PAGE_C_DB", MSOT_PAGE_D_DB = "MSOT_PAGE_D_DB", MSOT_PAGE_E_DB = "MSOT_PAGE_E_DB", MSOT_PAGE_F_DB = "MSOT_PAGE_F_DB", MSOT_PAGE_G_DB = "MSOT_PAGE_G_DB", MSOT_PAGE_H_DB = "MSOT_PAGE_H_DB", MSOT_PAGE_I_DB = "MSOT_PAGE_I_DB", MSOT_PAGE_J_DB = "MSOT_PAGE_J_DB", MSOT_PAGE_K_DB = "MSOT_PAGE_K_DB", MSOT_PAGE_L_DB = "MSOT_PAGE_L_DB", MSOT_PAGE_M_DB = "MSOT_PAGE_M_DB", MSOT_PAGE_N_DB = "MSOT_PAGE_N_DB", MSOT_PAGE_O_DB = "MSOT_PAGE_O_DB", MSOT_IMAGE_TB = "MSOT_IMAGE_TB", PAGE_ID = "PAGE_ID", MSOT_SIGN_DB = "MSOT_SIGN_DB", COMMANDS = "COMMANDS", QUESTION_ID = "QUESTION_ID", et1 = "et1", et2 = "et2", et3 = "et3", et4 = "et4", et5 = "et5", et6 = "et6", et7 = "et7", et8 = "et8", et9 = "et9", et10 = "et10", et11 = "et11", et12 = "et12", et13 = "et13", et14 = "et14", et15 = "et15", et16 = "et16", et17 = "et17", et18 = "et18", et19 = "et19", et20 = "et20",
            et21 = "et21", et22 = "et22", et23 = "et23", et24 = "et24", et25 = "et25", IMAGE_1 = "IMAGE_1",
            IMAGE_2 = "IMAGE_2", SERVICE_ADDRESS = "SERVICE_ADDRESS", TIME_MS = "TIME_MS", ACTIVITY_NAME = "ACTIVITY_NAME",
            TECH_NAME = "TECH_NAME", TECH_CODE = "TECH_CODE", TECH_ID = "TECH_ID", BRANCH_DB = "BRANCH_DB", AUDIT_TECH = "AUDIT_TECH", ACTIVITY_ID = "ACTIVITY_ID", AUDIT_BRANCH = "AUDIT_BRANCH", JOB_STAFF = "JOB_STAFF", TYPE_WORK = "TYPE_WORK", MSOT_NAME = "MSOT_NAME", BRANCH_CO_NAME = "BRANCH_CO_NAME", BRANCH_MANAGE = "BRANCH_MANAGE";
    public static final int DATABASE_VERSION = 1;

    public static final String BRANCH_END_DATE = "BRANCH_END_DATE";
    public static final String IMG_URL_1 = "IMG_URL_1";
    public static final String IMG_URL_2 = "IMG_URL_2";
    public static final String IMG_URL_3 = "IMG_URL_3";
    public static final String IMG_URL_4 = "IMG_URL_4";

    public static final String et26 = "et26";
    public static final String et27 = "et27";
    public static final String et28 = "et28";
    public static final String et29 = "et29";
    public static final String et30 = "et30";
    public static final String et31 = "et31";
    public static final String et32 = "et32";
    public static final String et33 = "et33";
    public static final String et34 = "et34";
    public static final String et35 = "et35";
    public static final String et36 = "et36";
    public static final String et37 = "et37";
    public static final String et38 = "et38";
    public static final String et39 = "et39";
    public static final String et40 = "et40";
    public static final String et41 = "et41";

    public static final String COMPLETED_DATE = "COMPLETED_DATE";
    public static final String MOBILE_MODEL_TABLE = "MOBILE_MODEL_TABLE";
    public static final String MOBILE_MODEL = "MOBILE_MODEL";
    public static final String UPDATE_PROFILE = "UPDATE_PROFILE";
    public static final String MSOT_TECH_TABLE = "MSOT_TECH_TABLE";
    public static final String OCC_TECH = "OCC_TECH";
    public static final String VERSION_NAME = "VERSION_NAME";
    public static final String IMAGE_1_1 = "IMAGE_1_1";
    public static final String IMAGE_1_2 = "IMAGE_1_2";
    public static final String RED_DOT_MAIN_TABLE = "RED_DOT_MAIN_TABLE";
    public static final String PCI_TITLE_1 = "PCI_TITLE_1";
    public static final String PCI_INSPEC_2 = "PCI_INSPEC_2";
    public static final String PCI_SIGN_3 = "PCI_SIGN_3";

    public static final String PTI_TITLE_1 = "PTI_TITLE_1";
    public static final String PTI_INSPEC_2 = "PTI_INSPEC_2";
    public static final String PTI_SIGN_3 = "PTI_SIGN_3";

    public static final String PC_VIR_DB_TITLE_1 = "PC_VIR_DB_TITLE_1";
    public static final String PC_VIR_DB_BODY_2 = "PC_VIR_DB_BODY_2";
    public static final String PC_VIR_DB_FUNCTION_3 = "PC_VIR_DB_FUNCTION_3";
    public static final String PC_VIR_DB_GENERAL_4 = "PC_VIR_DB_GENERAL_4";
    public static final String PC_VIR_DB_PPE_5 = "PC_VIR_DB_PPE_5";
    public static final String PC_VIR_DB_STANDARD_6 = "PC_VIR_DB_STANDARD_6";
    public static final String PC_VIR_DB_OTHER_7 = "PC_VIR_DB_OTHER_7";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    String CREATE_PCI_TITLE_1 = "CREATE TABLE " + PCI_TITLE_1 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            + et1 + " TEXT,"
            + et2 + " TEXT,"
            + et3 + " TEXT,"
            + et4 + " TEXT,"
            + et5 + " TEXT,"
            + et6 + " TEXT,"
            + et7 + " TEXT,"
            + STATUS + " TEXT,"
            + COMPLETED_DATE + " DATETIME,"
            + VERSION_NAME + " TEXT,"
            + DELETED + " INTEGER)";



    String CREATE_PCI_INSPEC_2 = "CREATE TABLE " + PCI_INSPEC_2 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            + et1 + " TEXT,"
            + et2 + " TEXT,"
            + et3 + " TEXT,"
            + IMAGE_1 + " TEXT,"
            + COUNT + " INTEGER,"
            + DELETED + " INTEGER)";

    String CREATE_PCI_SIGN_3 = "CREATE TABLE " + PCI_SIGN_3 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            + et1 + " TEXT,"
            + CUSTOMER_NAME + " TEXT,"
            + CUSTOMER_SIGN + " BLOB,"
            + BRANCH_END_DATE + " DATETIME,"
            + DELETED + " INTEGER)";


    String CREATE_PTI_TITLE_1 = "CREATE TABLE " + PTI_TITLE_1 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            + et1 + " TEXT,"
            + et2 + " TEXT,"
            + et3 + " TEXT,"
            + et4 + " TEXT,"
            + et5 + " TEXT,"
            + et6 + " TEXT,"
            + et7 + " TEXT,"
            + STATUS + " TEXT,"
            + COMPLETED_DATE + " DATETIME,"
            + VERSION_NAME + " TEXT,"
            + DELETED + " INTEGER)";



    String CREATE_PTI_INSPEC_2 = "CREATE TABLE " + PTI_INSPEC_2 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            + et1 + " TEXT,"
            + et2 + " TEXT,"
            + et3 + " TEXT,"
            + IMAGE_1 + " BLOB,"
            + IMAGE_1_1 + " BLOB,"
            + IMAGE_1_2 + " BLOB,"
            + COUNT + " INTEGER,"
            + DELETED + " INTEGER)";

    String CREATE_PTI_SIGN_3 = "CREATE TABLE " + PTI_SIGN_3 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            + et1 + " TEXT,"
            + CUSTOMER_NAME + " TEXT,"
            + CUSTOMER_SIGN + " BLOB,"
            + BRANCH_END_DATE + " DATETIME,"
            + DELETED + " INTEGER)";


    String CREATE_PC_VIR_TITLE_1 = "CREATE TABLE " + PC_VIR_DB_TITLE_1 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            + et1 + " TEXT,"
            + et2 + " TEXT,"
            + et3 + " TEXT,"
            + et4 + " TEXT,"
            + STATUS + " TEXT,"
            + COMPLETED_DATE + " DATETIME,"
            + VERSION_NAME + " TEXT,"
            + DELETED + " INTEGER)";


    String CREATE_PC_VIR_BODY_2 = "CREATE TABLE " + PC_VIR_DB_BODY_2 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            +IMG_URL_1+" TEXT,"
            +IMG_URL_2+" TEXT,"
            +IMG_URL_3+" TEXT,"
            +IMG_URL_4+" TEXT,"
            +et1+" TEXT,"
            + DELETED + " INTEGER)";

    String CREATE_PC_VIR_FUNCTION_3 = "CREATE TABLE " + PC_VIR_DB_FUNCTION_3 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            +et1+" TEXT,"
            +et2+" TEXT,"
            +et3+" TEXT,"
            +et4+" TEXT,"
            +et5+" TEXT,"
            +et6+" TEXT,"
            +et7+" TEXT,"
            +et8+" TEXT,"
            +et9+" TEXT,"
            +et10+" TEXT,"
            +et11+" TEXT,"
            +et12+" TEXT,"
            +et13+" TEXT,"
            +et14+" TEXT,"
            + DELETED + " INTEGER)";


    String CREATE_PC_VIR_GENERAL_4 = "CREATE TABLE " + PC_VIR_DB_GENERAL_4 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            +et1+" TEXT,"
            +et2+" TEXT,"
            +et3+" TEXT,"
            +et4+" TEXT,"
            +et5+" TEXT,"
            +et6+" TEXT,"
            +et7+" TEXT,"
            + DELETED + " INTEGER)";


    String CREATE_PC_VIR_DB_PPE_5 = "CREATE TABLE " + PC_VIR_DB_PPE_5 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            +et1+" TEXT,"
            +et2+" TEXT,"
            +et3+" TEXT,"
            +et4+" TEXT,"
            +et5+" TEXT,"
            +et6+" TEXT,"
            +et7+" TEXT,"
            +et8+" TEXT,"
            +et9+" TEXT,"
            +et10+" TEXT,"
            +et11+" TEXT,"
            +et12+" TEXT,"
            +et13+" TEXT,"
            +et14+" TEXT,"
            +et15+" TEXT,"
            +et16+" TEXT,"
            +et17+" TEXT,"
            +et18+" TEXT,"
            +et19+" TEXT,"
            +et20+" TEXT,"
            +et21+" TEXT,"
            +et22+" TEXT,"
            +et23+" TEXT,"
            +et24+" TEXT,"
            +et25+" TEXT,"
            +et26+" TEXT,"
            +et27+" TEXT,"
            +et28+" TEXT,"
            +et29+" TEXT,"
            +et30+" TEXT,"
            +et31+" TEXT,"
            +et32+" TEXT,"
            +et33+" TEXT,"
            +et34+" TEXT,"
            +et35+" TEXT,"
            +et36+" TEXT,"
            +et37+" TEXT,"
            +et38+" TEXT,"
            +et39+" TEXT,"
            +et40+" TEXT,"
            +et41+" TEXT,"
            + DELETED + " INTEGER)";


    String CREATE_PC_VIR_STANDARD_6 = "CREATE TABLE " + PC_VIR_DB_STANDARD_6 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            +et1+" TEXT,"
            +et2+" TEXT,"
            +et3+" TEXT,"
            +et4+" TEXT,"
            +et5+" TEXT,"
            +et6+" TEXT,"
            +et7+" TEXT,"
            +et8+" TEXT,"
            +et9+" TEXT,"
            +et10+" TEXT,"
            +et11+" TEXT,"
            +et12+" TEXT,"
            +et13+" TEXT,"
            +et14+" TEXT,"
            +et15+" TEXT,"
            +et16+" TEXT,"
            +et17+" TEXT,"
            +et18+" TEXT,"
            +et19+" TEXT,"
            +et20+" TEXT,"
            +et21+" TEXT,"
            +et22+" TEXT,"
            + DELETED + " INTEGER)";


    String CREATE_PC_VIR_OTHER_7 = "CREATE TABLE " + PC_VIR_DB_OTHER_7 + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MAIN_ID + " INTEGER,"
            +et1+" TEXT,"
            +et2+" TEXT,"
            +et3+" TEXT,"
            +et4+" TEXT,"
            +et5+" TEXT,"
            +et6+" TEXT,"
            +et7+" TEXT,"
            +et8+" TEXT,"
            + BRANCH_END_DATE + " DATETIME,"
            + DELETED + " INTEGER)";







    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_LIST_TABLE = "CREATE TABLE " + USER_PROFILE_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_NAME + " TEXT,"
                + USER_MAIL + " TEXT,"
                + BRANCH + " TEXT,"
                + COUNTRY + " TEXT)";
        String CREATE_UPDATE_PROFILE = "CREATE TABLE " + UPDATE_PROFILE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BRANCH + " TEXT,"
                + COUNTRY + " TEXT)";

        String CREATE_AUDIT_ACCESS_TB = "CREATE TABLE " + AUDIT_ACCESS_TB + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AUDIT_NAME + " TEXT,"
                + DATE + " DATE,"
                + DELETED + " TEXT)";
        String CREATE_MSOT_LIST_SELECT_TB = "CREATE TABLE " + MSOT_LIST_SELECT + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID + " INTEGER,"
                + ACTIVITY_ID + " INTEGER,"
                + ACTIVITY_NAME + " TEXT,"
                + DELETED + " TEXT)";

        String CREATE_MSOT_TECH_DETAIL = "CREATE TABLE " + MSOT_TECH_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID + " INTEGER,"
                + TECH_NAME + " TEXT,"
                + TECH_CODE + " TEXT,"
                + OCC_TECH + " TEXT,"
                + BRANCH + " TEXT,"
                + DATE + " DATE,"
                + DELETED + " TEXT)";
        String CREATE_DASHBOARD_TB = "CREATE TABLE " + DASHBOARD_TB + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AUDIT_NAME + " TEXT,"
                + COMPLETED + " TEXT,"
                + IN_PROGRESS + " TEXT,"
                + STATE + " TEXT,"
                + DATE + " TEXT,"
                + DELETED + " TEXT)";

        String CREATE_BRANCH_AUDITBY = "CREATE TABLE " + BRANCH_AUDITBY_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AUDIT_BY_NAME + " TEXT,"
                + JOB_TITLE + " TEXT,"
                + REP_BRANCH + " TEXT)";
        String CREATE_BRANCH_DB = "CREATE TABLE " + BRANCH_DB + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COUNTRY + " TEXT,"
                + BRANCH + " TEXT,"
                + DELETED + " INTEGER)";

        String CREATE_CHECK_STATUS = "CREATE TABLE " + CHECK_STATUS_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + STATUS + " TEXT)";

        String CREATE_MS_ACTIVITY_MAS = "CREATE TABLE " + MSOT_ACTIVITY_MAS_DB + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID + " INTEGER,"
                + DATE + " DATE,"
                + ACTIVITY_NAME + " TEXT,"
                + DELETED + " INTEGER)";
        String CREATE_MS_QUESTION_MAS = "CREATE TABLE " + MSOT_QUESTION_MAS_DB + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID + " INTEGER,"
                + QUESTION_ID + " TEXT,"
                + DELETED + " INTEGER)";
        String CREATE_MS_ACT_QUS_MAS = "CREATE TABLE " + MSOT_ACT_QUS_DB + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MAIN_ID + " INTEGER,"
                + ACTIVITY_ID + " INTEGER,"
                + QUESTION_ID + " INTEGER,"
                + DELETED + " INTEGER)";




        String CREATE_MOBILE_MODEL_TABLE = "CREATE TABLE " + MOBILE_MODEL_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MOBILE_MODEL + " TEXT)";





        db.execSQL(CREATE_USER_LIST_TABLE);
        db.execSQL(CREATE_MSOT_TECH_DETAIL);
        db.execSQL(CREATE_BRANCH_DB);
        db.execSQL(CREATE_UPDATE_PROFILE);

        db.execSQL(CREATE_BRANCH_AUDITBY);
        db.execSQL(CREATE_CHECK_STATUS);        //db.execSQL(CREATE_COMPLETE_STATUS);

        db.execSQL(CREATE_DASHBOARD_TB);
        db.execSQL(CREATE_AUDIT_ACCESS_TB);
        db.execSQL(CREATE_MS_ACTIVITY_MAS);
        db.execSQL(CREATE_MS_ACT_QUS_MAS);
        db.execSQL(CREATE_MS_QUESTION_MAS);
        db.execSQL(CREATE_MSOT_LIST_SELECT_TB);
        db.execSQL(CREATE_MOBILE_MODEL_TABLE);




        db.execSQL(CREATE_PCI_TITLE_1);
        db.execSQL(CREATE_PCI_INSPEC_2);
        db.execSQL(CREATE_PCI_SIGN_3);

        db.execSQL(CREATE_PTI_TITLE_1);
        db.execSQL(CREATE_PTI_INSPEC_2);
        db.execSQL(CREATE_PTI_SIGN_3);
        db.execSQL(CREATE_PC_VIR_TITLE_1);
        db.execSQL(CREATE_PC_VIR_BODY_2);
        db.execSQL(CREATE_PC_VIR_FUNCTION_3);
        db.execSQL(CREATE_PC_VIR_GENERAL_4);
        db.execSQL(CREATE_PC_VIR_DB_PPE_5);
        db.execSQL(CREATE_PC_VIR_STANDARD_6);
        db.execSQL(CREATE_PC_VIR_OTHER_7);


    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        if(oldVersion!=newVersion) {
            try {
                if (newVersion<20) {

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (newVersion==20) {

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //onCreate(db);
    }


//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
//        if(oldversion<newversion){
//            try {
//
//                Log.e("EEFFFFFF","old version = "+oldversion+"new version = "+newversion);
//                db.execSQL(ALTER_IR_HASIL_3);
//
//                db.execSQL(CREATE_NKA_TITLE_1);
//                db.execSQL(CREATE_NKA_INFOR_2);
//                db.execSQL(CREATE_NKA_KONDISI_3);
//                db.execSQL(CREATE_NKA_SUMBER_4);
//                db.execSQL(CREATE_NKA_CHALLENGES_5);
//                db.execSQL(CREATE_NKA_ACTION_6);
//                db.execSQL(CREATE_NKA_LIST_7);
//                db.execSQL(CREATE_NKA_SIGN_8);
//
//            } catch (SQLException e) {
//                Log.e("EEFFFFFF","error = "+e.getMessage());
//                e.printStackTrace();
//            }
//
////            db.execSQL(ALTER_IR_HASIL_3);
////            db.execSQL(CREATE_IR_INFOR_2);
////            db.execSQL(CREATE_IR_HASIL_3);
////            db.execSQL(CREATE_IR_PEST_4);
////            db.execSQL(CREATE_IR_SIGNATURE_5);
////            db.execSQL(CREATE_MOM_TITLE_1);
//
//
//        }
//
//    }
    public int getLogincount(SQLiteDatabase db) {
        int count_user=0;
        String countQuery = "SELECT  * FROM "+ USER_PROFILE_TABLE;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            count_user=cursor.getCount();
        }
        cursor.close();

        return count_user;
    } public int get_audit_count(SQLiteDatabase db) {
        int count_user=0;
        String countQuery = "SELECT  * FROM "+ AUDIT_ACCESS_TB;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            count_user=cursor.getCount();
        }
        cursor.close();

        return count_user;
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ USER_PROFILE_TABLE);
        db.close();
    }
    public List<String> getALL_ACTIVITE(SQLiteDatabase sd,int cus_id){
        List<String> lable=new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + ACTIVITIES_TABLE +" where MAIN_ID = '"+cus_id+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            do {
                lable.add(cursor.getString(2));
                Log.e("UUUUUU",""+cursor.getString(2));
            }while (cursor.moveToNext());


        }
        cursor.close();
        return lable;
    } public List<String> get_MSOT_LIST_SELECT(SQLiteDatabase sd,int key_id){
        List<String> lable=new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + MSOT_LIST_SELECT +" where MAIN_ID = '"+key_id+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            do {
                lable.add(cursor.getString(3));
                Log.e("UUUUUU",""+cursor.getString(3));
            }while (cursor.moveToNext());


        }
        cursor.close();
        return lable;
    }




    public int get_check_audit(SQLiteDatabase sd,String name){

        String selectQuery = "SELECT * FROM "+AUDIT_ACCESS_TB+" where AUDIT_NAME ='"+name+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        return cursor.getCount();
    }

    public int get_check_audit_test(SQLiteDatabase sd){

        String selectQuery = "SELECT * FROM "+AUDIT_ACCESS_TB;
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        return cursor.getCount();
    }
    public int get_msot_completed_cound(SQLiteDatabase sd){
        String Status  ="Completed";
        String selectQuery = "SELECT * FROM "+MSOT_MAIN_DB+" where STATUS ='"+Status+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        cursor.close();
        return cursor.getCount();
    }

    public  int get_msot_main_count(SQLiteDatabase sd){
        int main_id=0;
        Cursor c5;
        c5 = sd.rawQuery("Select * from " +MSOT_MAIN_DB, null);
        c5.moveToFirst();

        return c5.getCount();
    }
    public  int get_msot_main_tb_last_id(SQLiteDatabase sd){
        int last_id=0;
        Cursor c5;
        String Query="SELECT KEY_ID from MSOT_MAIN_DB order by KEY_ID DESC limit 1";
        c5 = sd.rawQuery(Query, null);
        c5.moveToFirst();
        return last_id=c5.getInt(c5.getColumnIndex(KEY_ID));
    }

    public String get_user_mailid(SQLiteDatabase sd){
        String Mobile="";
        String countQuery = "SELECT  * FROM "+ USER_PROFILE_TABLE;
        sd = this.getReadableDatabase();
        Cursor cursor = sd.rawQuery(countQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            Mobile=cursor.getString(cursor.getColumnIndex(USER_MAIL));
        }
        cursor.close();
        return Mobile;
    }


    public String get_country_selection(SQLiteDatabase sd){
        String country="";
        String countQuery = "SELECT  * FROM "+ UPDATE_PROFILE;
        sd = this.getReadableDatabase();
        Cursor cursor = sd.rawQuery(countQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            country=cursor.getString(cursor.getColumnIndex(COUNTRY));
        }
        cursor.close();
        return country;
    }

    public String get_branch_selection(SQLiteDatabase sd){
        String branch="";
        String countQuery = "SELECT  * FROM "+ UPDATE_PROFILE;
        sd = this.getReadableDatabase();
        Cursor cursor = sd.rawQuery(countQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            branch=cursor.getString(cursor.getColumnIndex(BRANCH));
        }
        cursor.close();
        return branch;
    }
    public int get_tech_list_count(SQLiteDatabase sd){
        int count=0;
        String countQuery = "SELECT  * FROM "+ MSOT_TECH_TABLE;
        sd = this.getReadableDatabase();
        Cursor cursor = sd.rawQuery(countQuery, null);
        cursor.moveToFirst();
        count=cursor.getCount();
        cursor.close();
        return count;
    }




    public String get_mobile_model(SQLiteDatabase sd){
        String Mobile="";
        String countQuery = "SELECT  * FROM "+ MOBILE_MODEL_TABLE;
        sd = this.getReadableDatabase();
        Cursor cursor = sd.rawQuery(countQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0)
        {
            Mobile=cursor.getString(cursor.getColumnIndex(MOBILE_MODEL));
        }
        cursor.close();
        return Mobile;
    }
    public int get_completed_count(SQLiteDatabase sd){
        String Status = "Completed";
        int count=0;
        String selectQuery = "SELECT * FROM " + MSOT_MAIN_DB + " where STATUS ='" + Status + "'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        count=cursor.getCount();
        cursor.close();

        return count;
    }



    public int get_last_id_tir(SQLiteDatabase sd){
        int last_id=0;
        Cursor c5;
        c5 = sd.rawQuery("Select * from " + PCI_TITLE_1, null);
        c5.moveToLast();
        if(c5.getCount()!=0){
            last_id=c5.getInt(c5.getColumnIndex(KEY_ID));
        }
        c5.close();
        return last_id;

    }
    public int get_last_id_PCI(SQLiteDatabase sd){
        int last_id=0;
        Cursor c5;
        c5 = sd.rawQuery("Select * from " + PCI_TITLE_1, null);
        c5.moveToLast();
        if(c5.getCount()!=0){
            last_id=c5.getInt(c5.getColumnIndex(KEY_ID));
        }
        c5.close();
        return last_id;

    }

    public int get_last_id_VIR(SQLiteDatabase sd){
        int last_id=0;
        Cursor c5;
        c5 = sd.rawQuery("Select * from " + PC_VIR_DB_TITLE_1, null);
        c5.moveToLast();
        if(c5.getCount()!=0){
            last_id=c5.getInt(c5.getColumnIndex(KEY_ID));
        }
        c5.close();
        return last_id;

    }



    public int get_last_id_PTI(SQLiteDatabase sd){
        int last_id=0;
        Cursor c5;
        c5 = sd.rawQuery("Select * from " + PTI_TITLE_1, null);
        c5.moveToLast();
        if(c5.getCount()!=0){
            last_id=c5.getInt(c5.getColumnIndex(KEY_ID));
        }
        c5.close();
        return last_id;

    }

    public int get_pci_completed_count(SQLiteDatabase sd){
        String Status  ="Completed";
        String selectQuery = "SELECT * FROM "+PCI_TITLE_1+" where STATUS ='"+Status+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        cursor.close();
        return cursor.getCount();
    }

    public int get_pti_completed_count(SQLiteDatabase sd){
        String Status  ="Completed";
        String selectQuery = "SELECT * FROM "+PTI_TITLE_1+" where STATUS ='"+Status+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        cursor.close();
        return cursor.getCount();
    }


    public int get_vir_completed_count(SQLiteDatabase sd){
        String Status  ="Completed";
        String selectQuery = "SELECT * FROM "+PC_VIR_DB_TITLE_1+" where STATUS ='"+Status+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        cursor.close();
        return cursor.getCount();
    }





}
