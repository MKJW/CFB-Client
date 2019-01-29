package com.mksoft.calculateoffunction;

public class TagData {
    String tagName;
    double id;
    double equationId;

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
                ", id=" + id +
                ", equationId=" + equationId +
                '}';
    }
}
