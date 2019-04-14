package com.mksoft.testrecfbapp.component.activity.LoginActivity;

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

import org.mozilla.javascript.tools.jsc.Main;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class JoinPageFragment extends Fragment implements MainActivity.onKeyBackPressedListener{


    RelativeLayout joinPageRelativeLayout;

    EditText joinPageIDEditText;
    TextView joinPageIDstate;

    EditText joinPagePWEditText;
    EditText joinPagePWCheckEditText;
    TextView joinPagePWstate;

    EditText joinPageEmailEditText;
    TextView joinPageEmailstate;

    Button joinPageJoinButton;
    Button joinPageBackButton;

    IDPWEmailStateCheck IDPWEmailStateCheck;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((LoginRootActivity) context).setOnKeyBackPressedListener(this);
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
        joinPageRelativeLayout = rootView.findViewById(R.id.loginProjectMakeIdRelativeLayout);

        joinPageIDEditText = rootView.findViewById(R.id.loginProjectMakeIdIDEditText);
        joinPageIDstate = rootView.findViewById(R.id.loginProjectMakeIdIDstate);

        joinPagePWEditText = rootView.findViewById(R.id.loginProjectMakeIdPWEditText);
        joinPagePWCheckEditText = rootView.findViewById(R.id.loginProjectMakeIdPWCheckEditText);
        joinPagePWstate = rootView.findViewById(R.id.loginProjectMakeIdPWstate);

        joinPageEmailEditText = rootView.findViewById(R.id.loginProjectMakeIdEmailEditText);
        joinPageEmailstate = rootView.findViewById(R.id.loginProjectMakeIdEmailstate);

        joinPageJoinButton = rootView.findViewById(R.id.loginProjectMakeIdJoinButton);
        joinPageBackButton = rootView.findViewById(R.id.loginProjectMakeIdBackButton);

        IDPWEmailStateCheck = new IDPWEmailStateCheck();
    }
    private void hideKeyboard(){
        MainActivity.mainActivity.getHideKeyboard().hideKeyboard();
    }
    private void clickHideKeyboard(){
        joinPageRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                IDCheckState();
                PWCheckState();
                EmailCheck();
            }
        });
    }
    private void IDCheckState(){
        String tempID = joinPageIDEditText.getText().toString();
        if(tempID!=null&&tempID.length() != 0){
            joinPageIDstate.setText(IDPWEmailStateCheck.checkID(tempID));
            if(IDPWEmailStateCheck.isIdState()){
                joinPageIDstate.setTextColor(Color.parseColor("#83f162"));
            }else{
                joinPageIDstate.setTextColor(Color.parseColor("#FFF35757"));
            }
        }
    }
    private void PWCheckState(){
        String tempPW1 = joinPagePWEditText.getText().toString();
        String tempPW2 = joinPagePWCheckEditText.getText().toString();
        if((tempPW1!=null&&tempPW1.length()!=0) && (tempPW2!=null&&tempPW2.length()!=0) ){
            joinPagePWstate.setText(IDPWEmailStateCheck.checkPW(tempPW1, tempPW2));
            if(IDPWEmailStateCheck.isPwState()){
                joinPagePWstate.setTextColor(Color.parseColor("#83f162"));
            }else{
                joinPagePWstate.setTextColor(Color.parseColor("#FFF35757"));
            }
        }
    }
    private void EmailCheck(){
        String tempEmail = joinPageEmailEditText.getText().toString();
        if(tempEmail != null && tempEmail.length() != 0){
            joinPageEmailstate.setText(IDPWEmailStateCheck.checkEmail(tempEmail));
            if(IDPWEmailStateCheck.isEmailState()){
                joinPageEmailstate.setTextColor(Color.parseColor("#83f162"));
            }else{
                joinPageEmailstate.setTextColor(Color.parseColor("#FFF35757"));
            }
        }
    }
    private void clickJoin(){
        joinPageJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(IDPWEmailStateCheck.allSate() == true){
                    //서버에 아이디 넣기
                }
            }
        });
    }
    private void clickBack(){
        joinPageBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackKey();
            }
        });

    }
    private void focusEditView(){
        joinPageIDEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                IDCheckState();
            }
        });
        joinPagePWEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                PWCheckState();
            }
        });
        joinPageEmailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EmailCheck();
            }
        });
    }//포커스 변경시 마다 적절한 인풋이 들어 갔는지 체크
    //이미 가입된 정보인지를 checkstate()에서 검토하자

    @Override
    public void onBackKey() {

        LoginRootActivity.loginRootActivity.setOnKeyBackPressedListener(null);
        LoginRootActivity.loginRootActivity.onBackPressed();
    }
}
