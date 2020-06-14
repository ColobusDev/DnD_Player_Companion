package com.colobus.dndplayercompanion.ui.dice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiceViewModel extends ViewModel {

    private MutableLiveData<String> d2_value;
    private MutableLiveData<String> d4_value;
    private MutableLiveData<String> d6_value;
    private MutableLiveData<String> d8_value;
    private MutableLiveData<String> d10_value;
    private MutableLiveData<String> d12_value;
    private MutableLiveData<String> d20_value;
    private MutableLiveData<String> d100_value;


    public DiceViewModel() {
        d2_value = new MutableLiveData<>();
        d4_value = new MutableLiveData<>();
        d6_value = new MutableLiveData<>();
        d8_value = new MutableLiveData<>();
        d10_value = new MutableLiveData<>();
        d12_value = new MutableLiveData<>();
        d20_value = new MutableLiveData<>();
        d100_value = new MutableLiveData<>();
    }

    public MutableLiveData<String> getD2_value() {
        return d2_value;
    }

    public MutableLiveData<String> getD4_value() {
        return d4_value;
    }

    public MutableLiveData<String> getD6_value() {
        return d6_value;
    }

    public MutableLiveData<String> getD8_value() {
        return d8_value;
    }

    public MutableLiveData<String> getD10_value() {
        return d10_value;
    }

    public MutableLiveData<String> getD12_value() {
        return d12_value;
    }

    public MutableLiveData<String> getD20_value() {
        return d20_value;
    }

    public MutableLiveData<String> getD100_value() {
        return d100_value;
    }
}
