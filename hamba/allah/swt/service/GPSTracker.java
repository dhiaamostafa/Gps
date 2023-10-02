package hamba.allah.swt.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
/* loaded from: classes.dex */
public class GPSTracker extends Service implements LocationListener {

    /* renamed from: a */
    boolean f10021a = false;

    /* renamed from: b */
    boolean f10022b = false;

    /* renamed from: c */
    boolean f10023c = false;

    /* renamed from: d */
    Location f10024d;

    /* renamed from: e */
    double f10025e;

    /* renamed from: f */
    double f10026f;

    /* renamed from: g */
    protected LocationManager f10027g;

    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public Location m485a() {
        Log.d("DOM", "GPS TRACKER STARTED");
        this.f10027g = (LocationManager) getSystemService("location");
        if (this.f10027g != null) {
            this.f10021a = this.f10027g.isProviderEnabled("gps");
            this.f10022b = this.f10027g.isProviderEnabled("network");
            this.f10023c = true;
            if (this.f10022b) {
                this.f10027g.requestLocationUpdates("network", 5000L, 0.0f, this);
                Log.d("DOM", "Network");
                this.f10024d = this.f10027g.getLastKnownLocation("network");
                if (this.f10024d != null) {
                    Log.d("DOM LOCATION UPDATE", String.valueOf(this.f10024d.getLatitude()));
                    this.f10025e = this.f10024d.getLatitude();
                    this.f10026f = this.f10024d.getLongitude();
                }
            }
            if (this.f10021a) {
                this.f10027g.requestLocationUpdates("gps", 5000L, 0.0f, this);
                Log.d("DOM", "GPS");
                this.f10024d = this.f10027g.getLastKnownLocation("gps");
                if (this.f10024d != null) {
                    Log.d("DOM LOCATION UPDATE ", String.valueOf(this.f10024d.getLatitude()));
                    this.f10025e = this.f10024d.getLatitude();
                    this.f10026f = this.f10024d.getLongitude();
                }
            }
        }
        return this.f10024d;
    }

    /* renamed from: b */
    public void m484b() {
        if (this.f10027g != null) {
            this.f10027g.removeUpdates(this);
            Log.d("DOM", "GPSTRACKER STOPED");
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        m484b();
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        m485a();
        return 2;
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
