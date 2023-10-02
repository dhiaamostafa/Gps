package hamba.allah.swt;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.design.widget.Snackbar;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.content.ContextCompat;
import android.support.p003v7.app.ActionBar;
import android.support.p003v7.app.AlertDialog;
import android.support.p003v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hlab.fabrevealmenu.view.FABRevealMenu;
/* loaded from: classes.dex */
public class Base extends AppCompatActivity {

    /* renamed from: d */
    private static Base f9937d;

    /* renamed from: a */
    public ActionBar f9938a;

    /* renamed from: b */
    final int f9939b = 7;

    /* renamed from: c */
    private RequestQueue f9940c;

    /* renamed from: e */
    private FABRevealMenu f9941e;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m590a(DialogInterface dialogInterface, int i) {
        finish();
    }

    /* renamed from: e */
    public static synchronized Base m585e() {
        Base base;
        synchronized (Base.class) {
            base = f9937d;
        }
        return base;
    }

    /* renamed from: a */
    public void m591a(int i) {
        Snackbar.make(findViewById(16908290), i, 0).show();
    }

    /* renamed from: a */
    public void m589a(Request request, String str) {
        request.m8219a((Object) str);
        m584f().m8194a(request);
    }

    /* renamed from: a */
    public void m588a(FABRevealMenu fABRevealMenu) {
        this.f9941e = fABRevealMenu;
    }

    /* renamed from: a */
    public void mo575a(String str) {
        Snackbar.make(findViewById(16908290), str, 0).show();
    }

    /* renamed from: a */
    public boolean m587a(Class<?> cls) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public boolean m586b(String str) {
        try {
            getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: f */
    public RequestQueue m584f() {
        if (this.f9940c == null) {
            this.f9940c = Volley.m8079a(getApplicationContext());
        }
        return this.f9940c;
    }

    /* renamed from: g */
    public void m583g() {
        if (!m586b("de.robv.android.xposed.installer")) {
            m591a(R.string.xposed_not_installed);
        } else if (Build.VERSION.SDK_INT >= 19) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(new ComponentName("de.robv.android.xposed.installer", "de.robv.android.xposed.installer.WelcomeActivity"));
            intent.addFlags(268435456);
            startActivity(intent);
        }
    }

    /* renamed from: h */
    public void m582h() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 7);
    }

    /* renamed from: i */
    public boolean m581i() {
        return ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0 && ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    @Keep
    public boolean isModuleActive() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.FragmentActivity, android.support.p000v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f9937d = this;
        this.f9938a = m9088a();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.support.p000v4.app.FragmentActivity, android.app.Activity, android.support.p000v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 7 && iArr.length > 0) {
            boolean z = iArr[0] == 0;
            boolean z2 = iArr[1] == 0;
            boolean z3 = iArr[2] == 0;
            boolean z4 = iArr[3] == 0;
            if (!z || !z2 || !z3 || !z4) {
                mo575a("Permission Denied");
                return;
            }
            mo575a("Permission Granted");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.m9091b("Permissions Granted! Restart App?");
            builder.m9094a(false);
            builder.m9095a("OK", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.-$$Lambda$Base$7agqB3uBn-6039JGL3_ox5GwRv8
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    Base.this.m590a(dialogInterface, i2);
                }
            });
            builder.m9089c();
        }
    }
}
