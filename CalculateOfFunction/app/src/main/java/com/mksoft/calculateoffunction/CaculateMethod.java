package com.mksoft.calculateoffunction;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class CaculateMethod {
    private String equation;
    private String caculateString;
    private Object[] valListname;
    private String[] valListString;
    private int numOfVariables;
    private Double resultVal;
    private boolean resultState = false;

    public CaculateMethod(String equation,Object[] valListname, String[] valListString, int numOfVariables) {
        this.equation = equation;
        this.valListname = valListname;
        this.valListString = valListString;
        this.numOfVariables = numOfVariables;
    }
    public void makeCaculateString(){
        caculateString = equation;
        for(int i =0; i<numOfVariables; i++){
            caculateString = caculateString.replace((String)valListname[i], valListString[i]);
        }
    }//대입하기
    public void caculateEquation(){
        makeCaculateString();//대입
        Double tempResult = 0.0;
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("rhino");

        try {
            resultState = true;
            resultVal = (Double) engine.eval(caculateString);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }//계산기 만들어주기
    public double getResultVal(){
        return resultVal;
    }

    public boolean isResultState() {
        return resultState;
    }
}
