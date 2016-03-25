package com.phearom.shop.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.phearom.shop.BR;
import com.phearom.shop.R;
import com.phearom.shop.api.bindapi.binder.CompositeItemBinder;
import com.phearom.shop.api.bindapi.binder.ItemBinder;
import com.phearom.shop.api.bindapi.listener.ClickHandler;
import com.phearom.shop.api.bindapi.listener.LongClickHandler;
import com.phearom.shop.api.respositories.ProductDao;
import com.phearom.shop.api.respositories.base.RealmHelper;
import com.phearom.shop.binder.ProductBinder;
import com.phearom.shop.databinding.ActivityProductBinding;
import com.phearom.shop.mapper.ProductMapper;
import com.phearom.shop.ui.ShopBaseActivity;
import com.phearom.shop.ui.fragments.ProductAddDFragment;
import com.phearom.shop.viewmodels.product.ProductViewModel;
import com.phearom.shop.viewmodels.product.ProductsViewModel;

import io.realm.RealmResults;

public class ProductActivity extends ShopBaseActivity {
    private ActivityProductBinding mBinding = null;

    private ProductsViewModel mModels;
    private ProductAddDFragment dFragment;
    private RecyclerView.LayoutManager layoutManager;
    private MenuItem menuItemMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_product);
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mModels = new ProductsViewModel();
        mBinding.setModels(mModels);
        mBinding.setView(this);

        mBinding.fabProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dFragment = new ProductAddDFragment();
                dFragment.show(getSupportFragmentManager(), "AddProduct");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        menuItemMode = menu.findItem(R.id.action_layout_toggle);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_layout_toggle) {
            toggleLayout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toggleLayout() {
        if (layoutManager instanceof LinearLayoutManager) {
            layoutManager = new GridLayoutManager(this, 2);
            menuItemMode.setIcon(R.drawable.ic_view_list);
        } else {
            layoutManager = new LinearLayoutManager(this);
            menuItemMode.setIcon(R.drawable.ic_view_grid);
        }
        mBinding.recyclerProduct.setLayoutManager(layoutManager);
        mModels.notifyPropertyChanged(BR.items);
    }

    public ClickHandler<ProductViewModel> clickHandler() {
        return new ClickHandler<ProductViewModel>() {
            @Override
            public void onClick(ProductViewModel viewModel, View v) {

            }
        };
    }

    public LongClickHandler<ProductViewModel> longClickHandler() {
        return new LongClickHandler<ProductViewModel>() {
            @Override
            public void onLongClick(ProductViewModel viewModel, View view) {

            }
        };
    }

    public ItemBinder<ProductViewModel> itemViewBinder() {
        return new CompositeItemBinder<>(new ProductBinder.Product(BR.model, R.layout.item_product));
    }

    public void pickImage(int requestCode) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        startActivityForResult(chooserIntent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != dFragment)
            dFragment.onActivityResult(requestCode, resultCode, data);
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    public void loadData() {
        mModels.items.clear();
        RealmResults<ProductDao> list = RealmHelper.init(this).getObject(ProductDao.class);
        if (mModels.items.size() == list.size()) return;
        for (ProductDao dao : list) {
            mModels.add(new ProductViewModel(ProductMapper.mapperProduct(dao)));
        }
    }
}
