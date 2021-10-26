package com.example.reworksample.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.reworksample.R;
import com.example.reworksample.databinding.FragmentHomeBinding;
import com.example.reworksample.model.repository.local.entity.FlexibleDeskEntity;
import com.example.reworksample.ui.adapter.FlexibleDeskAdapter;
import com.example.reworksample.ui.contact.SetOnClickItem;
import com.example.reworksample.viewmodel.HomeViewModel;

import java.time.chrono.JapaneseDate;

import io.reactivex.disposables.Disposable;

public class FragmentHome extends Fragment implements FlexibleDeskAdapter.OnClickItem {

    FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private NavController navController;
    private static FragmentHome fragmentHome;

    public FragmentHome() {
        // Required empty public constructor
    }

    public static FragmentHome newInstance() {

        if(fragmentHome == null){
            fragmentHome = new FragmentHome();
        }
        return fragmentHome;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel=new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        homeViewModel.initData(getActivity());
        setRecycleview();
        Disposable disposable=homeViewModel.getFlexibleDesk();
        navController= NavHostFragment.findNavController(this);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void setRecycleview(){
        homeViewModel.getFlexibleDeskResponeMutableLiveData().observe(getViewLifecycleOwner(), flexibleDeskRespone -> {
            if (binding.rvSpaceList.getAdapter()!=null)
            {
                binding.rvSpaceList.getAdapter().notifyDataSetChanged();
            }
        });
        binding.rvSpaceList.setLayoutManager(new LinearLayoutManager(getActivity()));
        FlexibleDeskAdapter adapter=new FlexibleDeskAdapter(getActivity(), homeViewModel.getFlexibleDeskResponeMutableLiveData(), this);
        binding.rvSpaceList.setAdapter(adapter);
    }

    @Override
    public void onClickItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("position", position);
        navController.navigate(R.id.action_fragmentHome2_to_fragmentDetail, bundle);
    }

    @Override
    public void onClickBtnFavourite(int position) {
        if (homeViewModel.getFlexibleDeskResponeMutableLiveData().getValue()==null) return;
        FlexibleDeskEntity flexibleDeskEntity=new FlexibleDeskEntity();
        flexibleDeskEntity.location=homeViewModel.getFlexibleDeskResponeMutableLiveData().getValue().getData().getResults().get(position).getSpaceMeta().getShortenAddress();
        flexibleDeskEntity.name=homeViewModel.getFlexibleDeskResponeMutableLiveData().getValue().getData().getResults().get(position).getSpaceMeta().getName();
        flexibleDeskEntity.thumbnail=homeViewModel.getFlexibleDeskResponeMutableLiveData().getValue().getData().getResults().get(position).getPhotos().get(0).getThumbnail();
        flexibleDeskEntity.uid=homeViewModel.getFlexibleDeskResponeMutableLiveData().getValue().getData().getResults().get(position).getId();
        SetOnClickItem onClickItem=new SetOnClickItem() {
            @Override
            public void onSuccess(String name) {
                Toast.makeText(getActivity(), "Thêm "+ name + " vào Yêu thích thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String message) {
                Toast.makeText(getActivity(), "Thêm thất bại "+ message, Toast.LENGTH_SHORT).show();
            }
        };
        homeViewModel.insertFlexibleDesk(flexibleDeskEntity, onClickItem);

    }
}