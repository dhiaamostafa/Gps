package hamba.allah.swt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
/* loaded from: classes.dex */
public class SharedPreferenceUtils {

    /* renamed from: b */
    private static SharedPreferenceUtils f10070b;

    /* renamed from: a */
    protected Context f10071a;

    /* renamed from: c */
    private SharedPreferences f10072c;

    /* renamed from: d */
    private SharedPreferences.Editor f10073d;

    @SuppressLint({"WorldReadableFiles", "SetWorldReadable"})
    private SharedPreferenceUtils(Context context) {
        this.f10071a = context;
        if (Build.VERSION.SDK_INT >= 24) {
            this.f10072c = context.getSharedPreferences("FGPS", 0);
        } else {
            this.f10072c = context.getSharedPreferences("FGPS", 1);
        }
        this.f10073d = this.f10072c.edit();
    }

    /* renamed from: a */
    public static synchronized SharedPreferenceUtils m444a(Context context) {
        SharedPreferenceUtils sharedPreferenceUtils;
        synchronized (SharedPreferenceUtils.class) {
            if (f10070b == null) {
                f10070b = new SharedPreferenceUtils(context.getApplicationContext());
            }
            sharedPreferenceUtils = f10070b;
        }
        return sharedPreferenceUtils;
    }

    /* renamed from: a */
    public void m443a(String str, String str2) {
        this.f10073d.putString(str, str2);
        this.f10073d.commit();
    }

    /* renamed from: a */
    public void m442a(String str, boolean z) {
        this.f10073d.putBoolean(str, z);
        this.f10073d.commit();
    }

    /* renamed from: b */
    public String m441b(String str, String str2) {
        return this.f10072c.getString(str, str2);
    }

    /* renamed from: b */
    public boolean m440b(String str, boolean z) {
        return this.f10072c.getBoolean(str, z);
    }
}
