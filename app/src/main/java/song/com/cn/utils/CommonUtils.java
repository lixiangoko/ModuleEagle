package song.com.cn.myapplication.utils;

import com.google.gson.Gson;

/**
 * @author lixiangsong
 * @date 创建时间 : 2017/9/6
 * @Description:
 */

public class CommonUtils {
    private static Gson gson;

    public static Object getGson(String msg, Class<?> tClass) {
        if (gson == null) {
            gson = new Gson();
        }
        return gson.fromJson(msg, tClass);
    }
}
