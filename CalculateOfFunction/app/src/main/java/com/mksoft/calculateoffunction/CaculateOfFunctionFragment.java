package com.mksoft.calculateoffunction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CaculateOfFunctionFragment extends Fragment {
    Button resultButton;
    Button bookMarkButton;
    RecyclerView recyclerView;
    String equation;
    Object[] valList;
    int cntOfVal;
    RecyclerView.LayoutManager mLayoutManager;


    ValListAdapter valListAdapter;
    TextView functionTextView;
    TextView resultVal;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.caculate_of_function, container, false);
        testInit();
        initUI(rootView);
        onClickResultButton();
        return rootView;
    }
    private void initUI(ViewGroup rootView){

        functionTextView = rootView.findViewById(R.id.functionTextView);
        functionTextView.setText(equation);


        resultButton = rootView.findViewById(R.id.resultButton);
        bookMarkButton = rootView.findViewById(R.id.bookMarkButton);
        resultVal = rootView.findViewById(R.id.resultVal);


        recyclerView = (RecyclerView)rootView.findViewById(R.id.valListRecyclerView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        valListAdapter = new ValListAdapter(rootView.getContext(), valList, cntOfVal);
        recyclerView.setAdapter(valListAdapter);
        //리사이크러뷰 초기화

    }
    private void onClickResultButton(){
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] userInputVal = new String[cntOfVal];
                for(int i =0; i<cntOfVal;i++){
                    if(valListAdapter.getEditText(i) == null)
                        return;
                    userInputVal[i] = valListAdapter.getEditText(i);

                }
                CaculateMethod caculateMethod = new CaculateMethod(equation, valList,userInputVal, cntOfVal);
                caculateMethod.caculateEquation();//계산수행
                if(caculateMethod.isResultState())
                    resultVal.setText(String.valueOf(caculateMethod.getResultVal()));
            }
        });
    }
    private void testInit(){
            equation = "";
            String[] temp = {"x", "y", "z"};
            valList = temp;
            cntOfVal = 3;
    }//인턴테로 받을꺼...



}
