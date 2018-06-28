package com.treeanimals.max.animal;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by 95112 on 2018/6/28.
 */

class MemoryCacheUtils {
    private LruCache<String,Bitmap> mMemoryCache;
    public MemoryCacheUtils(){
        long maxMemory = Runtime.getRuntime().maxMemory()/8;
        mMemoryCache = new LruCache<String,Bitmap>((int)maxMemory){
            @Override
            protected  int sizeOf(String key, Bitmap value){
                int byteCount = value.getByteCount();
                return byteCount;
            }
        };
    }
    public Bitmap getBitmapFromMemory(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        return bitmap;
    }



    public void setBitmapToMemory(String url, Bitmap results) {
        mMemoryCache.put(url,results);
    }
}
