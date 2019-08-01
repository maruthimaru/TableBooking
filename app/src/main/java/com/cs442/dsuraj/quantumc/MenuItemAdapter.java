package com.cs442.dsuraj.quantumc;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MyViewHolder> {
    Context context;
    ArrayList<MenuItemModel> menuItemModelArrayList;
    public MenuItemAdapter(Context context, ArrayList<MenuItemModel> menuItemModelArrayList) {
        this.context = context;
        this.menuItemModelArrayList=menuItemModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.menu_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        MenuItemModel menuItemModel=menuItemModelArrayList.get(i);
        myViewHolder.imageViewItem.setImageResource(menuItemModel.itemImage);
        myViewHolder.textviewItem.setText(menuItemModel.itemName);
    }

    @Override
    public int getItemCount() {
        return menuItemModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewItem;
        TextView textviewItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewItem=itemView.findViewById(R.id.imageViewItem);
            textviewItem=itemView.findViewById(R.id.textviewItem);
        }
    }
}
