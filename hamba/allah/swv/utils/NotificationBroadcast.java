package hamba.allah.swv.utils;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import hamba.allah.swv.Base;
import hamba.allah.swv.service.GPSTracker;
/* loaded from: classes.dex */
public class NotificationBroadcast extends BroadcastReceiver {

    /* renamed from: a */
    InterstitialAd f10046a;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!"hamba.allah.swv.action.NotificationBroadcast_Click".equals(intent.getAction()) && "hamba.allah.swv.action.NotificationBroadcast_Close".equals(intent.getAction())) {
            ((NotificationManager) context.getSystemService("notification")).cancelAll();
            SharedPreferenceUtils.m447a(context).m445a("FGPS", false);
            if (Base.m588e().m590a(GPSTracker.class)) {
                context.stopService(new Intent(context, GPSTracker.class));
            }
            this.f10046a = new InterstitialAd(context);
            this.f10046a.m8047a("ca-app-pub-1305986134697546/4193507098");
            this.f10046a.m8050a(new AdRequest.Builder().m8070a());
            if (this.f10046a.m8052a()) {
                this.f10046a.m8045b();
            }
        }
    }
}
