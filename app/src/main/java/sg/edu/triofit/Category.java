package sg.edu.triofit;

import java.util.ArrayList;

public class Category {
    private String CategoryName;
    private ArrayList<String> Category = new ArrayList();

    public Category() {
    }

//    public Category(String categoryName, ArrayList<String> category) {
//        this.CategoryName = categoryName;
//        for(int i=0; i < category.size(); i ++)
//        {
//            this.Category.add(category.get(i));
//        }
//    }


    public Category(String categoryName, ArrayList<String> category) {
        CategoryName = categoryName;
        this.Category = category;
        for(int i=0; i < category.size(); i ++)
        {
            this.Category.add(category.get(i));
        }

    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public ArrayList<String> getCategory() {
        return Category;
    }

    public void setCategory(ArrayList<String> categoryList) {
        Category = categoryList;
    }
}
