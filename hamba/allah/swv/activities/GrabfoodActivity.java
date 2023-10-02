package hamba.allah.swv.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import hamba.allah.swv.Base;
import hamba.allah.swv.R;
import hamba.allah.swv.activities.GrabfoodActivity;
import hamba.allah.swv.utils.HttpHandler;
import hamba.allah.swv.utils.SharedPreferenceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class GrabfoodActivity extends Base {

    /* renamed from: e */
    private static String f10000e = "GrabfoodActivity";

    /* renamed from: c */
    SharedPreferenceUtils f10001c;

    /* renamed from: d */
    ArrayList<HashMap<String, String>> f10002d;

    /* renamed from: f */
    private ProgressDialog f10003f;

    /* renamed from: g */
    private ListView f10004g;

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    /* loaded from: classes.dex */
    public class GetContacts extends AsyncTask<Void, Void, Void> {
        private GetContacts() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public /* synthetic */ void m500a() {
            Toast.makeText(GrabfoodActivity.this, "Couldn't get data from server. Try to move your marker", 1).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public /* synthetic */ void m499a(AdapterView adapterView, View view, int i, long j) {
            String[] split = ((TextView) view.findViewById(R.id.gf_coor)).getText().toString().split(",");
            String str = split[0];
            String str2 = split[1];
            FgpsActivity.f9953d = Double.valueOf(str);
            FgpsActivity.f9954e = Double.valueOf(str2);
            GrabfoodActivity.this.f10001c.m446a("lat", String.valueOf(FgpsActivity.f9953d));
            GrabfoodActivity.this.f10001c.m446a("lon", String.valueOf(FgpsActivity.f9954e));
            GrabfoodActivity.this.startActivity(new Intent(GrabfoodActivity.this, FgpsActivity.class));
            GrabfoodActivity.this.finish();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public /* synthetic */ void m497a(JSONException jSONException) {
            GrabfoodActivity grabfoodActivity = GrabfoodActivity.this;
            Toast.makeText(grabfoodActivity, "Json parsing error: " + jSONException.getMessage(), 1).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m495b(AdapterView adapterView, View view, int i, long j) {
            Toast.makeText(GrabfoodActivity.this, ((TextView) view).getText().toString(), 1).show();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            String m466a = new HttpHandler().m466a("https://grab-food.grabtaxi.com/api/v1/restaurants?latlng=" + (String.valueOf(FgpsActivity.f9953d) + "," + String.valueOf(FgpsActivity.f9954e)), false);
            Log.e(GrabfoodActivity.f10000e, "Response from url: " + m466a);
            if (m466a == null) {
                Log.e(GrabfoodActivity.f10000e, "Couldn't get json from server.");
                GrabfoodActivity.this.runOnUiThread(new Runnable() { // from class: hamba.allah.swv.activities.-$$Lambda$GrabfoodActivity$GetContacts$2DWiOpadwgrGjDCQhMgeJgXvDtY
                    @Override // java.lang.Runnable
                    public final void run() {
                        GrabfoodActivity.GetContacts.this.m500a();
                    }
                });
                return null;
            }
            try {
                JSONArray jSONArray = new JSONObject(m466a).getJSONArray("restaurants");
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
                    GrabfoodActivity.this.f10002d.add(hashMap);
                }
                return null;
            } catch (JSONException e) {
                Log.e(GrabfoodActivity.f10000e, "Json parsing error: " + e.getMessage());
                GrabfoodActivity.this.runOnUiThread(new Runnable() { // from class: hamba.allah.swv.activities.-$$Lambda$GrabfoodActivity$GetContacts$szWGNZK8izYkPUk6RnexJFs7_g4
                    @Override // java.lang.Runnable
                    public final void run() {
                        GrabfoodActivity.GetContacts.this.m497a(e);
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
            if (GrabfoodActivity.this.f10003f.isShowing()) {
                GrabfoodActivity.this.f10003f.dismiss();
            }
            GrabfoodActivity.this.f10004g.setAdapter((ListAdapter) new SimpleAdapter(GrabfoodActivity.this, GrabfoodActivity.this.f10002d, R.layout.item_layout, new String[]{"name", "address", "phone", "coor"}, new int[]{R.id.gf_name, R.id.gf_address, R.id.gf_phone, R.id.gf_coor}));
            GrabfoodActivity.this.f10004g.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$GrabfoodActivity$GetContacts$6MyuMDJZsKBpHd2wyTb5oZym99c
                @Override // android.widget.AdapterView.OnItemClickListener
                public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    GrabfoodActivity.GetContacts.this.m495b(adapterView, view, i, j);
                }
            });
            GrabfoodActivity.this.f10004g.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$GrabfoodActivity$GetContacts$l9nqTYOXjyaPyAKW3IhJ8QR4M4I
                @Override // android.widget.AdapterView.OnItemClickListener
                public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    GrabfoodActivity.GetContacts.this.m499a(adapterView, view, i, j);
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
    public /* synthetic */ void m509a(DialogInterface dialogInterface, int i) {
        startActivity(new Intent(this, FgpsActivity.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // hamba.allah.swv.Base, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gofood_activity);
        this.f10003f = new ProgressDialog(this);
        this.f10003f.setMessage("Please wait...");
        this.f10003f.show();
        ActionBar a = m9130a();
        a.getClass();
        a.mo8989b(true);
        m9130a().mo8994a(true);
        this.f10001c = SharedPreferenceUtils.m447a(this);
        this.f10002d = new ArrayList<>();
        this.f10004g = (ListView) findViewById(R.id.listv);
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest m8070a = new AdRequest.Builder().m8070a();
        adView.setAdListener(new AdListener() { // from class: hamba.allah.swv.activities.GrabfoodActivity.1
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
        if (FgpsActivity.f9953d == null) {
            new AlertDialog.Builder(this).m9138a("Unknown Location").m9133b("Tentukan Lokasi terlebih dahulu di FGPS.").m9143a(17301543).m9137a("OK", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$GrabfoodActivity$-wlfjPMwYWV2rC6XWrl3-oPYFOE
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    GrabfoodActivity.this.m509a(dialogInterface, i);
                }
            }).m9131c();
        } else {
            new GetContacts().execute(new Void[0]);
        }
    }

    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (FgpsActivity.f9953d != null) {
            new GetContacts().execute(new Void[0]);
        }
    }
}
