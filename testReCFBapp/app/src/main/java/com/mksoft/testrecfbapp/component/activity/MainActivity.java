package com.mksoft.testrecfbapp.component.activity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.mksoft.testrecfbapp.R;
import com.mksoft.testrecfbapp.component.activity.fragment.CaculateOfFuction.CaculateOfFunctionFragment;
import com.mksoft.testrecfbapp.component.activity.fragment.FunctionAddPage.MakeFunctionFragment;
import com.mksoft.testrecfbapp.component.activity.fragment.FunctionAddPage.TitleAndHashTagOfFunctionFragment;
import com.mksoft.testrecfbapp.component.activity.fragment.LoginPage.JoinPageFragment;
import com.mksoft.testrecfbapp.component.activity.fragment.LoginPage.LoginPageFragment;
import com.mksoft.testrecfbapp.component.activity.fragment.MainPage.MainButtonFragment;
import com.mksoft.testrecfbapp.otherMethod.HideKeyboard;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    MainButtonFragment mainButtonFragment;
    MakeFunctionFragment makeFunctionFragment;
    TitleAndHashTagOfFunctionFragment titleAndHashTagOfFunctionFragment;
    CaculateOfFunctionFragment caculateOfFunctionFragment;
    HideKeyboard hideKeyboard;
    FrameLayout mainContainer;
    JoinPageFragment joinPageFragment;


    LoginPageFragment loginFragment;
    SharedPreferences pref;
    String access_token;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureDagger();
        init();
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    private void configureDagger(){
        AndroidInjection.inject(this);
    }

    private void init(){
        tokenInit();
        joinPageFragment = new JoinPageFragment();
        mainButtonFragment = new MainButtonFragment();
        makeFunctionFragment = new MakeFunctionFragment();
        titleAndHashTagOfFunctionFragment = new TitleAndHashTagOfFunctionFragment();
        caculateOfFunctionFragment = new CaculateOfFunctionFragment();
        hideKeyboard = new HideKeyboard(this);
        loginFragment = new LoginPageFragment();
        mainContainer = findViewById(R.id.mainContainer);
        startPageSelect();
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
        }else if(idx == 5){
            //로그아웃
            tokenRemove();
            access_token ="";
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, loginFragment).commit();//로그인 화면

        }else if(idx == 6){
            //가입화면
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, joinPageFragment).commit();
        }
    }
    public HideKeyboard getHideKeyboard(){
        return hideKeyboard;
    }


    public void tokenInit(){
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        access_token = pref.getString("access_token", "");

    }
    private void tokenRemove(){
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        pref.edit().remove("access_token");

    }
    private void startPageSelect(){
        if(access_token != null && access_token != ""){
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, mainButtonFragment).commit();//처음 기본 플레그먼트 설정
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, loginFragment).commit();//로그인 화면

        }

    }
    public String getAccess_token(){
        return access_token;
    }
}
