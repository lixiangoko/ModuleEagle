package song.com.cn.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import song.com.cn.R;

/**
* @date :2017/10/17
* @author lixiang
* @Description:
*/
public class TextActivity extends AppCompatActivity {

    private TextView tex_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        tex_one = (TextView) findViewById(R.id.tex_one);
    }
}
