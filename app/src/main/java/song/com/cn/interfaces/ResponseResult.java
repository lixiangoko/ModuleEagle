package song.com.cn.myapplication.interfaces;

/**
 * @author lixiangsong
 * @date 创建时间 : 2017/9/6
 * @Description:
 */

public interface ResponseResult {
    void onSuccess(Object o);

    void onError(String err);

    void onCacheSuccess(Object o);
}
