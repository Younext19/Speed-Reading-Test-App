package com.damso.speedreadingtest;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    public TestFragment() {
        // Required empty public constructor
    }
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_test, container, false);
        // HNA KHASNI NEDI L ACTIVITY START TEST BECH TEBDA TEST MIN TLANCER CHRONO
        // HNA YJI BUTTON WTEXTVIEW YGOLEK CHA YLA9 DIR
        btn = view.findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TestActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
