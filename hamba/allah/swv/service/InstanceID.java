package hamba.allah.swv.service;

import android.content.Intent;
import android.support.p001v4.content.LocalBroadcastManager;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
/* loaded from: classes.dex */
public class InstanceID extends FirebaseInstanceIdService {

    /* renamed from: b */
    private static final String f10030b = "InstanceID";

    /* renamed from: a */
    private void m485a(String str) {
        String str2 = f10030b;
        Log.e(str2, "sendRegistrationToServer: " + str);
    }

    @Override // com.google.firebase.iid.FirebaseInstanceIdService
    /* renamed from: a */
    public void mo486a() {
        super.mo486a();
        String m1038e = FirebaseInstanceId.m1052a().m1038e();
        m485a(m1038e);
        Intent intent = new Intent("registrationComplete");
        intent.putExtra("token", m1038e);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
