package com.treeanimals.max.animal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 95112 on 2018/6/28.
 */

class NetCacheUtils {
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;
    public NetCacheUtils(LocalCacheUtils mLocalCacheUtils, MemoryCacheUtils mMemoryCacheUtils) {
        this.mLocalCacheUtils = mLocalCacheUtils;
        this.mMemoryCacheUtils = mMemoryCacheUtils;
    }

    public void getBitmapFromNet(ImageView imageView, String url) {
        new BitmapTask().execute(imageView,url);
    }

    private class BitmapTask extends AsyncTask<Object,Void,Bitmap>{
        private ImageView imageView;
        private String url;
        @Override
        protected Bitmap doInBackground(Object... params) {
            imageView = (ImageView) params[0];
            url = (String) params[1];
            return downloadBitmap(url);
        }

        private Bitmap downloadBitmap(String url) {
            HttpURLConnection conn = null;
            try{
                conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                if (responseCode == 200){
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                    Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream(),null,options);
                    return bitmap;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void[] values){
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Bitmap results){
            if(results != null){
                imageView.setImageBitmap(results);
                Log.d("图片工具类","从网络缓存图片");
                mLocalCacheUtils.setBitmapToLocal(url,results);
                mMemoryCacheUtils.setBitmapToMemory(url,results);
            }
        }
    }
}
