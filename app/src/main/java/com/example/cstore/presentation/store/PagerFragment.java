package com.example.cstore.presentation.store;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cstore.databinding.FragmentPagerBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class PagerFragment extends Fragment {

    /* **********************************************************************
     * Variable
     ********************************************************************** */
    private FragmentPagerBinding binding;
    private PagerViewModel viewModel;

    /* **********************************************************************
     * Constructor
     ********************************************************************** */
    public PagerFragment() {
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
        binding = FragmentPagerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PagerViewModel.class);
        List<PagerViewModel.Tab> tabs = viewModel.getTabs();
        PagerAdapter adapter = new PagerAdapter(tabs, (AppCompatActivity) requireActivity());
        binding.viewpager.setAdapter(adapter);
        new TabLayoutMediator(binding.navigation, binding.viewpager, (tab, position) -> {
            tab.setIcon(getResources().getDrawable(tabs.get(position).icon));
        }).attach();
        binding.viewpager.setUserInputEnabled(false);
        binding.navigation.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Integer position = tab.getPosition();
                binding.viewpager.setCurrentItem(position);
                binding.navigation.selectTab(binding.navigation.getTabAt(position));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}