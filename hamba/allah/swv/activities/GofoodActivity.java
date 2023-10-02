package hamba.allah.swv.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.p004v7.app.ActionBar;
import android.support.p004v7.app.AlertDialog;
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
import hamba.allah.swv.Base;
import hamba.allah.swv.R;
import hamba.allah.swv.utils.SharedPreferenceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class GofoodActivity extends Base {

    /* renamed from: e */
    private static String f9993e = "GofoodActivity";

    /* renamed from: c */
    SharedPreferenceUtils f9994c;

    /* renamed from: d */
    ArrayList<HashMap<String, String>> f9995d;

    /* renamed from: f */
    private ProgressDialog f9996f;

    /* renamed from: g */
    private ListView f9997g;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m518a(DialogInterface dialogInterface, int i) {
        startActivity(new Intent(this, FgpsActivity.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m517a(AdapterView adapterView, View view, int i, long j) {
        String[] split = ((TextView) view.findViewById(R.id.gf_coor)).getText().toString().split(",");
        String str = split[0];
        String str2 = split[1];
        FgpsActivity.f9953d = Double.valueOf(str);
        FgpsActivity.f9954e = Double.valueOf(str2);
        this.f9994c.m446a("lat", String.valueOf(FgpsActivity.f9953d));
        this.f9994c.m446a("lon", String.valueOf(FgpsActivity.f9954e));
        startActivity(new Intent(this, FgpsActivity.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m516a(VolleyError volleyError) {
        String str = f9993e;
        VolleyLog.m8219b(str, "Error: " + volleyError.getMessage());
        mo578a(volleyError.getMessage());
        m511l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m515a(JSONArray jSONArray) {
        Log.d(f9993e, jSONArray.toString());
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
                this.f9995d.add(hashMap);
            } catch (JSONException e) {
                e.printStackTrace();
                mo578a("Error: " + e.getMessage());
            }
        }
        this.f9997g.setAdapter((ListAdapter) new SimpleAdapter(this, this.f9995d, R.layout.item_layout, new String[]{"gf_name", "gf_address", "gf_phone", "gf_coor"}, new int[]{R.id.gf_name, R.id.gf_address, R.id.gf_phone, R.id.gf_coor}));
        this.f9997g.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$GofoodActivity$tAc9GuMOVlXiFoh55dkkJBdLXEU
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i2, long j) {
                GofoodActivity.this.m514b(adapterView, view, i2, j);
            }
        });
        this.f9997g.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$GofoodActivity$FggF4zWeTNqzBQePdwUJNMvHQy4
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i2, long j) {
                GofoodActivity.this.m517a(adapterView, view, i2, j);
            }
        });
        m511l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m514b(AdapterView adapterView, View view, int i, long j) {
        mo578a(((TextView) view).getText().toString());
    }

    /* renamed from: j */
    private void m513j() {
        m512k();
        Base.m588e().m592a(new JsonArrayRequest("https://api.gojekapi.com/gojek/merchant/find?limit=50&location=" + (String.valueOf(FgpsActivity.f9953d) + "," + String.valueOf(FgpsActivity.f9954e)), new Response.Listener() { // from class: hamba.allah.swv.activities.-$$Lambda$GofoodActivity$6-LIsHq6tmZHBpyCv3brleJwwsc
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                GofoodActivity.this.m515a((JSONArray) obj);
            }
        }, new Response.ErrorListener() { // from class: hamba.allah.swv.activities.-$$Lambda$GofoodActivity$vjefvReqWdOxqVGdBROQHfQZPWY
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                GofoodActivity.this.m516a(volleyError);
            }
        }) { // from class: hamba.allah.swv.activities.GofoodActivity.2
            @Override // com.android.volley.Request
            /* renamed from: j */
            public Map<String, String> mo510j() {
                HashMap hashMap = new HashMap();
                hashMap.put("Authorization", "Bearer " + GofoodActivity.this.f9994c.m444b("keyGojek", ""));
                return hashMap;
            }
        }, "GofoodRequest");
    }

    /* renamed from: k */
    private void m512k() {
        if (this.f9996f.isShowing()) {
            return;
        }
        this.f9996f.show();
    }

    /* renamed from: l */
    private void m511l() {
        if (this.f9996f.isShowing()) {
            this.f9996f.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // hamba.allah.swv.Base, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gofood_activity);
        ActionBar a = m9130a();
        a.getClass();
        a.mo8989b(true);
        m9130a().mo8994a(true);
        this.f9994c = SharedPreferenceUtils.m447a(this);
        this.f9995d = new ArrayList<>();
        this.f9997g = (ListView) findViewById(R.id.listv);
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest m8070a = new AdRequest.Builder().m8070a();
        adView.setAdListener(new AdListener() { // from class: hamba.allah.swv.activities.GofoodActivity.1
            @Override // com.google.android.gms.ads.AdListener
            /* renamed from: a */
            public void mo505a() {
            }

            @Override // com.google.android.gms.ads.AdListener
            /* renamed from: a */
            public void mo504a(int i) {
            }

            @Override // com.google.android.gms.ads.AdListener
            /* renamed from: b */
            public void mo503b() {
                super.mo503b();
            }

            @Override // com.google.android.gms.ads.AdListener
            /* renamed from: c */
            public void mo502c() {
            }

            @Override // com.google.android.gms.ads.AdListener
            /* renamed from: d */
            public void mo501d() {
            }
        });
        adView.mo8038a(m8070a);
        this.f9996f = new ProgressDialog(this);
        this.f9996f.setMessage("Please wait...");
        this.f9996f.setCancelable(false);
        if (FgpsActivity.f9953d == null) {
            new AlertDialog.Builder(this).m9138a("Unknown Location").m9133b("Tentukan Lokasi terlebih dahulu di FGPS.").m9143a(17301543).m9137a("OK", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$GofoodActivity$RZvJyJxLnUR9THBXVXXsRifcYL4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    GofoodActivity.this.m518a(dialogInterface, i);
                }
            }).m9131c();
        } else {
            m513j();
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (FgpsActivity.f9953d != null) {
            m513j();
        }
    }
}
