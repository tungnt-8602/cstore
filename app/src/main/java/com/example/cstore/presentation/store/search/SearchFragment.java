package com.example.cstore.presentation.store.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cstore.R;
import com.example.cstore.databinding.FragmentSearchBinding;
import com.example.cstore.model.Product;
import com.example.cstore.model.api.ApiBuilder;
import com.example.cstore.presentation.store.product.detail.ProductDetailFragment;
import com.example.cstore.presentation.store.product.product_category.ProductItemAdapter;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;
    ProductItemAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.back.setOnClickListener(it -> getParentFragmentManager().popBackStack());
        binding.searchKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.equals("")){
                    adapter = new ProductItemAdapter(requireContext(), new ArrayList<>());
                    binding.searchList.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                    binding.searchList.setAdapter(adapter);
                }else {
                    ApiBuilder.apiService.getProductsByName(URLDecoder.decode(charSequence.toString()) )
                            .enqueue(new Callback<List<Product>>() {
                                @Override
                                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                                    List<Product> pList = response.body();
                                    if(pList == null || pList.size() == 0){
                                        adapter = new ProductItemAdapter(requireContext(), new ArrayList<>());
                                        binding.searchList.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                                        binding.searchList.setAdapter(adapter);
                                    }else {
                                        adapter = new ProductItemAdapter(requireContext(), pList);
                                        binding.searchList.setLayoutManager(new GridLayoutManager(requireContext(), 2));
                                        binding.searchList.setAdapter(adapter);
                                        adapter.setOnClickListener((position, p) -> {
                                            FragmentManager fm = requireActivity().getSupportFragmentManager();
                                            FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                                                    R.anim.slide_in,  // enter
                                                    R.anim.fade_out,  // exit
                                                    R.anim.fade_in,   // popEnter
                                                    R.anim.slide_out  // popExit
                                            );
                                            transaction.replace(R.id.wrapper, ProductDetailFragment.newInstance(p.getId()), null).addToBackStack(null).commit();
                                        });
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Product>> call, Throwable t) {

                                }
                            });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}