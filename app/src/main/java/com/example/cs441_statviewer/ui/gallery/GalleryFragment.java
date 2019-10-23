package com.example.cs441_statviewer.ui.gallery;

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

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Context context = getContext();
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TableLayout NARLCSStandingsTable = root.findViewById(R.id.na_rlcs_standings_table);
        String[] NARLCSTeamNames = context.getResources().getStringArray(R.array.NA_RLCS_Teams);
        for(int i=1; i < NARLCSStandingsTable.getChildCount(); i++) {
            TableRow curRow = (TableRow) NARLCSStandingsTable.getChildAt(i);
            TextView placement = (TextView) curRow.getChildAt(0);
            TextView teamName = (TextView) curRow.getChildAt(1);
            placement.setText(Integer.toString(i));
            teamName.setText(NARLCSTeamNames[i-1]);
        }
        return root;
    }
}