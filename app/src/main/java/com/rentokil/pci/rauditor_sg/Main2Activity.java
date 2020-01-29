package com.rentokil.pci.rauditor_sg;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {


    Button button;
    AlertDialog alertDialog1;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateAlertDialogWithRadioButtonGroup() ;

            }
        });
    }

    public void CreateAlertDialogWithRadioButtonGroup() {


        final String[] listItems = {"one", "two", "three", "four", "five"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
        builder.setTitle("Choose item");

        int checkedItem = 0; //this will checked the item when user open the dialog
        builder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Main2Activity.this, "Position: " + which + " Value: " + listItems[which], Toast.LENGTH_LONG).show();
                textView.setText(listItems[which]);
            }
        });

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//                textView.setText(which);
                dialog.dismiss();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
