/**
 * This is the Adapter class for the Pizza Item Activity Spinner
 * @author Abhijeet Singh, Khizar Saud
 */

package com.example.rupizza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rupizza.Pizza;
import com.example.rupizza.StoreOrder;

import java.util.ArrayList;

public class PizzaItemAdapter extends RecyclerView.Adapter<PizzaItemAdapter.ItemsHolder>{

    private Context context;
    private ArrayList<Pizza> listofPizzas;

    public PizzaItemAdapter(Context context, ArrayList<Pizza> pizzas) {
        this.context = context;
        this.listofPizzas = pizzas;
    }


    /**
     * Creates the veiw holder for the PIzza Items
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lINF = LayoutInflater.from(context);
        View view = lINF.inflate(R.layout.activitypizza, parent, false);

        return new ItemsHolder(view);
    }


    /**
     * Bind View Holder for binding the view in the spinner.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        Pizza pzza1 = listofPizzas.get(position);
        holder.textViewPizza.setText(pzza1.toString());
        holder.imageViewDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreOrder.getCurOrder().getPizzas().remove(pzza1);
                listofPizzas.remove(pzza1);
                notifyDataSetChanged();
                CartActivity.update();
            }
        });
    }


    /**
     * Gets the item count of the list of pizzas in arrayList.
     * @return type int of size.
     */
    @Override
    public int getItemCount() {
        return listofPizzas.size();
    }


    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView textViewPizza;
        private ImageView imageViewDel;

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            textViewPizza = itemView.findViewById(R.id.tvPizza);
            imageViewDel = itemView.findViewById(R.id.ivDelete);
        }
    }


}