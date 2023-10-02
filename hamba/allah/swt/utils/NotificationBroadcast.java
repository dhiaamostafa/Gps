package hamba.allah.swt.utils;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import hamba.allah.swt.Base;
import hamba.allah.swt.service.GPSTracker;
/* loaded from: classes.dex */
public class NotificationBroadcast extends BroadcastReceiver {

    /* renamed from: a */
    InterstitialAd f10044a;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!"hamba.allah.swt.action.NotificationBroadcast_Click".equals(intent.getAction()) && "hamba.allah.swt.action.NotificationBroadcast_Close".equals(intent.getAction())) {
            ((NotificationManager) context.getSystemService("notification")).cancelAll();
            SharedPreferenceUtils.m444a(context).m442a("FGPS", false);
            if (Base.m585e().m587a(GPSTracker.class)) {
                context.stopService(new Intent(context, GPSTracker.class));
            }
            this.f10044a = new InterstitialAd(context);
            this.f10044a.m8038a("ca-app-pub-1305986134697546/4193507098");
            this.f10044a.m8041a(new AdRequest.Builder().m8061a());
            if (this.f10044a.m8043a()) {
                this.f10044a.m8036b();
            }
        }
    }
}
