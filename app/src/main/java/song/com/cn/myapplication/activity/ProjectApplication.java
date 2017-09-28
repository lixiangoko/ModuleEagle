package song.com.cn.myapplication.activity;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * @author lixiangsong
 * @date 创建时间 : 2017/8/30
 * @Description:
 */

public class ProjectApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
    }
}
