package hamba.allah.swt.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import hamba.allah.swt.Base;
import hamba.allah.swt.R;
import hamba.allah.swt.activities.GrabfoodActivity;
import hamba.allah.swt.utils.HttpHandler;
import hamba.allah.swt.utils.SharedPreferenceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class GrabfoodActivity extends Base {

    /* renamed from: e */
    private static String f9998e = "GrabfoodActivity";

    /* renamed from: c */
    SharedPreferenceUtils f9999c;

    /* renamed from: d */
    ArrayList<HashMap<String, String>> f10000d;

    /* renamed from: f */
    private ProgressDialog f10001f;

    /* renamed from: g */
    private ListView f10002g;

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    /* loaded from: classes.dex */
    public class GetContacts extends AsyncTask<Void, Void, Void> {
        private GetContacts() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public /* synthetic */ void m497a() {
            Toast.makeText(GrabfoodActivity.this, "Couldn't get data from server. Try to move your marker", 1).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public /* synthetic */ void m496a(AdapterView adapterView, View view, int i, long j) {
            String[] split = ((TextView) view.findViewById(R.id.gf_coor)).getText().toString().split(",");
            String str = split[0];
            String str2 = split[1];
            FgpsActivity.f9951d = Double.valueOf(str);
            FgpsActivity.f9952e = Double.valueOf(str2);
            GrabfoodActivity.this.f9999c.m443a("lat", String.valueOf(FgpsActivity.f9951d));
            GrabfoodActivity.this.f9999c.m443a("lon", String.valueOf(FgpsActivity.f9952e));
            GrabfoodActivity.this.startActivity(new Intent(GrabfoodActivity.this, FgpsActivity.class));
            GrabfoodActivity.this.finish();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public /* synthetic */ void m494a(JSONException jSONException) {
            GrabfoodActivity grabfoodActivity = GrabfoodActivity.this;
            Toast.makeText(grabfoodActivity, "Json parsing error: " + jSONException.getMessage(), 1).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m492b(AdapterView adapterView, View view, int i, long j) {
            Toast.makeText(GrabfoodActivity.this, ((TextView) view).getText().toString(), 1).show();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            String m463a = new HttpHandler().m463a("https://grab-food.grabtaxi.com/api/v1/restaurants?latlng=" + (String.valueOf(FgpsActivity.f9951d) + "," + String.valueOf(FgpsActivity.f9952e)), false);
            Log.e(GrabfoodActivity.f9998e, "Response from url: " + m463a);
            if (m463a == null) {
                Log.e(GrabfoodActivity.f9998e, "Couldn't get json from server.");
                GrabfoodActivity.this.runOnUiThread(new Runnable() { // from class: hamba.allah.swt.activities.-$$Lambda$GrabfoodActivity$GetContacts$2DWiOpadwgrGjDCQhMgeJgXvDtY
                    @Override // java.lang.Runnable
                    public final void run() {
                        GrabfoodActivity.GetContacts.this.m497a();
                    }
                });
                return null;
            }
            try {
                JSONArray jSONArray = new JSONObject(m463a).getJSONArray("restaurants");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    JSONObject jSONObject2 = jSONObject.getJSONObject("address");
                    String string = jSONObject2.getString("name");
                    String string2 = jSONObject2.getString("combined_address");
                    String string3 = jSONObject.getJSONObject("restaurantData").getString("phone_number");
                    JSONObject jSONObject3 = jSONObject.getJSONObject("latlng");
                    String string4 = jSONObject3.getString("latitude");
                    String string5 = jSONObject3.getString("longitude");
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("name", string);
                    hashMap.put("address", string2);
                    hashMap.put("phone", string3);
                    hashMap.put("lat", string4);
                    hashMap.put("lon", string5);
                    hashMap.put("coor", string4 + "," + string5);
                    GrabfoodActivity.this.f10000d.add(hashMap);
                }
                return null;
            } catch (JSONException e) {
                Log.e(GrabfoodActivity.f9998e, "Json parsing error: " + e.getMessage());
                GrabfoodActivity.this.runOnUiThread(new Runnable() { // from class: hamba.allah.swt.activities.-$$Lambda$GrabfoodActivity$GetContacts$szWGNZK8izYkPUk6RnexJFs7_g4
                    @Override // java.lang.Runnable
                    public final void run() {
                        GrabfoodActivity.GetContacts.this.m494a(e);
                    }
                });
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public void onPostExecute(Void r8) {
            super.onPostExecute(r8);
            if (GrabfoodActivity.this.f10001f.isShowing()) {
                GrabfoodActivity.this.f10001f.dismiss();
            }
            GrabfoodActivity.this.f10002g.setAdapter((ListAdapter) new SimpleAdapter(GrabfoodActivity.this, GrabfoodActivity.this.f10000d, R.layout.item_layout, new String[]{"name", "address", "phone", "coor"}, new int[]{R.id.gf_name, R.id.gf_address, R.id.gf_phone, R.id.gf_coor}));
            GrabfoodActivity.this.f10002g.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$GrabfoodActivity$GetContacts$6MyuMDJZsKBpHd2wyTb5oZym99c
                @Override // android.widget.AdapterView.OnItemClickListener
                public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    GrabfoodActivity.GetContacts.this.m492b(adapterView, view, i, j);
                }
            });
            GrabfoodActivity.this.f10002g.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$GrabfoodActivity$GetContacts$l9nqTYOXjyaPyAKW3IhJ8QR4M4I
                @Override // android.widget.AdapterView.OnItemClickListener
                public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    GrabfoodActivity.GetContacts.this.m496a(adapterView, view, i, j);
                }
            });
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m506a(DialogInterface dialogInterface, int i) {
        startActivity(new Intent(this, FgpsActivity.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // hamba.allah.swt.Base, android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.FragmentActivity, android.support.p000v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gofood_activity);
        this.f10001f = new ProgressDialog(this);
        this.f10001f.setMessage("Please wait...");
        this.f10001f.show();
        ActionBar a = m9088a();
        a.getClass();
        a.mo8947b(true);
        m9088a().mo8952a(true);
        this.f9999c = SharedPreferenceUtils.m444a(this);
        this.f10000d = new ArrayList<>();
        this.f10002g = (ListView) findViewById(R.id.listv);
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest m8061a = new AdRequest.Builder().m8061a();
        adView.setAdListener(new AdListener() { // from class: hamba.allah.swt.activities.GrabfoodActivity.1
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
        if (FgpsActivity.f9951d == null) {
            new AlertDialog.Builder(this).m9096a("Unknown Location").m9091b("Tentukan Lokasi terlebih dahulu di FGPS.").m9101a(17301543).m9095a("OK", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$GrabfoodActivity$-wlfjPMwYWV2rC6XWrl3-oPYFOE
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    GrabfoodActivity.this.m506a(dialogInterface, i);
                }
            }).m9089c();
        } else {
            new GetContacts().execute(new Void[0]);
        }
    }

    @Override // android.support.p000v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (FgpsActivity.f9951d != null) {
            new GetContacts().execute(new Void[0]);
        }
    }
}
