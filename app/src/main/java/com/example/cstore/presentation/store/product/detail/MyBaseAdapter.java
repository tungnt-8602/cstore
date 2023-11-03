package com.example.cstore.presentation.store.product.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cstore.R;
import com.example.cstore.model.Product;

import java.util.List;

public class MyBaseAdapter extends RecyclerView.Adapter<MyBaseAdapter.ViewHolder> {

    private Context context;
    private List<String> stringList;
    OnClickListener onClickListener;

    public MyBaseAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView = inflater.inflate(R.layout.item_string, parent, false);
        return new ViewHolder(productView);
    }

    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s = stringList.get(position);
        holder.stringTv.setText(s);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public interface OnClickListener {
        void onClick(int position, Product p);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView stringTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stringTv = itemView.findViewById(R.id.string_tv);
        }
    }
}
