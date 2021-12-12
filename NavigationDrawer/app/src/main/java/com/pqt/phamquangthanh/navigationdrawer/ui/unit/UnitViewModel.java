package com.pqt.phamquangthanh.navigationdrawer.ui.unit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UnitViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UnitViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}