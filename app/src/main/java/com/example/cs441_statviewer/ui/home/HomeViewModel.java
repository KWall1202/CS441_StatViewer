package com.example.cs441_statviewer.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the home page!\n" +
                "This is a rocket league standings viewing app!\n" +
                "You can view the global rankings on the global rankings page\n" +
                "Or you can filter the rankings by region on the regional page\n" +
                "Use the checkboxes below if you want to filter that table by multiple regions at once!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}