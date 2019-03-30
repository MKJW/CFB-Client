package com.mksoft.testrecfbapp.component.activity.fragment.LoginPage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mksoft.testrecfbapp.R;
import com.mksoft.testrecfbapp.component.activity.MainActivity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class JoinPageFragment extends Fragment {
    MainActivity mainActivity;

    RelativeLayout loginProjectMakeIdRelativeLayout;

    EditText loginProjectMakeIdIDEditText;
    TextView loginProjectMakeIdIDstate;

    EditText loginProjectMakeIdPWEditText;
    EditText loginProjectMakeIdPWCheckEditText;
    TextView loginProjectMakeIdPWstate;

    EditText loginProjectMakeIdEmailEditText;
    TextView loginProjectMakeIdEmailstate;

    Button loginProjectMakeIdJoinButton;
    Button loginProjectMakeIdBackButton;

    IDPWEmailStateCheck idpwEmailStateCheck;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.login_project_make_id, container, false);
        initUI(rootView);
        hideKeyboard();
        clickHideKeyboard();
        clickBack();
        clickJoin();
        focusEditView();
        return rootView;
    }

    private void initUI(ViewGroup rootView){
        loginProjectMakeIdRelativeLayout = rootView.findViewById(R.id.loginProjectMakeIdRelativeLayout);

        loginProjectMakeIdIDEditText = rootView.findViewById(R.id.loginProjectMakeIdIDEditText);
        loginProjectMakeIdIDstate = rootView.findViewById(R.id.loginProjectMakeIdIDstate);

        loginProjectMakeIdPWEditText = rootView.findViewById(R.id.loginProjectMakeIdPWEditText);
        loginProjectMakeIdPWCheckEditText = rootView.findViewById(R.id.loginProjectMakeIdPWCheckEditText);
        loginProjectMakeIdPWstate = rootView.findViewById(R.id.loginProjectMakeIdPWstate);

        loginProjectMakeIdEmailEditText = rootView.findViewById(R.id.loginProjectMakeIdEmailEditText);
        loginProjectMakeIdEmailstate = rootView.findViewById(R.id.loginProjectMakeIdEmailstate);

        loginProjectMakeIdJoinButton = rootView.findViewById(R.id.loginProjectMakeIdJoinButton);
        loginProjectMakeIdBackButton = rootView.findViewById(R.id.loginProjectMakeIdBackButton);

        idpwEmailStateCheck = new IDPWEmailStateCheck();
    }
    private void hideKeyboard(){
        mainActivity.getHideKeyboard().hideKeyboard();
    }
    private void clickHideKeyboard(){
        loginProjectMakeIdRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                checkState();

            }
        });
    }
    private void checkState(){
        String tempID = loginProjectMakeIdIDEditText.getText().toString();
        if(tempID!=null&&tempID.length() != 0){
            loginProjectMakeIdIDstate.setText(idpwEmailStateCheck.checkID(tempID));
            if(idpwEmailStateCheck.isIdState()){
                loginProjectMakeIdIDstate.setTextColor(Color.parseColor("#83f162"));
            }else{
                loginProjectMakeIdIDstate.setTextColor(Color.parseColor("#FFF35757"));
            }
        }
        String tempPW1 = loginProjectMakeIdPWEditText.getText().toString();
        String tempPW2 = loginProjectMakeIdPWCheckEditText.getText().toString();
        if((tempPW1!=null&&tempPW1.length()!=0) && (tempPW2!=null&&tempPW2.length()!=0) ){
            loginProjectMakeIdPWstate.setText(idpwEmailStateCheck.checkPW(tempPW1, tempPW2));
            if(idpwEmailStateCheck.isPwState()){
                loginProjectMakeIdPWstate.setTextColor(Color.parseColor("#83f162"));
            }else{
                loginProjectMakeIdPWstate.setTextColor(Color.parseColor("#FFF35757"));
            }
        }
        String tempEmail = loginProjectMakeIdEmailEditText.getText().toString();
        if(tempEmail != null && tempEmail.length() != 0){
            loginProjectMakeIdEmailstate.setText(idpwEmailStateCheck.checkEmail(tempEmail));
            if(idpwEmailStateCheck.isEmailState()){
                loginProjectMakeIdEmailstate.setTextColor(Color.parseColor("#83f162"));
            }else{
                loginProjectMakeIdEmailstate.setTextColor(Color.parseColor("#FFF35757"));
            }
        }
    }
    private void clickJoin(){
        loginProjectMakeIdJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkState();
                if(idpwEmailStateCheck.allSate() == true){
                    //서버에 아이디 넣기
                }
            }
        });
    }
    private void clickBack(){
        loginProjectMakeIdBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onFragmentChange(5, null);
            }
        });

    }
    private void focusEditView(){
        loginProjectMakeIdIDEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkState();
            }
        });
        loginProjectMakeIdPWEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkState();
            }
        });
        loginProjectMakeIdEmailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkState();
            }
        });
    }

}
