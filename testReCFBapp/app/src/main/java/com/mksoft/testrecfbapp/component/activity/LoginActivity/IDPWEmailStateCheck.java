package com.mksoft.testrecfbapp.component.activity.LoginActivity;

import android.util.Log;

public class IDPWEmailStateCheck {
    private boolean pwState = false;
    private boolean idState = false;
    private boolean emailState = false;


    public IDPWEmailStateCheck() {
    }

    public String checkID(String id){
        if(id.length()<6){
            idState = false;
            return "아이디 길이는 5자 이상으로 하세요.";
        }//서버에 보내서 확인 작업...

        idState = true;
        return "사용가능 아이디입니다.";

    }
    public String checkPW(String pw1, String pw2){

        boolean pwSpeciaCharlState = false;
        boolean pwNumCharlState = false;
        boolean pwAZCharState= false;

        for(int i =0; i<pw1.length(); i++){
            if((pw1.charAt(i)>= '!' && pw1.charAt(i) <='/') || (pw1.charAt(i)>= ':' && pw1.charAt(i) <='@') || (pw1.charAt(i)>= '[' && pw1.charAt(i) <'a')
                    ||(pw1.charAt(i)>= '{' && pw1.charAt(i) <='~')){

                pwSpeciaCharlState = true;
            }else if((pw1.charAt(i)>= '0' && pw1.charAt(i) <='9')){
                pwNumCharlState = true;
            }else if((pw1.charAt(i)>= 'A' && pw1.charAt(i) <='Z')||(pw1.charAt(i)>= 'a' && pw1.charAt(i) <='z')){
                pwAZCharState = true;
            }
        }

        if(pw1.length()<12 || pw1.length()>20){
            pwState = false;
            return "비밀번호는 11자리 이상 20자리 이하로 설정하세요.";
        }else if(!(pwSpeciaCharlState == true && pwNumCharlState == true && pwAZCharState == true)){
            pwState = false;
            return "비밀번호에 숫자, 특수문자, 알파벳을 포함시키세요.";
        }else if(pw1.equals(pw2) == false){
            Log.d("test pw", pw1 + "    " + pw2);
            Log.d("test pw", String.valueOf(pw1.equals(pw2)));
            pwState = false;
            return"비밀번호가 일치하지 않습니다.";
        }
        pwState = true;
        return"비밀번호 사용가능";
    }
    public String checkEmail(String email){
        boolean emailAtCharState = false;
        boolean emailDotCharState = false;
        boolean emailNotCharState = false;

        for(int i =0; i<email.length(); i++){
            if(email.charAt(i) == '@'){
                emailAtCharState = true;
            }else if(email.charAt(i) == '.'){
                emailDotCharState = true;
            }else if((email.charAt(i)>= '!' && email.charAt(i) <='/') || (email.charAt(i)>= ':' && email.charAt(i) <='@') || (email.charAt(i)>= '[' && email.charAt(i) <'a')
                    ||(email.charAt(i)>= '{' && email.charAt(i) <='~')){

                emailNotCharState = true;
            }
        }
        if(!(emailAtCharState == true && emailDotCharState == true && emailNotCharState == false)){
            emailState = false;
            return "이메일 형식이 맞지 않습니다.";
        }
        //가입된 이메일 체크...
        emailState = true;
        return "사용가능 이메일";
    }



    public boolean isPwState() {
        return pwState;
    }

    public boolean isIdState() {
        return idState;
    }

    public boolean isEmailState() {
        return emailState;
    }
    public boolean allSate(){
        if(idState == true && pwState == true && emailState == true)
            return true;
        return false;
    }
}
