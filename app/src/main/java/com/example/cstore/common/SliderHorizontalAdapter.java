package com.example.cstore.common;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cstore.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderHorizontalAdapter extends SliderViewAdapter<SliderHorizontalAdapter.SliderAdapterViewHolder> {
    /* **********************************************************************
     * Variable
     ********************************************************************** */
    private final List<SliderData> mSliderItems;

    /* **********************************************************************
     * Constructor
     ********************************************************************** */
    public SliderHorizontalAdapter(ArrayList<SliderData> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
    }

    /* **********************************************************************
     * Lifecycle
     ********************************************************************** */
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        @SuppressLint("InflateParams")
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_slider_horizontal, null);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        final SliderData sliderItem = mSliderItems.get(position);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImgUrl())
                .optionalCenterInside()
                .into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    /* **********************************************************************
     * Class
     ********************************************************************** */
    static class SliderAdapterViewHolder extends ViewHolder {
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.my_image);
            this.itemView = itemView;
        }
    }
}
