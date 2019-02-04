package com.mksoft.cfbapp.FunctionAddPage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.mksoft.cfbapp.MainActivity;
import com.mksoft.cfbapp.R;
import com.mksoft.cfbapp.Repository.API.WebService;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    RelativeLayout makeFunctionMainRalativeLayout;
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
    public void onResume() {
        super.onResume();
        getAllArguments();

    }//멈추고 다시 수행될 때 이전에 등록한 값 복원 할지 말지....

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.make_function, container, false);
        initUI(rootView);
        clickNextButton();
        initRepos();
        clickBackButton();
        hideKeyboard();//전페이지에서 살아있는 키보드 숨기기
        clickHideKeyboard();//페이지 화면에서 다른 부분 클릭시 키보드 숨기기
        return rootView;
    }
    private void hideKeyboard(){
        mainActivity.getHideKeyboard().hideKeyboard();
    }
    private void initUI(ViewGroup rootView){
        nextButton = rootView.findViewById(R.id.nextButton);
        backButton = rootView.findViewById(R.id.backButton);
        editFunctionText = (EditText) rootView.findViewById(R.id.editFunctionText);
        makeFunctionMainRalativeLayout = rootView.findViewById(R.id.makeFunctionRalativeLayout);


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
    private void clickHideKeyboard(){
        makeFunctionMainRalativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

            }
        });
    }
}
