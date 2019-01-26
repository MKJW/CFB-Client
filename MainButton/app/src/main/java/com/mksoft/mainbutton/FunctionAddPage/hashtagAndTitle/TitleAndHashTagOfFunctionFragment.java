package com.mksoft.mainbutton.FunctionAddPage.hashtagAndTitle;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mksoft.mainbutton.DataType.FunctionArray;
import com.mksoft.mainbutton.DataType.MakeFunctionArray;
import com.mksoft.mainbutton.MainActivity;
import com.mksoft.mainbutton.R;
import com.mksoft.mainbutton.WebService;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TitleAndHashTagOfFunctionFragment extends Fragment {

    Retrofit retrofit;
    WebService functionWebService;
    EditText nameOfEquationEditText;
    EditText hashTagOfEquationEditText;
    Button submitButton;
    Button backButton;
    TextView userFunctionTextView;
    String expressionString;
    MainActivity mainActivity;
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.title_and_hash_tag_of_function, container, false);
        initUI(rootView);
        initRepos();
        clickSubmitButton();
        clickBackButton();
        //test();
        return rootView;
    }
    private void initUI(ViewGroup rootView){
        nameOfEquationEditText = rootView.findViewById(R.id.nameOfEquationEditText);
        backButton = rootView.findViewById(R.id.backButton);
        userFunctionTextView = rootView.findViewById(R.id.userFunctionTextView);
        hashTagOfEquationEditText = rootView.findViewById(R.id.hashTagOfEquationEditText);
        submitButton = rootView.findViewById(R.id.submitButton);
        getAllArguments();

    }

    private void getAllArguments(){
        expressionString = getArguments().getString("expression");
        userFunctionTextView.setText(expressionString);
    }
    private void initRepos(){
        retrofit = new Retrofit.Builder().baseUrl("http://114.202.9.170:8080").addConverterFactory(GsonConverterFactory.create()).build();
        functionWebService = retrofit.create(WebService.class);
    }

    private void clickBackButton(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onFragmentChange(2, null);
            }
        });
    }
    private void clickSubmitButton(){
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MakeFunctionArray makeFunctionArray = new MakeFunctionArray(expressionString, nameOfEquationEditText.getText().toString(), hashTagOfEquationEditText.getText().toString());
                makeFunctionArray.parsingInputString();

                makeFunctionArray.parsingInputString();
                makeFunctionArray.insertFunctionArray();
                if(makeFunctionArray.isSuccess == true){
                    postFunction(makeFunctionArray.getFunctionArray());
                }

            }
        });
    }
    private void postFunction(FunctionArray functionArray){
        Call<String> call = functionWebService.postFunction(functionArray);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getActivity().getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
            }//중복 확인 결과 받아서 처리해주자...

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(getActivity().getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void test(){
        MakeFunctionArray makeFunctionArray1 = new MakeFunctionArray("x + y + z", "명기 공식", "#더하기 #명기 #기본");

        makeFunctionArray1.parsingInputString();
        makeFunctionArray1.insertFunctionArray();
        MakeFunctionArray makeFunctionArray2 = new MakeFunctionArray("x * y * z", "재원 공식", "#곱하기 #재원 #기본");
        makeFunctionArray2.parsingInputString();
        makeFunctionArray2.insertFunctionArray();
        MakeFunctionArray makeFunctionArray3 = new MakeFunctionArray("x / y * z", "쩌는 공식", "#심화 #곱하기 #나누기");
        makeFunctionArray3.parsingInputString();
        makeFunctionArray3.insertFunctionArray();
        MakeFunctionArray makeFunctionArray4 = new MakeFunctionArray("x - y + z", "공식", "#더하기 #빼기 #심화 #기본");
        makeFunctionArray4.parsingInputString();
        makeFunctionArray4.insertFunctionArray();
        postFunction(makeFunctionArray1.getFunctionArray());
        postFunction(makeFunctionArray2.getFunctionArray());
        postFunction(makeFunctionArray3.getFunctionArray());
        postFunction(makeFunctionArray4.getFunctionArray());

    }
}
