package com.mksoft.creatingfunctionbody;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MakeFunctionFragment makeFunctionFragment;
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
        makeFunctionFragment = new MakeFunctionFragment();
        titleAndHashTagOfFunctionFragment = new TitleAndHashTagOfFunctionFragment();

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mainContainer = findViewById(R.id.mainContainer);
        mainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            }
        });//클릭시 키보드 숨기기
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, makeFunctionFragment).commit();//처음 기본 플레그먼트 설정
    }
    public void onFragmentChange(int idx, Bundle bundle){
        if(idx == 1){

            //getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, mainButtonFragment).commit();
            //메인버튼 프레그먼트
        }else if(idx == 2){

            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, makeFunctionFragment).commit();
            //add 페이지
        }else if(idx == 3){
            titleAndHashTagOfFunctionFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, titleAndHashTagOfFunctionFragment).commit();
        }
    }
}
