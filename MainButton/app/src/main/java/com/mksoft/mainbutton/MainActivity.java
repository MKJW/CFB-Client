package com.mksoft.mainbutton;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.mksoft.mainbutton.CaculateOfFunction.CaculateOfFunctionFragment;
import com.mksoft.mainbutton.FunctionAddPage.MakeFunctionFragment;
import com.mksoft.mainbutton.FunctionAddPage.hashtagAndTitle.TitleAndHashTagOfFunctionFragment;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MainButtonFragment mainButtonFragment;
    MakeFunctionFragment makeFunctionFragment;
    TitleAndHashTagOfFunctionFragment titleAndHashTagOfFunctionFragment;
    CaculateOfFunctionFragment caculateOfFunctionFragment;
    HideKeyboard hideKeyboard;
    FrameLayout mainContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mainButtonFragment = new MainButtonFragment();
        makeFunctionFragment = new MakeFunctionFragment();
        titleAndHashTagOfFunctionFragment = new TitleAndHashTagOfFunctionFragment();
        caculateOfFunctionFragment = new CaculateOfFunctionFragment();
        hideKeyboard = new HideKeyboard(this);
        mainContainer = findViewById(R.id.mainContainer);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, mainButtonFragment).commit();//처음 기본 플레그먼트 설정
    }
    public void onFragmentChange(int idx, Bundle bundle){
        if(idx == 1){

            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, mainButtonFragment).commit();
            //메인버튼 프레그먼트
        }else if(idx == 2){
            makeFunctionFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, makeFunctionFragment).commit();
            //add 페이지
        }else if(idx == 3) {
            titleAndHashTagOfFunctionFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, titleAndHashTagOfFunctionFragment).commit();
        }else if(idx == 4){
            //계산기 화면
            caculateOfFunctionFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, caculateOfFunctionFragment).commit();
        }
    }
    public HideKeyboard getHideKeyboard(){
        return hideKeyboard;
    }
}
