package com.example.cstore.presentation.store.cart;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.cstore.R;
import com.example.cstore.common.Utility;
import com.example.cstore.databinding.FragmentCartBinding;
import com.example.cstore.model.Order;
import com.example.cstore.model.ProductOrder;
import com.example.cstore.model.ShippingDelivery;
import com.example.cstore.model.api.ApiBuilder;
import com.example.cstore.presentation.store.product.detail.ProductDetailFragment;
import com.example.cstore.presentation.store.setting.SignupFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {

    FragmentCartBinding binding;
    private RecyclerView recyclerCart;
    private ItemCartAdapter poAdapter;
    ArrayAdapter shippingAdapter ;
    ListPopupWindow shippingPopup;
    CartViewModel viewModel = new CartViewModel();
    Integer totalPrice = 0;
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
        binding.back.setOnClickListener(it -> getParentFragmentManager().popBackStack());
        recyclerCart = binding.cartList;
        shippingPopup = new ListPopupWindow(requireContext());
        List<ProductOrder> poList = viewModel.getCart();
        if(poList.size() == 0){
            binding.totalPrice.setText(getResources().getString(viewModel.noPoInCart));
        } else {
            binding.totalPrice.setText(Utility.formatIntNumber(viewModel.getProductPrice() + viewModel.getShippingPrice()));
        }
        poAdapter = new ItemCartAdapter(requireContext(), poList);
        binding.cartList.setAdapter(poAdapter);
        binding.cartList.setLayoutManager(new LinearLayoutManager(requireContext()));
        poAdapter.setOnClickListener((position, po) -> {
            recyclerCart.setAdapter(poAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
            recyclerCart.setLayoutManager(layoutManager);
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                    R.anim.slide_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.slide_out  // popExit
            );
            transaction.replace(R.id.wrapper, ProductDetailFragment.newInstance(po.getId()), null).addToBackStack(null).commit();
        });

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ProductOrder po = poList.get(position);
                poList.remove(position);
                viewModel.updateAllCart(poList);
                Integer price = viewModel.getProductPrice() - po.getOrderNumber()*po.getPrice();
                viewModel.updateProductPrice(price);

                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle(getResources().getString(viewModel.deleteConfirmTitle));
                builder.setMessage(getResources().getString(viewModel.deleteConfirmMessage));
                builder.setPositiveButton(getResources().getString(viewModel.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        poAdapter.notifyDataSetChanged();
                        if(poList.size() == 0){
                            binding.totalPrice.setText(getResources().getString(viewModel.noPoInCart));
                        }else{
                            binding.totalPrice.setText(Utility.formatIntNumber(viewModel.getProductPrice() + viewModel.getShippingPrice()));

                            Snackbar.make(
                                            binding.getRoot(),getResources().getString(viewModel.deleted) + po.getName() + " " + getResources().getString(viewModel.fromCart),
                                            Snackbar.LENGTH_LONG
                                    )
                                    .setAction(viewModel.undo, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            poList.add(position, po);
                                            viewModel.updateAllCart(poList);
                                            poAdapter.notifyDataSetChanged();
                                            Integer price = viewModel.getProductPrice() + po.getOrderNumber()*po.getPrice();
                                            viewModel.updateProductPrice(price);
                                            binding.totalPrice.setText(Utility.formatIntNumber(viewModel.getProductPrice() + viewModel.getShippingPrice()));
                                        }
                                    })
                                    .show();
                        }
                    }
                });

                builder.setNegativeButton(getResources().getString(viewModel.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        poList.add(position, po);
                        viewModel.updateAllCart(poList);
                        poAdapter.notifyDataSetChanged();
                        Integer price = viewModel.getProductPrice() + po.getOrderNumber()*po.getPrice();
                        viewModel.updateProductPrice(price);
                        binding.totalPrice.setText(Utility.formatIntNumber(viewModel.getProductPrice() + viewModel.getShippingPrice()));
                        Snackbar.make(binding.getRoot(), getResources().getString(viewModel.undoDelete), Snackbar.LENGTH_LONG)
                                .setAnchorView(binding.getRoot())
                                .show();
                    }
                });
                builder.show();
            }
        });
        touchHelper.attachToRecyclerView(binding.cartList);
        binding.shippingPick.setText(viewModel.getShipping());

        ApiBuilder.apiService.getAllShipping()
                .enqueue(new Callback<List<ShippingDelivery>>() {
                    @Override
                    public void onResponse(Call<List<ShippingDelivery>> call, Response<List<ShippingDelivery>> response) {
                        List<ShippingDelivery> lsp = response.body();
                        List<String> shippingListName = new ArrayList<>();
                        List<String> shippingListId = new ArrayList<>();
                        for (ShippingDelivery sd: lsp) {
                            shippingListName.add(sd.getName());
                            shippingListId.add(sd.getId());
                        }
                        shippingAdapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_item, shippingListName);
                        shippingPopup.setAdapter(shippingAdapter);
                        shippingPopup.setAnchorView(binding.shippingBotDiv);
                        shippingPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                binding.shippingPick.setText(shippingListName.get(i));
                                viewModel.updateShipping(shippingListName.get(i));
                                viewModel.updateShippingId(shippingListId.get(i));
                                Integer shippingFee = Integer.valueOf(lsp.get(i).getPrice());
                                viewModel.updateShippingPrice(shippingFee);
                                shippingPopup.dismiss();
                                binding.totalPrice.setText(Utility.formatIntNumber(viewModel.getProductPrice() + viewModel.getShippingPrice()));
                            }
                        });

                        binding.shippingOption.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                shippingPopup.show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<ShippingDelivery>> call, Throwable t) {

                    }
                });
        binding.orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewModel.getAccount() != null){
                    List<ProductOrder> poList = viewModel.getCart();
                    String accountId = viewModel.getAccount().getId();
                    String shippingId = viewModel.getShippingId();
                    Order order = new Order(accountId, poList, shippingId);
                    ApiBuilder.apiService.saveOrder(order).enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Call<Order> call, Response<Order> response) {
                            Log.d("order api", "onResponse: " + response.body());
                            Snackbar.make(binding.getRoot(), "Đặt hàng thành công", Snackbar.LENGTH_SHORT).show();
                            getParentFragmentManager().popBackStack();;
                        }

                        @Override
                        public void onFailure(Call<Order> call, Throwable t) {
                            Log.d("order api", "onResponse: " + t.getMessage());
                        }
                    });

                }else{
                    Snackbar.make(binding.getRoot(), "Vui lòng đăng ký tài khoản để đặt hàng", Snackbar.LENGTH_SHORT).show();
                    FragmentManager fm = requireActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    );
                    transaction.replace(R.id.wrapper, new SignupFragment(), null).addToBackStack(null).commit();
                }

            }
        });
    }
}