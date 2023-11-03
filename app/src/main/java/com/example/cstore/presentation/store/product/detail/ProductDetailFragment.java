package com.example.cstore.presentation.store.product.detail;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cstore.R;
import com.example.cstore.common.SliderData;
import com.example.cstore.common.SliderVerticalAdapter;
import com.example.cstore.databinding.FragmentProductDetailBinding;
import com.example.cstore.model.Image;
import com.example.cstore.model.Product;
import com.example.cstore.model.api.ApiBuilder;
import com.example.cstore.presentation.store.cart.CartFragment;
import com.example.cstore.presentation.store.cart.detail.CartDetailFragment;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends Fragment {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    private static final String ARG_PRODUCT_ID= "productId";

    // TODO: Rename and change types of parameters
    private String productId;
    FragmentProductDetailBinding binding;

    /* **********************************************************************
     * Constructor
     ********************************************************************** */
    public ProductDetailFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static ProductDetailFragment newInstance(String productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }
    /* **********************************************************************
     * Lifecycle
     ********************************************************************** */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productId = getArguments().getString(ARG_PRODUCT_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchProductApi();
        binding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                );
                transaction.replace(R.id.wrapper, new CartFragment(), null).addToBackStack(null).commit();
            }
        });

        binding.back.setOnClickListener(it -> getParentFragmentManager().popBackStack());
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
    private void fetchProductApi() {
        ArrayList<String> imageSlider = new ArrayList<>();
        ApiBuilder.apiService.getProductById(productId).
                enqueue(new Callback<Product>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        Product p = response.body();
                        for (Image image: p.getImages()) {
                            imageSlider.add(image.getUrl());
                        }
                        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
                        SliderView sliderView = binding.imageProductSlider;
                        for (String imageItem : imageSlider) {
                            sliderDataArrayList.add(new SliderData(imageItem));
                        }
                        SliderVerticalAdapter adapter = new SliderVerticalAdapter(sliderDataArrayList);
                        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                        sliderView.setSliderAdapter(adapter);
                        sliderView.setScrollTimeInSec(3);
                        sliderView.setAutoCycle(true);
                        sliderView.startAutoCycle();

                        binding.productName.setText(p.getName());
                        binding.productPrice.setText(p.getPrice().toString());
                        binding.colorPick.setAdapter(new MyBaseAdapter(requireContext(), p.getColors()));
                        binding.sizePick.setAdapter(new MyBaseAdapter(requireContext(), p.getSizes()));

                        binding.cartAddBtn.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onClick(View view) {
                                FragmentManager fm = requireActivity().getSupportFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                                        R.anim.slide_down,  // enter
                                        R.anim.fade_out,  // exit
                                        R.anim.fade_in,   // popEnter
                                        R.anim.slide_up  // popExit
                                );
                                transaction.replace(R.id.wrapper, CartDetailFragment.newInstance(p.getId())).addToBackStack(null).commit();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {

                    }
                });
    }
}
