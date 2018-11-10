package com.example.shakil.firebaserclmultipletype.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shakil.firebaserclmultipletype.Interface.IItemClickListener;
import com.example.shakil.firebaserclmultipletype.R;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView txt_item_text, txt_child_item_text;
    public RelativeLayout button;
    public ExpandableLinearLayout expandableLinearLayout;

    IItemClickListener iItemClickListener;

    public void setiItemClickListener(IItemClickListener iItemClickListener) {
        this.iItemClickListener = iItemClickListener;
    }

    public ItemViewHolder(@NonNull View itemView, boolean isExpandable) {
        super(itemView);

        if (isExpandable) {
            txt_item_text = itemView.findViewById(R.id.txt_item_text);
            txt_child_item_text = itemView.findViewById(R.id.txt_child_item_text);

            button = itemView.findViewById(R.id.button);

            expandableLinearLayout = itemView.findViewById(R.id.expandableLayout);
        }
        else {
            txt_item_text = itemView.findViewById(R.id.txt_item_text);
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iItemClickListener.onClick(v, getAdapterPosition());
            }
        });
    }
}
