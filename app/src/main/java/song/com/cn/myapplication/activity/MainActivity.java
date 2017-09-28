package song.com.cn.myapplication.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import song.com.cn.myapplication.R;
import song.com.cn.myapplication.adapter.HomeViewpageAdapter;
import song.com.cn.myapplication.fragment.HomeFragment;
import song.com.cn.myapplication.fragment.RecreationFragment;
import song.com.cn.myapplication.fragment.SetFragment;
import song.com.cn.myapplication.fragment.ShopFragment;

public class MainActivity extends BaseActivity {
    private String[] tab = {"首页", "娱乐", "购物", "个人"};
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private List<Fragment> fList = new ArrayList<Fragment>();
    private HomeFragment homeFragment;
    private RecreationFragment mapFragment;
    private SetFragment setFragment;
    private ShopFragment shopFragment;

    private HomeViewpageAdapter homeViewpageAdapter;

    @Override
    public void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        viewPager = sFind(R.id.viewPager);
        tableLayout = sFind(R.id.tab_tl);

        homeFragment = new HomeFragment();
        mapFragment = new RecreationFragment();
        shopFragment = new ShopFragment();
        setFragment = new SetFragment();
        fList.add(homeFragment);
        fList.add(mapFragment);
        fList.add(shopFragment);
        fList.add(setFragment);
    }

    @Override
    public void initData() {
        homeViewpageAdapter = new HomeViewpageAdapter(getSupportFragmentManager(), fList, tab, this);
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(homeViewpageAdapter);
        tableLayout.setupWithViewPager(viewPager);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
