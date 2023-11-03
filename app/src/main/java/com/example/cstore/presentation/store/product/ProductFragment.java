package com.example.cstore.presentation.store.product;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cstore.R;
import com.example.cstore.databinding.FragmentProductBinding;
import com.example.cstore.model.Category;
import com.example.cstore.model.api.ApiBuilder;
import com.example.cstore.presentation.MainActivity;
import com.example.cstore.presentation.store.product.product_category.ProductCategoryAdapter;
import com.example.cstore.presentation.store.product.product_category.ProductCategoryFragment;
import com.example.cstore.presentation.store.search.SearchFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        List<Category> categoryList = new ArrayList<>();
        ApiBuilder.apiService.getAllCategory()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                        Log.d("call api", "onResponse: " + response.body());
                        List<Category> categories = response.body();
                        if(categories != null){
                            categoryList.addAll(categories);
                        }
                        for (Category c: categoryList) {
                            ProductCategoryFragment f = ProductCategoryFragment.newInstance(c.getId());
                            fragments.add(f);
                        }
                        binding.viewPager.setAdapter(new ProductCategoryAdapter((MainActivity) requireActivity(), fragments));
                        new TabLayoutMediator(binding.categoryTabLayout, binding.viewPager, (tab, position) -> {
                            tab.setText(categoryList.get(position).getName().toUpperCase());
                        }).attach();
                        binding.categoryTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                int position = tab.getPosition();
                                binding.viewPager.setCurrentItem(position);
                                binding.categoryTabLayout.selectTab(binding.categoryTabLayout.getTabAt(position));
                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            }
                        });
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                        Log.d("call api", "onResponse: " + t.getMessage());
                    }
                });
        //TODO: call api category and products by that category id
        //TODO: set it to fragment list

        //Fake data


        binding.textSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                );
                    transaction.replace(R.id.wrapper, new SearchFragment(), null).addToBackStack(null).commit();

            }
        });
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
}