package com.mksoft.mainbutton.FunctionAddPage;

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
import com.mksoft.mainbutton.HideKeyboard;
import com.mksoft.mainbutton.MainActivity;
import com.mksoft.mainbutton.R;
import com.mksoft.mainbutton.WebService;

import java.util.TooManyListenersException;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MakeFunctionFragment extends Fragment {

    Button nextButton;
    Button backButton;
    EditText editFunctionText;
    Retrofit retrofit;
    WebService functionWebService;
    MainActivity mainActivity;//메인엑티비티에서 요청을 하기위하여 필요.
    String expression;
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.make_function, container, false);
        initUI(rootView);
        clickNextButton();
        initRepos();
        clickBackButton();
        hideKeyboard();
        getAllArguments();
        return rootView;
    }
    private void hideKeyboard(){
        mainActivity.getHideKeyboard().hideKeyboard();
    }
    private void initUI(ViewGroup rootView){
        nextButton = rootView.findViewById(R.id.nextButton);
        backButton = rootView.findViewById(R.id.backButton);
        editFunctionText = (EditText) rootView.findViewById(R.id.editFunctionText);


    }


    private void initRepos(){
        retrofit = new Retrofit.Builder().baseUrl("http://114.202.9.170:8080").addConverterFactory(GsonConverterFactory.create()).build();
        functionWebService = retrofit.create(WebService.class);
    }

    private void clickNextButton(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editFunctionText.getText().toString().length() == 0){
                    Toast.makeText(getContext(), "함수를 입력하세요.", Toast.LENGTH_SHORT).show();
                }else{
                    Bundle bundle = new Bundle(1);
                    bundle.putString("expression",editFunctionText.getText().toString());
                    editFunctionText.setText("");
                    mainActivity.onFragmentChange(3, bundle);
                }



            }
        });
    }
    private void clickBackButton(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editFunctionText.setText("");
                mainActivity.onFragmentChange(1, null);//메인버튼페이지로 돌아감

            }
        });
    }
    private void getAllArguments(){
        if(getArguments() != null){
            expression =  getArguments().getString("expression");
            editFunctionText.setText(expression);
        }

    }
}
