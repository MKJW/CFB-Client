package com.mksoft.testrecfbapp.component.activity.LoginActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.mksoft.testrecfbapp.R;
import com.mksoft.testrecfbapp.component.activity.BackPressCloseHandler;
import com.mksoft.testrecfbapp.component.activity.MainActivity;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class LoginRootActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    BackPressCloseHandler backPressCloseHandler;
    public static LoginRootActivity loginRootActivity;
    FrameLayout mainContainer;
    LoginPageFragment loginFragment;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_root_activity);
        loginRootActivity = this;
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
        mainContainer = findViewById(R.id.loginrootmainContainer);
        backPressCloseHandler = new BackPressCloseHandler(this);
        loginFragment = new LoginPageFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.loginrootmainContainer, loginFragment).commit();//로그인 화면

    }

    ////////////////////// back key

    public interface onKeyBackPressedListener{
        void onBackKey();
    }
    private MainActivity.onKeyBackPressedListener mOnKeyBackPressedListener;
    public void setOnKeyBackPressedListener(MainActivity.onKeyBackPressedListener listener){
        mOnKeyBackPressedListener = listener;
    }


    @Override
    public void onBackPressed() {
        if(mOnKeyBackPressedListener != null) {
            mOnKeyBackPressedListener.onBackKey();
        }else{
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                backPressCloseHandler.onBackPressed();
            }
            else{
                super.onBackPressed();
            }
        }
    }
}
