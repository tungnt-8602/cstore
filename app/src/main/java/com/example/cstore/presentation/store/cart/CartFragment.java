package com.example.cstore.presentation.store.cart;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cstore.R;
import com.example.cstore.databinding.FragmentCartBinding;
import com.example.cstore.model.ProductOrder;
import com.example.cstore.presentation.store.product.detail.ProductDetailFragment;

import java.util.List;

public class CartFragment extends Fragment {

    FragmentCartBinding binding;
    private RecyclerView recyclerCart;
    private ItemCartAdapter poAdapter;
    private CartViewModel viewModel = new CartViewModel();
    /* **********************************************************************
     * Constructor
     ********************************************************************** */
    public CartFragment() {
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
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerCart = binding.cartList;
        List<ProductOrder> poList = viewModel.getCart();
        poAdapter = new ItemCartAdapter(requireContext(), poList);
        recyclerCart.setAdapter(poAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerCart.setLayoutManager(layoutManager);
        poAdapter.setOnClickListener((position, p) -> {
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                    R.anim.slide_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.slide_out  // popExit
            );
            transaction.replace(R.id.wrapper, new ProductDetailFragment(), null).addToBackStack(null).commit();
        });
    }
}