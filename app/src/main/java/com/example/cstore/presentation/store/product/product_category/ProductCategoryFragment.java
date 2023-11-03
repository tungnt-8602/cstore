package com.example.cstore.presentation.store.product.product_category;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cstore.R;
import com.example.cstore.databinding.FragmentProductCategoryBinding;
import com.example.cstore.model.Product;
import com.example.cstore.model.api.ApiBuilder;
import com.example.cstore.presentation.store.cart.detail.CartDetailFragment;
import com.example.cstore.presentation.store.product.detail.ProductDetailFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCategoryFragment extends Fragment {

    /* **********************************************************************
     * Variable
     ********************************************************************** */
    private final ProductCategoryViewModel viewModel = new ProductCategoryViewModel();
    public List<Product> productList = new ArrayList<>();
    private RecyclerView recyclerProduct;
    private ProductItemAdapter productAdapter ;
    private FragmentProductCategoryBinding binding;
    private Integer categoryId;
    private static final String ARG_CATEGORY_ID = "categoryId";

    /* **********************************************************************
     * Constructor
     ********************************************************************** */
    public ProductCategoryFragment() {
        // Required empty public constructor
    }

    public static ProductCategoryFragment newInstance(String categoryId) {
        ProductCategoryFragment fragment = new ProductCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_ID, categoryId);
        fragment.setArguments(args);
        return fragment;
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
        binding = FragmentProductCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!= null) {
            String categoryId = getArguments().getString("categoryId");
            ApiBuilder.apiService.getProductsByCategoryId(categoryId)
                    .enqueue(new Callback<List<Product>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                            Log.d("call api", "onResponse: " + response.body());
                            List<Product> lp = response.body();
                            if (lp != null) {
                                productList.addAll(lp);
                            }
                            recyclerProduct = binding.productList;
                            productAdapter = new ProductItemAdapter(requireContext(), lp);
                            recyclerProduct.setAdapter(productAdapter);
                            GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
                            recyclerProduct.setLayoutManager(layoutManager);
                            productAdapter.setOnClickListener((position, p) -> {
                                FragmentManager fm = requireActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                                        R.anim.slide_in,  // enter
                                        R.anim.fade_out,  // exit
                                        R.anim.fade_in,   // popEnter
                                        R.anim.slide_out  // popExit
                                );
                                transaction.replace(R.id.wrapper, new ProductDetailFragment(), null).addToBackStack(null).commit();
                            });
                            productAdapter.setOnAddToCartClickListener(((position, p) -> {
//                                viewModel.addToCart(p);
                                FragmentManager fm = requireActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                                        R.anim.slide_down,  // enter
                                        R.anim.fade_out,  // exit
                                        R.anim.fade_in,   // popEnter
                                        R.anim.slide_up  // popExit
                                );
                                transaction.replace(R.id.wrapper, CartDetailFragment.newInstance(p.getId())).addToBackStack(null).commit();
                            }));
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                            Log.d("call api", "onResponse: " + t.getMessage());
                        }
                    });
        }
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */

    /* **********************************************************************
     * Class
     ********************************************************************** */
}