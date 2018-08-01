package com.dashen.ningbaoqi.datasave;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.widget.Toast;

import java.io.File;

public class GetSDcardStateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = Environment.getExternalStorageDirectory();
        /**
         * 该对象存放一些关于存储方面的API
         * */
        StatFs statFs = new StatFs(file.getPath());
        /**
         * 获取块区尺寸
         * */
        long blockSize = statFs.getBlockSizeLong();
        /**
         * 获取总块区数量
         * */
        long blocks = statFs.getBlockCountLong();
        /**
         * 获取可用块区的数量
         * */
        long avalible = statFs.getAvailableBlocksLong();
        String text = Formatter.formatFileSize(this, avalible * blockSize);
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
