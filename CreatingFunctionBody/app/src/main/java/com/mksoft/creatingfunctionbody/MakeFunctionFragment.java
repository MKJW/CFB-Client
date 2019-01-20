package com.mksoft.creatingfunctionbody;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MakeFunctionFragment extends Fragment {

    Button submitButton;
    EditText editFunctionText;
    Retrofit retrofit;
    WebService functionWebService;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.make_function, container, false);
        initUI(rootView);
        clickSubmitButton();
        initRepos();
        return rootView;
    }

    private void initUI(ViewGroup rootView){
        submitButton = rootView.findViewById(R.id.submitButton);
        editFunctionText = rootView.findViewById(R.id.editFunctionText);
    }


    private void initRepos(){
        retrofit = new Retrofit.Builder().baseUrl("http://114.202.9.170:8080").addConverterFactory(GsonConverterFactory.create()).build();
        functionWebService = retrofit.create(WebService.class);
    }

    private void clickSubmitButton(){
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeFunctionArray makeFunctionArray = new MakeFunctionArray(editFunctionText.getText().toString());

                makeFunctionArray.parsingInputString();
                makeFunctionArray.insertFunctionArray();
                if(makeFunctionArray.isSuccess == true){
                    postFunction(makeFunctionArray.getFunctionArray());
                }//인텐트로 넘겨주는 걸로 수정하자.
                //인텐트로 아직 해쉬테그 오브젝트와 함수이름 스트링을 비운 상태의 클레스를 넘겨주자
                //그리고 name and hash tag 플래그먼트에서 클래스를 완성하여 보내주자

            }
        });
    }

    private void postFunction(FunctionArray functionArray){
        Call<String> call = functionWebService.postFunction(functionArray);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getActivity().getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(getActivity().getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
