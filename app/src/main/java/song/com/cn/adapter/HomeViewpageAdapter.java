package song.com.cn.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import java.util.List;
import song.com.cn.R;


/**
 * @author lixiangsong
 * @date 创建时间 : 2017/8/10
 * @Description:
 */

public class HomeViewpageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fList;
    private String[] tab;
    private Context mContext;

    public HomeViewpageAdapter(FragmentManager fm, List<Fragment> fragments, String[] title, Context context) {
        super(fm);
        this.fList = fragments;
        this.tab = title;
        this.mContext = context;
    }

    public View getTabView(int position) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.activity_tablayout, null);
        TextView tabTitle = (TextView) tabView.findViewById(R.id.tv_tab_title);
        tabTitle.setText(tab[position]);
        return tabView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fList.get(position);
    }

    @Override
    public int getCount() {
        return fList.size();
    }
}
