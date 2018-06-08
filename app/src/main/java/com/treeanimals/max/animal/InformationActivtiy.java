package com.treeanimals.max.animal;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 95112 on 2018/6/7.
 */

public class InformationActivtiy extends AppCompatActivity {
    private final ClickListener clickListener = new ClickListener();
    private LinearLayout mainLayout;
    private WindowManager.LayoutParams layoutParams;
    private  PopupWindow window;
    private CircleImageView headPortrait;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.information_page);
        ImageView previous = (ImageView)findViewById(R.id.InformationA2MainA);
        previous.setOnClickListener(clickListener);
        LinearLayout headPicture = (LinearLayout)findViewById(R.id.headPicture);
        headPicture.setOnClickListener(clickListener);
        mainLayout = (LinearLayout)findViewById(R.id.main_layout);
        headPortrait = (CircleImageView)findViewById(R.id.headPortrait);

    }



    //展示头像图片选择项
    private void showPopupWindow(){
        layoutParams = getWindow().getAttributes();
        layoutParams.alpha = 0.3f;
        getWindow().setAttributes(layoutParams);
        View contentView=LayoutInflater.from(InformationActivtiy.this).inflate(R.layout.headpicture_select,null);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        window =new PopupWindow(mainLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setContentView(contentView);
        // 设置PopupWindow的背景
//                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
//                    window.showAsDropDown(findViewById(R.id.headPicture));
        window.showAsDropDown(findViewById(R.id.main_layout),0,1500);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                layoutParams = getWindow().getAttributes();
                layoutParams.alpha = 1f;
                getWindow().setAttributes(layoutParams);
            }
        });
        LinearLayout cameraButton,photographButton,headPictureCancel;
        cameraButton = (LinearLayout)contentView.findViewById(R.id.cameraButton);
        photographButton = (LinearLayout)contentView.findViewById(R.id.photographButton);
        headPictureCancel = (LinearLayout)contentView.findViewById(R.id.headPictureCancel);
        cameraButton.setOnClickListener(clickListener);
        photographButton.setOnClickListener(clickListener);
        headPictureCancel.setOnClickListener(clickListener);
    }

    private void cameraPic(){
        File  outputFile = new File(getExternalCacheDir(),"output.png");
        try{
            if (outputFile.exists()){
                outputFile.delete();
            }
            outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >=24){
            imageUri = FileProvider.getUriForFile(this,"com.rachel.studyapp.fileprovider",outputFile);
        }else{
            imageUri = Uri.fromFile(outputFile);
        }
        Intent intent  = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,ToolUtil.PHOTOCAMERA);
    }
    private String getImagePath(Uri uri,String selection){
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String  imagePath){
        if(imagePath!=null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            headPortrait.setImageBitmap(bitmap);
        }else{
            Toast.makeText(InformationActivtiy.this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK){
            switch(requestCode){
                case ToolUtil.PHOTOCAMERA:
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        headPortrait.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case ToolUtil.PHOTOGRAPHSELECTIONS:

                    Uri uri = data.getData();
                    String imagePath = null;
                    if (DocumentsContract.isDocumentUri(this,uri)){
                        String docId = DocumentsContract.getDocumentId(uri);
                        if("com.android.providers.media.documents".equals(uri.getAuthority())){
                            String id = docId.split(":")[1];
                            String selection = MediaStore.Images.Media._ID+"="+id;
                            imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
                        }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                            Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                            imagePath = getImagePath(contentUri,null);
                        }
                    }else if("content".equalsIgnoreCase(uri.getScheme())){
                        imagePath = getImagePath(uri,null);
                    }else if ("file".equalsIgnoreCase(uri.getScheme())){
                        imagePath = getImagePath(uri,null);
                    }
                    Toast.makeText(InformationActivtiy.this,imagePath,Toast.LENGTH_SHORT).show();
                    displayImage(imagePath);
                    break;
                case ToolUtil.PHONE_CROP:
                    try{
                        Bitmap bitmap  = BitmapFactory.decodeStream(
                                this.getContentResolver().openInputStream(imageUri));
                        headPortrait.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                default:
                    break;
            }
        }
    }
    private void checkPermission(){
        if(ContextCompat.checkSelfPermission(InformationActivtiy.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(InformationActivtiy.this,new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE },1);
        }else{
            openAlbum();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(InformationActivtiy.this,"You denied the permissions",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    private void openAlbum(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,ToolUtil.PHOTOGRAPHSELECTIONS);
    }
    static class ToolUtil{
        public final static int PHONE_CROP = 1999;
        public final static int PHOTOGRAPHSELECTIONS = 2001;
        public final static int PHOTOCAMERA = 2002;
    }

    class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.InformationA2MainA:
                    finish();
                    break;
                case R.id.headPicture:
                    showPopupWindow();
                    break;
                case R.id.cameraButton:
                    cameraPic();
//                    window.dismiss();
                    break;
                case R.id.photographButton:
                    checkPermission();
                    window.dismiss();
                    break;
                case R.id.headPictureCancel:
                    window.dismiss();
                    break;
                default:
                    break;
            }
        }
    }

}
