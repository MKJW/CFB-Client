package com.mksoft.mainbutton.DataType;

public class TagData {
    String tagName;
    double id;
    double equationId;

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
