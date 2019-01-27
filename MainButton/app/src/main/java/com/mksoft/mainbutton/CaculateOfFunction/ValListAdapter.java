package com.mksoft.mainbutton.CaculateOfFunction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mksoft.mainbutton.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ValListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView valTextView;
        EditText valEditText;

        MyViewHolder(View view){
            super(view);
            valTextView = view.findViewById(R.id.valTextView);
            valEditText = view.findViewById(R.id.valEditText);


        }
    }
    Object[] items;
    Context context;
    EditText[] valEditText;
    int itemsSize;
    public ValListAdapter(Context context, Object[] items, int itemsSize){
        this.context = context;
        this.items = items;
        this.itemsSize = itemsSize;
        this.valEditText = new EditText[itemsSize];
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.caculate_of_function_val_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.valTextView.setText((String)items[position] +" : ");
        valEditText[position] = myViewHolder.valEditText;
    }

    @Override
    public int getItemCount() {
        return this.itemsSize;
    }
    public String getEditText(int position){
        return this.valEditText[position].getText().toString();
    }
}//리스트뷰에 필요한 어뎁터를 만들어주는 공간이다.
