package com.rentokil.pci.rauditor_sg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RequestActivity extends AppCompatActivity {

    TextView mkelogn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_test);

        mkelogn=(TextView) findViewById(R.id.signinhere);

        mkelogn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RequestActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
