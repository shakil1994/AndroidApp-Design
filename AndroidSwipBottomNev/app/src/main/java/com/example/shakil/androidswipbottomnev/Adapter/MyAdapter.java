package com.example.shakil.androidswipbottomnev.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shakil.androidswipbottomnev.R;

import java.util.List;

/**
 * Created by Shakil on 4/11/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    List<String> stringList;

    public MyAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_item_view.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_item_view;

        public ViewHolder(View itemView) {
            super(itemView);

            txt_item_view = itemView.findViewById(R.id.txtItemName);

        }
    }
}
