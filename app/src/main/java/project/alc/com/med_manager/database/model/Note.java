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

    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_START = "start";

    public static final String COLUMN_END = "end";
    public static final String COLUMN_FREQUENCY = "frequency";

    private int id;
    private String note;
    private String timestamp;
    private String description;
    private String start;
    private String end;
    private String frequency;
    private int dose;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOTE + " TEXT,"
                    + COLUMN_DESC + " TEXT,"
                    + COLUMN_START + " TEXT,"
                    + COLUMN_END + " TEXT,"
                    + COLUMN_FREQUENCY + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + COLUMN_DOSE + " INTEGER"
                    + ")";

    public Note() {
    }

    public Note(int id, String note, String description, String start, String end, String frequency, String timestamp, int dose) {
        this.id = id;
        this.note = note;
        this.description = description;
        this.start = start;
        this.end = end;
        this.frequency = frequency;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }


}
