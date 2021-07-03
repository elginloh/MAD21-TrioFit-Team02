package sg.edu.triofit;

import java.util.ArrayList;

public class Category {
    private String CategoryName;
    private ArrayList<String> CategoryList = new ArrayList();

    public Category() {
    }

    public Category(String categoryName, ArrayList<String> categoryList) {
        this.CategoryName = categoryName;
        for(int i=0; i < categoryList.size(); i ++)
        {
            this.CategoryList.add(categoryList.get(i));
        }
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public ArrayList<String> getCategoryList() {
        return CategoryList;
    }

    public void setCategoryList(ArrayList<String> categoryList) {
        CategoryList = categoryList;
    }
}
