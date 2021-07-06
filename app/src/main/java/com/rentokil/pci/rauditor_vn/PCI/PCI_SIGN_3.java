package com.rentokil.pci.rauditor_vn.PCI;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.rentokil.pci.rauditor_vn.Category_Type_Activity;
import com.rentokil.pci.rauditor_vn.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_vn.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dmax.dialog.SpotsDialog;

public class PCI_SIGN_3 extends AppCompatActivity {

    Button sub_info_11,back_info_11;
    String key_id = "";
    byte [] bytes_et1=null,bytes_et2=null;
    LinearLayout  tech_signature,img_lay;
    Dialog dl;
    byte [] bytes_Man=null,bytes_Co=null;

    String Sign_Check = "";
    ImageView  T_signature;
    PCI_SIGN_3.signature mSignature;
    Button Clear_bt, Cancel_bt, Save_bt;
    TextView Text_date;
    Boolean isInternetPresent = false;
    RelativeLayout mContent;
    Button go_home;
    Bitmap bitmap_et1,bitmap_et2;
    String Branch_Name,Co_Name;
    String db_user_name="";
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv_main,cv_sign;
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    String Image_1,Image_2,Image_3,Image_4;
    String ASS_sign,Co_sign,Manager_sign;
    private android.app.AlertDialog pd;
    Bitmap bitmap_branch_manager;

    ByteArrayInputStream imageStream1,imageStream2;
    Bitmap bitmapImage1, bitmapImage2;
    String bitmapget1, bitmapget2;

    EditText ass_name;

    String get_ass_name;

    String toggle_Status="";
    androidx.cardview.widget.CardView free_txt;
    String get_reason;
    com.rentokil.pci.rauditor_vn.CustomEditTextWithBullets et_reason;
    Toolbar mTopToolbar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pci__sign_3);

        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTopToolbar.setTitle("");
        mTopToolbar.setSubtitle("");
        setSupportActionBar(mTopToolbar);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.menuicon);
        mTopToolbar.setOverflowIcon(drawable);

        Intent intent2 = getIntent();
        key_id = intent2.getStringExtra("key_id");


        sub_info_11 =(Button) findViewById(R.id.sub_info_11);
        back_info_11 =(Button) findViewById(R.id.back_info_11);

        ass_name =(EditText) findViewById(R.id.ass_name);


        ass_name.setSingleLine(false);




        db = new DatabaseHelper(this);
        sd = db.getReadableDatabase();
        cv_main = new ContentValues();
        cv_sign = new ContentValues();
        Category_Type_Activity.Entry_Status="OLD";

        get_profile_db();

        tech_signature = (LinearLayout) findViewById(R.id.tech_signature_button);
        tech_signature.setOnClickListener(onButtonClick);


        T_signature = (ImageView) findViewById(R.id.tech_imageView);
    
        img_lay = (LinearLayout) findViewById(R.id.techsignature);
        free_txt = (androidx.cardview.widget.CardView) findViewById(R.id.free_txt);



        ass_name.setText(db_user_name);
        et_reason = (com.rentokil.pci.rauditor_vn.CustomEditTextWithBullets) findViewById(R.id.et_reason);

        et_reason.setSingleLine(false);


        pd = new SpotsDialog(PCI_SIGN_3.this, R.style.Custom);

        if(key_id!=null){
            get_offline(key_id);
        }


        sub_info_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validation()) {
                    insert_off_sign(key_id);
                }
                else{
                    Toast.makeText(PCI_SIGN_3.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }

            }
        });
        back_info_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(PCI_SIGN_3.this, PCI_Audit_Page_2.class);
                i.putExtra("key_id",key_id);
                startActivity(i);

            }
        });
    }

    public void get_signature(String name_author) {
        dl = new Dialog(PCI_SIGN_3.this);
        dl.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dl.setContentView(R.layout.nka_sign);
        dl.show();
        dl.setCancelable(false);
        Clear_bt = (Button) dl.findViewById(R.id.clear);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Text_date = (TextView) dl.findViewById(R.id.sign_date);
        Text_date.setText(currentDateTimeString);




        Cancel_bt = (Button) dl.findViewById(R.id.cancel);
        Save_bt = (Button) dl.findViewById(R.id.save);
        mContent = (RelativeLayout) dl.findViewById(R.id.mysignature);
        mSignature = new PCI_SIGN_3.signature(this, null);
        mContent.addView(mSignature);
        Save_bt.setOnClickListener(onButtonClick);
        Clear_bt.setOnClickListener(onButtonClick);
        Cancel_bt.setOnClickListener(onButtonClick);

    }

    Button.OnClickListener onButtonClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            if (v == Clear_bt) {
                mSignature.clear();
            } else if (v == Save_bt) {
                mSignature.save();

            } else if (v == Cancel_bt) {
                mSignature.clear();
                dl.dismiss();

            } else if (v == tech_signature) {

                Log.e("MMMM","tech");

                Sign_Check = "Man";
                get_signature(Sign_Check);

            }


        }
    };


    public class signature extends View {
        static final float STROKE_WIDTH = 5f;
        static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        Paint paint = new Paint();
        Path path = new Path();
        float lastTouchX;
        float lastTouchY;
        final RectF dirtyRect = new RectF();

        public signature(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }

        public void clear() {
            path.reset();
            invalidate();
            Save_bt.setEnabled(false);
        }

        public void save() {
            String Man1 = "Man";
            String Co1 = "Co";
            String Msot1 = "msot";
            String Staff1 = "staff";
            Bitmap returnedBitmap = Bitmap.createBitmap(mContent.getWidth(),
                    mContent.getHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(returnedBitmap);

            Drawable bgDrawable = mContent.getBackground();

            if (bgDrawable != null)
                bgDrawable.draw(canvas);
            else
                canvas.drawColor(Color.WHITE);
            mContent.draw(canvas);

            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            //  Context context;
            returnedBitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
            // Bitmap b=BitmapFactory.decodeStream(mContent.getBbs.toByteArray());
            if (Sign_Check.equals("Man")) {
                T_signature.setVisibility(View.VISIBLE);

                T_signature.setImageBitmap(returnedBitmap);
            }
         

            dl.dismiss();

            // finish();
        }


        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();
            Save_bt.setEnabled(true);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;
            }
            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));
            lastTouchX = eventX;
            lastTouchY = eventY;
            return true;
        }

        private void resetDirtyRect(float eventX, float eventY) {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }

    public void check_development_plan() {
    }

    public String getStringImage(Bitmap bmp){
        String encodedImage = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bmp.setPixel(20,100, Color.BLUE);
            byte[] imageBytes = baos.toByteArray();
            encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return encodedImage;
    }

    public void get_profile_db() {
        Cursor c5;
        c5 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
        c5.moveToFirst();

        if (c5 != null) {

            db_user_name = c5.getString(c5.getColumnIndex(db.USER_NAME));


        }
        c5.close();

    }


    private boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            Log.e("Available ", "" + ni.getTypeName());
            if (ni.getTypeName().equalsIgnoreCase("WIFI")) {
                if (ni.isConnected()) {
                    haveConnectedWifi = true;
                    Log.i("WIFI CONNECTION ", "AVAILABLE");
                } else {
                    Log.i("WIFI CONNECTION ", "NOT AVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("MOBILE NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("MOBILE NET CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("USBNET")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("USBNET NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("USBNET NET CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("mobile_internet")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("mobile_net CONNECTION", "AVAILABLE");
                } else {
                    Log.i("mobile_net CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("mobile")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("mobile NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("mobile NET CONNECTION", "NOTAVAILABLE");
                }
            }
        }
        return haveConnectedWifi || haveConnectedMobile;
    }



    public boolean validation() {

        boolean check_point=false;
        try {

                try {
                    bitmap_branch_manager=((BitmapDrawable) T_signature.getDrawable()).getBitmap();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {

                    get_reason=et_reason.getText().toString();


                    get_ass_name=ass_name.getText().toString();

                    if ( get_reason.length() != 0) {
                        check_point = true;
                    } else {
                        check_point = false;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }


                try {
                    if ( bitmap_branch_manager != null) {
                        check_point = true;
                    } else {
                        check_point = false;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }




        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return check_point;

    }

    public void insert_off_sign(String key_id1){


        get_reason=et_reason.getText().toString();
        get_ass_name=ass_name.getText().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_time = sdf.format(new Date());
        try {
            cv_main.put(db.COMPLETED_DATE,date_time);
            cv_main.put(db.STATUS,"Completed");
            sd.update( db.PCI_TITLE_1, cv_main, db.KEY_ID + "=" + key_id + "", null );
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(),"Report Completed Successfully,Please Sync",Toast.LENGTH_SHORT).show();
        try {
            bitmap_branch_manager=((BitmapDrawable) T_signature.getDrawable()).getBitmap();
        } catch (Exception e) {
            Log.e("ZZZZZZZ bran",""+e.getMessage());
            e.printStackTrace();
        }

       
        if (bitmap_branch_manager!=null) {
            ByteArrayOutputStream stream_1 = new ByteArrayOutputStream();
            bitmap_branch_manager.compress(Bitmap.CompressFormat.PNG, 100, stream_1);
            bytes_Man = stream_1.toByteArray();
            cv_sign.put(db.CUSTOMER_SIGN,bytes_Man);
        }

        get_reason=et_reason.getText().toString();

        if(get_reason==null){
            get_reason="";
        }

        Log.e("VVVVVVVVFD","reto name = "+Branch_Name);
        Log.e("VVVVVVVVFD","cus name = "+Co_Name);

        cv_sign.put(db.et1,""+get_reason);
        cv_sign.put(db.MAIN_ID,key_id);
        cv_sign.put(db.CUSTOMER_NAME,get_ass_name);
        cv_sign.put(db.BRANCH_END_DATE,date_time);

        try {
            if (check_sign()==0) {
                sd.insert(db.PCI_SIGN_3,null,cv_sign);
                Intent intent=new Intent(PCI_SIGN_3.this, Category_Type_Activity.class);
                startActivity(intent);


            } else {
                sd.update( db.PCI_SIGN_3, cv_sign, db.MAIN_ID + "=" + key_id + "", null );
                Intent intent=new Intent(PCI_SIGN_3.this, Category_Type_Activity.class);
                startActivity(intent);

            }
        } catch (Exception e) {
            Log.e("ZZZZZZZ 44",""+e.getMessage());
            e.printStackTrace();
        }



        Toast.makeText(getApplicationContext(),"PC INSPECTION REPORT is completed, Kindly sync...",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed(){
    }

    public int check_sign(){
        int count=0;
        String selectQuery = "SELECT * FROM "+db.PCI_SIGN_3+" where MAIN_ID ='"+key_id+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        count=cursor.getCount();
        cursor.close();
        return count;

    }

    public void get_offline(String id){

        Log.e("DDDSSS","E2");
        Log.e("DDDSSS","id = "+id);

        String Query="select * from "+db.PCI_SIGN_3 +" where MAIN_ID = '"+id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        Log.e("RRRTTTH","count"+cursor.getCount());

        if (cursor.getCount()!=0) {
            Log.e("BBBFFFFC","E3");
            try {

                bytes_et2=cursor.getBlob(cursor.getColumnIndex(db.CUSTOMER_SIGN));

                String met1 = cursor.getString(cursor.getColumnIndex(db.CUSTOMER_NAME));
                String met2 = cursor.getString(cursor.getColumnIndex(db.et1));



                et_reason.setText(met2);

          
                if (bytes_et2!=null) {
                    imageStream2 = new ByteArrayInputStream(bytes_et2);
                    bitmap_et2 = BitmapFactory.decodeStream(imageStream2);
                    T_signature.setImageBitmap(Bitmap.createScaledBitmap(bitmap_et2, 400, 400, false));
                    T_signature.setVisibility(View.VISIBLE);

                }
                ass_name.setText(met1);

            } catch (Exception e) {

                Log.e("LLPPPPUU","error ="+e.getMessage());
                e.printStackTrace();
            }
        } else {

            Log.e("XXXDDDD","NNNN Ess");
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu_pci, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pci_home:
                Intent sir_home = new Intent(PCI_SIGN_3.this, Category_Type_Activity.class);
                sir_home.putExtra("key_id", key_id);
                startActivity(sir_home);
                break;
            case R.id.pci_title_page_1:
                if (key_id != null) {
                    Intent sir_customer = new Intent(PCI_SIGN_3.this, PCI_Title_Page_1.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.pci_obs_2:

                if (key_id != null) {
                    Intent sir_observation = new Intent(PCI_SIGN_3.this, PCI_Audit_Page_2.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }

                break;
            case R.id.pci_sign_3:
                Toast.makeText(getApplicationContext(),"Already,You Are in Same Page",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }



}
