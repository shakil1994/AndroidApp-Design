package com.example.shakil.firebaserclmultipletype;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.shakil.firebaserclmultipletype.Interface.IItemClickListener;
import com.example.shakil.firebaserclmultipletype.Model.Item;
import com.example.shakil.firebaserclmultipletype.ViewHolder.ItemViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Item> items = new ArrayList<>();
    FirebaseRecyclerAdapter<Item, ItemViewHolder> adapter;

    SparseBooleanArray expandState = new SparseBooleanArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init view
        recyclerView = findViewById(R.id.lst_item);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Retrieve Data
        retrieveData();

        setData();
    }

    private void setData() {
        Query query = FirebaseDatabase.getInstance().getReference().child("Items");
        FirebaseRecyclerOptions<Item> options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(query, Item.class).build();

        adapter = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(options) {

            @Override
            public int getItemViewType(int position) {
                if (items.get(position).isExpandable()) {
                    return 1;
                } else {
                    return 0;
                }
            }

            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder holder, final int position, @NonNull final Item model) {
                switch (holder.getItemViewType()) {
                    //Without item
                    case 0: {
                        ItemViewHolder viewHolder = holder;
                        viewHolder.setIsRecyclable(false);
                        viewHolder.txt_item_text.setText(model.getText());

                        //Event
                        viewHolder.setiItemClickListener(new IItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Toast.makeText(MainActivity.this, "" + items.get(position).getText(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;
                    //With item
                    case 1: {
                        final ItemViewHolder viewHolder = holder;
                        viewHolder.setIsRecyclable(false);
                        viewHolder.txt_item_text.setText(model.getText());

                        //Because we use Recycler View so we need one Expandable Linear layout
                        viewHolder.expandableLinearLayout.setInRecyclerView(true);
                        viewHolder.expandableLinearLayout.setExpanded(expandState.get(position));
                        viewHolder.expandableLinearLayout.setListener(new ExpandableLayoutListenerAdapter() {
                            @Override
                            public void onPreOpen() {
                                changeRotate(viewHolder.button, 0f, 180f).start();
                                expandState.put(position, true);
                            }

                            @Override
                            public void onPreClose() {
                                changeRotate(viewHolder.button, 180f, 0f).start();
                                expandState.put(position, false);
                            }
                        });

                        viewHolder.button.setRotation(expandState.get(position)?180f:0f);
                        viewHolder.button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                viewHolder.expandableLinearLayout.toggle();
                            }
                        });

                        viewHolder.txt_child_item_text.setText(model.getSubText());
                        viewHolder.txt_child_item_text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "" + viewHolder.txt_child_item_text.getText(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        //Set Event
                        viewHolder.setiItemClickListener(new IItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Toast.makeText(MainActivity.this, "" + model.getText(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;
                    default:
                        break;
                }
            }

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                if (viewType == 0) {
                    View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_without_child, viewGroup, false);
                    return new ItemViewHolder(itemView, viewType == 1);
                } else {
                    View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_with_child, viewGroup, false);
                    return new ItemViewHolder(itemView, viewType == 1);
                }
            }
        };

        //Init SparseArray , all view is no expandable
        expandState.clear();
        for (int i = 0; i<items.size(); i++){
            expandState.append(i, false);
        }

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private ObjectAnimator changeRotate(RelativeLayout button, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    private void retrieveData() {
        items.clear();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Items");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    Item item = itemSnapshot.getValue(Item.class);
                    items.add(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("ERROR", "" + databaseError.getMessage());
            }
        });
    }

    @Override
    protected void onStart() {
        if (adapter != null){
            adapter.startListening();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (adapter != null){
            adapter.stopListening();
        }
        super.onStop();
    }
}
