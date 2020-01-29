package com.rentokil.pci.rauditor_sg;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes;
import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {
    String key_id;
    DatabaseHelper db;
    SQLiteDatabase sd;
    Button btn_generate;
    TextView tv_link;
    ImageView iv_image;
    LinearLayout ll_pdflayout;
    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    boolean boolean_save;
    Bitmap bitmap;
    ProgressDialog progressDialog;
TextView txt_1,txt_2,txt_3,txt_4,txt_5,txt_head;
    ArrayList<List_Item_Methodes> arraylist;
String get_et1_1,get_et1_2,get_et1_3,get_et1_4,get_et1_5,get_et1_6,get_et1_7,get_status;
    ListView lst;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


      db = new DatabaseHelper(this);
      sd = db.getReadableDatabase();
      Intent intent1=getIntent();
      key_id=intent1.getStringExtra("key_id");
      Log.e("AAXCCSAA"," id 1 = "+key_id);
      lst = (ListView) findViewById(R.id.lst);
      arraylist = new ArrayList<List_Item_Methodes>();


      get_pc_page_1(key_id);


      request(key_id);

      txt_head=(TextView) findViewById(R.id.txt_head);
      txt_1=(TextView) findViewById(R.id.txt_1);
      txt_2=(TextView) findViewById(R.id.txt_2);
      txt_3=(TextView) findViewById(R.id.txt_3);
      txt_4=(TextView) findViewById(R.id.txt_4);
      txt_5=(TextView) findViewById(R.id.txt_5);


      txt_head.setText(get_et1_1);
      txt_1.setText(get_et1_4);
      txt_2.setText(get_et1_3);
      txt_3.setText(get_et1_7);
      txt_4.setText(get_et1_5);
      txt_5.setText(get_et1_2);
      init();
      fn_permission();
      listener();
      
    }

    public void get_pc_page_1(String key){
        String et1,et2;
        String Query="select * from "+db.PCI_TITLE_1 +" where KEY_ID = '" +key+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();
        if(cursor.getCount()!=0){

            get_et1_1 = cursor.getString(cursor.getColumnIndex(db.et1));
            get_et1_2 = cursor.getString(cursor.getColumnIndex(db.et2));
            get_et1_3 = cursor.getString(cursor.getColumnIndex(db.et3));
            get_et1_4 = cursor.getString(cursor.getColumnIndex(db.et4));
            get_et1_5 = cursor.getString(cursor.getColumnIndex(db.et5));
            get_et1_6 = cursor.getString(cursor.getColumnIndex(db.et6));
            get_et1_7 = cursor.getString(cursor.getColumnIndex(db.et7));
            get_status = cursor.getString(cursor.getColumnIndex(db.STATUS));
        }

        cursor.close();
    }





    private void init(){
        btn_generate = (Button)findViewById(R.id.btn_generate);
        tv_link = (TextView)findViewById(R.id.tv_link);
        iv_image = (ImageView) findViewById(R.id.iv_image);
        ll_pdflayout = (LinearLayout) findViewById(R.id.ll_pdflayout);

    }

    private void listener(){
        btn_generate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_generate:

                if (boolean_save) {
                    Intent intent = new Intent(getApplicationContext(), PDFViewActivity.class);
                    startActivity(intent);

                } else {
                    if (boolean_permission) {
                        progressDialog = new ProgressDialog(Main4Activity.this);
                        progressDialog.setMessage("Please wait");
                        bitmap = loadBitmapFromView(ll_pdflayout, ll_pdflayout.getWidth(), ll_pdflayout.getHeight());
                        createPdf();
//                        saveBitmap(bitmap);
                    } else {

                    }

                    createPdf();
                    break;
                }
        }
    }

    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();


        Paint paint = new Paint();
        canvas.drawPaint(paint);


        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);


        // write the document content
        String targetPdf = "/sdcard/test.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            btn_generate.setText("Check PDF");
            boolean_save=true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
    }



    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)||
                (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(Main4Activity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(Main4Activity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);

            }

            if ((ActivityCompat.shouldShowRequestPermissionRationale(Main4Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(Main4Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);

            }
        } else {
            boolean_permission = true;


        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                boolean_permission = true;


            } else {
                Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

            }
        }
    }

    public void request(String key) {

arraylist.clear();
        String selectQuery = "select * from (select KEY_ID as KEY_ID,et1 as Customer_name,et2 as Conducted_date,et3 as report_no,COALESCE(" +
                "  ('PCI')," +
                "  'PCI'" +
                " ) AS Type from PCI_INSPEC_2 where MAIN_ID = '" + key_id + "'" +
                " )tbl order by KEY_ID desc";


        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Log.e("HHHHHH count",""+cursor.getCount());
        List_Item_Methodes wp;
        if(cursor.getCount()!=0){
            for (int i=0;i<cursor.getCount();i++) {
                wp = new List_Item_Methodes( Integer.parseInt(cursor.getString(cursor.getColumnIndex("KEY_ID"))),
                        cursor.getString(cursor.getColumnIndex("Type")),
                        cursor.getString(cursor.getColumnIndex("Customer_name")) ,
                        cursor.getString(cursor.getColumnIndex("Conducted_date")),
                        "","");
                arraylist.add( wp );
                cursor.moveToNext();
            }

            CustomAdapter customAdapter = new CustomAdapter(Main4Activity.this, arraylist);
            lst.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();

        }

    }
}

