package song.com.cn.pure;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import song.com.cn.R;

/**
 * @author lixiang
 * @date :2017/11/3
 * @Description: 纯代码
 */

public class PureCodeActivity extends Activity {

    private EditText editInfo;
    private EditText passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    @SuppressLint("ResourceType")
    public final void initUI() {
        ScrollView main = new ScrollView(this);
        main.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        main.setBackgroundColor(Color.WHITE);

        //根布局参数
        LinearLayout.LayoutParams layoutParamsRoot = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsRoot.gravity = Gravity.CENTER;
        //根布局
        LinearLayout layoutRoot = new LinearLayout(this);
        layoutRoot.setLayoutParams(layoutParamsRoot);
        layoutRoot.setOrientation(LinearLayout.VERTICAL);

        //上边距（dp值）
        int topMargin = dip2px(this, 30);
        //imageMain宽度（dp值）
        int widthMain = dip2px(this, 240);
        //imageMain高度（dp值）
        int heightMain = dip2px(this, 120);

        //imageMain布局参数
        LinearLayout.LayoutParams layoutParamsImageMain = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, heightMain);
        layoutParamsImageMain.topMargin = topMargin;
        layoutParamsImageMain.bottomMargin = topMargin;
        layoutParamsImageMain.leftMargin = topMargin;
        layoutParamsImageMain.rightMargin = topMargin;
        layoutParamsImageMain.gravity = Gravity.CENTER_HORIZONTAL;
        //初始化ImageView
        ImageView imageMain = new ImageView(this);
        imageMain.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageMain.setAdjustViewBounds(true);
        imageMain.setImageResource(R.mipmap.laoying);
        layoutRoot.addView(imageMain, layoutParamsImageMain);

        //按钮布局
        LinearLayout layoutUser = new LinearLayout(this);
        layoutUser.setLayoutParams(layoutParamsImageMain);
        layoutUser.setOrientation(LinearLayout.HORIZONTAL);
        //editInfo布局参数
        LinearLayout.LayoutParams layoutParamsEditInfo = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsEditInfo.gravity = Gravity.LEFT;
        layoutParamsEditInfo.leftMargin = dip2px(this, 10);
        layoutParamsEditInfo.rightMargin = dip2px(this, 5);
        layoutParamsEditInfo.weight = 1;

        //初始化textInfo
        TextView textInfo = new TextView(this);
        textInfo.setLayoutParams(layoutParamsEditInfo);
        textInfo.setGravity(Gravity.LEFT);
        textInfo.setTextSize(18);
        textInfo.setText("账号:");
        layoutUser.addView(textInfo);

        //
        LinearLayout.LayoutParams layoutParamsET = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsET.gravity = Gravity.RIGHT;
        layoutParamsET.leftMargin = dip2px(this, 10);
        layoutParamsET.rightMargin = dip2px(this, 5);
        layoutParamsET.weight = 1;
        //初始化editInfo
        editInfo = new EditText(this);
        editInfo.setLayoutParams(layoutParamsET);
        editInfo.setGravity(Gravity.LEFT);
        editInfo.setHint("请输入账号");
        editInfo.setLines(1);
        editInfo.setEllipsize(TextUtils.TruncateAt.END);
        //设置可输入的最大长度
        InputFilter[] filters = {new InputFilter.LengthFilter(20)};
        editInfo.setFilters(filters);
        editInfo.setTextSize(18);
        layoutUser.addView(editInfo);

        layoutRoot.addView(layoutUser, layoutParamsEditInfo);
        //密码输入
        passWord(layoutRoot, layoutParamsImageMain);
        //上边距（dp值）
        int minHeight = dip2px(this, 54);
        //上padding（dp值）
        int topPadding = dip2px(this, 4);
        //左padding（dp值）
        int leftPadding = dip2px(this, 2);
        //按钮布局
        LinearLayout layoutButton = new LinearLayout(this);
        layoutButton.setLayoutParams(layoutParamsEditInfo);
        layoutButton.setOrientation(LinearLayout.HORIZONTAL);
        layoutButton.setMinimumHeight(minHeight);
        layoutButton.setPadding(leftPadding, topPadding, leftPadding, topPadding);
        layoutButton.setId(100000001);

        //buttonOK布局参数
        LinearLayout.LayoutParams layoutParamsButtonOK = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsButtonOK.gravity = Gravity.LEFT;
        layoutParamsButtonOK.leftMargin = dip2px(this, 10);
        layoutParamsButtonOK.rightMargin = dip2px(this, 5);
        layoutParamsButtonOK.weight = 1;
        //Button确定
        Button buttonOK = new Button(this);
        buttonOK.setLayoutParams(layoutParamsButtonOK);
        buttonOK.setMaxLines(2);
        buttonOK.setTextSize(18);
        buttonOK.setText("登录");
        layoutButton.addView(buttonOK);

        //buttonCancel布局参数
        LinearLayout.LayoutParams layoutParamsButtonCancel = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsButtonCancel.gravity = Gravity.RIGHT;
        layoutParamsButtonCancel.leftMargin = dip2px(this, 5);
        layoutParamsButtonCancel.rightMargin = dip2px(this, 10);
        layoutParamsButtonCancel.weight = 1;
        //Button取消
        Button buttonCancel = new Button(this);
        buttonCancel.setLayoutParams(layoutParamsButtonCancel);
        buttonCancel.setMaxLines(2);
        buttonCancel.setTextSize(18);
        buttonCancel.setText("取消");

        layoutButton.addView(buttonCancel);

        layoutRoot.addView(layoutButton, layoutParamsEditInfo);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PureCodeActivity.this, "账号:" + editInfo.getText().toString() + " 密码：" + passWord.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //RelativeLayout布局参数
        LinearLayout.LayoutParams layoutParamsBottom = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout layoutBottom = new RelativeLayout(this);
        layoutBottom.setLayoutParams(layoutParamsBottom);

        main.addView(layoutRoot);
        setContentView(main);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private void passWord(LinearLayout layoutRoot, LinearLayout.LayoutParams layoutParamsImageMain) {
        //按钮布局
        LinearLayout layoutUser = new LinearLayout(this);
        layoutUser.setLayoutParams(layoutParamsImageMain);
        layoutUser.setOrientation(LinearLayout.HORIZONTAL);
        //editInfo布局参数
        LinearLayout.LayoutParams layoutParamsEditInfo = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsEditInfo.gravity = Gravity.LEFT;
        layoutParamsEditInfo.leftMargin = dip2px(this, 10);
        layoutParamsEditInfo.rightMargin = dip2px(this, 5);
        layoutParamsEditInfo.weight = 1;

        //初始化textInfo
        TextView textInfo = new TextView(this);
        textInfo.setLayoutParams(layoutParamsEditInfo);
        textInfo.setGravity(Gravity.LEFT);
        textInfo.setTextSize(18);
        textInfo.setText("密码:");
        layoutUser.addView(textInfo);

        //buttonOK布局参数
        LinearLayout.LayoutParams layoutParamsET = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsET.gravity = Gravity.RIGHT;
        layoutParamsET.leftMargin = dip2px(this, 10);
        layoutParamsET.rightMargin = dip2px(this, 5);
        layoutParamsET.weight = 1;
        //初始化editInfo
        passWord = new EditText(this);
        passWord.setLayoutParams(layoutParamsEditInfo);
        passWord.setGravity(Gravity.LEFT);
        passWord.setLines(1);
        passWord.setEllipsize(TextUtils.TruncateAt.END);
        passWord.setHint("请输入密码");
        //设置可输入的最大长度
        InputFilter[] filters = {new InputFilter.LengthFilter(20)};
        passWord.setFilters(filters);
        passWord.setTextSize(18);
        layoutUser.addView(passWord);

        layoutRoot.addView(layoutUser, layoutParamsEditInfo);
    }
}
