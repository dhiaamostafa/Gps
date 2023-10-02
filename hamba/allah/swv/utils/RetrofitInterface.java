package hamba.allah.swv.utils;

import hamba.allah.swv.model.ModelList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
/* loaded from: classes.dex */
public interface RetrofitInterface {
    @GET("users/module/{key}")
    Call<ModelList> getKey(@Path("key") String str);

    @GET("users/module/fgps")
    Call<ModelList> update();
}
