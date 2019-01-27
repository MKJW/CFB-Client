package com.mksoft.viewallfunction;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
    Retrofit retrofit;
    WebService functionWebService;
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
        initRepos();
        getAllFunction();//통신
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


    private void initRepos(){
        retrofit = new Retrofit.Builder().baseUrl("http://114.202.9.170:8080").addConverterFactory(GsonConverterFactory.create()).build();
        functionWebService = retrofit.create(WebService.class);
    }
    //데거를 이용하여 한번만 선언하여 사용하자

    public void getAllFunction() {
        Call<ArrayList<FunctionArray>> call = functionWebService.getAllFunction();

        call.enqueue(new Callback<ArrayList<FunctionArray>>(){

            @Override
            public void onResponse(Call<ArrayList<FunctionArray>> call, Response<ArrayList<FunctionArray>> response) {
                if(response.isSuccessful() ==true && response.body() != null) {
                    functionArrayAdapter = new FunctionArrayAdapter(getContext(), response.body());
                    Log.d("서버응답", response.body().get(0).getHashtags()[0].getTagName());
                    recyclerView.setAdapter(functionArrayAdapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<FunctionArray>> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d("testResult", t.getLocalizedMessage());
            }
        });
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
