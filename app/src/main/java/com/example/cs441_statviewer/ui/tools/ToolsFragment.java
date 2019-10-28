package com.example.cs441_statviewer.ui.tools;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;

public class ToolsFragment extends Fragment {

    private String[] teams;
    private String[] regions;
    private String[] elos;
    private Context context;
    private static ArrayList<String> regionList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        final TableLayout eloTable = root.findViewById(R.id.regional_elo_table);
        context = getContext();
        teams = context.getResources().getStringArray(R.array.global_top_50_teams);
        regions = context.getResources().getStringArray(R.array.global_top_50_regions);
        elos = context.getResources().getStringArray(R.array.global_top_50_elos);
        TableRow buttonsRow = root.findViewById(R.id.regional_button_row);
        for(int i = 0; i < buttonsRow.getChildCount(); i++) {
            Button button = (Button)buttonsRow.getChildAt(i);
            final String text = button.getText().toString();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    regionList.clear();
                    regionList.add(text);
                    filterTableByRegion(eloTable);
                }
            });
        }

        filterTableByRegion(eloTable);
        return root;
    }

    private void filterTableByRegion(TableLayout eloTable){
        // Create array of teams
        // Create array of ELO ratings
        while(eloTable.getChildCount() > 1) {
            eloTable.removeViewAt(1);
        }
        int j=0;
        for(int i=0; i < teams.length; i++) {
            boolean proceed = false;
            for(int k=0; k < regionList.size(); k++) {
                if(regions[i].equals(regionList.get(k))) proceed = true;
            }
            if(proceed) {
                TableRow newRow = new TableRow(context);
                newRow.setBackgroundColor(Color.BLACK);
                newRow.addView(makeFormattedTextView(Integer.toString(++j)));
                newRow.addView(makeFormattedTextView(teams[i]));
                newRow.addView(makeFormattedTextView(regions[i]));
                newRow.addView(makeFormattedTextView(elos[i]));
                eloTable.addView(newRow);
            }
        }
    }

    private LinearLayout makeFormattedTextView(String text) {
        // Have to do layout params of Parent class!!
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams();
        rowParams.width = TableRow.LayoutParams.MATCH_PARENT;
        rowParams.height = TableRow.LayoutParams.MATCH_PARENT;
        rowParams.setMargins(2,2,2,2);

        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundColor(Color.WHITE);
        layout.setLayoutParams(rowParams);

        TextView textView = new TextView(context);
        layout.addView(textView);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setText(text);

        return layout;
    }

    public static void addRegion(String reg){
        regionList.add(reg);
    }

    public static void clearRegions(){
        regionList.clear();
    }
}