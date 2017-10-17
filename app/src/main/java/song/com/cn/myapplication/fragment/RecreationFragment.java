package song.com.cn.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import song.com.cn.myapplication.R;
import song.com.cn.myapplication.fold.MyAdapter;

/**
* @date :2017/10/17
* @author lixiang
* @Description:
*/
public class RecreationFragment extends Fragment {
    private ExpandableListView expandableListView;
    private MyAdapter myAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment_item, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        expandableListView = (ExpandableListView) view.findViewById(R.id.expendablelistview);
        addData("幼稚园同学", new String[]{"满20减5", "折扣商品8.8折起 ", "消费满100送咖啡2杯", "可开发票/增值型发票", "新用户立减3元", "08:10~09:10 可享受9折优惠"});
    }

    /**
     * 用来添加数据的方法
     */
    private void addData(String group, String[] friend) {
        List<String> groupList = new ArrayList<>();
        List<List<String>> childList = new ArrayList<>();
        List<String> chi = new ArrayList<String>();
        groupList.add(group);
        //每一个item打开又是一个不同的list集合
        List<String> childitem = new ArrayList<>();
        for (int i = 0; i < friend.length; i++) {
            if (i > 1) {
                childitem.add(friend[i]);
            } else {
                chi.add(friend[i]);
            }
        }
        childList.add(childitem);
        myAdapter = new MyAdapter(getActivity(), groupList, childList, chi);
        expandableListView.setAdapter(myAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
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
