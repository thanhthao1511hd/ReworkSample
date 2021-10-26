package com.example.reworksample.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.reworksample.R;
import com.example.reworksample.databinding.FlexibleItemEntityBinding;
import com.example.reworksample.databinding.FragmentFavouriteBinding;
import com.example.reworksample.ui.adapter.FlexibleDeskAdapter;
import com.example.reworksample.ui.adapter.FlexibleDeskEntityAdapter;
import com.example.reworksample.ui.contact.SetOnClickItem;
import com.example.reworksample.viewmodel.FavouriteViewModel;

import java.util.Objects;

public class FragmentFavourite extends Fragment implements  FlexibleDeskEntityAdapter.OnClickItem {

    FragmentFavouriteBinding binding;
    private FavouriteViewModel viewModel;
    public FragmentFavourite() {
        // Required empty public constructor
    }

    public static FragmentFavourite newInstance() {

        Bundle args = new Bundle();
        FragmentFavourite fragment = new FragmentFavourite();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel=new ViewModelProvider(getActivity()).get(FavouriteViewModel.class);
        viewModel.initData(getActivity());
        viewModel.getAllFlexibleDesk();
        setView();
    }

    private void setView() {
        viewModel.getFlexibleDeskLiveData().observe(getViewLifecycleOwner(), flexibleDeskEntities -> {
            if (binding.rvFavouriteList!=null)
            {
                binding.rvFavouriteList.getAdapter().notifyDataSetChanged();
            }
        });
        binding.rvFavouriteList.setLayoutManager(new LinearLayoutManager(getActivity()));
        FlexibleDeskEntityAdapter adpapter=new FlexibleDeskEntityAdapter(getActivity(), viewModel.getFlexibleDeskLiveData(), this);
        binding.rvFavouriteList.setAdapter(adpapter);
    }

    @Override
    public void onClickUpdate(int position) {

    }

    @Override
    public void onClickBtnRemove(int position) {
        if (viewModel.getFlexibleDeskLiveData().getValue()==null) return;
        SetOnClickItem onClickItem=new SetOnClickItem() {
            @Override
            public void onSuccess(String name) {
                Toast.makeText(getActivity(), "Xoá "+name+" thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String message) {
                Toast.makeText(getActivity(), "Xoá thất bại: "+ message, Toast.LENGTH_SHORT).show();
            }
        };
        viewModel.deleteFlexibleDesk(viewModel.getFlexibleDeskLiveData().getValue().get(position), onClickItem);
    }
}