package project.aark.starWars.model;

import java.io.Serializable;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public class DiseaseCategoryModel implements Serializable{

    private String categoryTitle;
    private String categoryName;
    private String asciiValue;

    public DiseaseCategoryModel(String title,String name,String value){
        categoryTitle = title;
        categoryName = name;
        asciiValue = value;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAsciiValue() {
        return asciiValue;
    }

    public void setAsciiValue(String asciiValue) {
        this.asciiValue = asciiValue;
    }
}
