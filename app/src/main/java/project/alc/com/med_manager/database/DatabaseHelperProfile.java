package project.alc.com.med_manager.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by 4ran6 on 29/03/2018.
 */
public class DatabaseHelperProfile extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "med";
    public static final String TABLE_NAME = "profile";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "IMAGE";
    public static final String COL_4 = "EMAIL";

    String[] yData = {};

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " VARCHAR, " + COL_3 + " VARCHAR, " + COL_4 + " BLOB)");
    }

    public DatabaseHelperProfile(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String name, String email, byte[] image) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO PROFILE VALUES (NULL,?,?,?)";

        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, name);
        sqLiteStatement.bindString(2, email);
        sqLiteStatement.bindBlob(3, image);
        sqLiteStatement.executeInsert();
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "1", null);
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

    public String[] getDetails(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // String[] x = {};
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = " + id + " Limit 1", null);

        if (cursor.moveToFirst()) {

            String[] columnNames = cursor.getColumnNames();

            yData = new String[columnNames.length];

            for (int i = 0; i < columnNames.length; i++) {

                // Assume every column is int
                yData[i] = cursor.getString(cursor.getColumnIndex(columnNames[i]));
//                x[i] = String.valueOf(yData[i]);
            }

        }
//        String y = Arrays.toString(yData);
        cursor.close();
        db.close();
        return yData;
    }
}