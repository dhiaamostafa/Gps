package hamba.allah.swt.utils;

import android.content.SharedPreferences;
import java.io.Serializable;
/* loaded from: classes.dex */
public class Settings implements Serializable {

    /* renamed from: a */
    public String f10058a;

    /* renamed from: b */
    public String f10059b;

    /* renamed from: c */
    public Boolean f10060c;

    /* renamed from: d */
    public Boolean f10061d;

    /* renamed from: e */
    public Boolean f10062e;

    /* renamed from: f */
    public Boolean f10063f;

    /* renamed from: g */
    public Boolean f10064g;

    /* renamed from: h */
    public Boolean f10065h;

    /* renamed from: i */
    public String f10066i;

    /* renamed from: j */
    public String f10067j;

    /* renamed from: k */
    public String f10068k;

    /* renamed from: l */
    public Boolean f10069l;

    public Settings(SharedPreferences sharedPreferences) {
        m445a(sharedPreferences);
    }

    /* renamed from: a */
    public void m445a(SharedPreferences sharedPreferences) {
        this.f10058a = sharedPreferences.getString("lat", "");
        this.f10059b = sharedPreferences.getString("lon", "");
        this.f10060c = Boolean.valueOf(sharedPreferences.getBoolean("ping", false));
        this.f10061d = Boolean.valueOf(sharedPreferences.getBoolean("gr", false));
        this.f10062e = Boolean.valueOf(sharedPreferences.getBoolean("grbc", false));
        this.f10063f = Boolean.valueOf(sharedPreferences.getBoolean("grbb", false));
        this.f10064g = Boolean.valueOf(sharedPreferences.getBoolean("gcr", false));
        this.f10065h = Boolean.valueOf(sharedPreferences.getBoolean("box", false));
        this.f10066i = sharedPreferences.getString("GDI", "");
        this.f10067j = sharedPreferences.getString("GT", "");
        this.f10068k = sharedPreferences.getString("GAT", "");
        this.f10069l = Boolean.valueOf(sharedPreferences.getBoolean("FGPS", false));
    }
}
