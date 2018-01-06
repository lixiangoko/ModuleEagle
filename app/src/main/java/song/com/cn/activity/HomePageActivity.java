package song.com.cn.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import song.com.cn.R;
import song.com.cn.fragment.HomeFragment;
import song.com.cn.fragment.RecreationFragment;
import song.com.cn.fragment.SetFragment;
import song.com.cn.fragment.ShopFragment;

/**
 * @author lixiang
 * @date :2017/10/20
 * @Description:
 */
public class HomePageActivity extends FragmentActivity implements View.OnClickListener {
    private RadioButton shouye, maimai, chat, shop, geren;
    private FragmentManager fManager;
    //第一个
    private HomeFragment homeFragment;
    //第二个
    private RecreationFragment recreationFragment;
    //第三个
    private SetFragment setFragment;
    //第四个
    private ShopFragment shopFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        shouye = (RadioButton) findViewById(R.id.shou_ye_rb);
        maimai = (RadioButton) findViewById(R.id.shou_ye_rb1);
        shop = (RadioButton) findViewById(R.id.shou_ye_rb3);
        geren = (RadioButton) findViewById(R.id.shou_ye_rb4);
        fManager = getSupportFragmentManager();
        initOnClick();
    }

    private void initOnClick() {
        shouye.setOnClickListener(this);
        maimai.setOnClickListener(this);
        shop.setOnClickListener(this);
        geren.setOnClickListener(this);
        //开启事务，fragment的控制是由事务来实现的
        initFragment();
    }

    /**
     * 显示第一个fragment
     */
    private void initFragment() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = fManager.beginTransaction();
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            transaction.add(R.id.main_frame_layout, homeFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(homeFragment);
        //提交事务
        transaction.commit();
    }

    /**
     * 隐藏所有的fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (recreationFragment != null) {
            transaction.hide(recreationFragment);
        }
        if (shopFragment != null) {
            transaction.hide(shopFragment);
        }
        if (setFragment != null) {
            transaction.hide(setFragment);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fManager.beginTransaction();
        //隐藏所有fragment
        hideFragment(transaction);
        switch (v.getId()) {
            case R.id.shou_ye_rb:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_frame_layout, homeFragment);
                }
                //显示需要显示的fragment
                transaction.show(homeFragment);
                break;
            case R.id.shou_ye_rb1:
                if (recreationFragment == null) {
                    recreationFragment = new RecreationFragment();
                    transaction.add(R.id.main_frame_layout, recreationFragment);
                }

                //显示需要显示的fragment
                transaction.show(recreationFragment);
                break;
            case R.id.shou_ye_rb3:
                if (shopFragment == null) {
                    shopFragment = new ShopFragment();
                    transaction.add(R.id.main_frame_layout, shopFragment);
                }
                //显示需要显示的fragment
                transaction.show(shopFragment);
                break;
            case R.id.shou_ye_rb4:
                if (setFragment == null) {
                    setFragment = new SetFragment();
                    transaction.add(R.id.main_frame_layout, setFragment);
                }
                //显示需要显示的fragment
                transaction.show(setFragment);
                break;
            default:
        }
        transaction.commit();
    }
}
