package com.bookstore.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ShoppingCartFragment extends Fragment {
    public interface ItemListener {
        public void itemSelected(ShoppingCartItem cartItem);
    }

    private ShoppingCartActivity mActivity = null;

    public ShoppingCartFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ShoppingCartFragment.ItemListener){
            this.mActivity = (ShoppingCartActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView mRecyclerView = view.findViewById(R.id.shopping_cart_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<ShoppingCartItem> items = Model.getInstance(mActivity).getShoppingCart().getItems();

        RecyclerView.Adapter adapter = new ShoppingCartAdapter(mActivity, items);
        mRecyclerView.setAdapter(adapter);
    }
}
