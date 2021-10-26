package com.example.reworksample.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reworksample.R;
import com.example.reworksample.databinding.FlexibleItemEntityBinding;
import com.example.reworksample.model.repository.local.entity.FlexibleDeskEntity;
import com.example.reworksample.ui.fragment.FragmentFavourite;

import java.util.List;


// Set dữ liệu cho item trong list yêu thích
public class FlexibleDeskEntityAdapter extends RecyclerView.Adapter<FlexibleDeskEntityAdapter.MyViewHolder>{

    private Context context;
    private MutableLiveData<List<FlexibleDeskEntity>> flexibleDeskResponeLiveData;
    private OnClickItem onClickItem;

    public FlexibleDeskEntityAdapter(Context context, MutableLiveData<List<FlexibleDeskEntity>> flexibleDeskResponeLiveData, OnClickItem onClickItem) {
        this.context = context;
        this.flexibleDeskResponeLiveData = flexibleDeskResponeLiveData;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FlexibleItemEntityBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.flexible_item_entity, parent, false);
        return new MyViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(flexibleDeskResponeLiveData.getValue() == null) return;
        holder.binding.setFlexibleDesk(flexibleDeskResponeLiveData.getValue().get(position));
        holder.binding.buttonDelete.setOnClickListener(v -> {
            onClickItem.onClickBtnRemove(position);
        });
        holder.binding.buttonUpdate.setOnClickListener(v -> {
            onClickItem.onClickUpdate(position);
        });



    }

    @Override
    public int getItemCount() {
        if(flexibleDeskResponeLiveData.getValue() == null)
            return 0;
        else {
            return flexibleDeskResponeLiveData.getValue().size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        FlexibleItemEntityBinding binding;

        public MyViewHolder(@NonNull FlexibleItemEntityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

    public interface OnClickItem{
        void onClickUpdate(int position);
        void onClickBtnRemove(int position);
    }
}
