package hamba.allah.swv.service;

import android.content.Context;
import android.content.Intent;
import android.support.p001v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import hamba.allah.swv.SplashActivity;
import hamba.allah.swv.activities.AdsActivity;
import hamba.allah.swv.utils.NotificationUtils;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class Messaging extends FirebaseMessagingService {

    /* renamed from: b */
    private static final String f10031b = "Messaging";

    /* renamed from: c */
    private NotificationUtils f10032c;

    /* renamed from: a */
    private void m484a(Context context, String str, String str2, String str3, Intent intent) {
        this.f10032c = new NotificationUtils(context);
        intent.setFlags(268468224);
        this.f10032c.m459a(str, str2, str3, intent);
    }

    /* renamed from: a */
    private void m483a(Context context, String str, String str2, String str3, Intent intent, String str4) {
        this.f10032c = new NotificationUtils(context);
        intent.setFlags(268468224);
        this.f10032c.m458a(str, str2, str3, intent, str4);
    }

    /* renamed from: a */
    private void m481a(JSONObject jSONObject) {
        String str = f10031b;
        Log.e(str, "push json: " + jSONObject.toString());
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            String string = jSONObject2.getString("title");
            String string2 = jSONObject2.getString("message");
            boolean z = jSONObject2.getBoolean("is_background");
            String string3 = jSONObject2.getString("image");
            String string4 = jSONObject2.getString("timestamp");
            JSONObject jSONObject3 = jSONObject2.getJSONObject("payload");
            String str2 = f10031b;
            Log.e(str2, "title: " + string);
            String str3 = f10031b;
            Log.e(str3, "message: " + string2);
            String str4 = f10031b;
            Log.e(str4, "isBackground: " + z);
            String str5 = f10031b;
            Log.e(str5, "payload: " + jSONObject3.toString());
            String str6 = f10031b;
            Log.e(str6, "imageUrl: " + string3);
            String str7 = f10031b;
            Log.e(str7, "timestamp: " + string4);
            Intent intent = new Intent(this, AdsActivity.class);
            intent.putExtra("from_id", "iklan");
            intent.putExtra("title", string);
            intent.putExtra("content", string2);
            startActivity(intent);
            if (NotificationUtils.m463a(getApplicationContext())) {
                Intent intent2 = new Intent(getApplicationContext(), SplashActivity.class);
                intent2.putExtra("message", string2);
                if (TextUtils.isEmpty(string3)) {
                    m484a(getApplicationContext(), string, string2, string4, intent2);
                } else {
                    m483a(getApplicationContext(), string, string2, string4, intent2, string3);
                }
            } else {
                Intent intent3 = new Intent("pushNotification");
                intent3.putExtra("message", string2);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent3);
                new NotificationUtils(getApplicationContext()).m464a();
            }
        } catch (JSONException e) {
            String str8 = f10031b;
            Log.e(str8, "Json Exception: " + e.getMessage());
        } catch (Exception e2) {
            String str9 = f10031b;
            Log.e(str9, "Exception: " + e2.getMessage());
        }
    }

    /* renamed from: b */
    private void m480b(String str) {
        if (NotificationUtils.m463a(getApplicationContext())) {
            return;
        }
        Intent intent = new Intent("pushNotification");
        intent.putExtra("message", str);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        new NotificationUtils(getApplicationContext()).m464a();
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    /* renamed from: a */
    public void mo482a(RemoteMessage remoteMessage) {
        String str = f10031b;
        Log.e(str, "From: " + remoteMessage.m907a());
        if (remoteMessage == null) {
            return;
        }
        if (remoteMessage.m905c() != null) {
            String str2 = f10031b;
            Log.e(str2, "Notification Body: " + remoteMessage.m905c().m904a());
            m480b(remoteMessage.m905c().m904a());
        }
        if (remoteMessage.m906b().size() > 0) {
            String str3 = f10031b;
            Log.e(str3, "Data Payload: " + remoteMessage.m906b().toString());
            try {
                m481a(new JSONObject(remoteMessage.m906b().toString()));
            } catch (Exception e) {
                String str4 = f10031b;
                Log.e(str4, "Exception: " + e.getMessage());
            }
        }
    }
}
