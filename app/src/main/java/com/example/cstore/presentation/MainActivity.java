package com.example.cstore.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.cstore.R;
import com.example.cstore.databinding.ActivityMainBinding;
import com.example.cstore.presentation.store.PagerFragment;

public class MainActivity extends AppCompatActivity {

    /* **********************************************************************
     * Variable
     ********************************************************************** */
    private ActivityMainBinding binding;

    /* **********************************************************************
     * Lifecycle
     ********************************************************************** */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.wrapper, new PagerFragment(), null).commit();
    }
}