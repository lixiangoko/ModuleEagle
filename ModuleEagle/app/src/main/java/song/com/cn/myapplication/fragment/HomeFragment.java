package song.com.cn.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import song.com.cn.myapplication.R;
import song.com.cn.myapplication.activity.BusinessActivity;
import song.com.cn.myapplication.activity.LongPhotoActivity;
import song.com.cn.myapplication.activity.MapActivity;
import song.com.cn.myapplication.bean.WeatherBean;
import song.com.cn.myapplication.bean.WeatherBean.ResultsBean;
import song.com.cn.myapplication.interfaces.ResponseResult;
import song.com.cn.myapplication.service.ServiceCallback;
import song.com.cn.myapplication.utils.CommonUtils;
import song.com.cn.myapplication.utils.ServiceUrl;

/**
 * @author lixiangsong
 * @date 创建时间 : 2017/8/30
 * @Description:
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    private ImageView button_map;
    private Button cha_tian_qi;
    private EditText content_et;
    private TextView name, time_tv, text_tv;
    private ImageView imageView;
    private TextView longPhoto, business_tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_item, container, false);
        button_map = (ImageView) view.findViewById(R.id.button_map);
        cha_tian_qi = (Button) view.findViewById(R.id.cha_tian_qi);
        content_et = (EditText) view.findViewById(R.id.content_et);
        name = (TextView) view.findViewById(R.id.name_adree_tv);
        time_tv = (TextView) view.findViewById(R.id.time_tv);
        text_tv = (TextView) view.findViewById(R.id.text_tv);
        imageView = (ImageView) view.findViewById(R.id.image_iv);
        longPhoto = (TextView) view.findViewById(R.id.button_long);
        business_tv = (TextView) view.findViewById(R.id.business_tv);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        cha_tian_qi.setOnClickListener(this);
        longPhoto.setOnClickListener(this);
        button_map.setOnClickListener(this);

        getData("深圳");
    }

    private void setUI(ResultsBean resultsBean) {
        name.setText(resultsBean.getLocation().getName());
        time_tv.setText(resultsBean.getLast_update());
        text_tv.setText(resultsBean.getNow().getText());
        int possion = Integer.valueOf(resultsBean.getNow().getCode());
        setBg(possion);
    }

    private void setBg(int code) {
        switch (code) {
            case 0:
                imageView.setImageResource(R.mipmap.a);
                break;
            case 1:
                imageView.setImageResource(R.mipmap.b);
                break;
            case 2:
                imageView.setImageResource(R.mipmap.jk);
                break;
            case 3:
                imageView.setImageResource(R.mipmap.d);
                break;
            case 4:
                imageView.setImageResource(R.mipmap.e);
                break;
            case 7:
            case 8:
                imageView.setImageResource(R.mipmap.i);
                break;
            case 9:
                imageView.setImageResource(R.mipmap.j);
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                imageView.setImageResource(R.mipmap.q);
                break;
            case 16:
            case 17:
            case 18:
                imageView.setImageResource(R.mipmap.n);
                break;
            default:
                imageView.setImageResource(R.mipmap.g);
                break;
        }
    }

    private void getData(String name) {
        String url = ServiceUrl.WEATHER_URL + "&location=" + name + "&language=zh-Hans&unit=c";
//        Log.d("HomeFragment", "------------url-----" + url);
        ServiceCallback.get(url, new ResponseResult() {
            @Override
            public void onSuccess(Object o) {
//                Log.d("HomeFragment", "------------onSuccess-----" + o);
                try {
                    WeatherBean weatherBean = (WeatherBean) CommonUtils.getGson(o.toString(), WeatherBean.class);
                    setUI(weatherBean.getResults().get(0));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "解析错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String err) {
                Toast.makeText(getActivity(), "" + err, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCacheSuccess(Object o) {
                try {
                    WeatherBean weatherBean = (WeatherBean) CommonUtils.getGson(o.toString(), WeatherBean.class);
                    setUI(weatherBean.getResults().get(0));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "解析错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cha_tian_qi: //查询按钮
                String name = content_et.getText().toString().trim();
                getData(name);
                break;
            case R.id.button_long:
                Intent intent1 = new Intent(getActivity(), LongPhotoActivity.class);
                startActivity(intent1);
                break;
            case R.id.button_map:
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
                break;
            case R.id.business_tv://营业执照
                Intent intent2 = new Intent(getActivity(), BusinessActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
