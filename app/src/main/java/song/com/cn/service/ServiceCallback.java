package song.com.cn.service;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import song.com.cn.interfaces.ResponseResult;


/**
 * @author lixiangsong
 * @date 创建时间 : 2017/9/6
 * @Description:
 */

public class ServiceCallback {
    //普通的GET请求
    public static void get(String url, final ResponseResult responseResult) {
        OkGo.<String>get(url).cacheKey("homefragment").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                responseResult.onSuccess(response.body());
            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                super.onCacheSuccess(response);
                Log.d("HomeFragment", "----------onCacheSuccess----" + response.body());
                responseResult.onCacheSuccess(response.body());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.d("HomeFragment", "----------onError----" + response);
                responseResult.onError(response.body());
            }
        });
    }
}
