package com.mksoft.loginproject.Login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mksoft.loginproject.MainActivity;
import com.mksoft.loginproject.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LoginPageFragment extends Fragment {

    MainActivity mainActivity;
    RelativeLayout loginProjectLoginPageRelativeLayout;
    EditText loginProjectLoginPageIdEditText;
    EditText loginProjectLoginPagePwEditText;
    Button loginProjectLoginPageLoginButton;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
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
        return rootView;
    }
    private void initUI(ViewGroup rootView){
        loginProjectLoginPageRelativeLayout = rootView.findViewById(R.id.loginProjectLoginPageRelativeLayout);
        loginProjectLoginPageIdEditText = rootView.findViewById(R.id.loginProjectLoginPageIdEditText);
        loginProjectLoginPagePwEditText = rootView.findViewById(R.id.loginProjectLoginPagePwEditText);
        loginProjectLoginPageLoginButton = rootView.findViewById(R.id.loginProjectLoginPageLoginButton);
    }
    private void hideKeyboard(){
        mainActivity.getHideKeyboard().hideKeyboard();
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
                    mainActivity.getLoginServiceComponent().makeLoginService().getToken(getContext(),
                            loginProjectLoginPagePwEditText.getText().toString(),
                            loginProjectLoginPageIdEditText.getText().toString(),
                            loginProjectLoginPagePwEditText.getText().toString());

                }
            }
        });
    }

}
