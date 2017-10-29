package song.com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import song.com.cn.R;


/**
 * @author lixiangsong
 * @date 创建时间 : 2017/8/30
 * @Description:
 */

public class SetFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_fragment_item, container, false);
        return view;
    }
}
