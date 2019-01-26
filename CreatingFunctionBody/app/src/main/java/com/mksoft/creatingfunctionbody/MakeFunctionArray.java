package com.mksoft.creatingfunctionbody;

import android.util.Log;

public class MakeFunctionArray {
    public boolean isSuccess = false;//함수배열을 만드는데 성공하면 true로 설정
    private String equation;
    private int numOfVariables = 0;
    private FunctionArray functionArray;
    private String[] nameOfVariables;

    public MakeFunctionArray(String inputString){
        this.equation = inputString;

    }
    public void insertFunctionArray(){
        if(nameOfVariables[0] == null){
            Log.d("error nuLL", "nameOfVariables NULL");
            return;
        }
        if(equation == null){
            Log.d("error nuLL", "functionString NULL");
            return;
        }
        if(numOfVariables == 0){
            Log.d("error zero", "countOfValue zero");
            return;
        }

        functionArray = new FunctionArray(equation, nameOfVariables);
        isSuccess = true;
        return;
    }
    public void parsingInputString(){
        if(equation == null){
            Log.d("error nuLL", "functionString NULL");
            return;
        }
        String tempInputString = equation;
        tempInputString = tempInputString.replace('+', ' ');
        tempInputString = tempInputString.replace('-', ' ');
        tempInputString = tempInputString.replace('*', ' ');
        tempInputString = tempInputString.replace('/', ' ');
        tempInputString = tempInputString.replace(')', ' ');
        tempInputString = tempInputString.replace('(', ' ');
        //+, -, *, /, (, )죽이기
        String[] tempValue = tempInputString.split("\\s");
        for(String temp : tempValue){
            if(temp.length() != 0){
                this.numOfVariables++;
            }
        }
        nameOfVariables = new String[numOfVariables];
        int i =0;
        int j =0;
        for(String temp : tempValue){
            if(temp.length() != 0){
                nameOfVariables[i] = tempValue[j];
                i++;
            }
            j++;

        }
    }

    public FunctionArray getFunctionArray(){
        if(functionArray == null)
            Log.d("error null", "functionArray NULL...");
        return functionArray;
    }

}