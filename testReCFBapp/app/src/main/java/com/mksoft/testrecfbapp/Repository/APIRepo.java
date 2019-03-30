package com.mksoft.testrecfbapp.Repository;


import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.mksoft.testrecfbapp.Repository.Data.FunctionArray;
import com.mksoft.testrecfbapp.Repository.webservice.APIService;
import com.mksoft.testrecfbapp.component.activity.MainActivity;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class APIRepo {
    private final APIService webservice;

    @Inject
    public APIRepo(APIService webservice) {
        Log.d("testResultRepo", "make it!!!");
        this.webservice = webservice;
    }

    public LiveData<ArrayList<FunctionArray>> getAllFunctionLiveData(String authorization){
        final MutableLiveData<ArrayList<FunctionArray>> data = new MutableLiveData<>();
        webservice.getAllFunction(authorization).enqueue(new Callback<ArrayList<FunctionArray>>() {
            @Override
            public void onResponse(Call<ArrayList<FunctionArray>> call, Response<ArrayList<FunctionArray>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FunctionArray>> call, Throwable t) {
                data.setValue(null);
                Log.d("error", "LiveData response null");
            }
        });
        return data;

    }
    public void postFunction(FunctionArray functionArray, final MainActivity mainActivity, final EditText hashTagOfEquationEditText, final EditText nameOfEquationEditText){
        Call<String> call = webservice.postFunction(mainActivity.getAccess_token(), functionArray);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()==true && response.body() != null){
                    if(response.body() == "false"){

                        Toast.makeText(mainActivity.getApplicationContext(), "이미 등록된 이름입니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mainActivity.getApplicationContext(), "저장성공", Toast.LENGTH_SHORT).show();
                        hashTagOfEquationEditText.setText("");
                        nameOfEquationEditText.setText("");
                        mainActivity.onFragmentChange(1, null);//메인버튼페이지로 돌아감

                    }
                }

            }//중복 확인 결과 받아서 처리해주자...

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("server test", t.toString());
                Toast.makeText(mainActivity.getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }

        });
    }

}
