package hamba.allah.swv.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import hamba.allah.swv.model.History;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class HistoryDBHandler extends SQLiteOpenHelper {
    public HistoryDBHandler(Context context) {
        super(context, "history_db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    /* renamed from: a */
    public long m470a(String str, String str2, String str3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str);
        contentValues.put("latitude", str2);
        contentValues.put("longitude", str3);
        long insert = writableDatabase.insert(History.TABLE_NAME, null, contentValues);
        writableDatabase.close();
        return insert;
    }

    /* renamed from: a */
    public History m471a(long j) {
        Cursor query = getReadableDatabase().query(History.TABLE_NAME, new String[]{"id", "name", "latitude", "longitude", "timestamp"}, "id=?", new String[]{String.valueOf(j)}, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
        }
        History history = new History(query.getInt(query.getColumnIndex("id")), query.getString(query.getColumnIndex("name")), query.getString(query.getColumnIndex("latitude")), query.getString(query.getColumnIndex("longitude")), query.getString(query.getColumnIndex("timestamp")));
        query.close();
        return history;
    }

    /* renamed from: a */
    public List<History> m472a() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM history ORDER BY timestamp DESC", null);
        if (!rawQuery.moveToFirst()) {
            writableDatabase.close();
            return arrayList;
        }
        do {
            History history = new History();
            history.setId(rawQuery.getInt(rawQuery.getColumnIndex("id")));
            history.setName(rawQuery.getString(rawQuery.getColumnIndex("name")));
            history.setLat(rawQuery.getString(rawQuery.getColumnIndex("latitude")));
            history.setLon(rawQuery.getString(rawQuery.getColumnIndex("longitude")));
            history.setTimestamp(rawQuery.getString(rawQuery.getColumnIndex("timestamp")));
            arrayList.add(history);
        } while (rawQuery.moveToNext());
        writableDatabase.close();
        return arrayList;
    }

    /* renamed from: b */
    public int m469b() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT  * FROM history", null);
        int count = rawQuery.getCount();
        rawQuery.close();
        return count;
    }

    /* renamed from: c */
    public void m468c() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(History.TABLE_NAME, null, null);
        writableDatabase.close();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(History.CREATE_TABLE);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS history");
        onCreate(sQLiteDatabase);
    }
}
