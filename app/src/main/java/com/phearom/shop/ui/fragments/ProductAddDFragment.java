package com.phearom.shop.ui.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.phearom.shop.R;
import com.phearom.shop.api.respositories.base.RealmHelper;
import com.phearom.shop.databinding.FragmentProductAddBinding;
import com.phearom.shop.mapper.ProductMapper;
import com.phearom.shop.models.Product;
import com.phearom.shop.ui.activities.ProductActivity;
import com.phearom.shop.viewmodels.product.ProductViewModel;

import java.util.UUID;

/**
 * Created by phearom on 3/21/16.
 */
public class ProductAddDFragment extends DialogFragment {
    private static final int REQUEST_PICK_IMAGE = 222;

    private FragmentProductAddBinding mBinding;
    private ProductViewModel mModel;

    private String mFilePath;

    private ProductActivity productActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        productActivity = (ProductActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_add, container, false);
        initData();
        mModel = new ProductViewModel(new Product());
        mBinding.setModel(mModel);
        mBinding.setView(this);

        return mBinding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppTheme_NoActionBar);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme_NoActionBar);
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PICK_IMAGE:
                    mFilePath = data.getDataString();
                    Glide.with(getContext()).load(mFilePath).override(100, 100).centerCrop().into(mBinding.imvProImage);
                    break;
                case 00:
                    break;
            }
        } else {
            Toast.makeText(getContext(), "Something wrong", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.imvProImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productActivity.pickImage(REQUEST_PICK_IMAGE);
            }
        });

        mBinding.btnProSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product();
                product.setId(UUID.randomUUID().toString());
                product.setUrl(mFilePath);
                product.setName(mBinding.edtProName.getText().toString());
                product.setCurrency(mBinding.spnProCurrency.getSelectedItemPosition());
                product.setPrice(Double.valueOf(mBinding.edtProPrice.getText().toString()));
                product.setDescription(mBinding.edtProDescription.getText().toString());
                RealmHelper.init(getContext()).addObject(ProductMapper.mapperProduct(product));
                productActivity.loadData();
                dismiss();
            }
        });
    }

    private void initData() {
        mBinding.toolbar.setTitle("Add Product");
        mBinding.toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }
}
