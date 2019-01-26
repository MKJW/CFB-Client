package com.mksoft.mainbutton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.mksoft.mainbutton.ViewAllFunction.ViewAllFunctionFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainButtonFragment extends Fragment {

    Button bookMarkList;
    Button searchButton;
    Button optionButton;

    FrameLayout contentFrameLayout;

    ViewAllFunctionFragment viewAllFunctionFragment;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.main_button, container, false);
        initUI(rootView);

        return rootView;
    }
    private void initFragment(){
        viewAllFunctionFragment = new ViewAllFunctionFragment();
    }
    private void initUI(ViewGroup rootView){
        bookMarkList = rootView.findViewById(R.id.bookMarkList);
        searchButton = rootView.findViewById(R.id.searchButton);
        optionButton = rootView.findViewById(R.id.optionButton);

        contentFrameLayout = rootView.findViewById(R.id.contentFrameLayout);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, viewAllFunctionFragment).commit();
        //기본 화면 초기화...
    }


}
