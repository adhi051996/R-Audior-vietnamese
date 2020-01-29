package com.rentokil.pci.rauditor_sg.Image;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.rentokil.pci.rauditor_sg.R;

public class Snackbar_Test extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar__test);

        coordinatorLayout=(CoordinatorLayout) findViewById(R.id.coordinatorLayout);



    }
}
