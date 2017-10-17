package song.com.cn.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.OkGo;

/**
* @date :2017/10/17
* @author lixiang
* @Description:
*/

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCreate(savedInstanceState);
        initData();

    }

    public abstract void initCreate(Bundle savedInstanceState);

    public abstract void initData();


    /**
     * 绑定空件 ID
     *
     * @param layoutId
     * @param <T>
     * @return
     */
    protected <T extends View> T sFind(int layoutId) {
        return (T) super.findViewById(layoutId);
    }

    /**
     * 不带参数的跳转
     *
     * @param targetActivityClass 跳转到的目标类
     */
    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }

    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    // short吐司
    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    // long吐司
    public void log(String text) {
        Log.i(this.getLocalClassName(), "--------" + text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        OkGo.getInstance().cancelAll();
    }
}
