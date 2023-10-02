package hamba.allah.swv.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import hamba.allah.swv.model.Bookmark;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class BookmarkDBHandler extends SQLiteOpenHelper {
    public BookmarkDBHandler(Context context) {
        super(context, "bookmark_db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    /* renamed from: a */
    public int m477a(Bookmark bookmark) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", bookmark.getName());
        contentValues.put("latitude", bookmark.getLat());
        contentValues.put("longitude", bookmark.getLon());
        return writableDatabase.update(Bookmark.TABLE_NAME, contentValues, "id = ?", new String[]{String.valueOf(bookmark.getId())});
    }

    /* renamed from: a */
    public long m476a(String str, String str2, String str3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str);
        contentValues.put("latitude", str2);
        contentValues.put("longitude", str3);
        long insert = writableDatabase.insert(Bookmark.TABLE_NAME, null, contentValues);
        writableDatabase.close();
        return insert;
    }

    /* renamed from: a */
    public Bookmark m478a(long j) {
        Cursor query = getReadableDatabase().query(Bookmark.TABLE_NAME, new String[]{"id", "name", "latitude", "longitude", "timestamp"}, "id=?", new String[]{String.valueOf(j)}, null, null, null, null);
        if (query != null) {
            query.moveToFirst();
        }
        Bookmark bookmark = new Bookmark(query.getInt(query.getColumnIndex("id")), query.getString(query.getColumnIndex("name")), query.getString(query.getColumnIndex("latitude")), query.getString(query.getColumnIndex("longitude")), query.getString(query.getColumnIndex("timestamp")));
        query.close();
        return bookmark;
    }

    /* renamed from: a */
    public List<Bookmark> m479a() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT  * FROM bookmark ORDER BY timestamp DESC", null);
        if (!rawQuery.moveToFirst()) {
            writableDatabase.close();
            return arrayList;
        }
        do {
            Bookmark bookmark = new Bookmark();
            bookmark.setId(rawQuery.getInt(rawQuery.getColumnIndex("id")));
            bookmark.setName(rawQuery.getString(rawQuery.getColumnIndex("name")));
            bookmark.setLat(rawQuery.getString(rawQuery.getColumnIndex("latitude")));
            bookmark.setLon(rawQuery.getString(rawQuery.getColumnIndex("longitude")));
            bookmark.setTimestamp(rawQuery.getString(rawQuery.getColumnIndex("timestamp")));
            arrayList.add(bookmark);
        } while (rawQuery.moveToNext());
        writableDatabase.close();
        return arrayList;
    }

    /* renamed from: b */
    public int m475b() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT  * FROM bookmark", null);
        int count = rawQuery.getCount();
        rawQuery.close();
        return count;
    }

    /* renamed from: b */
    public void m474b(Bookmark bookmark) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(Bookmark.TABLE_NAME, "id = ?", new String[]{String.valueOf(bookmark.getId())});
        writableDatabase.close();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(Bookmark.CREATE_TABLE);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS bookmark");
        onCreate(sQLiteDatabase);
    }
}
