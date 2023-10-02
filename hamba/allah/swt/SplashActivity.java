package hamba.allah.swt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.p003v7.app.AlertDialog;
import hamba.allah.swt.activities.FgpsActivity;
/* loaded from: classes.dex */
public class SplashActivity extends Base {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m580a(DialogInterface dialogInterface, int i) {
        m583g();
    }

    /* renamed from: j */
    private void m579j() {
        new AlertDialog.Builder(this).m9096a("FGPS Belum Aktif").m9091b("FGPS tidak bisa berjalan tanpa Xposed\nCeklis FGPS di Xposed Module lalu restart HP.").m9095a("Open Xposed", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.-$$Lambda$SplashActivity$V9eROntZkiWXBKnCvf92i0sNq5A
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SplashActivity.this.m580a(dialogInterface, i);
            }
        }).m9090b("NO", null).m9101a(17301543).m9089c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public /* synthetic */ void m578k() {
        if (!m581i()) {
            m582h();
        } else if (!isModuleActive()) {
            m579j();
        } else {
            startActivity(new Intent(this, FgpsActivity.class));
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // hamba.allah.swt.Base, android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.FragmentActivity, android.support.p000v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(512, 512);
        }
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() { // from class: hamba.allah.swt.-$$Lambda$SplashActivity$vK9CPCPL8lVynp3PsoCF3aflu_Q
            @Override // java.lang.Runnable
            public final void run() {
                SplashActivity.this.m578k();
            }
        }, 2000L);
    }
}
