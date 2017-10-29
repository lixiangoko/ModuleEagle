package song.com.cn.personal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import song.com.cn.R;


/**
 * @author lixiang
 * @date :2017/10/29
 * @Description: 自定义点击效果
 */

public class PersonalView extends View {
    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 圆心 x ,y 轴
     */
    private float x, y;
    /**
     * 内圆宽
     */
    private int strokeWidth;

    /**
     * 半径
     */
    private int radius;
    private static final int FULL_VIEW = 100;

    public PersonalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //初始化
        mPaint = new Paint();
        //抗据齿
        mPaint.setAntiAlias(true);
        //设置蓝色
        mPaint.setColor(getResources().getColor(R.color.red));
        //设置style 为空心
        mPaint.setStyle(Paint.Style.STROKE);
        //设置内容宽度
        mPaint.setStrokeWidth(strokeWidth);
        //设置透明度 0-255
        mPaint.setAlpha(255);
        //重置开始值
        strokeWidth = 0;
        radius = 0;

    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆形
        canvas.drawCircle(x, y, radius, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 可以按下有效果，可以抬起有效果
         */
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下的
                x = event.getX();
                y = event.getY();
                Log.d("PersonalView", "-----------" + x + "," + y);

                break;
            case MotionEvent.ACTION_UP:
                //抬起
                break;
            default:
        }
        init();
        handler.sendEmptyMessage(FULL_VIEW);
        return true;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case FULL_VIEW:
                    flushView();
                    //view 重绘
                    invalidate();
                    //判断颜色值是否继续刷新view
                    if (mPaint.getAlpha() != 0) {
                        handler.sendEmptyMessageDelayed(FULL_VIEW, 100);
                    }
                    break;
                default:
            }
        }
    };

    //刷新
    private void flushView() {
        //半径累加10
        radius += 5;
        //设置内圆宽
        strokeWidth = radius / 4;
        //重置画笔宽度
        mPaint.setStrokeWidth(strokeWidth);
        //获取透明度 -20
        int alpha = mPaint.getAlpha() - 10;
        //为了防止减少到负数
        if (alpha < 20) {
            alpha = 0;
        }
        //画笔透明度
        mPaint.setAlpha(alpha);
    }
}
