package com.example.cstore.presentation.store.product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cstore.R;
import com.example.cstore.model.Products;

import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ViewHolder> {

    private Context context;
    private List<Products> productsArrayList;
    private OnClickListener onClickListener;

    public ProductItemAdapter(Context context, List<Products> productsArrayList) {
        this.context = context;
        this.productsArrayList = productsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(productView);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Products p = productsArrayList.get(position);
        Glide.with(context).load(p.getThumbnail()).into(holder.productThumb);
        holder.productName.setText(p.getProductName());
        holder.productPrice.setText(p.getPrice().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick( position, productsArrayList.get(position));
                }
            }
        });
    }

    public interface OnClickListener {
        void onClick(int position, Products p);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productThumb;
        private TextView productName;
        private TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productThumb = itemView.findViewById(R.id.productThumb);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.price);
        }
    }
}
