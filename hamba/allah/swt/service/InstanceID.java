package hamba.allah.swt.service;

import android.content.Intent;
import android.support.p000v4.content.LocalBroadcastManager;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
/* loaded from: classes.dex */
public class InstanceID extends FirebaseInstanceIdService {

    /* renamed from: b */
    private static final String f10028b = "InstanceID";

    /* renamed from: a */
    private void m482a(String str) {
        String str2 = f10028b;
        Log.e(str2, "sendRegistrationToServer: " + str);
    }

    @Override // com.google.firebase.iid.FirebaseInstanceIdService
    /* renamed from: a */
    public void mo483a() {
        super.mo483a();
        String m1029e = FirebaseInstanceId.m1043a().m1029e();
        m482a(m1029e);
        Intent intent = new Intent("registrationComplete");
        intent.putExtra("token", m1029e);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
