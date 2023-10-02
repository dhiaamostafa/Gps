package hamba.allah.swv.utils;

import de.robv.android.xposed.XSharedPreferences;
import java.io.File;
/* loaded from: classes.dex */
public class Readable {
    /* renamed from: a */
    public static Settings m452a() {
        XSharedPreferences xSharedPreferences = new XSharedPreferences("hamba.allah.swv", "FGPS");
        m451b();
        xSharedPreferences.makeWorldReadable();
        xSharedPreferences.reload();
        return new Settings(xSharedPreferences);
    }

    /* renamed from: b */
    private static void m451b() {
        new File("/data/data/hamba.allah.swv").setExecutable(true, false);
        new File("/data/data/hamba.allah.swv").setReadable(true, false);
        new File("/data/data/hamba.allah.swv/shared_prefs/").setExecutable(true, false);
        new File("/data/data/hamba.allah.swv/shared_prefs/").setReadable(true, false);
        new File("/data/data/hamba.allah.swv/shared_prefs/FGPS.xml").setReadable(true, false);
    }
}
