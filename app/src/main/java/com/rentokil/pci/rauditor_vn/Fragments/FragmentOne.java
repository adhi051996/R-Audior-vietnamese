package com.rentokil.pci.rauditor_vn.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.rentokil.pci.rauditor_vn.R;

/**
 * Created by ashvin vinoth on 11-09-2018.
 */

public class FragmentOne extends Fragment {
    Intent intent;
    private OnFragmentInteractionListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        intent= Please c Intent(getActivity(), ExpandableListView.class);
//        startActivity(intent);

        return inflater.inflate(R.layout.activity_fragmentone, container, false);


    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment childFragment = new Fragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, childFragment).commit();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void messageFromParentFragment(Uri uri);
    }


}
