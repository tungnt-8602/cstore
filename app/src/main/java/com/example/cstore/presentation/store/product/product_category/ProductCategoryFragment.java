package com.example.cstore.presentation.store.product.product_category;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cstore.R;
import com.example.cstore.databinding.FragmentProductCategoryBinding;
import com.example.cstore.model.Product;
import com.example.cstore.model.Products;
import com.example.cstore.model.api.ApiBuilder;
import com.example.cstore.presentation.store.product.detail.ProductDetailFragment;
import com.google.android.material.snackbar.Snackbar;

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
    public List<Products> productsList = new ArrayList<>();
    public List<Product> productList = new ArrayList<>();
    public List<String> colorList = new ArrayList<>();
    public List<String> sizeList = new ArrayList<>();
    public List<String> imageList = new ArrayList<>();
    private RecyclerView recyclerProduct;
    private ProductItemAdapter productAdapter ;
    private FragmentProductCategoryBinding binding;

    /* **********************************************************************
     * Constructor
     ********************************************************************** */
    public ProductCategoryFragment() {
        // Required empty public constructor
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
        initProdcts();
        recyclerProduct = binding.productList;
        productAdapter = new ProductItemAdapter(requireContext(), productsList);
        recyclerProduct.setAdapter(productAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(),2);
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
            viewModel.addToCart(p);
            Snackbar.make(binding.productList, "Add to cart", Snackbar.LENGTH_SHORT).show();
        }));

        ApiBuilder.apiService.getProductList("")
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                        Log.d("call api", "onResponse: " + response.body());
                        List<Product> lp = response.body();
                        if(lp != null){
                            productList.addAll(lp);
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                        Log.d("call api", "onResponse: " + t.getMessage());
                    }
                });
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
    private void initProdcts(){
        colorList.add("Trắng");
        colorList.add("Xanh biển");
        colorList.add("Be");
        colorList.add("Xanh đậm");
        colorList.add("Đen");
        colorList.add("Nâu");
        colorList.add("Xanh mint");
        colorList.add("Xanh rêu");
        colorList.add("Xanh tím");
        sizeList.add("S");
        sizeList.add("M");
        sizeList.add("L");
        sizeList.add("XL");
        sizeList.add("2XL");
        sizeList.add("3XL");
        sizeList.add("4XL");
        imageList.add("https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b");
        productsList.add(new Products("1", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productsList.add(new Products("2", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://mcdn.coolmate.me/image/July2023/mceclip0_67.jpg"));
        productsList.add(new Products("3", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productsList.add(new Products("4", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productsList.add(new Products("5", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://mcdn.coolmate.me/image/July2023/mceclip0_67.jpg"));
        productsList.add(new Products("6", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productsList.add(new Products("7", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productsList.add(new Products("8", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productsList.add(new Products("9", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://mcdn.coolmate.me/image/July2023/mceclip0_67.jpg"));
        productsList.add(new Products("10", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));

    }
}