package com.rentokil.pci.rauditor_vn;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class workout extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);


 
        company pci = new company();
        pci.set_name("ashvinvinoth");


        Log.e("HRREWWSSS","name = "+pci.get_name());
    }

    public class company{

        private  String name ="Rentokil";

        public String get_name(){

            return name;
        }

        public void set_name(String newname){

            this.name=newname;

        }





    }


}
