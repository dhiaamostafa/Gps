package hamba.allah.swv;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.design.widget.Snackbar;
import android.support.p001v4.app.ActivityCompat;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.app.ActionBar;
import android.support.p004v7.app.AlertDialog;
import android.support.p004v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hlab.fabrevealmenu.view.FABRevealMenu;
/* loaded from: classes.dex */
public class Base extends AppCompatActivity {

    /* renamed from: d */
    private static Base f9939d;

    /* renamed from: a */
    public ActionBar f9940a;

    /* renamed from: b */
    final int f9941b = 7;

    /* renamed from: c */
    private RequestQueue f9942c;

    /* renamed from: e */
    private FABRevealMenu f9943e;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m593a(DialogInterface dialogInterface, int i) {
        finish();
    }

    /* renamed from: e */
    public static synchronized Base m588e() {
        Base base;
        synchronized (Base.class) {
            base = f9939d;
        }
        return base;
    }

    /* renamed from: a */
    public void m594a(int i) {
        Snackbar.make(findViewById(16908290), i, 0).show();
    }

    /* renamed from: a */
    public void m592a(Request request, String str) {
        request.m8261a((Object) str);
        m587f().m8236a(request);
    }

    /* renamed from: a */
    public void m591a(FABRevealMenu fABRevealMenu) {
        this.f9943e = fABRevealMenu;
    }

    /* renamed from: a */
    public void mo578a(String str) {
        Snackbar.make(findViewById(16908290), str, 0).show();
    }

    /* renamed from: a */
    public boolean m590a(Class<?> cls) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public boolean m589b(String str) {
        try {
            getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: f */
    public RequestQueue m587f() {
        if (this.f9942c == null) {
            this.f9942c = Volley.m8121a(getApplicationContext());
        }
        return this.f9942c;
    }

    /* renamed from: g */
    public void m586g() {
        if (!m589b("de.robv.android.xposed.installer")) {
            m594a(R.string.xposed_not_installed);
        } else if (Build.VERSION.SDK_INT >= 19) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(new ComponentName("de.robv.android.xposed.installer", "de.robv.android.xposed.installer.WelcomeActivity"));
            intent.addFlags(268435456);
            startActivity(intent);
        }
    }

    /* renamed from: h */
    public void m585h() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 7);
    }

    /* renamed from: i */
    public boolean m584i() {
        return ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0 && ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    @Keep
    public boolean isModuleActive() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f9939d = this;
        this.f9940a = m9130a();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity, android.support.p001v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 7 && iArr.length > 0) {
            boolean z = iArr[0] == 0;
            boolean z2 = iArr[1] == 0;
            boolean z3 = iArr[2] == 0;
            boolean z4 = iArr[3] == 0;
            if (!z || !z2 || !z3 || !z4) {
                mo578a("Permission Denied");
                return;
            }
            mo578a("Permission Granted");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.m9133b("Permissions Granted! Restart App?");
            builder.m9136a(false);
            builder.m9137a("OK", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.-$$Lambda$Base$7agqB3uBn-6039JGL3_ox5GwRv8
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    Base.this.m593a(dialogInterface, i2);
                }
            });
            builder.m9131c();
        }
    }
}
