package com.treeanimals.max.animal;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by 95112 on 2018/6/28.
 */

public class BitmapUtils {
    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;
    public BitmapUtils(){
        mLocalCacheUtils = new LocalCacheUtils();
        mMemoryCacheUtils = new MemoryCacheUtils();
        mNetCacheUtils = new NetCacheUtils(mLocalCacheUtils, mMemoryCacheUtils);
    }
    public void disPlay(ImageView imageView , String url){
        imageView.setImageResource(R.drawable.cat);
        Bitmap bitmap;
        bitmap = mMemoryCacheUtils.getBitmapFromMemory(url);
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
            Log.d("图片工具类","从缓存获取图片");
            return;
        }
        bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
            Log.d("图片工具类","c从内存中获取图片");
            return;
        }

        mNetCacheUtils.getBitmapFromNet(imageView,url);

    }
}
