package com.sda.bluj.marcin.androidpart2.adapters;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sda.bluj.marcin.androidpart2.AndroidApplication;
import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.view.MainActivity;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductCardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MSI on 16.03.2017.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter implements ProductCardView.ProductCardViewInterface { //todo adapter

    private List<Product> mProducts = new ArrayList<>();

    private ProductCardView.ProductCardViewInterface mListener;

    public ProductRecyclerAdapter(List<Product> products, ProductCardView.ProductCardViewInterface listener) {
        this.mProducts.addAll(products);
        this.mListener = listener;
    }

    @Override
    public void onProductClicked(Product product) {
        if (mListener != null) {
            mListener.onProductClicked(product);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final ProductCardView view = new ProductCardView(context);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Product product = getItem(position);
        ((ProductViewHolder) holder).bind(product);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public Product getItem(int position) {
        return mProducts.get(position);
    }

    public void swapData(List<Product> products) {
        if (products != null) {
            mProducts.clear();
            mProducts.addAll(products);
            notifyDataSetChanged();
        }
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder {

        public ProductViewHolder(View pItem) {
            super(pItem);
        }

        public void bind(Product product) {
            ((ProductCardView) itemView).bindTo(product, ProductRecyclerAdapter.this);
        }
    }
}
