package com.bookstore.listview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private List<ShoppingCartItem> mItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout mCartItem;
        public TextView mBook;
        public TextView mQuantity;
        public TextView mTotalPrice;
        public Button mAddButton;
        public Button mRemoveButton;

        public ViewHolder(View v) {
            super(v);
            mCartItem = v.findViewById(R.id.list_item_cart);
            mBook = v.findViewById(R.id.book);
            mQuantity = v.findViewById(R.id.quantity);
            mTotalPrice = v.findViewById(R.id.price);
            mAddButton = v.findViewById(R.id.button_add);
            mRemoveButton = v.findViewById(R.id.button_remove);
        }
    }

    private ShoppingCartActivity mActivity;

    public ShoppingCartAdapter(ShoppingCartActivity activity, List<ShoppingCartItem> items) {
        mActivity = activity;
        mItems = items;
    }

    @Override
    public ShoppingCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);
        ShoppingCartAdapter.ViewHolder vh = new ShoppingCartAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ShoppingCartItem c = mItems.get(position);
        holder.mBook.setText(c.getBook().getTitle());
        holder.mQuantity.setText("quantity : " + c.getQuantity());
        holder.mTotalPrice.setText("price : " + c.getTotalPrice());

        holder.mAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int quantity = c.getQuantity();
                c.setQuantity(quantity+1);

                ShoppingCartAdapter.this.notifyDataSetChanged();
            }
        });

        holder.mRemoveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int quantity = c.getQuantity();

                //if quantity >1 else mItems.remove(c)
                if (c.getQuantity() > 1) {
                    c.setQuantity(quantity - 1);
                }
                else {
                    mItems.remove(c);
                }

                ShoppingCartAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }
}