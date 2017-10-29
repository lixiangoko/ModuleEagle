package song.com.cn.activity;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
* @date :2017/10/17
* @author lixiang
* @Description:
*/

public class ProjectApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
    }
}
