package com.example.reworksample.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reworksample.R;
import com.example.reworksample.model.model.Photo;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;



// Set dữ liệu cho slider
public class SliderAdapter extends SliderViewAdapter<SliderAdapter.viewHolder> {
    private Context context;
    private List<Photo> sliderItemList=new ArrayList<>();

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(List<Photo> sliderItemList){
        this.sliderItemList=sliderItemList;
        notifyDataSetChanged();
    }
    public void deleteItem(int position)
    {
        this.sliderItemList.remove(position);
        notifyDataSetChanged();
    }
    public void addItem(Photo items)
    {
        this.sliderItemList.add(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Photo sliderItem=sliderItemList.get(position);
        Glide.with(holder.itemView).load(sliderItem.getThumbnail())
                .fitCenter().into(holder.imgBackground);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getCount() {
        return sliderItemList.size();
    }

    public class viewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imgBackground, imgGifContainer;
        TextView description;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgBackground=itemView.findViewById(R.id.iv_auto_image_slider);
            imgGifContainer=itemView.findViewById(R.id.iv_gif_container);
            this.itemView=itemView;
        }
    }
}
