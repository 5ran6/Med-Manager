package project.alc.com.med_manager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import project.alc.com.med_manager.database.model.Doctor;

/**
 * Created by 5ran6 on 26/03/18.
 */

public class DatabaseHelperDoctor extends SQLiteOpenHelper {

    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "doctors_info";

    public DatabaseHelperDoctor(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create doctors table
        db.execSQL(Doctor.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Doctor.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertDoctor(String name, String phone) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Doctor.COLUMN_NAME, name);
        values.put(Doctor.COLUMN_PHONE, phone);

        // insert row
        long id = db.insert(Doctor.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Doctor getDoctor(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Doctor.TABLE_NAME,
                new String[]{Doctor.COLUMN_ID, Doctor.COLUMN_NAME, Doctor.COLUMN_PHONE, Doctor.COLUMN_TIMESTAMP},
                Doctor.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare doc object
        Doctor doc = new Doctor(
                cursor.getInt(cursor.getColumnIndex(Doctor.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Doctor.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Doctor.COLUMN_PHONE)),
                cursor.getString(cursor.getColumnIndex(Doctor.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return doc;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Doctor.TABLE_NAME + " ORDER BY " +
                Doctor.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Doctor doctor = new Doctor();
                doctor.setId(cursor.getInt(cursor.getColumnIndex(Doctor.COLUMN_ID)));
                doctor.setName(cursor.getString(cursor.getColumnIndex(Doctor.COLUMN_NAME)));
                doctor.setPhone(cursor.getString(cursor.getColumnIndex(Doctor.COLUMN_PHONE)));
                doctor.setTimestamp(cursor.getString(cursor.getColumnIndex(Doctor.COLUMN_TIMESTAMP)));
                doctors.add(doctor);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return doctors list
        return doctors;
    }

    public int getDoctorsCount() {
        String countQuery = "SELECT  * FROM " + Doctor.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateDoctor(Doctor doctor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Doctor.COLUMN_NAME, doctor.getName());
        values.put(Doctor.COLUMN_PHONE, doctor.getPhone());

        // updating row
        return db.update(Doctor.TABLE_NAME, values, Doctor.COLUMN_ID + " = ?",
                new String[]{String.valueOf(doctor.getId())});
    }

    public void deleteDoctor(Doctor doctor) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Doctor.TABLE_NAME, Doctor.COLUMN_ID + " = ?",
                new String[]{String.valueOf(doctor.getId())});
        db.close();
    }
}
