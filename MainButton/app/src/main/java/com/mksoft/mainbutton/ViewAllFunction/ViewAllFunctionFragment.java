package com.mksoft.mainbutton.ViewAllFunction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mksoft.mainbutton.DataType.FunctionArray;
import com.mksoft.mainbutton.MainActivity;
import com.mksoft.mainbutton.R;
import com.mksoft.mainbutton.Repository.WebService;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewAllFunctionFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FunctionArrayAdapter functionArrayAdapter;
    Button sortButton;
    Button addButton;
    MainActivity mainActivity;//메인엑티비티에서 요청을 하기위하여 필요.

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
        mainActivity.getCfbServiceComponent().makeCFBService().getAllFunction(getContext(), getActivity(), recyclerView);
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




}
