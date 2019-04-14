package com.mksoft.testrecfbapp.component.activity.fragment.MainPage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.mksoft.testrecfbapp.R;
import com.mksoft.testrecfbapp.component.activity.MainActivity;
import com.mksoft.testrecfbapp.component.activity.fragment.ViewAllFunction.ViewAllFunctionFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainButtonFragment extends Fragment {

    Button bookMarkList;
    Button searchButton;
    Button optionButton;

    FrameLayout contentFrameLayout;
    MainActivity mainActivity;
    ViewAllFunctionFragment viewAllFunctionFragment;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
        ((MainActivity) context).setOnKeyBackPressedListener(null);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.main_button, container, false);
        initFragment();
        initUI(rootView);
        hideKeyboard();
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
        getChildFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, viewAllFunctionFragment).commit();
        //기본 화면 초기화...
    }

    private void hideKeyboard(){
        mainActivity.getHideKeyboard().hideKeyboard();
    }

}
