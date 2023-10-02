package hamba.allah.swt.utils;

import de.robv.android.xposed.XSharedPreferences;
import java.io.File;
/* loaded from: classes.dex */
public class Readable {
    /* renamed from: a */
    public static Settings m449a() {
        XSharedPreferences xSharedPreferences = new XSharedPreferences("hamba.allah.swt", "FGPS");
        m448b();
        xSharedPreferences.makeWorldReadable();
        xSharedPreferences.reload();
        return new Settings(xSharedPreferences);
    }

    /* renamed from: b */
    private static void m448b() {
        new File("/data/data/hamba.allah.swt").setExecutable(true, false);
        new File("/data/data/hamba.allah.swt").setReadable(true, false);
        new File("/data/data/hamba.allah.swt/shared_prefs/").setExecutable(true, false);
        new File("/data/data/hamba.allah.swt/shared_prefs/").setReadable(true, false);
        new File("/data/data/hamba.allah.swt/shared_prefs/FGPS.xml").setReadable(true, false);
    }
}
