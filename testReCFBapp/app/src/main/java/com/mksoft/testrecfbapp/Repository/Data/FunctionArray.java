package com.mksoft.testrecfbapp.Repository.Data;


import java.io.Serializable;
import java.util.Arrays;

public class FunctionArray  implements Serializable {
    int id = 0;
    private String expression = null;
    Object[] nameOfVariables = null;
    String title = null;
    TagData[] hashTags = null;


    public FunctionArray(String equation, Object[] nameOfVariables, String nameOfEquation, TagData[] hashOfEquation) {

        this.expression = equation;
        this.nameOfVariables = nameOfVariables;
        this.title = nameOfEquation;
        this.hashTags =  hashOfEquation;

    }

    public FunctionArray(String functionString, Object[] functionOfValue){
        this.expression = functionString;
        this.nameOfVariables =  functionOfValue;


    }


    public FunctionArray(String functionString, String[] functionOfValue, int countOfValue, int id){
        this.expression = functionString;
        this.nameOfVariables = new Object[countOfValue];
        this.nameOfVariables =  functionOfValue;
        this.id=id;
    }

    @Override
    public String toString() {
        return "FunctionArray{" +
                "id=" + id +
                ", expression='" + expression + '\'' +
                ", nameOfVariables=" + Arrays.toString(nameOfVariables) +
                ", title='" + title + '\'' +
                ", hashTags=" + Arrays.toString(hashTags) +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getExpression() {
        return expression;
    }

    public Object[] getNameOfVariables() {
        return nameOfVariables;
    }



    public TagData[] getHashtags() {
        return hashTags;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setNameOfVariables(Object[] nameOfVariables) {
        this.nameOfVariables = nameOfVariables;
    }


    public void setHashtags(Object[] hashTags) {
        this.hashTags = (TagData[])hashTags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
