package song.com.cn.myapplication.activity;

import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.File;

import song.com.cn.myapplication.R;

public class LongPhotoActivity extends BaseActivity {
    private SubsamplingScaleImageView imageView;

    @Override
    public void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_long_photo);
    }

    @Override
    public void initData() {
        imageView = (SubsamplingScaleImageView) findViewById(R.id.submenuarrow_iv);
        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        imageView.setMinScale(0.9f);
        imageView.setMaxScale(2.0f);
        String url = "http://testuserhead.oss-cn-shenzhen.aliyuncs.com/2e375d8a-70b2-4d36-b118-cffc728cef59138f48c6-c497-47d0-a26f-b105af864dcd00.jpg";
        Glide.with(this)
                .load(url).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                imageView.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(0.9f, new PointF(0, 0), 0));
            }
        });
    }
}
