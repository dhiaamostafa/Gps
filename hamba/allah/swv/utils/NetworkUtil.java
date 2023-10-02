package hamba.allah.swv.utils;

import hamba.allah.swv.App;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/* loaded from: classes.dex */
public class NetworkUtil {
    /* renamed from: a */
    public static Retrofit m465a() {
        return new Retrofit.Builder().baseUrl(App.m595a("aHR0cHM6Ly9kb20tdGVhbS5jb20vYXBpL3YxLw==")).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().build()).build();
    }
}
