package com.mksoft.testrecfbapp.component.activity.fragment.ViewAllFunction;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.mksoft.testrecfbapp.R;
import com.mksoft.testrecfbapp.Repository.Data.FunctionArray;
import com.mksoft.testrecfbapp.component.activity.MainActivity;
import com.mksoft.testrecfbapp.viewmodel.FunctionListViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.AndroidSupportInjection;

public class ViewAllFunctionFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FunctionArrayAdapter functionArrayAdapter;
    Button sortButton;
    Button addButton;
    MainActivity mainActivity;//메인엑티비티에서 요청을 하기위하여 필요.
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    FunctionListViewModel functionListViewModel;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();

    }
    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel(){
        functionListViewModel = ViewModelProviders.of(this, viewModelFactory).get(FunctionListViewModel.class);
        functionListViewModel.init(mainActivity.getAccess_token());
        functionListViewModel.getFunctionList().observe(this, functionArrayList->refreshItem(functionArrayList));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.view_all_function, container, false);
        initUI(rootView);
        clickAddButton();
        return rootView;
    }

    private void initUI(ViewGroup rootView){

        sortButton = rootView.findViewById(R.id.sortButton);
        addButton = rootView.findViewById(R.id.addButton);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);

    }



    private void clickAddButton(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //요청
                mainActivity.onFragmentChange(2, null);//2번 페이지 add페이지로 넘어가기
            }
        });



    }//메인으로 애드페이지를 띄우라고 요청하는 함수


    public void refreshItem(ArrayList<FunctionArray> item){
        functionArrayAdapter.refreshItem(item);

    }

}
