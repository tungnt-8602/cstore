package com.example.cstore.presentation.store.setting;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cstore.R;
import com.example.cstore.databinding.FragmentSettingBinding;
import com.example.cstore.model.Account;
import com.example.cstore.presentation.login.LoginFragment;
import com.example.cstore.presentation.login.SignupFragment;
import com.example.cstore.presentation.store.PagerFragment;
import com.example.cstore.presentation.store.cart.CartFragment;
import com.google.android.material.snackbar.Snackbar;

public class SettingFragment extends Fragment {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    private FragmentSettingBinding binding;
    SettingViewModel viewModel = new SettingViewModel();

    /* **********************************************************************
     * Constructor
     ********************************************************************** */
    public SettingFragment() {
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
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Account account = viewModel.getAccount();
        if(account == null){
            binding.loginLayout.setVisibility(View.GONE);
            binding.notLoginLayout.setVisibility(View.VISIBLE);
            binding.loginInfo.setVisibility(View.GONE);
            binding.loginBtn.setOnClickListener(view1 -> {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                );
                transaction.replace(R.id.wrapper, new LoginFragment(), null).addToBackStack(null).commit();
            });
            binding.signupBtn.setOnClickListener(view12 -> {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                );
                transaction.replace(R.id.wrapper, new SignupFragment(), null).addToBackStack(null).commit();
            });
        }
        else {
            binding.loginLayout.setVisibility(View.VISIBLE);
            binding.notLoginLayout.setVisibility(View.GONE);
            binding.loginInfo.setVisibility(View.VISIBLE);
            TextView username = binding.username;
            TextView email = binding.email;
            username.setText(account.getUsername());
            email.setText(account.getEmail());
            binding.logoutBtn.setOnClickListener(v -> {
                viewModel.saveAccount(null);
                viewModel.updateAllCart(null);
                viewModel.updateProductPrice(0);
                viewModel.updateProductPrice(0);
                viewModel.updateShippingId("");
                viewModel.updateShipping("");
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                );
                transaction.replace(R.id.wrapper, new PagerFragment(), null).addToBackStack(null).commit();
                Snackbar.make(binding.getRoot(), "Đăng xuất thành công", Snackbar.LENGTH_SHORT).show();
            });

            binding.inCart.setOnClickListener(view13 -> {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                );
                transaction.replace(R.id.wrapper, new CartFragment(), null).addToBackStack(null).commit();
            });
        }

    }
}