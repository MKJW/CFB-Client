package com.mksoft.viewallfunction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FunctionArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView functionTextView;
        TextView functionNameTextView;
        TextView hashTagTextView;

        MyViewHolder(View view){
            super(view);
            functionTextView = view.findViewById(R.id.functionTextView);
            functionNameTextView = view.findViewById(R.id.functionNameTextView);
            hashTagTextView = view.findViewById(R.id.hashTagTextView);
        }
    }
    ArrayList<FunctionArray> items;
    Context context;
    public FunctionArrayAdapter(Context context, ArrayList<FunctionArray> items){
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.function_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.functionTextView.setText(items.get(position).getExpression());
        myViewHolder.functionNameTextView.setText(items.get(position).getTitle());
        TagData[] tagData =  items.get(position).getHashtags();
        if(tagData.length<4){
            String tempHash = "";
            for(int i =0;i<tagData.length; i++){
                tempHash += "#"+tagData[i].getTagName()+" ";
            }
            myViewHolder.hashTagTextView.setText(tempHash);
        }else{
            String tempHash = "";
            for(int i =0; i<3;i++){
                tempHash += tagData[i].getTagName()+" ";
            }
            myViewHolder.hashTagTextView.setText(tempHash);
        }
        //해쉬테그 불러오기...
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}//리스트뷰에 필요한 어뎁터를 만들어주는 공간이다.
