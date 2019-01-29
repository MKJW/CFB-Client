package com.mksoft.mainbutton.ViewAllFunction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mksoft.mainbutton.CaculateOfFunction.CaculateOfFunctionFragment;
import com.mksoft.mainbutton.DataType.FunctionArray;
import com.mksoft.mainbutton.DataType.TagData;
import com.mksoft.mainbutton.R;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_function_function_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.functionTextView.setText(items.get(position).getExpression());
        myViewHolder.functionNameTextView.setText(items.get(position).getTitle());
        TagData[] tagData =  items.get(position).getHashtags();
        String tempHash = "";
        if(tagData.length<4){

            for(int i =0;i<tagData.length; i++){
                tempHash += "#"+tagData[i].getTagName()+" ";
            }
            myViewHolder.hashTagTextView.setText(tempHash);
        }else{
            for(int i =0; i<3;i++){
                tempHash += tagData[i].getTagName()+" ";
            }
            myViewHolder.hashTagTextView.setText(tempHash);
        }
        final String finalTempHash = tempHash;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //액티비티 띄우기
                Intent mainIntent = new Intent(context, CaculateOfFunctionFragment.class);
                //add bundle to intent

                mainIntent.putExtra("valList", items.get(position).getNameOfVariables());
                mainIntent.putExtra("title", items.get(position).getTitle());
                mainIntent.putExtra("expression", items.get(position).getExpression());
                mainIntent.putExtra("tagName", finalTempHash);
                //start activity
                context.startActivity(mainIntent);
            }
        });
        //해쉬테그 불러오기...
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}//리스트뷰에 필요한 어뎁터를 만들어주는 공간이다.
