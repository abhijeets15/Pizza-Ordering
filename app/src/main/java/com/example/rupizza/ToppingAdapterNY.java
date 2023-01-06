package com.example.rupizza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ToppingAdapterNY extends RecyclerView.Adapter<MyViewHolderNY> {


    Context context;
    List<ItemNY> items;
    private ToppingsRecyclerInterfaceNY mToppingsRecyclerInterfaceny;

    public List<ItemNY> getAll(){
        return items;
    }

    public ToppingsRecyclerInterfaceNY getmToppingsRecyclerInterface() {
        return mToppingsRecyclerInterfaceny;
    }

    public ToppingAdapterNY(Context context, List<ItemNY> items, ToppingsRecyclerInterfaceNY toppingsRecyclerInterfaceny) {
        this.context = context;
        this.items = items;
        this.mToppingsRecyclerInterfaceny = toppingsRecyclerInterfaceny;
    }

    public void setItems(ArrayList<ItemNY> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public List<ItemNY> getItems(){
        return items;
    }

    @NonNull
    @Override
    public MyViewHolderNY onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderNY(LayoutInflater.from(context).inflate(R.layout.topping_view_chicago,parent, false), mToppingsRecyclerInterfaceny);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNY holder, int position) {
        final ItemNY item = items.get(position);
        System.out.println(item.getTopping());
        holder.topping.setText(items.get(position).getTopping());
        System.out.println("testinggg");
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.view.setBackgroundColor(item.isChecked() ? Color.CYAN : Color.WHITE);
        holder.topping.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (getSelected().size() < 7) {
                    item.setChecked(!item.isChecked());
                    holder.view.setBackgroundColor(item.isChecked() ? Color.CYAN : Color.WHITE);
                }
                else{
                    Toast.makeText(view.getContext(),
                            "Can't pick more than 7 toppings!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (getSelected().size() < 7) {
                    item.setChecked(!item.isChecked());
                    holder.view.setBackgroundColor(item.isChecked() ? Color.CYAN : Color.WHITE);
                }
                else{
                    Toast.makeText(view.getContext(),
                            "Can't pick more than 7 toppings!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (getSelected().size() < 7) {
                    item.setChecked(!item.isChecked());
                    holder.view.setBackgroundColor(item.isChecked() ? Color.CYAN : Color.WHITE);
                }
                else{
                    Toast.makeText(view.getContext(),
                            "Can't pick more than 7 toppings!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<ItemNY> getSelected(){
        ArrayList<ItemNY> selected = new ArrayList<>();
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).isChecked()){
                selected.add(items.get(i));
            }
        }
        return selected;
    }

    public String[] getSelectedConverted(){
        String [] toppings = new String[getSelected().size()];
        int i = 0;
        for(ItemNY x : getSelected()){
            toppings[i] = x.getTopping();
            i+=1;
        }
        return toppings;
    }
}