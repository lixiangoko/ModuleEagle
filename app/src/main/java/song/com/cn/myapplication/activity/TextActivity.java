package song.com.cn.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import song.com.cn.myapplication.R;

public class TextActivity extends AppCompatActivity {

    private TextView tex_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        tex_one = (TextView) findViewById(R.id.tex_one);
    }
}
