package hamba.allah.swv.model;
/* loaded from: classes.dex */
public class History {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LAT = "latitude";
    public static final String COLUMN_LON = "longitude";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String CREATE_TABLE = "CREATE TABLE history(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,latitude TEXT,longitude TEXT,timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";
    public static final String TABLE_NAME = "history";

    /* renamed from: id */
    private int f10022id;
    private String lat;
    private String lon;
    private String name;
    private String timestamp;

    public History() {
    }

    public History(int i, String str, String str2, String str3, String str4) {
        this.f10022id = i;
        this.name = str;
        this.lat = str2;
        this.lon = str3;
        this.timestamp = str4;
    }

    public int getId() {
        return this.f10022id;
    }

    public String getLat() {
        return this.lat;
    }

    public String getLon() {
        return this.lon;
    }

    public String getName() {
        return this.name;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setId(int i) {
        this.f10022id = i;
    }

    public void setLat(String str) {
        this.lat = str;
    }

    public void setLon(String str) {
        this.lon = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }
}
