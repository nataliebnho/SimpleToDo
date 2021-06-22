package com.example.simpletodo;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adapter is responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public interface OnLongClickListener {
        // position: the class implementing this method (MainActivity) needs to know the position
        // of where the long press was done so that it can notify the adapter that this is where an
        // item should be deleted
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    // constructor for adapter
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    // Responsible for creating each view
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull
                                                     ViewGroup parent, int viewType) {
        //1) Create a new view and 2) Wrap it inside a ViewHolder
        //Use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(
                android.R.layout.simple_list_item_1, parent, false);
        //Wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }

    // Responsible for taking data at a particular position and putting it into a ViewHolder
    // binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull
                                             ItemsAdapter.ViewHolder holder, int position) {
        //Grab the item at a position
        String item = items.get(position);
        //Bind the item into a specified View Holder
        holder.bind(item);
    }

    // Number of items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //ViewHolder
    // Container to provide easy access to views that represent access to the list
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //Update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //notifying the listener which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }

}