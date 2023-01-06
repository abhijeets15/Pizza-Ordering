/**
 * This is the Adapter class for the Topping Activity Recycler
 * @author Abhijeet Singh, Khizar Saud
 */

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

public class ToppingAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Context context;
    List<Item> items;
    private ToppingsRecyclerInterface mToppingsRecyclerInterface;

    public List<Item> getAll(){
        return items;
    }

    /**
     * Constructor for Toppings recycler Interface.
     * @return
     */
    public ToppingsRecyclerInterface getmToppingsRecyclerInterface() {
        return mToppingsRecyclerInterface;
    }

    /**
     * Constructor for Topping Adapter.
     * @param context
     * @param items
     * @param toppingsRecyclerInterface
     */
    public ToppingAdapter(Context context, List<Item> items, ToppingsRecyclerInterface toppingsRecyclerInterface) {
        this.context = context;
        this.items = items;
        this.mToppingsRecyclerInterface = toppingsRecyclerInterface;
    }

    /**
     * Sets all items inthe arrayList of type Items.
     * @param items
     */
    public void setItems(ArrayList<Item> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public List<Item> getItems(){
        return items;
    }

    /**
     * Creates the view holder for Recycler View with the items.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.topping_view_chicago,parent, false), mToppingsRecyclerInterface);
    }

    /**
     * Bind View Holder for the items on the recycler view.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.topping.setText(items.get(position).getTopping());
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

    /**
     * Returns the items in the arraylist.
     * @return type int of the size of arraylist.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Returns the selected Items in the recycler view.
     * @return
     */
    public List<Item> getSelected(){
        ArrayList<Item> selected = new ArrayList<>();
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).isChecked()){
                selected.add(items.get(i));
            }
        }
        return selected;
    }

    /**
     * Returns selected in converted form.
     * @return String array.
     */
    public String[] getSelectedConverted(){
        String [] toppings = new String[getSelected().size()];
        int i = 0;
        for(Item x : getSelected()){
            toppings[i] = x.getTopping();
            i+=1;
        }
        return toppings;
    }
}