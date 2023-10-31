package com.example.cstore.presentation.store.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import com.example.broadcastation.common.logger.Logger;
import com.example.cstore.databinding.FragmentProductBinding;
import com.example.cstore.presentation.MainActivity;
import com.example.cstore.presentation.store.product.product_category.ProductCategoryAdapter;
import com.example.cstore.presentation.store.product.product_category.ProductCategoryFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    private FragmentProductBinding binding;
    private ProductViewModel viewModel;

    /* **********************************************************************
     * Constructor
     ********************************************************************** */
    public ProductFragment() {
    }

    /* **********************************************************************
     * Lifecycle
     ********************************************************************** */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<ProductCategoryFragment> fragments = new ArrayList<>();
        //TODO: call api category and products by that category id
        //TODO: set it to fragment list

        //Fake data
        for (int i = 0; i < 10; i++) {
            fragments.add(new ProductCategoryFragment());
        }
        binding.viewPager.setAdapter(new ProductCategoryAdapter((MainActivity) requireActivity(), fragments));
        new TabLayoutMediator(binding.categoryTabLayout, binding.viewPager, (tab, position) -> {
            tab.setText("CATEGORY".toUpperCase());
        }).attach();
        binding.categoryTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Integer position = tab.getPosition();
                if (position != null) {
                    binding.viewPager.setCurrentItem(position);
                    binding.categoryTabLayout.selectTab(binding.categoryTabLayout.getTabAt(position));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
}