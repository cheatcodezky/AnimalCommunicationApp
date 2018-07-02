package com.treeanimals.max.animal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 95112 on 2018/7/1.
 */

public class SQLiteUtils extends SQLiteOpenHelper {
    private Context mContext;
    public static final String CREATE_TABLE = "create table Book (" +
            "id integer primary key autoincrement, " +
            "autho text" +
            "price real" +
            "name text)";

    public SQLiteUtils(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Toast.makeText(mContext,"Create table successfully",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
