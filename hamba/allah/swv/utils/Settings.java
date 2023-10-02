package hamba.allah.swv.utils;

import android.content.SharedPreferences;
import java.io.Serializable;
/* loaded from: classes.dex */
public class Settings implements Serializable {

    /* renamed from: a */
    public String f10060a;

    /* renamed from: b */
    public String f10061b;

    /* renamed from: c */
    public Boolean f10062c;

    /* renamed from: d */
    public Boolean f10063d;

    /* renamed from: e */
    public Boolean f10064e;

    /* renamed from: f */
    public Boolean f10065f;

    /* renamed from: g */
    public Boolean f10066g;

    /* renamed from: h */
    public Boolean f10067h;

    /* renamed from: i */
    public String f10068i;

    /* renamed from: j */
    public String f10069j;

    /* renamed from: k */
    public String f10070k;

    /* renamed from: l */
    public Boolean f10071l;

    public Settings(SharedPreferences sharedPreferences) {
        m448a(sharedPreferences);
    }

    /* renamed from: a */
    public void m448a(SharedPreferences sharedPreferences) {
        this.f10060a = sharedPreferences.getString("lat", "");
        this.f10061b = sharedPreferences.getString("lon", "");
        this.f10062c = Boolean.valueOf(sharedPreferences.getBoolean("ping", false));
        this.f10063d = Boolean.valueOf(sharedPreferences.getBoolean("gr", false));
        this.f10064e = Boolean.valueOf(sharedPreferences.getBoolean("grbc", false));
        this.f10065f = Boolean.valueOf(sharedPreferences.getBoolean("grbb", false));
        this.f10066g = Boolean.valueOf(sharedPreferences.getBoolean("gcr", false));
        this.f10067h = Boolean.valueOf(sharedPreferences.getBoolean("box", false));
        this.f10068i = sharedPreferences.getString("GDI", "");
        this.f10069j = sharedPreferences.getString("GT", "");
        this.f10070k = sharedPreferences.getString("GAT", "");
        this.f10071l = Boolean.valueOf(sharedPreferences.getBoolean("FGPS", false));
    }
}
