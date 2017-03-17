package com.sda.bluj.marcin.androidpart2.view.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepository;
import com.sda.bluj.marcin.androidpart2.repository.ProductRepositoryInterface;
import com.sda.bluj.marcin.androidpart2.view.AddProductActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 15.03.2017.
 */

public class ProductDetailsFragment extends Fragment {

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.primary_tv)
    TextView mProductName;

    @BindView(R.id.secondary_tv)
    TextView mProductPrice;

    @BindView(R.id.product_description)
    TextView mProductDescription;

    private Bundle bundle;

    private Product currentProduct;

    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bundle = getActivity().getIntent().getExtras();
        if (bundle == null) {
            List<Product> productList = mProductRepository.getProducts();
            if (!productList.isEmpty()) {
                displayProductData(productList.get(0));
            }
            return;
        }

        int productId = bundle.getInt(ProductDetailsActivity.INTENT_PRODUCT_ID);
        Log.d("Shop", "Product id: " + productId);

        Product product = mProductRepository.getProduct(productId);

        displayProductData(product);
    }

    private void displayProductData(Product product) {
        currentProduct = product;

        int drawableResourceId = getActivity().getResources().getIdentifier(product.getImageName(),
                "drawable", getActivity().getPackageName());
        mProductImage.setImageResource(drawableResourceId);
        mProductName.setText(product.getName());
        mProductPrice.setText(String.valueOf(product.getPrice()));
        mProductDescription.setText(product.getDescription());
    }

    @OnClick(R.id.edit_product)
    public void edit(View view) {
        int id = currentProduct.getId();

        Intent intent = new Intent(getActivity(), AddProductActivity.class);
        intent.setAction(ProductDetailsActivity.EDIT_ACTION);
        intent.putExtra(ProductDetailsActivity.EDIT_PRODUCT_ID, id);
        startActivity(intent);
    }

    public void updateProduct(Product product) {
        displayProductData(product);
    }
}
