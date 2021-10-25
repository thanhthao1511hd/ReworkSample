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
public class FlexibleDeskEntityAdapter extends RecyclerView.Adapter<FlexibleDeskEntityAdapter.viewHolder> {
   private Context context;
   private MutableLiveData<List<FlexibleDeskEntity>> flexibleDeskEntityMutableLiveData;
   private OnClickItem onClickItem;

    public FlexibleDeskEntityAdapter(Context context, MutableLiveData<List<FlexibleDeskEntity>> flexibleDeskEntityMutableLiveData, OnClickItem onClickItem) {
        this.context = context;
        this.flexibleDeskEntityMutableLiveData = flexibleDeskEntityMutableLiveData;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FlexibleItemEntityBinding binding=  DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.flexible_item_entity, parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
       if (flexibleDeskEntityMutableLiveData.getValue()==null)
       {
           return;
       }else{
           holder.binding.setFlexibleDesk(flexibleDeskEntityMutableLiveData.getValue().get(position));
           holder.binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onClickItem.onClickBtnRemove(position);
               }
           });
           holder.binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onClickItem.onClickUpdate(position);
               }
           });
       }
    }

    @Override
    public int getItemCount() {
        if (flexibleDeskEntityMutableLiveData.getValue()==null)
        {
            return 0;
        }else {
            return flexibleDeskEntityMutableLiveData.getValue().size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        FlexibleItemEntityBinding binding;
        public viewHolder(@NonNull FlexibleItemEntityBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
    public interface OnClickItem{
        void onClickUpdate(int position);
        void onClickBtnRemove(int position);
    }
}
