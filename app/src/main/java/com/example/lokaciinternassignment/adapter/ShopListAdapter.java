package com.example.lokaciinternassignment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lokaciinternassignment.R;
import com.example.lokaciinternassignment.model.ShopListItem;

import java.util.ArrayList;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {

    private ArrayList<ShopListItem> shopListItems;

    public ShopListAdapter(ArrayList<ShopListItem> shopListItems) {
        this.shopListItems = shopListItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.shop_list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ShopListItem currentItem = shopListItems.get(position);
        viewHolder.imageView.setImageResource(currentItem.getImageid());
        viewHolder.nameView.setText(currentItem.getShopName());
        viewHolder.ratingView.setText(String.valueOf(currentItem.getRating()));
        viewHolder.distanceView.setText(String.valueOf(currentItem.getDistance()));
        viewHolder.addressView.setText(currentItem.getAddress());
    }

    @Override
    public int getItemCount() {
        return shopListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nameView;
        private TextView ratingView;
        private TextView distanceView;
        private TextView addressView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.shop_image);
            nameView = itemView.findViewById(R.id.name_view);
            ratingView = itemView.findViewById(R.id.rating_view);
            distanceView = itemView.findViewById(R.id.distance_view);
            addressView = itemView.findViewById(R.id.address_view);
        }
    }
}
