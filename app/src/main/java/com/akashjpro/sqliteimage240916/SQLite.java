package com.akashjpro.sqliteimage240916;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by Akashjpro on 9/24/2016.
 */
public class SQLite extends SQLiteOpenHelper {
    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase  database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public void insertData(String ten, String mota, byte[] hinh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO DongVat VALUES(NULL, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);// bien dich lai  theo cach cua minh
        statement.clearBindings();// xoa truoc khi lam

        statement.bindString(1, ten);// 1: index, ten la gia tri
        statement.bindString(2, mota);
        statement.bindBlob(3, hinh);// bindBlog kieu byte

        statement.executeInsert();// insert vao database
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
