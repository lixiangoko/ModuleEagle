package song.com.cn.myapplication.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.aip.util.Base64Util;

import java.io.FileInputStream;
import java.io.IOException;

import song.com.cn.myapplication.R;

/**
 * @author lixiangsong
 * @date :2017/9/18
 * @Description: 营业执照
 */
public class BusinessActivity extends BaseActivity {
    private static final int SELECT_PHOTO = 10001;
    private Button button;

    @Override
    public void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_business);
    }

    @Override
    public void initData() {
        button = sFind(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_photo();
            }
        });
    }

    /**
     * 从相册中获取图片
     */
    public void select_photo() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }
    }

    /**
     * 打开相册的方法
     */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO) {
            if (resultCode == RESULT_OK) {
                //判断手机系统版本号
                if (Build.VERSION.SDK_INT > 19) {
                    //4.4及以上系统使用这个方法处理图片
                    handleImgeOnKitKat(data);
                } else {
                    handleImageBeforeKitKat(data);
                }
            }
        }
    }

    /**
     * 通过uri和selection来获取真实的图片路径
     */
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    /**
     * 4.4以下系统处理图片的方法
     */
    private void handleImageBeforeKitKat(Intent data) {

        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);

    }

    /**
     * 4.4及以上系统处理图片的方法
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImgeOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //解析出数字格式的id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的uri，则使用普通方式处理
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
            //根据图片路径显示图片
            Log.d("BusinessActivity", "----------imagePath----" + imagePath);
            try {
                String e = encodeImage(imagePath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * TODO:将图片以Base64方式编码为字符串
     *
     * @param imgUrl 图片的绝对路径（例如：D:\\jsontest\\abc.jpg）
     * @return 编码后的字符串
     * @throws IOException
     */
    public static String encodeImage(String imgUrl) throws IOException {
        FileInputStream fis = new FileInputStream(imgUrl);
        byte[] rs = new byte[fis.available()];
        fis.read(rs);
        fis.close();
        return encode(rs);
    }

    /**
     * TODO:将byte数组以Base64方式编码为字符串
     *
     * @param bytes 待编码的byte数组
     * @return 编码后的字符串
     */
    public static String encode(byte[] bytes) {
        return new Base64Util().encode(bytes);
    }
}
