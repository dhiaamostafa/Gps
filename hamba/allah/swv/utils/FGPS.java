package hamba.allah.swv.utils;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.SystemClock;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import hamba.allah.swv.App;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
/* loaded from: classes.dex */
public class FGPS implements IXposedHookLoadPackage {
    /* renamed from: a */
    private void m473a(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        Method[] declaredMethods;
        XposedHelpers.findAndHookMethod(App.m595a("YW5kcm9pZC5sb2NhdGlvbi5Mb2NhdGlvbg=="), loadPackageParam.classLoader, App.m595a("Z2V0TGF0aXR1ZGU="), new Object[]{new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.3
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                super.beforeHookedMethod(methodHookParam);
                if (Readable.m452a().f10071l.booleanValue()) {
                    methodHookParam.setResult(Double.valueOf(Readable.m452a().f10060a));
                }
            }
        }});
        XposedHelpers.findAndHookMethod(App.m595a("YW5kcm9pZC5sb2NhdGlvbi5Mb2NhdGlvbg=="), loadPackageParam.classLoader, App.m595a("Z2V0TG9uZ2l0dWRl"), new Object[]{new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.4
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                super.beforeHookedMethod(methodHookParam);
                if (Readable.m452a().f10071l.booleanValue()) {
                    methodHookParam.setResult(Double.valueOf(Readable.m452a().f10061b));
                }
            }
        }});
        XposedHelpers.findAndHookMethod(App.m595a("YW5kcm9pZC5sb2NhdGlvbi5Mb2NhdGlvbg=="), loadPackageParam.classLoader, App.m595a("Z2V0QWNjdXJhY3k="), new Object[]{new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.5
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                super.beforeHookedMethod(methodHookParam);
                if (Readable.m452a().f10071l.booleanValue()) {
                    methodHookParam.setResult(Float.valueOf(0.1f));
                }
            }
        }});
        XposedHelpers.findAndHookMethod(App.m595a("YW5kcm9pZC5sb2NhdGlvbi5Mb2NhdGlvbk1hbmFnZXI="), loadPackageParam.classLoader, App.m595a("Z2V0TGFzdEtub3duTG9jYXRpb24="), new Object[]{String.class, new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.6
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (!Readable.m452a().f10071l.booleanValue() || methodHookParam.getResult() == null) {
                    return;
                }
                Location location = new Location("network");
                location.setLatitude(Double.valueOf(Readable.m452a().f10060a).doubleValue());
                location.setLongitude(Double.valueOf(Readable.m452a().f10061b).doubleValue());
                location.setAccuracy(0.1f);
            }
        }});
        XposedHelpers.findAndHookMethod(App.m595a("YW5kcm9pZC5sb2NhdGlvbi5Mb2NhdGlvbk1hbmFnZXIuTGlzdGVuZXJUcmFuc3BvcnQ="), loadPackageParam.classLoader, App.m595a("b25Mb2NhdGlvbkNoYW5nZWQ="), new Object[]{Location.class, new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.7
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (Readable.m452a().f10071l.booleanValue()) {
                    Location location = new Location("network");
                    location.setLatitude(Double.valueOf(Readable.m452a().f10060a).doubleValue());
                    location.setLongitude(Double.valueOf(Readable.m452a().f10061b).doubleValue());
                    location.setAccuracy(0.1f);
                }
            }
        }});
        XposedHelpers.findAndHookMethod(LocationManager.class, App.m595a("Z2V0TGFzdExvY2F0aW9u"), new Object[]{new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.8
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (Readable.m452a().f10071l.booleanValue()) {
                    Location location = new Location("network");
                    location.setLatitude(Double.valueOf(Readable.m452a().f10060a).doubleValue());
                    location.setLongitude(Double.valueOf(Readable.m452a().f10061b).doubleValue());
                    location.setAccuracy(0.1f);
                    location.setTime(0L);
                    methodHookParam.setResult(location);
                }
            }
        }});
        XposedHelpers.findAndHookMethod(LocationManager.class, App.m595a("Z2V0TGFzdEtub3duTG9jYXRpb24="), new Object[]{String.class, new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.9
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (Readable.m452a().f10071l.booleanValue()) {
                    Location location = new Location("network");
                    location.setLatitude(Double.valueOf(Readable.m452a().f10060a).doubleValue());
                    location.setLongitude(Double.valueOf(Readable.m452a().f10061b).doubleValue());
                    location.setAccuracy(0.1f);
                    location.setTime(System.currentTimeMillis());
                    if (Build.VERSION.SDK_INT >= 17) {
                        location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
                    }
                    methodHookParam.setResult(location);
                }
            }
        }});
        XposedHelpers.findAndHookMethod(App.m595a("YW5kcm9pZC5sb2NhdGlvbi5Mb2NhdGlvbk1hbmFnZXI="), loadPackageParam.classLoader, "getGpsStatus", new Object[]{GpsStatus.class, new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.10
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                if (((GpsStatus) methodHookParam.getResult()) == null) {
                    return;
                }
                Method method = null;
                Method[] declaredMethods2 = GpsStatus.class.getDeclaredMethods();
                int length = declaredMethods2.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Method method2 = declaredMethods2[i];
                    if (method2.getName().equals("setStatus") && method2.getParameterTypes().length > 1) {
                        method = method2;
                        break;
                    }
                    i++;
                }
                if (method == null) {
                    return;
                }
                method.setAccessible(true);
            }
        }});
        for (Method method : LocationManager.class.getDeclaredMethods()) {
            if (method.getName().equals("requestLocationUpdates") && !Modifier.isAbstract(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
                XposedBridge.hookMethod(method, new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.11
                    protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                        if (methodHookParam.args.length < 4 || !(methodHookParam.args[3] instanceof LocationListener)) {
                            return;
                        }
                        LocationListener locationListener = (LocationListener) methodHookParam.args[3];
                        Method method2 = null;
                        Method[] declaredMethods2 = LocationListener.class.getDeclaredMethods();
                        int length = declaredMethods2.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            Method method3 = declaredMethods2[i];
                            if (method3.getName().equals(App.m595a("b25Mb2NhdGlvbkNoYW5nZWQ=")) && !Modifier.isAbstract(method3.getModifiers())) {
                                method2 = method3;
                                break;
                            }
                            i++;
                        }
                        if (Readable.m452a().f10071l.booleanValue()) {
                            Location location = new Location("network");
                            location.setLatitude(Double.valueOf(Readable.m452a().f10060a).doubleValue());
                            location.setLongitude(Double.valueOf(Readable.m452a().f10061b).doubleValue());
                            location.setAccuracy(0.1f);
                            location.setTime(System.currentTimeMillis());
                            if (Build.VERSION.SDK_INT >= 17) {
                                location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
                            }
                            XposedHelpers.callMethod(locationListener, App.m595a("b25Mb2NhdGlvbkNoYW5nZWQ="), new Object[]{location});
                            if (method2 != null) {
                                try {
                                    method2.invoke(locationListener, location);
                                } catch (Exception e) {
                                    XposedBridge.log(e);
                                }
                            }
                        }
                    }
                });
            }
            if (method.getName().equals("requestSingleUpdate ") && !Modifier.isAbstract(method.getModifiers()) && Modifier.isPublic(method.getModifiers())) {
                XposedBridge.hookMethod(method, new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.12
                    protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                        if (methodHookParam.args.length < 3 || !(methodHookParam.args[1] instanceof LocationListener)) {
                            return;
                        }
                        LocationListener locationListener = (LocationListener) methodHookParam.args[3];
                        Method method2 = null;
                        Method[] declaredMethods2 = LocationListener.class.getDeclaredMethods();
                        int length = declaredMethods2.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            Method method3 = declaredMethods2[i];
                            if (method3.getName().equals(App.m595a("b25Mb2NhdGlvbkNoYW5nZWQ=")) && !Modifier.isAbstract(method3.getModifiers())) {
                                method2 = method3;
                                break;
                            }
                            i++;
                        }
                        if (!Readable.m452a().f10071l.booleanValue() || method2 == null) {
                            return;
                        }
                        try {
                            Location location = new Location("network");
                            location.setLatitude(Double.valueOf(Readable.m452a().f10060a).doubleValue());
                            location.setLongitude(Double.valueOf(Readable.m452a().f10061b).doubleValue());
                            location.setAccuracy(0.1f);
                            location.setTime(System.currentTimeMillis());
                            if (Build.VERSION.SDK_INT >= 17) {
                                location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
                            }
                            method2.invoke(locationListener, location);
                        } catch (Exception e) {
                            XposedBridge.log(e);
                        }
                    }
                });
            }
        }
    }

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        if (loadPackageParam.packageName.equals("hamba.allah.swv")) {
            XposedHelpers.findAndHookMethod("hamba.allah.swv.Base", loadPackageParam.classLoader, "isModuleActive", new Object[]{new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.1
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
                    methodHookParam.setResult(true);
                }
            }});
        }
        m473a(loadPackageParam);
        XposedHelpers.findAndHookMethod(App.m595a("YW5kcm9pZC5sb2NhdGlvbi5Mb2NhdGlvbg=="), loadPackageParam.classLoader, App.m595a("Z2V0QWNjdXJhY3k="), new Object[]{new XC_MethodHook() { // from class: hamba.allah.swv.utils.FGPS.2
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable {
                super.beforeHookedMethod(methodHookParam);
                methodHookParam.setResult(Float.valueOf(0.1f));
            }
        }});
    }
}
