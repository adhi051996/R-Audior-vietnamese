package com.rentokil.pci.rauditor_sg.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.rentokil.pci.rauditor_sg.R;

/**
 * Created by Rp on 8/30/2016.
 */
public class Fragment_Banner extends Fragment {

    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);



        return view;

    }
    }
