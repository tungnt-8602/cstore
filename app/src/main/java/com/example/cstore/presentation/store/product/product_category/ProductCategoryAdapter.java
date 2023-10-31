package com.example.cstore.presentation.store.product.product_category;

import androidx.annotation.NonNull;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cstore.presentation.MainActivity;

import java.util.List;

public class ProductCategoryAdapter extends FragmentStateAdapter {

    private final List<ProductCategoryFragment> fragments;

    public ProductCategoryAdapter(MainActivity fragmentActivity, List<ProductCategoryFragment> fragments) {
        super(fragmentActivity);
        this.fragments = fragments;
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    @NonNull
    @Override
    public ProductCategoryFragment createFragment(int position) {
        return fragments.get(position);
    }
}