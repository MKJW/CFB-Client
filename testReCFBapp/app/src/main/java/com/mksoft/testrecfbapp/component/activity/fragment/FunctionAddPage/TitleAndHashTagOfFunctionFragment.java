package com.mksoft.testrecfbapp.component.activity.fragment.FunctionAddPage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mksoft.testrecfbapp.R;
import com.mksoft.testrecfbapp.Repository.APIRepo;
import com.mksoft.testrecfbapp.component.activity.MainActivity;
import com.mksoft.testrecfbapp.Repository.Data.MakeFunctionArray;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import dagger.android.support.AndroidSupportInjection;

public class TitleAndHashTagOfFunctionFragment extends Fragment {

    EditText nameOfEquationEditText;
    EditText hashTagOfEquationEditText;
    Button submitButton;
    Button backButton;
    TextView userFunctionTextView;
    String expressionString;
    MainActivity mainActivity;
    RelativeLayout makeFunctionTitleAndHashTagOfFunctionMainRalativeLayout;
    @Inject
    APIRepo apiRepo;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }


    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.make_function_title_and_hash_tag_of_function, container, false);
        initUI(rootView);

        clickSubmitButton();
        clickBackButton();
        hideKeyboard();
        clickHideKeyboard();
        //test();
        return rootView;
    }
    private void initUI(ViewGroup rootView){
        nameOfEquationEditText = rootView.findViewById(R.id.nameOfEquationEditText);
        backButton = rootView.findViewById(R.id.backButton);
        userFunctionTextView = rootView.findViewById(R.id.userFunctionTextView);
        hashTagOfEquationEditText = rootView.findViewById(R.id.hashTagOfEquationEditText);
        submitButton = rootView.findViewById(R.id.submitButton);
        makeFunctionTitleAndHashTagOfFunctionMainRalativeLayout = rootView.findViewById(R.id.titleAndHashTagOfFunctionRalativeLayout);
        getAllArguments();

    }

    private void getAllArguments(){
        if(getArguments() != null){
            expressionString = getArguments().getString("expression");
            userFunctionTextView.setText(expressionString);
        }

    }

    private void clickBackButton(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle(1);
                bundle.putString("expression",expressionString);
                hashTagOfEquationEditText.setText("");
                nameOfEquationEditText.setText("");
                mainActivity.onFragmentChange(2, bundle);
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
                    //Toast.makeText(getContext(), makeFunctionArray.getFunctionArray().toString(), Toast.LENGTH_LONG).show();
                    apiRepo.postFunction(
                            makeFunctionArray.getFunctionArray(), mainActivity,
                            hashTagOfEquationEditText,nameOfEquationEditText);

                }

            }
        });
    }

    private void hideKeyboard(){
        mainActivity.getHideKeyboard().hideKeyboard();
    }
    private void clickHideKeyboard(){
        makeFunctionTitleAndHashTagOfFunctionMainRalativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

            }
        });
    }
}
