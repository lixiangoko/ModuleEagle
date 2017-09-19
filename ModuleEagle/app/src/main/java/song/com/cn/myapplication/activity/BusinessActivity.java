package song.com.cn.myapplication.activity;

import android.os.Bundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import song.com.cn.myapplication.R;

/**
* @date :2017/9/18
* @author lixiangsong
* @Description: 营业执照
*/
public class BusinessActivity extends BaseActivity {

    @Override
    public void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_business);
    }

    @Override
    public void initData() {

        // 对图像进行base64编码
        String img_base64 = "";
        try {
            File file = new File(img_file);
            byte[] content = new byte[(int) file.length()];
            FileInputStream finputstream = new FileInputStream(file);
            finputstream.read(content);
            finputstream.close();
            img_base64 = (new BASE64Encoder()).encode(content);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
