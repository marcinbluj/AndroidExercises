package com.sda.bluj.marcin.androidpart2.view.widget;

import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepository;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepositoryInterface;
import com.sda.bluj.marcin.androidpart2.view.AddProductActivity;
import com.sda.bluj.marcin.androidpart2.view.MainActivity;

import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 14.03.2017.
 */

public class ProductListFragment extends Fragment implements ProductCardView.ProductCardViewInterface{

    @BindDimen(R.dimen.product_cardview_height)
    int mProductCardViewHeight;

    @BindDimen(R.dimen.product_cardview_margin)
    int mProductCardViewMargin;

    @BindView(R.id.line1)
    LinearLayout linearLayout;

    @BindView(R.id.fragment_product_list_layout)
    CoordinatorLayout mRootLayout;

    private ProductRepositoryInterface mProductRepository
            = ProductRepository.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayData();
    }

    @Override
    public void onProductClicked(Product product) {
        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.INTENT_PRODUCT_ID, product.getId());
        startActivity(intent);

        Log.d("Shop", "Product clicked: " + product.getName());
    }

    private void displayData() {
        List<Product> products = mProductRepository.getProducts();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            ProductCardView cardView = new ProductCardView(getActivity());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cardView.setStateListAnimator(AnimatorInflater.loadStateListAnimator(getActivity(), R.animator.card_view_translation_z));
            }

            int height = mProductCardViewHeight;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

            int margin = mProductCardViewMargin;
            params.setMargins(margin, margin, margin, margin);
            cardView.setLayoutParams(params);
            cardView.bindTo(product, this);

            linearLayout.addView(cardView);
        }
    }

    @OnClick(R.id.add_new_product)
    public void onAddProductClicked(View view) {
        Log.d("Shop", "New product click");

        Snackbar snackbar = Snackbar.make(mRootLayout, "New product click", Snackbar.LENGTH_LONG)
                .setAction("Dodaj nowy produkt", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), AddProductActivity.class);
                        startActivity(intent);
                    }
                })
                .setActionTextColor(ContextCompat.getColor(getActivity(), R.color.colorTextColor));

        snackbar.show();
    }
}
