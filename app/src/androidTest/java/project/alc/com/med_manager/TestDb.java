package project.alc.com.med_manager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import project.alc.com.med_manager.database.DatabaseHelper;
import project.alc.com.med_manager.database.model.Note;

/**
 * Created by 4ran6 on 10/04/2018.
 */

public class TestDb extends AndroidTestCase {
    public static final String LOG_TAG = TestDb.class.getSimpleName();

    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        SQLiteDatabase db = new DatabaseHelper(this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    public void testInsertReadDb() {
        String note = "Paracetamol";
        String description = "For pain relief";
        String dose = "2";
        String frequency = "3";
        String start = "18/03/2018";
        String end = "28/03/2018";
        String timeStamp = "April 23, 2018";

        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Note.COLUMN_NOTE, note);
        values.put(Note.COLUMN_DESC, description);
        values.put(Note.COLUMN_START, start);
        values.put(Note.COLUMN_END, end);
        values.put(Note.COLUMN_FREQUENCY, frequency);
        values.put(Note.COLUMN_DOSE, dose);
        values.put(Note.COLUMN_TIMESTAMP, timeStamp);

        long rowID;
        rowID = db.insert(Note.TABLE_NAME, null, values);

        //Verify we got a row back
        assertTrue(rowID != -1);
        Log.d(LOG_TAG, "New row id: " + rowID);


        //Specify which columns you want
        String[] columns = {
                Note.COLUMN_ID,
                Note.COLUMN_NOTE,
                Note.COLUMN_DESC,
                Note.COLUMN_START,
                Note.COLUMN_END,
                Note.COLUMN_FREQUENCY,
                Note.COLUMN_DOSE,
                Note.COLUMN_TIMESTAMP,


        };

        //move through the db with a cursor

        Cursor cursor = db.query(Note.TABLE_NAME, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(Note.COLUMN_ID);
            String id = cursor.getString(index);

            int drugNameIndex = cursor.getColumnIndex(Note.COLUMN_NOTE);
            String name = cursor.getString(drugNameIndex);

            int DescIndex = cursor.getColumnIndex(Note.COLUMN_DESC);
            String desc = cursor.getString(DescIndex);

            int freqIndex = cursor.getColumnIndex(Note.COLUMN_FREQUENCY);
            String freq = cursor.getString(freqIndex);

            int doseIndex = cursor.getColumnIndex(Note.COLUMN_DOSE);
            String dosage = cursor.getString(doseIndex);

            int startIndex = cursor.getColumnIndex(Note.COLUMN_START);
            String start_date = cursor.getString(startIndex);

            int endIndex = cursor.getColumnIndex(Note.COLUMN_END);
            String end_date = cursor.getString(endIndex);

            int timeIndex = cursor.getColumnIndex(Note.COLUMN_TIMESTAMP);
            String time = cursor.getString(timeIndex);


        } else {
            fail("No values returned ;(");
        }

    }

}
