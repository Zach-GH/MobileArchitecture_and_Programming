package com.example.meisner_band_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String name, String price, byte[] image) {

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO BAND VALUES (NULL, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }

    public void insertUserData(String userName, String userPass) {

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO USER VALUES (NULL, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, userName);
        statement.bindString(2, userPass);

        statement.executeInsert();
    }

    public void updateUserData(String userName, String userPass, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE USER SET userName = ?, userPass = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, userName);
        statement.bindString(2, userPass);
        statement.bindDouble(3, (double)id);

        statement.execute();
        database.close();
    }

    public void updateData(String name, String price, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE BAND SET name = ?, price = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM BAND WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
