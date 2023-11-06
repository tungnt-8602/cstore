package com.example.cstore.presentation.store.product.product_category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cstore.R;
import com.example.cstore.common.Utility;
import com.example.cstore.model.Product;

import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ViewHolder> {

    private Context context;
    private List<Product> productsArrayList;
    private OnClickListener onClickListener;
    private OnClickListener onAddToCartClickListener;

    public ProductItemAdapter(Context context, List<Product> productsArrayList) {
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

    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = productsArrayList.get(position);
        Glide.with(context).load(p.getImages().get(0).getUrl()).into(holder.productThumb);
        holder.productName.setText(p.getName());
        holder.productPrice.setText(Utility.formatIntNumber(p.getPrice()));
        holder.fakePrice.setText(Utility.formatDoubleNumber(p.getPrice()*1.5));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, p);
                }
            }
        });
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onAddToCartClickListener != null) {
                    onAddToCartClickListener.onClick(position, p);
                }
            }
        });
    }

    public interface OnClickListener {
        void onClick(int position, Product p);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        notifyDataSetChanged();
    }

    public void setOnAddToCartClickListener(OnClickListener onAddToCartClick) {
        this.onAddToCartClickListener = onAddToCartClick;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productThumb;
        private TextView productName;
        private TextView productPrice;
        private TextView fakePrice;
        private ImageButton addToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productThumb = itemView.findViewById(R.id.productThumb);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.price);
            fakePrice = itemView.findViewById(R.id.priceFake);
            addToCart = itemView.findViewById(R.id.add_to_cart_btn);
        }
    }
}
