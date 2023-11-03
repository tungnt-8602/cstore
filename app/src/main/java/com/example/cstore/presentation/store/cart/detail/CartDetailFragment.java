package com.example.cstore.presentation.store.cart.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.cstore.R;
import com.example.cstore.databinding.FragmentCartDetailBinding;
import com.example.cstore.model.Product;
import com.example.cstore.model.api.ApiBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartDetailFragment extends Fragment {
    /* **********************************************************************
     * Variable
     ********************************************************************** */

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PRODUCT_ID= "productId";

    // TODO: Rename and change types of parameters
    private String productId;
    FragmentCartDetailBinding binding;
    ArrayAdapter colorAdapter ;
    ListPopupWindow colorPopup;
    ArrayAdapter sizeAdapter ;
    ListPopupWindow sizePopup;

    /* **********************************************************************
     * Constructor
     ********************************************************************** */
    public CartDetailFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static CartDetailFragment newInstance(String productId) {
        CartDetailFragment fragment = new CartDetailFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        colorPopup = new ListPopupWindow(requireContext());
        sizePopup = new ListPopupWindow(requireContext());

        ApiBuilder.apiService.getProductById(productId).
                enqueue(new Callback<Product>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        Product p = response.body();
                        if (p != null) {
                            Glide.with(requireContext()).load(p.getImages().get(0).getUrl()).into(binding.poImage);
                            binding.poName.setText(p.getName());
                            binding.poPrice.setText(p.getPrice().toString());
                            binding.poInStock.setText(binding.poInStock.getText() + p.getQuantity().toString());
                            List<String> colorOption = p.getColors();
                            List<String> sizeOption = p.getSizes();
                            colorAdapter = new ArrayAdapter(getContext(), R.layout.dropdown_item, colorOption);
                            colorPopup.setAdapter(colorAdapter);
                            colorPopup.setAnchorView(binding.colorTopic);
                            colorPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    binding.colorText.setText(colorOption.get(i));
                                    colorPopup.dismiss();
                                }
                            });

                            binding.colorText.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    colorPopup.show();
                                }
                            });

                            sizeAdapter = new ArrayAdapter(getContext(), R.layout.dropdown_item, sizeOption);
                            sizePopup.setAdapter(sizeAdapter);
                            sizePopup.setAnchorView(binding.sizeTopic);
                            sizePopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    binding.sizeText.setText(sizeOption.get(i));
                                    sizePopup.dismiss();
                                }
                            });

                            binding.sizeText.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    sizePopup.show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {

                    }
                });
    }
}