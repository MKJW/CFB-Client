package com.mksoft.calculateoffunction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CaculateOfFunctionFragment extends Fragment {
    Button resultButton;
    Button bookMarkButton;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    FunctionArray functionArray;
    ValListAdapter valListAdapter;
    TextView functionTextView;
    TextView resultVal;
    TextView caculateOfFunctionPageHashTagTextView;
    TextView functionTitle;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.caculate_of_function, container, false);
        initUI(rootView);
        onClickResultButton();
        return rootView;
    }
    private void initUI(ViewGroup rootView){

        this.getAllArguments();

        functionTextView = rootView.findViewById(R.id.functionTitle);
        if(functionArray.getTitle() != null)
            functionTextView.setText(functionArray.getTitle());//번들로 함수를 넘겨 받고
        functionTitle = rootView.findViewById(R.id.functionTextView);
        if(functionArray.getExpression() != null)
            functionTitle.setText(functionArray.getExpression());
        caculateOfFunctionPageHashTagTextView = rootView.findViewById(R.id.caculateOfFunctionPageHashTagTextView);

        if(functionArray.getHashtags() !=null){
            String tempTag = "";
            for(int i =0; i<functionArray.getHashtags().length; i++){
                tempTag += "#"+functionArray.getHashtags()[i].getTagName()+" ";
            }
            caculateOfFunctionPageHashTagTextView.setText(tempTag);
        }

        if(functionArray.getNameOfVariables() != null){
            recyclerView = (RecyclerView)rootView.findViewById(R.id.valListRecyclerView);
            recyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(rootView.getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            valListAdapter = new ValListAdapter(rootView.getContext(), functionArray.getNameOfVariables(), functionArray.getNameOfVariables().length);
            recyclerView.setAdapter(valListAdapter);
            //리사이크러뷰 초기화

        }

        resultButton = rootView.findViewById(R.id.resultButton);
        bookMarkButton = rootView.findViewById(R.id.bookMarkButton);
        resultVal = rootView.findViewById(R.id.resultVal);
        functionTitle = rootView.findViewById(R.id.functionNameTextView);



    }
    private void getAllArguments(){
        this.functionArray = (FunctionArray) getArguments().getSerializable("FunctionArray");

    }
    private void onClickResultButton(){
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] userInputVal = new String[functionArray.getNameOfVariables().length];
                for(int i =0; i<functionArray.getNameOfVariables().length;i++){
                    if(valListAdapter.getEditText(i) == null)
                        return;
                    userInputVal[i] = valListAdapter.getEditText(i);

                }
                CaculateMethod caculateMethod = new CaculateMethod(functionArray.getExpression(), functionArray.getNameOfVariables(),userInputVal, functionArray.getNameOfVariables().length);
                caculateMethod.caculateEquation();//계산수행
                if(caculateMethod.isResultState())
                    resultVal.setText(String.valueOf(caculateMethod.getResultVal()));
            }
        });
    }
    /*private void testInit(){
            equation = "";
            String[] temp = {"x", "y", "z"};
            valList = temp;
            cntOfVal = 3;
    }//인턴테로 받을꺼...
    */


}
