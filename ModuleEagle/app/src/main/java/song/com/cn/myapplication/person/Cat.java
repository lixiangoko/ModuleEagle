package song.com.cn.myapplication.person;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static android.content.ContentValues.TAG;

/**
 * @author lixiangsong
 * @date 创建时间 : 2017/9/13
 * @Description:
 */

public class Cat extends Animal {
    @Override
    public void eat() {
        Log.d(TAG, "------Cat----eat: ");
        getUTF8XMLString("松下");
    }
    public  String getUTF8XMLString(String xml) {
        // A StringBuffer Object
        StringBuffer sb = new StringBuffer();
        sb.append(xml);
        String xmString = "";
        String xmlUTF8="";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
            System.out.println("utf-8 编码：" + xmlUTF8) ;
            Log.d(TAG, "------------xmlUTF8----"+xmlUTF8);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return to String Formed
        return xmlUTF8;
    }
}
