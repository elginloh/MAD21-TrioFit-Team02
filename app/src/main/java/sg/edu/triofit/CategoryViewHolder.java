package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private final ActivityAdapter adapter;

    final TextView categoryName;
    final RecyclerView rvCategory;
    final List<String> categoryList;
//    Context context;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryName = itemView.findViewById(R.id.Category);
        rvCategory = itemView.findViewById(R.id.recyclerView);

        categoryList = new ArrayList<>();
        adapter = new ActivityAdapter(categoryList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);

        rvCategory.setLayoutManager(layoutManager);
        rvCategory.setAdapter(adapter);
    }

    void setCategoryList(List<String> categoryList){
        this.categoryList.clear();
        this.categoryList.addAll(categoryList);
        adapter.notifyDataSetChanged();
    }
}