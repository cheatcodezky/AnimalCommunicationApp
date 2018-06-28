package com.treeanimals.max.animal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by 95112 on 2018/6/28.
 */

class LocalCacheUtils {
    private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/PicSave";

    public Bitmap getBitmapFromLocal(String url) {
        String fileName = null;
        try{
            fileName = url;
            File file = new File(CACHE_PATH,fileName);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBitmapToLocal(String url, Bitmap results) {
        try{
            String fileName = url;
            File file = new File(CACHE_PATH,fileName);
            File parentFile = file.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdir();
            }
            results.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
