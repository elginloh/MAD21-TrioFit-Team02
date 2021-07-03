package sg.edu.triofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private final List<Category> categoryList;

    public CategoryAdapter(List<Category> categoryList)
    {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view_holder, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder catHolder, int position){
        Category category = categoryList.get(position);
        catHolder.categoryName.setText(category.getCategoryName());
        catHolder.setCategoryList(category.getCategoryList());
    }

    @Override
    public int getItemCount()
    {
        return categoryList.size();
    }
}
