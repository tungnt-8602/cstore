package com.example.cstore.presentation.store.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.cstore.R;
import com.example.cstore.model.ProductOrder;
import com.example.cstore.model.Products;
import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.ViewHolder> {

    private Context context;
    private List<ProductOrder> poArrayList;
    private OnClickListener onClickListener;

    public ItemCartAdapter(Context context, List<ProductOrder> poArrayList) {
        this.context = context;
        this.poArrayList = poArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View productView = inflater.inflate(R.layout.item_cart, parent, false);
        return new ItemCartAdapter.ViewHolder(productView);
    }

    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductOrder p = poArrayList.get(position);
        Glide.with(context).load(p.getImage()).into(holder.poImage);
        holder.poName.setText(p.getName());
        holder.poPrice.setText(p.getPrice().toString());
        holder.poSize.setText(holder.poSize.getText() + p.getSize());
        holder.poColor.setText(holder.poColor.getText() + p.getColor());
        holder.poNumber.setText(p.getOrderNumber().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.poMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.poBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public interface OnClickListener {
        void onClick(int position, Products p);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return poArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView poImage;
        private final TextView poName;
        private final TextView poPrice;
        private final TextView poSize;
        private final TextView poColor;
        private EditText poNumber;
        private final ImageButton poMinus;
        private final ImageButton poBonus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poImage = itemView.findViewById(R.id.po_image);
            poName = itemView.findViewById(R.id.po_name);
            poPrice = itemView.findViewById(R.id.po_price);
            poSize = itemView.findViewById(R.id.po_size);
            poColor = itemView.findViewById(R.id.po_color);
            poNumber = itemView.findViewById(R.id.po_count);
            poMinus = itemView.findViewById(R.id.minus_po);
            poBonus = itemView.findViewById(R.id.bonus_po);
        }
    }
}