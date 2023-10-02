package hamba.allah.swv;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.util.Base64;
import com.google.android.gms.ads.MobileAds;
import java.io.UnsupportedEncodingException;
/* loaded from: classes.dex */
public class App extends Application {
    /* renamed from: a */
    public static String m595a(String str) {
        byte[] bArr;
        byte[] bArr2 = new byte[0];
        try {
            bArr = Base64.decode(str.getBytes("UTF-8"), 0);
        } catch (UnsupportedEncodingException unused) {
            bArr = bArr2;
        }
        return new String(bArr);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        MultiDex.m9355a(this);
        MobileAds.m8042a(this, "ca-app-pub-4418796586524797~7020744742");
    }
}
