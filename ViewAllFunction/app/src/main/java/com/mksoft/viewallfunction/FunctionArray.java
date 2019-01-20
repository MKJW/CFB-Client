package com.mksoft.viewallfunction;


import java.util.Arrays;

public class FunctionArray {
    int id = 0;
    private String equation = null;
    Object[] nameOfVariables = null;
    String nameOfEquation = null;
    Object[] hashOfEquation = null;

    public FunctionArray(String equation, Object[] nameOfVariables, String nameOfEquation, Object[] hashOfEquation) {

        this.equation = equation;
        this.nameOfVariables = nameOfVariables;
        this.nameOfEquation = nameOfEquation;
        this.hashOfEquation = hashOfEquation;

    }

    public FunctionArray(String functionString, Object[] functionOfValue){
        this.equation = functionString;
        this.nameOfVariables =  functionOfValue;


    }


    public FunctionArray(String functionString, String[] functionOfValue, int countOfValue, int id){
        this.equation = functionString;
        this.nameOfVariables = new Object[countOfValue];
        this.nameOfVariables =  functionOfValue;
        this.id=id;
    }


    @Override
    public String toString() {
        return "FunctionArray{" +
                "id=" + id +
                ", equation='" + equation + '\'' +
                ", nameOfVariables=" + Arrays.toString(nameOfVariables) +
                ", nameOfEquation='" + nameOfEquation + '\'' +
                ", hashOfEquation=" + Arrays.toString(hashOfEquation) +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getEquation() {
        return equation;
    }

    public Object[] getNameOfVariables() {
        return nameOfVariables;
    }

    public String getNameOfEquation() {
        return nameOfEquation;
    }

    public Object[] getHashOfEquation() {
        return hashOfEquation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public void setNameOfVariables(Object[] nameOfVariables) {
        this.nameOfVariables = nameOfVariables;
    }


    public void setNameOfEquation(String nameOfEquation) {
        this.nameOfEquation = nameOfEquation;
    }

    public void setHashOfEquation(Object[] hashOfEquation) {
        this.hashOfEquation = hashOfEquation;
    }

}
