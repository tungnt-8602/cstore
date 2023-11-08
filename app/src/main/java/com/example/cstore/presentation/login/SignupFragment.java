package com.example.cstore.presentation.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cstore.R;
import com.example.cstore.databinding.FragmentSignupBinding;
import com.example.cstore.model.Account;
import com.example.cstore.model.api.ApiBuilder;
import com.example.cstore.presentation.store.PagerFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {

    /* **********************************************************************
     * Variable
     ********************************************************************** */
    FragmentSignupBinding binding;
    SignupViewModel viewModel = new SignupViewModel();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.signupBtn.setOnClickListener(view1 -> {
            String userName;
            String password;
            String email;
            String phone;
            String address;
            if(Objects.requireNonNull(binding.userNameText.getText()).toString().equals("")) {
                binding.userName.setError(getResources().getString(R.string.empty_username_field));
                return;
            } else {
                userName = binding.userNameText.getText().toString();
            }

            if(Objects.requireNonNull(binding.passwordText.getText()).toString().equals("")) {
                binding.passwordText.setError(getResources().getString(R.string.empty_password_field));
                return;
            } else {
                password = binding.passwordText.getText().toString();
            }

            if(Objects.requireNonNull(binding.passwordAgainText.getText()).toString().equals("")) {
                binding.passwordAgainText.setError(getResources().getString(R.string.empty_password_again_field));
                return;
            } else if (!binding.passwordAgainText.getText().toString().equals(binding.passwordText.getText().toString())) {
                binding.passwordAgainText.setError(getResources().getString(R.string.wrong_password_again_field));
                return;
            } else {
                password = binding.passwordText.getText().toString();
            }

            if(Objects.requireNonNull(binding.emailText.getText()).toString().equals("")) {
                binding.emailText.setError(getResources().getString(R.string.empty_email_field));
                return;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailText.getText()).matches()) {
                binding.emailText.setError(getResources().getString(R.string.wrong_format_email_field));
                return;
            }
            else {
                email = binding.emailText.getText().toString();
            }

            if(Objects.requireNonNull(binding.phoneText.getText()).toString().equals("")) {
                binding.phoneText.setError(getResources().getString(R.string.empty_phone_field));
                return;
            } else if (!PhoneNumberUtils.isGlobalPhoneNumber(binding.phoneText.getText().toString())) {
                binding.phoneText.setError(getResources().getString(R.string.wrong_format_phone_field));
                return;
            }
            else {
                phone = binding.phoneText.getText().toString();
            }

            if(Objects.requireNonNull(binding.addressText.getText()).toString().equals("")) {
                binding.addressText.setError(getResources().getString(R.string.empty_address_field));
                return;
            } else {
                address = binding.addressText.getText().toString();
            }
            Account registerAccount = new Account( userName, password, address, phone, email);
            ApiBuilder.apiService.register(registerAccount).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(@NonNull Call<Account> call, @NonNull Response<Account> response) {
                    Account registeredAccount = response.body();
                    if(registeredAccount != null){
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

        binding.loginSwapBtn.setOnClickListener(it -> {
            Snackbar.make(binding.getRoot(), getResources().getString(R.string.login_swap), Snackbar.LENGTH_SHORT).show();
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                    R.anim.slide_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.slide_out  // popExit
            );
            transaction.replace(R.id.wrapper, new LoginFragment(), null).addToBackStack(null).commit();
        });
    }
}