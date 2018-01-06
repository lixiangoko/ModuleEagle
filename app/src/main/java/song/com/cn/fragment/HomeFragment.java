package song.com.cn.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.squareup.picasso.Picasso;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import song.com.cn.R;
import song.com.cn.activity.LongPhotoActivity;
import song.com.cn.activity.MapActivity;
import song.com.cn.bean.WeatherBean;
import song.com.cn.interfaces.ResponseResult;
import song.com.cn.personal.CustomActivity;
import song.com.cn.pure.AsyncHttpActivity;
import song.com.cn.pure.PureCodeActivity;
import song.com.cn.service.ServiceCallback;
import song.com.cn.utils.CommonUtils;
import song.com.cn.utils.ServiceUrl;

/**
 * @author lixiang
 * @date :2017/10/17
 * @Description:
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    private ImageView button_map;
    private Button cha_tian_qi;
    private EditText content_et;
    private TextView name, time_tv, textTv, pure_tv;
    private ImageView imageView;
    private TextView longPhoto, business_tv, async_http_bt;
    private XBanner xbanner_xb;
    private List<Integer> listBanner = new ArrayList<Integer>();
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_item, container, false);
        button_map = (ImageView) view.findViewById(R.id.button_map);
        cha_tian_qi = (Button) view.findViewById(R.id.cha_tian_qi);
        content_et = (EditText) view.findViewById(R.id.content_et);
        name = (TextView) view.findViewById(R.id.name_adree_tv);
        time_tv = (TextView) view.findViewById(R.id.time_tv);
        textTv = (TextView) view.findViewById(R.id.text_tv);
        pure_tv = (TextView) view.findViewById(R.id.pure_tv);
        imageView = (ImageView) view.findViewById(R.id.image_iv);
        longPhoto = (TextView) view.findViewById(R.id.button_long);
        xbanner_xb = view.findViewById(R.id.xbanner_xb);
        business_tv = (TextView) view.findViewById(R.id.business_tv);
        async_http_bt = (TextView) view.findViewById(R.id.async_http_bt);
        return view;
    }

    private Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName()
                + FOREWARD_SLASH + resourceId);
    }

    @Override
    public void onStart() {
        super.onStart();
        cha_tian_qi.setOnClickListener(this);
        longPhoto.setOnClickListener(this);
        button_map.setOnClickListener(this);
        business_tv.setOnClickListener(this);
        pure_tv.setOnClickListener(this);
        async_http_bt.setOnClickListener(this);

        getData("深圳");
        listBanner.add(R.mipmap.one);
        listBanner.add(R.mipmap.meigui);
        listBanner.add(R.mipmap.sun);
        xbanner_xb.setData(listBanner, null);
        xbanner_xb.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Uri uri = resourceIdToUri(getActivity(), listBanner.get(position));
                Picasso.with(getActivity()).load(uri).into((ImageView) view);
            }
        });
        xbanner_xb.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(getActivity(), "点击 " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUI(WeatherBean.ResultsBean resultsBean) {
        name.setText(resultsBean.getLocation().getName());
        time_tv.setText(resultsBean.getLast_update());
        textTv.setText(resultsBean.getNow().getText());
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
        ServiceCallback.get(url, new ResponseResult() {
            @Override
            public void onSuccess(Object o) {
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
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.cha_tian_qi:
                //查询按钮
                String name = content_et.getText().toString().trim();
                getData(name);
                break;
            case R.id.button_long:
                intent.setClass(getActivity(), LongPhotoActivity.class);
                break;
            case R.id.button_map:
                intent.setClass(getActivity(), MapActivity.class);
                break;
            case R.id.business_tv:
                //自定义view
                intent.setClass(getActivity(), CustomActivity.class);
                break;
            case R.id.pure_tv:
                //纯代码
                intent.setClass(getActivity(), PureCodeActivity.class);
                break;
            case R.id.async_http_bt:
                intent.setClass(getActivity(), AsyncHttpActivity.class);
                break;
            default:

        }
        startActivity(intent);
    }
}
