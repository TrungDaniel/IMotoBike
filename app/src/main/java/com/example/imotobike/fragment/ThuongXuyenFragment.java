package com.example.imotobike.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imotobike.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThuongXuyenFragment extends Fragment {


    public ThuongXuyenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thuong_xuyen, container, false);
    }

}
