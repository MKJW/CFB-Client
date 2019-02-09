package com.mksoft.loginproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.mksoft.loginproject.DI.CFBServiceComponent;
import com.mksoft.loginproject.DI.DaggerCFBServiceComponent;
import com.mksoft.loginproject.DI.DaggerLoginServiceComponent;
import com.mksoft.loginproject.DI.LoginServiceComponent;
import com.mksoft.loginproject.Login.JoinPageFragment;
import com.mksoft.loginproject.Login.LoginPageFragment;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    //MainButtonFragment mainButtonFragment;
    //MakeFunctionFragment makeFunctionFragment;
    //TitleAndHashTagOfFunctionFragment titleAndHashTagOfFunctionFragment;
    //CaculateOfFunctionFragment caculateOfFunctionFragment;
    HideKeyboard hideKeyboard;
    FrameLayout mainContainer;
    //CFBServiceComponent cfbServiceComponent;
    JoinPageFragment joinPageFragment;

    LoginPageFragment loginFragment;
    LoginServiceComponent loginServiceComponent;
    SharedPreferences pref;
    String access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        tokenInit();
        //mainButtonFragment = new MainButtonFragment();
        //makeFunctionFragment = new MakeFunctionFragment();
        //titleAndHashTagOfFunctionFragment = new TitleAndHashTagOfFunctionFragment();
        //caculateOfFunctionFragment = new CaculateOfFunctionFragment();
        joinPageFragment = new JoinPageFragment();
        hideKeyboard = new HideKeyboard(this);
        loginFragment = new LoginPageFragment();
        //cfbServiceComponent = DaggerCFBServiceComponent.create();
        loginServiceComponent = DaggerLoginServiceComponent.create();
        mainContainer = findViewById(R.id.mainContainer);
        startPageSelect();
    }
    public void onFragmentChange(int idx, Bundle bundle){
        /*if(idx == 1){

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
        }else*/ if(idx == 5){
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

    /*public CFBServiceComponent getCfbServiceComponent() {
        return cfbServiceComponent;
    }*/
    public LoginServiceComponent getLoginServiceComponent() {
        return loginServiceComponent;
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
        //if(access_token != null && access_token != ""){
        //    getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, mainButtonFragment).commit();//처음 기본 플레그먼트 설정
        //}else{
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, loginFragment).commit();//로그인 화면

        //}

    }
    public String getAccess_token(){
        return access_token;
    }
}
