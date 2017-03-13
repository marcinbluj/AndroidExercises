package com.sda.bluj.marcin.androidpart2.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepository;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepositoryInterface;
import com.sda.bluj.marcin.androidpart2.view.widget.ProductDetailsActivity;

import butterknife.BindView;

/**
 * Created by RENT on 2017-03-13.
 */

public class ProductDetailsFragment extends Fragment { //todo

    public static final String INTENT_PRODUCT_ID
            = ProductDetailsActivity.class.getSimpleName() + "productId";
    public static final String EDIT_PRODUCT_ID = "EDIT_PRODUCT_ID";
    public static final String EDIT_ACTION = "EDIT";

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.primary_tv)
    TextView mProductName;

    @BindView(R.id.secondary_tv)
    TextView mProductPrice;

    @BindView(R.id.product_description)
    TextView mProductDescription;

//    Bundle bundle;

    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();

    public static  ProductDetailsFragment newInstance(int id) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_PRODUCT_ID, id);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) { //todo slack
        super.onViewCreated(view, savedInstanceState);


    }

    private void displayProductData(Product product) {
        int drawableResourceId = this.getResources()
                .getIdentifier(product.getImageName(), "drawable", getActivity().getPackageName());
        mProductImage.setImageResource(drawableResourceId);
        mProductName.setText(product.getName());
        mProductPrice.setText(String.valueOf(product.getPrice()));
        mProductDescription.setText(product.getDescription());
    }
}
