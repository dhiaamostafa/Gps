package hamba.allah.swv;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.p004v7.app.AlertDialog;
import hamba.allah.swv.activities.FgpsActivity;
/* loaded from: classes.dex */
public class SplashActivity extends Base {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m583a(DialogInterface dialogInterface, int i) {
        m586g();
    }

    /* renamed from: j */
    private void m582j() {
        new AlertDialog.Builder(this).m9138a("FGPS Belum Aktif").m9133b("FGPS tidak bisa berjalan tanpa Xposed\nCeklis FGPS di Xposed Module lalu restart HP.").m9137a("Open Xposed", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.-$$Lambda$SplashActivity$V9eROntZkiWXBKnCvf92i0sNq5A
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SplashActivity.this.m583a(dialogInterface, i);
            }
        }).m9132b("NO", null).m9143a(17301543).m9131c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public /* synthetic */ void m581k() {
        if (!m584i()) {
            m585h();
        } else if (!isModuleActive()) {
            m582j();
        } else {
            startActivity(new Intent(this, FgpsActivity.class));
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // hamba.allah.swv.Base, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(512, 512);
        }
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() { // from class: hamba.allah.swv.-$$Lambda$SplashActivity$vK9CPCPL8lVynp3PsoCF3aflu_Q
            @Override // java.lang.Runnable
            public final void run() {
                SplashActivity.this.m581k();
            }
        }, 2000L);
    }
}
