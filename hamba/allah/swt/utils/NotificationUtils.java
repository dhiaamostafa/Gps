package hamba.allah.swt.utils;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.p000v4.app.NotificationCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;
import hamba.allah.swt.R;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/* loaded from: classes.dex */
public class NotificationUtils {

    /* renamed from: a */
    private static String f10045a = "NotificationUtils";

    /* renamed from: b */
    private Context f10046b;

    public NotificationUtils(Context context) {
        this.f10046b = context;
    }

    /* renamed from: a */
    private void m459a(Bitmap bitmap, NotificationCompat.Builder builder, int i, String str, String str2, String str3, PendingIntent pendingIntent, Uri uri) {
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle(str);
        bigPictureStyle.setSummaryText(Html.fromHtml(str2).toString());
        bigPictureStyle.bigPicture(bitmap);
        ((NotificationManager) this.f10046b.getSystemService("notification")).notify(101, builder.setSmallIcon(i).setTicker(str).setWhen(0L).setAutoCancel(true).setContentTitle(str).setContentIntent(pendingIntent).setSound(uri).setStyle(bigPictureStyle).setWhen(m454b(str3)).setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(this.f10046b.getResources(), i)).setContentText(str2).build());
    }

    /* renamed from: a */
    private void m458a(NotificationCompat.Builder builder, int i, String str, String str2, String str3, PendingIntent pendingIntent, Uri uri) {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(str2);
        ((NotificationManager) this.f10046b.getSystemService("notification")).notify(100, builder.setSmallIcon(i).setTicker(str).setWhen(0L).setAutoCancel(true).setContentTitle(str).setContentIntent(pendingIntent).setSound(uri).setStyle(inboxStyle).setWhen(m454b(str3)).setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(this.f10046b.getResources(), i)).setContentText(str2).build());
    }

    /* renamed from: a */
    public static boolean m460a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        boolean z = true;
        if (Build.VERSION.SDK_INT <= 20) {
            return !activityManager.getRunningTasks(1).get(0).topActivity.getPackageName().equals(context.getPackageName());
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.importance == 100) {
                boolean z2 = z;
                for (String str : runningAppProcessInfo.pkgList) {
                    if (str.equals(context.getPackageName())) {
                        z2 = false;
                    }
                }
                z = z2;
            }
        }
        return z;
    }

    /* renamed from: b */
    public static long m454b(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /* renamed from: a */
    public Bitmap m457a(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void m461a() {
        try {
            RingtoneManager.getRingtone(this.f10046b, Uri.parse("android.resource://" + this.f10046b.getPackageName() + "/raw/notification")).play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m456a(String str, String str2, String str3, Intent intent) {
        m455a(str, str2, str3, intent, null);
    }

    /* renamed from: a */
    public void m455a(String str, String str2, String str3, Intent intent, String str4) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        intent.setFlags(603979776);
        PendingIntent activity = PendingIntent.getActivity(this.f10046b, 0, intent, 268435456);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.f10046b);
        Uri parse = Uri.parse("android.resource://" + this.f10046b.getPackageName() + "/raw/notification");
        if (TextUtils.isEmpty(str4)) {
            m458a(builder, R.drawable.ic_fgpslogo, str, str2, str3, activity, parse);
            m461a();
        } else if (str4 == null || str4.length() <= 4 || !Patterns.WEB_URL.matcher(str4).matches()) {
        } else {
            Bitmap m457a = m457a(str4);
            if (m457a != null) {
                m459a(m457a, builder, R.drawable.ic_fgpslogo, str, str2, str3, activity, parse);
            } else {
                m458a(builder, R.drawable.ic_fgpslogo, str, str2, str3, activity, parse);
            }
        }
    }
}
