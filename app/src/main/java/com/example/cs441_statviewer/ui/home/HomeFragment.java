package com.example.cs441_statviewer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cs441_statviewer.R;
import com.example.cs441_statviewer.ui.tools.ToolsFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        LinearLayout checkboxes = root.findViewById(R.id.checkboxes);
        final ArrayList<CheckBox> boxes = new ArrayList<>();
        for(int i=1; i< checkboxes.getChildCount(); i++) boxes.add((CheckBox) checkboxes.getChildAt(i));
        for(int i=1; i < checkboxes.getChildCount(); i++) {
            checkboxes.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToolsFragment.clearRegions();
                    for(CheckBox i : boxes) {
                        if(i.isChecked()) {
                            ToolsFragment.addRegion(i.getText().toString());
                        }
                    }
                }
            });
        }
        return root;
    }
}