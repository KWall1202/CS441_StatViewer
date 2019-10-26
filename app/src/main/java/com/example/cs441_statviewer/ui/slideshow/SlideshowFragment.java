package com.example.cs441_statviewer.ui.slideshow;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cs441_statviewer.R;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class SlideshowFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        // Create array of teams
        // Create array of ELO ratings
        Context context = getContext();
        String[] teams = context.getResources().getStringArray(R.array.global_top_50_teams);
        String[] regions = context.getResources().getStringArray(R.array.global_top_50_regions);
        String[] elos = context.getResources().getStringArray(R.array.global_top_50_elos);
        TableLayout eloTable = root.findViewById(R.id.global_elo_table);


        for(int i=0; i < teams.length; i++) {
            TableRow newRow = new TableRow(context);
            newRow.setBackgroundColor(Color.BLACK);
            newRow.addView(makeFormattedTextView(Integer.toString(i+1)));
            newRow.addView(makeFormattedTextView(teams[i]));
            newRow.addView(makeFormattedTextView(regions[i]));
            newRow.addView(makeFormattedTextView(elos[i]));
            eloTable.addView(newRow);
        }

        return root;
    }

    private LinearLayout makeFormattedTextView(String text) {
        // Have to do layout params of Parent class!!
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams();
        rowParams.width = TableRow.LayoutParams.MATCH_PARENT;
        rowParams.height = TableRow.LayoutParams.MATCH_PARENT;
        rowParams.setMargins(2,2,2,2);

        LinearLayout layout = new LinearLayout(getContext());
        layout.setBackgroundColor(Color.WHITE);
        layout.setLayoutParams(rowParams);

        TextView textView = new TextView(getContext());
        layout.addView(textView);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setText(text);

        return layout;
    }
}