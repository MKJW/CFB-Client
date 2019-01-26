package com.mksoft.titleandhashtagoffunction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TitleAndHashTagOfFunctionFragment titleAndHashTagOfFunctionFragment;
    InputMethodManager imm;
    FrameLayout mainContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        titleAndHashTagOfFunctionFragment = new TitleAndHashTagOfFunctionFragment();

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mainContainer = findViewById(R.id.mainContainer);
        mainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            }
        });//클릭시 키보드 숨기기
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, titleAndHashTagOfFunctionFragment).commit();//처음 기본 플레그먼트 설정
    }

}
