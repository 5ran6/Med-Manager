package project.alc.com.med_manager.database.model;

/**
 * Created by 5ran6 on 26/03/18.
 */

public class Note {
    public static final String TABLE_NAME = "notes";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DOSE = "dose";

    private int id;
    private String note;
    private String timestamp;
    private int dose;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOTE + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + COLUMN_DOSE + " INTEGER"
                    + ")";

    public Note() {
    }

    public Note(int id, String note, String timestamp, int dose) {
        this.id = id;
        this.note = note;
        this.timestamp = timestamp;
        this.dose = dose;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public int getDose() {
        return dose;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }
}
