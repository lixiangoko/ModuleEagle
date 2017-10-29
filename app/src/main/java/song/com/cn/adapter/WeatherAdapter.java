package song.com.cn.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import song.com.cn.R;
import song.com.cn.bean.WeatherBean;

/**
 * @author lixiangsong
 * @date 创建时间 : 2017/9/7
 * @Description: 天气预报
 */

public class WeatherAdapter extends BaseQuickAdapter<WeatherBean.ResultsBean, BaseViewHolder> {

    public WeatherAdapter(List<WeatherBean.ResultsBean> data) {
        super(R.layout.weather_home_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, WeatherBean.ResultsBean resultsBean) {
        WeatherBean.ResultsBean.NowBean e = resultsBean.getNow();
        baseViewHolder.setText(R.id.name_adree_tv, resultsBean.getLocation().getName());//地名
        baseViewHolder.setText(R.id.time_tv, resultsBean.getLast_update());
        baseViewHolder.setText(R.id.text_tv, e.getText());
        ImageView im = baseViewHolder.getView(R.id.image_iv);
    }


}
