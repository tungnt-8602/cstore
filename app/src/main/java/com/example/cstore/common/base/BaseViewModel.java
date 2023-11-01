package com.example.cstore.common.base;

import androidx.lifecycle.ViewModel;

import com.example.cstore.control.LocalControl;
import com.google.gson.Gson;

public abstract class BaseViewModel extends ViewModel {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    public Gson gson = new Gson();
    public LocalControl local = LocalControl.getInstance();
}
