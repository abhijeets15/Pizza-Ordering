package com.example.rupizza;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderNY extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView imageView;
    TextView topping;
    View view;
    ToppingsRecyclerInterfaceNY toppingsRecyclerInterfaceny;

    public MyViewHolderNY(@NonNull View itemView, ToppingsRecyclerInterfaceNY toppingsRecyclerInterfaceny) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageviewNYTopping);
        topping = itemView.findViewById(R.id.toppingChoiceNYTopping);
        view = itemView;
        this.toppingsRecyclerInterfaceny = toppingsRecyclerInterfaceny;

        itemView.setOnClickListener(this);
    }

    public void bind(final ItemNY item){
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
        toppingsRecyclerInterfaceny.onItemCLick(getAdapterPosition());
    }
}
