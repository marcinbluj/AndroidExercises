package com.sda.bluj.marcin.androidpart2.view.widget;

import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepository;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepositoryInterface;

import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListFragment extends Fragment implements ProductCardView.ProductCardViewInterface {

    @BindDimen(R.dimen.product_list_item_height)
    int mProductListItemHeight;
    @BindView(R.id.line1)
    LinearLayout mContainer;

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

    private void displayData() {
        List<Product> products = mProductRepository.getProducts();

        for (Product product : products) {

            ProductCardView cardView = new ProductCardView(getActivity());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cardView.setStateListAnimator(AnimatorInflater.loadStateListAnimator(getActivity(), R.animator.card_view_translation_z));
            }

            int height = mProductListItemHeight;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

//            int margin = getPixelsFromDp(4);
//            params.setMargins(margin, margin, margin, margin);
            cardView.setLayoutParams(params);
            cardView.bindTo(product, this);

            mContainer.addView(cardView);
        }
    }

    @Override
    public void onProductClicked(Product product) {
        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.INTENT_PRODUCT_ID, product.getId());
        startActivity(intent);

        Log.d("Shop", "Product clicked: " + product.getName());
    }
}
