package hamba.allah.swv.service;

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
    boolean f10023a = false;

    /* renamed from: b */
    boolean f10024b = false;

    /* renamed from: c */
    boolean f10025c = false;

    /* renamed from: d */
    Location f10026d;

    /* renamed from: e */
    double f10027e;

    /* renamed from: f */
    double f10028f;

    /* renamed from: g */
    protected LocationManager f10029g;

    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public Location m488a() {
        Log.d("DOM", "GPS TRACKER STARTED");
        this.f10029g = (LocationManager) getSystemService("location");
        if (this.f10029g != null) {
            this.f10023a = this.f10029g.isProviderEnabled("gps");
            this.f10024b = this.f10029g.isProviderEnabled("network");
            this.f10025c = true;
            if (this.f10024b) {
                this.f10029g.requestLocationUpdates("network", 5000L, 0.0f, this);
                Log.d("DOM", "Network");
                this.f10026d = this.f10029g.getLastKnownLocation("network");
                if (this.f10026d != null) {
                    Log.d("DOM LOCATION UPDATE", String.valueOf(this.f10026d.getLatitude()));
                    this.f10027e = this.f10026d.getLatitude();
                    this.f10028f = this.f10026d.getLongitude();
                }
            }
            if (this.f10023a) {
                this.f10029g.requestLocationUpdates("gps", 5000L, 0.0f, this);
                Log.d("DOM", "GPS");
                this.f10026d = this.f10029g.getLastKnownLocation("gps");
                if (this.f10026d != null) {
                    Log.d("DOM LOCATION UPDATE ", String.valueOf(this.f10026d.getLatitude()));
                    this.f10027e = this.f10026d.getLatitude();
                    this.f10028f = this.f10026d.getLongitude();
                }
            }
        }
        return this.f10026d;
    }

    /* renamed from: b */
    public void m487b() {
        if (this.f10029g != null) {
            this.f10029g.removeUpdates(this);
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
        m487b();
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
        m488a();
        return 2;
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
