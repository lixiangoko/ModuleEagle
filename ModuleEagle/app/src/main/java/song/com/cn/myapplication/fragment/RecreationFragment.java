package song.com.cn.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import song.com.cn.myapplication.R;
import song.com.cn.myapplication.person.Cat;

/**
 * @author lixiangsong
 * @date 创建时间 : 2017/8/30
 * @Description:
 */

public class RecreationFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment_item, container, false);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Cat cat=new Cat();
        cat.eat();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

}
