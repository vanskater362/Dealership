package com.cardealership;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

//with a little modification this class can be modified to accept a JSON string
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private List<Cars> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    CarAdapter(Context context, List<Cars> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.car_list_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cars car = mData.get(position);
        holder.make.setText("Make: " + car.getMake());
        holder.year.setText("Year: " + car.getYear());
        holder.price.setText("Price: $" + car.getPrice());
        holder.color.setText("Color: " + car.getColor());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView make;
        TextView year;
        TextView price;
        TextView color;

        ViewHolder(View itemView) {
            super(itemView);
            make = itemView.findViewById(R.id.make);
            year = itemView.findViewById(R.id.year);
            price = itemView.findViewById(R.id.price);
            color = itemView.findViewById(R.id.color);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Cars getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

