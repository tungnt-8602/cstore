package com.example.cstore.presentation.store.product;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import java.util.Collections;
import java.util.List;

public class ProductViewModel extends ViewModel {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    private List<SubTab> tabs;

    {
        tabs = Collections.emptyList();
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
    public List<SubTab> getTabs() {
        return tabs;
    }

    /* **********************************************************************
     * Class
     ********************************************************************** */
    public static class SubTab {
        public final int category;
        public Fragment fragment;

        public SubTab(int category, Fragment fragment) {
            this.category = category;
            this.fragment = fragment;
        }
    }
}
