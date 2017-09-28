package song.com.cn.myapplication.fold;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import song.com.cn.myapplication.R;

/**
 * @author lixiangsong
 * @date 创建时间 : 2017/9/28
 * @Description:
 */

public class MyAdapter extends BaseExpandableListAdapter {
    private List<String> groupList;//外层的数据源
    private List<List<String>> childList;//里层的数据源
    private Context context;
    private List<String> twoChild;
    public MyAdapter(Context context, List<String> groupList, List<List<String>> child,List<String> twoChild) {
        this.context = context;
        this.groupList = groupList;
        this.childList = child;
        this.twoChild = twoChild;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    /**
     * 这个返回的一定要是对应外层的item里面的List集合的size
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.map_group_item, null);
        //分组名字
        TextView textView = (TextView) convertView.findViewById(R.id.group_textview);
        //子元素的个数
        TextView numbe2r = (TextView) convertView.findViewById(R.id.group_textview2);
        textView.setText(twoChild.get(0));
        numbe2r.setText(twoChild.get(1));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.map_content_child_item, null);
        TextView textView = (TextView) view.findViewById(R.id.child_name);
        //外层的分组名字
        textView.setText(childList.get(groupPosition).get(childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
