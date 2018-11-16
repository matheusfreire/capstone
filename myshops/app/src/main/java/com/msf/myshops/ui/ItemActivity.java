package com.msf.myshops.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.msf.myshops.R;
import com.msf.myshops.model.Item;
import com.msf.myshops.model.Shop;
import com.msf.myshops.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemActivity extends AppCompatActivity implements NewItemFragment.OnNewItemListener {

    public static final String KEY_NEW_ITEM_FRAG = "NEW_ITEM_FRAGMENT";
    private ItemsFragment itemsFragment;

    @BindView(R.id.toolbar_item)
    Toolbar mToolbarItem;

    private Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbarItem);
        setTitleToolbar(getString(R.string.items));
        shop = getIntent().getParcelableExtra(Constants.SHOP.getKey());
    }

    public void setTitleToolbar(String title){
        mToolbarItem.setTitle(title);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(shop != null){
            itemsFragment = ItemsFragment.newInstance(shop.getItemList());
        } else {
            itemsFragment = new ItemsFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.item_container, itemsFragment).commit();
    }

    @Override
    public void onNewItemSave(Item item) {
        setTitleToolbar(getString(R.string.items));
//        getSupportFragmentManager().beginTransaction().replace(R.id.item_container, itemsFragment).commit();
        if(shop == null){
            shop = new Shop();
        }
        itemsFragment.addItemOnAdapter(shop,item);
    }

    public void addNewItem() {
        NewItemFragment newItemFragment = NewItemFragment.newInstance(this);
        setTitleToolbar(getString(R.string.add_new_item));
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.para_esquerda_entra, R.anim.para_esquerda_sai, R.anim.para_direita_entra, R.anim.para_direita_sai)
                .replace(R.id.item_container, newItemFragment).addToBackStack(KEY_NEW_ITEM_FRAG).commit();
    }
}
