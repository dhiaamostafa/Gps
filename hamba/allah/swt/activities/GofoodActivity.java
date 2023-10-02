package hamba.allah.swt.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.p003v7.app.ActionBar;
import android.support.p003v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import hamba.allah.swt.Base;
import hamba.allah.swt.R;
import hamba.allah.swt.utils.SharedPreferenceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class GofoodActivity extends Base {

    /* renamed from: e */
    private static String f9991e = "GofoodActivity";

    /* renamed from: c */
    SharedPreferenceUtils f9992c;

    /* renamed from: d */
    ArrayList<HashMap<String, String>> f9993d;

    /* renamed from: f */
    private ProgressDialog f9994f;

    /* renamed from: g */
    private ListView f9995g;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m515a(DialogInterface dialogInterface, int i) {
        startActivity(new Intent(this, FgpsActivity.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m514a(AdapterView adapterView, View view, int i, long j) {
        String[] split = ((TextView) view.findViewById(R.id.gf_coor)).getText().toString().split(",");
        String str = split[0];
        String str2 = split[1];
        FgpsActivity.f9951d = Double.valueOf(str);
        FgpsActivity.f9952e = Double.valueOf(str2);
        this.f9992c.m443a("lat", String.valueOf(FgpsActivity.f9951d));
        this.f9992c.m443a("lon", String.valueOf(FgpsActivity.f9952e));
        startActivity(new Intent(this, FgpsActivity.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m513a(VolleyError volleyError) {
        String str = f9991e;
        VolleyLog.m8177b(str, "Error: " + volleyError.getMessage());
        mo575a(volleyError.getMessage());
        m508l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m512a(JSONArray jSONArray) {
        Log.d(f9991e, jSONArray.toString());
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String string = jSONObject.getString("name");
                String string2 = jSONObject.getString("address");
                String string3 = jSONObject.getString("phone");
                String string4 = jSONObject.getString("latLong");
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("gf_name", string);
                hashMap.put("gf_address", string2);
                hashMap.put("gf_phone", string3);
                hashMap.put("gf_coor", string4);
                this.f9993d.add(hashMap);
            } catch (JSONException e) {
                e.printStackTrace();
                mo575a("Error: " + e.getMessage());
            }
        }
        this.f9995g.setAdapter((ListAdapter) new SimpleAdapter(this, this.f9993d, R.layout.item_layout, new String[]{"gf_name", "gf_address", "gf_phone", "gf_coor"}, new int[]{R.id.gf_name, R.id.gf_address, R.id.gf_phone, R.id.gf_coor}));
        this.f9995g.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$GofoodActivity$tAc9GuMOVlXiFoh55dkkJBdLXEU
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i2, long j) {
                GofoodActivity.this.m511b(adapterView, view, i2, j);
            }
        });
        this.f9995g.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$GofoodActivity$FggF4zWeTNqzBQePdwUJNMvHQy4
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i2, long j) {
                GofoodActivity.this.m514a(adapterView, view, i2, j);
            }
        });
        m508l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m511b(AdapterView adapterView, View view, int i, long j) {
        mo575a(((TextView) view).getText().toString());
    }

    /* renamed from: j */
    private void m510j() {
        m509k();
        Base.m585e().m589a(new JsonArrayRequest("https://api.gojekapi.com/gojek/merchant/find?limit=50&location=" + (String.valueOf(FgpsActivity.f9951d) + "," + String.valueOf(FgpsActivity.f9952e)), new Response.Listener() { // from class: hamba.allah.swt.activities.-$$Lambda$GofoodActivity$6-LIsHq6tmZHBpyCv3brleJwwsc
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                GofoodActivity.this.m512a((JSONArray) obj);
            }
        }, new Response.ErrorListener() { // from class: hamba.allah.swt.activities.-$$Lambda$GofoodActivity$vjefvReqWdOxqVGdBROQHfQZPWY
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                GofoodActivity.this.m513a(volleyError);
            }
        }) { // from class: hamba.allah.swt.activities.GofoodActivity.2
            @Override // com.android.volley.Request
            /* renamed from: j */
            public Map<String, String> mo507j() {
                HashMap hashMap = new HashMap();
                hashMap.put("Authorization", "Bearer " + GofoodActivity.this.f9992c.m441b("keyGojek", ""));
                return hashMap;
            }
        }, "GofoodRequest");
    }

    /* renamed from: k */
    private void m509k() {
        if (this.f9994f.isShowing()) {
            return;
        }
        this.f9994f.show();
    }

    /* renamed from: l */
    private void m508l() {
        if (this.f9994f.isShowing()) {
            this.f9994f.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // hamba.allah.swt.Base, android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.FragmentActivity, android.support.p000v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gofood_activity);
        ActionBar a = m9088a();
        a.getClass();
        a.mo8947b(true);
        m9088a().mo8952a(true);
        this.f9992c = SharedPreferenceUtils.m444a(this);
        this.f9993d = new ArrayList<>();
        this.f9995g = (ListView) findViewById(R.id.listv);
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest m8061a = new AdRequest.Builder().m8061a();
        adView.setAdListener(new AdListener() { // from class: hamba.allah.swt.activities.GofoodActivity.1
            @Override // com.google.android.gms.ads.AdListener
            /* renamed from: a */
            public void mo502a() {
            }

            @Override // com.google.android.gms.ads.AdListener
            /* renamed from: a */
            public void mo501a(int i) {
            }

            @Override // com.google.android.gms.ads.AdListener
            /* renamed from: b */
            public void mo500b() {
                super.mo500b();
            }

            @Override // com.google.android.gms.ads.AdListener
            /* renamed from: c */
            public void mo499c() {
            }

            @Override // com.google.android.gms.ads.AdListener
            /* renamed from: d */
            public void mo498d() {
            }
        });
        adView.mo8029a(m8061a);
        this.f9994f = new ProgressDialog(this);
        this.f9994f.setMessage("Please wait...");
        this.f9994f.setCancelable(false);
        if (FgpsActivity.f9951d == null) {
            new AlertDialog.Builder(this).m9096a("Unknown Location").m9091b("Tentukan Lokasi terlebih dahulu di FGPS.").m9101a(17301543).m9095a("OK", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$GofoodActivity$RZvJyJxLnUR9THBXVXXsRifcYL4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    GofoodActivity.this.m515a(dialogInterface, i);
                }
            }).m9089c();
        } else {
            m510j();
        }
    }

    @Override // android.support.p000v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (FgpsActivity.f9951d != null) {
            m510j();
        }
    }
}
