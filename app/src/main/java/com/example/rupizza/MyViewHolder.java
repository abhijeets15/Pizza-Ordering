/**
 * This is view holder class for making the veiw of the Toppings class.
 * @author Abhijeet Singh, Khizar Saud
 */

package com.example.rupizza;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView imageView;
    TextView topping;
    View view;
    ToppingsRecyclerInterface toppingsRecyclerInterface;

    /**
     * View Holder constructor
     * @param itemView view of the item of type View
     * @param toppingsRecyclerInterface Uses interface for topping Recycler.
     */
    public MyViewHolder(@NonNull View itemView, ToppingsRecyclerInterface toppingsRecyclerInterface) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview1);
        topping = itemView.findViewById(R.id.toppingChoice1);
        view = itemView;
        this.toppingsRecyclerInterface = toppingsRecyclerInterface;

        itemView.setOnClickListener(this);
    }

    /**
     * For binding the image veiws and items all together in the recylcer view.
     * @param item
     */
    public void bind(final Item item){
        imageView.setVisibility(item.isChecked() ? View.VISIBLE : View.GONE);
        topping.setText(item.getTopping());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setChecked(!item.isChecked());
                imageView.setVisibility(item.isChecked() ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        toppingsRecyclerInterface.onItemCLick(getAdapterPosition());
    }
}
