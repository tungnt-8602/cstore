package com.example.cstore.presentation.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cstore.R;
import com.example.cstore.databinding.FragmentLoginBinding;
import com.example.cstore.model.Account;
import com.example.cstore.model.api.ApiBuilder;
import com.example.cstore.presentation.store.PagerFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    /* **********************************************************************
     * Lifecycle
     ********************************************************************** */
    private FragmentLoginBinding binding;
    LoginViewModel viewModel = new LoginViewModel();
    /* **********************************************************************
     * Constructor
     ********************************************************************** */
    public LoginFragment() {
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
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.idBtnLogin.setOnClickListener(view1 -> {
            String userName;
            String password;
            if(Objects.requireNonNull(binding.idEdtUserName.getText()).toString().equals("")) {
                binding.userName.setError(getResources().getString(R.string.empty_username_field));
                return;
            } else {
                userName = binding.idEdtUserName.getText().toString();
            }

            if(Objects.requireNonNull(binding.idEdtPassword.getText()).toString().equals("")) {
                binding.idEdtPassword.setError(getResources().getString(R.string.empty_password_field));
                return;
            } else {
                password = binding.idEdtPassword.getText().toString();
            }
            Account registerAccount = new Account(userName, password);
            ApiBuilder.apiService.login(registerAccount).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(@NonNull Call<Account> call, @NonNull Response<Account> response) {
                    Account registeredAccount = response.body();
                    if(registeredAccount != null) {
                        viewModel.saveAccount(registeredAccount);
                    }
                    Snackbar.make(binding.getRoot(), getResources().getString(R.string.login_success), Snackbar.LENGTH_SHORT).show();
                    FragmentManager fm = requireActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    );
                    transaction.replace(R.id.wrapper, new PagerFragment(), null).addToBackStack(null).commit();
                }

                @Override
                public void onFailure(@NonNull Call<Account> call, @NonNull Throwable t) {
                    Log.d("call account api", "onFailure: " + t.getMessage());
                }
            });

        });

        binding.signupSwapBtn.setOnClickListener(it -> {
            Snackbar.make(binding.getRoot(), getResources().getString(R.string.signup_title), Snackbar.LENGTH_SHORT).show();
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

    /* **********************************************************************
     * Function
     ********************************************************************** */
}