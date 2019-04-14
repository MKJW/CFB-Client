package com.mksoft.testrecfbapp.component.activity.LoginActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.mksoft.testrecfbapp.R;
import com.mksoft.testrecfbapp.Repository.LoginRepo;
import com.mksoft.testrecfbapp.component.activity.MainActivity;

import org.mozilla.javascript.tools.jsc.Main;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import dagger.android.support.AndroidSupportInjection;

public class LoginPageFragment extends Fragment {

    RelativeLayout loginProjectLoginPageRelativeLayout;
    EditText loginProjectLoginPageIdEditText;
    EditText loginProjectLoginPagePwEditText;
    Button loginProjectLoginPageLoginButton;
    Button loginProjectLoginPageJoinButton;

    FragmentTransaction fragmentTransaction;

    @Inject
    LoginRepo loginRepo;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();

    }

    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((LoginRootActivity) context).setOnKeyBackPressedListener(null);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.login_project_login_page, container, false);
        initUI(rootView);
        hideKeyboard();
        clickHideKeyboard();
        clickLoginButton();
        clickJoinButton();
        return rootView;
    }
    private void initUI(ViewGroup rootView){
        loginProjectLoginPageRelativeLayout = rootView.findViewById(R.id.loginProjectLoginPageRelativeLayout);
        loginProjectLoginPageIdEditText = rootView.findViewById(R.id.loginProjectLoginPageIdEditText);
        loginProjectLoginPagePwEditText = rootView.findViewById(R.id.loginProjectLoginPagePwEditText);
        loginProjectLoginPageLoginButton = rootView.findViewById(R.id.loginProjectLoginPageLoginButton);
        loginProjectLoginPageJoinButton = rootView.findViewById(R.id.loginProjectLoginPageJoinButton);
    }
    private void hideKeyboard(){
        MainActivity.mainActivity.getHideKeyboard().hideKeyboard();
    }
    private void clickHideKeyboard(){
        loginProjectLoginPageRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

            }
        });
    }
    private void clickLoginButton(){
        loginProjectLoginPageLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginProjectLoginPagePwEditText.getText().toString() != null &&
                        loginProjectLoginPageIdEditText.getText().toString() != null) {
                    loginRepo.getToken(getContext(),
                            loginProjectLoginPagePwEditText.getText().toString(),
                            loginProjectLoginPageIdEditText.getText().toString(),
                            loginProjectLoginPagePwEditText.getText().toString(),
                            MainActivity.mainActivity
                            );

                }
            }
        });
    }
    private void clickJoinButton(){
        loginProjectLoginPageJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getFragmentManager().beginTransaction();
                LoginRootActivity.loginRootActivity.getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.loginrootmainContainer, new JoinPageFragment(), null);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}
