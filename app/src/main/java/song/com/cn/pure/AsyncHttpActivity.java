package song.com.cn.pure;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;
import song.com.cn.R;
import song.com.cn.activity.BaseActivity;

/**
 * @author lixiang
 * @date :2017/11/6
 * @Description:
 */
public class AsyncHttpActivity extends BaseActivity implements View.OnClickListener {

    private Button get;
    private Button post;
    private Button download;
    private Button upload;
    private AsyncHttpClient asyncHttpClient;

    @Override
    public void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_async_http);
        get = sFind(R.id.get_async_bt);
        post = sFind(R.id.post_async_bt);
        download = sFind(R.id.download_async_bt);
        upload = sFind(R.id.upload_async_bt);
        get.setOnClickListener(this);
        post.setOnClickListener(this);
        download.setOnClickListener(this);
        upload.setOnClickListener(this);
        asyncHttpClient = new AsyncHttpClient();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_async_bt:
                //get 有道词典的接口，get查询
                getYouDao();
                break;
            case R.id.post_async_bt:
                //post
                break;
            case R.id.download_async_bt:
                //download
                downloadAPk();
                break;
            case R.id.upload_async_bt:
                //upload
                break;
            default:
        }
    }

    private void downloadAPk() {

    }

    private void getYouDao() {

        asyncHttpClient.get("https://www.csdn.net/", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                String content = Html2Text(str);
                Log.d("AsyncHttpActivity", "---------onSuccess----" + content);
                Toast.makeText(AsyncHttpActivity.this, "" + content, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("AsyncHttpActivity", "---------onFailure----" + responseBody + "----statusCode:" + statusCode);
                Toast.makeText(AsyncHttpActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 去除html标签
     *
     * @param inputString
     * @return
     */
    private String Html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
        //剔除空格行
        textStr = textStr.replaceAll("[ ]+", " ");
        textStr = textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
        return textStr;// 返回文本字符串
    }
}
