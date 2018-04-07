package project.alc.com.med_manager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

/**
 * Created by 4ran6 on 29/03/2018.
 */
public class DatabaseHelperProfile extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "profile";
    public static final String TABLE_NAME = "picture";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "IMAGE";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR, CATEGORY VARCHAR, IMAGE BLOB)");
    }

    public DatabaseHelperProfile(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "1", null);
    }

    public void insertData(String name, String email, byte[] image) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO PICTURE VALUES (NULL,?,?,?)";

        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, name);
        sqLiteStatement.bindString(2, email);
        sqLiteStatement.bindBlob(3, image);

        sqLiteStatement.executeInsert();
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

}