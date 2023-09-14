package com.example.cstore.store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cstore.store.favorite.FavoriteFragment;
import com.example.cstore.store.home.HomeFragment;
import com.example.cstore.store.product.ProductFragment;
import com.example.cstore.store.setting.SettingFragment;

import java.util.List;

public class PagerAdapter extends FragmentStateAdapter {

    private final List<PagerViewModel.Tab> tabs;

    public PagerAdapter(List<PagerViewModel.Tab> tabs, AppCompatActivity activity) {
        super(activity);
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        PagerViewModel.Tab item = tabs.get(position);
        Fragment fragment = item.fragment;
        if (fragment == null) {
            switch (item.type) {
                case HOME:
                    fragment = new HomeFragment();
                    break;
                case PRODUCT:
                    fragment = new ProductFragment();
                    break;
                case FAVORITE:
                    fragment = new FavoriteFragment();
                    break;
                case SETTING:
                    fragment = new SettingFragment();
                    break;
            }
            tabs.get(position).fragment = fragment;
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return tabs.size();
    }
}
