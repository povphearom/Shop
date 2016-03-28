package com.phearom.shop.ui.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.phearom.shop.R;
import com.phearom.shop.api.utils.K;
import com.phearom.shop.databinding.FragmentDetailProductBinding;
import com.phearom.shop.models.Product;

public class DetailProductFragment extends DialogFragment {
    private FragmentDetailProductBinding mBinding;
    private Product mProduct;

    public DetailProductFragment() {
        // Required empty public constructor
    }

    public static DetailProductFragment init(Product product) {
        DetailProductFragment fragment = new DetailProductFragment();
        Bundle args = new Bundle();
        args.putString(K.Product.ID, product.getId());
        args.putString(K.Product.NAME, product.getName());
        args.putString(K.Product.DESCRIPTION, product.getDescription());
        args.putDouble(K.Product.PRICE, product.getPrice());
        args.putInt(K.Product.CURRENCY, product.getCurrency());
        args.putString(K.Product.URL, product.getUrl());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.AppTheme_NoActionBar);
        mProduct = getProduct();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null)
            return;

        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    private Product getProduct() {
        if (getArguments() != null) {
            Product product = new Product();
            product.setId(getArguments().getString(K.Product.ID));
            product.setName(getArguments().getString(K.Product.NAME));
            product.setDescription(getArguments().getString(K.Product.DESCRIPTION));
            product.setPrice(getArguments().getDouble(K.Product.PRICE));
            product.setCurrency(getArguments().getInt(K.Product.CURRENCY));
            product.setUrl(getArguments().getString(K.Product.URL));
            return product;
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_product, container, false);
        mBinding.toolbar.setTitle(mProduct.getName());
        mBinding.toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        mBinding.txvProName.setText(mProduct.getName());
        return mBinding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
