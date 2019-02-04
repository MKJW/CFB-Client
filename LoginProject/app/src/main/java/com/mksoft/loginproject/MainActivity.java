package com.mksoft.loginproject;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.mksoft.loginproject.DI.CFBServiceComponent;
import com.mksoft.loginproject.DI.DaggerLoginServiceComponent;
import com.mksoft.loginproject.DI.LoginServiceComponent;
import com.mksoft.loginproject.Login.LoginPageFragment;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    LoginServiceComponent loginServiceComponent;
    HideKeyboard hideKeyboard;
    FrameLayout mainContainer;
    LoginPageFragment loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        loginFragment = new LoginPageFragment();
        hideKeyboard = new HideKeyboard(this);
        loginServiceComponent = DaggerLoginServiceComponent.create();
        mainContainer = findViewById(R.id.mainContainer);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, loginFragment).commit();//처음 기본 플레그먼트 설정
    }

    public HideKeyboard getHideKeyboard(){
        return hideKeyboard;
    }
    public LoginServiceComponent getLoginServiceComponent() {
        return loginServiceComponent;
    }
}
