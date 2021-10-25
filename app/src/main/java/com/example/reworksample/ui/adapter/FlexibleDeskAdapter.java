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
import com.example.reworksample.model.model.FlexibleDeskRespone;


// Set dữ liệu cho item trong list hiển thị ở trang Home
public class FlexibleDeskAdapter extends RecyclerView.Adapter<FlexibleDeskAdapter.viewHolder> {
   private Context context;
   private MutableLiveData<FlexibleDeskRespone> flexibleDeskResponeMutableLiveData;
   private OnClickItem onClickItem;

    public FlexibleDeskAdapter(Context context, MutableLiveData<FlexibleDeskRespone> flexibleDeskResponeMutableLiveData, OnClickItem onClickItem) {
        this.context = context;
        this.flexibleDeskResponeMutableLiveData = flexibleDeskResponeMutableLiveData;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       FlexibleItemBinding binding= DataBindingUtil.inflate(LayoutInflater
               .from(parent.getContext()), R.layout.flexible_item, parent, false);
       return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (flexibleDeskResponeMutableLiveData.getValue()==null)
        {
            return;
        }else{
            holder.binding.setResult(flexibleDeskResponeMutableLiveData.getValue().getData().getResults().get(position));
            holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.onClickItem(position);
                }
            });
            holder.binding.buttonFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.onClickBtnFavourite(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(flexibleDeskResponeMutableLiveData.getValue()==null)
        {
            return 0;
        }else{
            return  flexibleDeskResponeMutableLiveData.getValue().getData().getResults().size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        FlexibleItemBinding binding;
        public viewHolder(@NonNull FlexibleItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
    public interface OnClickItem{
        void onClickItem(int position);
        void onClickBtnFavourite(int position);
    }
}
