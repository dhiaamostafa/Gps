package hamba.allah.swv.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
/* loaded from: classes.dex */
public class SharedPreferenceUtils {

    /* renamed from: b */
    private static SharedPreferenceUtils f10072b;

    /* renamed from: a */
    protected Context f10073a;

    /* renamed from: c */
    private SharedPreferences f10074c;

    /* renamed from: d */
    private SharedPreferences.Editor f10075d;

    @SuppressLint({"WorldReadableFiles", "SetWorldReadable"})
    private SharedPreferenceUtils(Context context) {
        this.f10073a = context;
        if (Build.VERSION.SDK_INT >= 24) {
            this.f10074c = context.getSharedPreferences("FGPS", 0);
        } else {
            this.f10074c = context.getSharedPreferences("FGPS", 1);
        }
        this.f10075d = this.f10074c.edit();
    }

    /* renamed from: a */
    public static synchronized SharedPreferenceUtils m447a(Context context) {
        SharedPreferenceUtils sharedPreferenceUtils;
        synchronized (SharedPreferenceUtils.class) {
            if (f10072b == null) {
                f10072b = new SharedPreferenceUtils(context.getApplicationContext());
            }
            sharedPreferenceUtils = f10072b;
        }
        return sharedPreferenceUtils;
    }

    /* renamed from: a */
    public void m446a(String str, String str2) {
        this.f10075d.putString(str, str2);
        this.f10075d.commit();
    }

    /* renamed from: a */
    public void m445a(String str, boolean z) {
        this.f10075d.putBoolean(str, z);
        this.f10075d.commit();
    }

    /* renamed from: b */
    public String m444b(String str, String str2) {
        return this.f10074c.getString(str, str2);
    }

    /* renamed from: b */
    public boolean m443b(String str, boolean z) {
        return this.f10074c.getBoolean(str, z);
    }
}
