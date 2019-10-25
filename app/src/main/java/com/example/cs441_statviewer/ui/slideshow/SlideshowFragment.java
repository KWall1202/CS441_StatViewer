package com.example.cs441_statviewer.ui.slideshow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cs441_statviewer.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Create array of teams
        // Create array of ELO ratings
        Context context = getContext();
        String[] teams = context.getResources().getStringArray(R.array.global_top_50_teams);
        String[] regions = context.getResources().getStringArray(R.array.global_top_50_regions);
        String[] elos = context.getResources().getStringArray(R.array.global_top_50_elos);
        TableLayout eloTable = root.findViewById(R.id.global_elo_table);

        ViewGroup.LayoutParams textViewParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        for(int i=0; i < 1; i++) {
            TableRow newRow = new TableRow(context);
            TextView ranking = new TextView(context);
            TextView teamName = new TextView(context);
            TextView elo = new TextView(context);
            TextView region = new TextView(context);
            ranking.setText(Integer.toString(i+1));
            ranking.setLayoutParams(textViewParams);
            teamName.setText(teams[i]);
            teamName.setLayoutParams(textViewParams);
            region.setText(regions[i]);
            region.setLayoutParams(textViewParams);
            elo.setText(elos[i]);
            elo.setLayoutParams(textViewParams);
            newRow.addView(ranking);
            newRow.addView(teamName);
            newRow.addView(region);
            newRow.addView(elo);
            eloTable.addView(eloTable);
        }

        return root;
    }
}