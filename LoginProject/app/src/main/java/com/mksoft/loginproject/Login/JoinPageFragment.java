package com.mksoft.loginproject.Login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mksoft.loginproject.MainActivity;
import com.mksoft.loginproject.R;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.login_project_login_page, container, false);
        initUI(rootView);
        hideKeyboard();
        clickHideKeyboard();
        clickBack();
        clickJoin();
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
                //loginProjectMakeIdIDstate.setTextColor(#83f162);
            }else{

            }
        }
        String tempPW1 = loginProjectMakeIdPWEditText.getText().toString();
        String tempPW2 = loginProjectMakeIdPWCheckEditText.getText().toString();
        if((tempPW1!=null&&tempPW1.length()!=0) && (tempPW2!=null&&tempPW2.length()!=0)){

        }
        String tempEmail = loginProjectMakeIdEmailEditText.getText().toString();
        if(tempEmail != null && tempEmail.length() != 0){

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

}
