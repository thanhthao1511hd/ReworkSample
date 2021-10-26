package com.example.reworksample.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reworksample.R;
import com.example.reworksample.databinding.FlexibleItemBinding;
import com.example.reworksample.databinding.FlexibleItemEntityBinding;
import com.example.reworksample.model.model.FlexibleDeskRespone;


// Set dữ liệu cho item trong list hiển thị ở trang Home
public class FlexibleDeskAdapter extends RecyclerView.Adapter<FlexibleDeskAdapter.MyViewHolder> {
    private Context context;
    private MutableLiveData<FlexibleDeskRespone> flexibleDeskResponeLiveData;
    private OnClickItem onClickItem;

    public FlexibleDeskAdapter(Context context, MutableLiveData<FlexibleDeskRespone> flexibleDeskResponeLiveData, OnClickItem onClickItem) {
        this.context = context;
        this.flexibleDeskResponeLiveData = flexibleDeskResponeLiveData;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FlexibleItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.flexible_item, parent, false);
        return new MyViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(flexibleDeskResponeLiveData.getValue() == null) return;
        holder.binding.setResult(flexibleDeskResponeLiveData.getValue().getData().getResults().get(position));
        holder.binding.getRoot().setOnClickListener(v -> {
            onClickItem.onClickItem(position);
        });
        holder.binding.llFavourite.setOnClickListener(v -> {
            onClickItem.onClickBtnFavourite(position);
        });



    }

    @Override
    public int getItemCount() {
        if(flexibleDeskResponeLiveData.getValue() == null)
            return 0;
        else {
            return flexibleDeskResponeLiveData.getValue().getData().getResults().size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        FlexibleItemBinding binding;

        public MyViewHolder(@NonNull FlexibleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

    public interface OnClickItem{
        void onClickItem(int position);
        void onClickBtnFavourite(int position);
    }
}
