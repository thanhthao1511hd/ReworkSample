package com.example.reworksample.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reworksample.R;
import com.example.reworksample.databinding.FragmentDetailBinding;
import com.example.reworksample.ui.adapter.SliderAdapter;
import com.example.reworksample.viewmodel.HomeViewModel;
import com.google.android.material.slider.Slider;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class FragmentDetail extends Fragment {
    private static final String ARG_PARAM1 = "position";

    private int position;
    private FragmentDetailBinding binding;
    private HomeViewModel viewModel;

    public FragmentDetail() {
    }

    public static FragmentDetail newInstance(int param1) {
        FragmentDetail fragment = new FragmentDetail();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        viewModel.initData(getActivity());

        setClick();

        SliderAdapter adapter = new SliderAdapter(getActivity());
        if(viewModel.getFlexibleDeskResponeLiveData().getValue() != null){

            binding.setResult(viewModel.getFlexibleDeskResponeLiveData().getValue().getData().getResults().get(position));

            adapter.renewItems(viewModel.getFlexibleDeskResponeLiveData().getValue().getData().getResults().get(position).getPhotos());
            binding.imageSlider.setSliderAdapter(adapter);
        }
    }

    private void setClick() {
        binding.btnBack.setOnClickListener(v -> {
            if(getActivity() != null)
                getActivity().onBackPressed();
        });
    }
}