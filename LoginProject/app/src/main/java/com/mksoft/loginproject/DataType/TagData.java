package com.mksoft.loginproject.DataType;

import java.io.Serializable;

public class TagData implements Serializable {
    String tagName;


    public TagData(String tagName){
        this.tagName = tagName;
    }
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "TagData{" +
                "tagName='" + tagName + '\'' +

                '}';
    }
}
