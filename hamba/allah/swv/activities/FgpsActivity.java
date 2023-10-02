package hamba.allah.swv.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.app.ActionBar;
import android.support.p004v7.app.AlertDialog;
import android.support.p004v7.widget.AppCompatImageView;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.hlab.fabrevealmenu.listeners.OnFABMenuSelectedListener;
import com.hlab.fabrevealmenu.view.FABRevealMenu;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import hamba.allah.swv.Base;
import hamba.allah.swv.R;
import hamba.allah.swv.adapter.BookmarkAdapter;
import hamba.allah.swv.adapter.HistoryAdapter;
import hamba.allah.swv.model.Bookmark;
import hamba.allah.swv.model.History;
import hamba.allah.swv.model.ModelList;
import hamba.allah.swv.service.GPSTracker;
import hamba.allah.swv.utils.BookmarkDBHandler;
import hamba.allah.swv.utils.HistoryDBHandler;
import hamba.allah.swv.utils.NetworkUtil;
import hamba.allah.swv.utils.PlaceAutocompleteAdapter;
import hamba.allah.swv.utils.RecyclerTouchListener;
import hamba.allah.swv.utils.RetrofitInterface;
import hamba.allah.swv.utils.SharedPreferenceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
/* loaded from: classes.dex */
public class FgpsActivity extends Base implements GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback, OnFABMenuSelectedListener {

    /* renamed from: I */
    private static final LatLngBounds f9952I = new LatLngBounds(new LatLng(-40.0d, -168.0d), new LatLng(71.0d, 136.0d));

    /* renamed from: d */
    public static Double f9953d;

    /* renamed from: e */
    public static Double f9954e;

    /* renamed from: B */
    private TextView f9956B;

    /* renamed from: C */
    private HistoryDBHandler f9957C;

    /* renamed from: D */
    private BookmarkDBHandler f9958D;

    /* renamed from: E */
    private BookmarkAdapter f9959E;

    /* renamed from: G */
    private TextView f9961G;

    /* renamed from: H */
    private GoogleApiClient f9962H;
    @BindView(R.id.btn_gojek)
    AppCompatImageView btn_gojek;
    @BindView(R.id.btn_grabbike)
    AppCompatImageView btn_grabbike;
    @BindView(R.id.btn_grabcar)
    AppCompatImageView btn_grabcar;

    /* renamed from: c */
    MapFragment f9963c;

    /* renamed from: f */
    SharedPreferenceUtils f9964f;
    @BindView(R.id.fab_location)
    ImageView fab_location;

    /* renamed from: g */
    LayoutInflater f9965g;
    @BindView(R.id.google_now)
    SmoothProgressBar google_now;

    /* renamed from: h */
    View f9966h;

    /* renamed from: i */
    AlertDialog f9967i;

    /* renamed from: j */
    RecyclerView.LayoutManager f9968j;

    /* renamed from: k */
    LayoutInflater f9969k;

    /* renamed from: l */
    View f9970l;

    /* renamed from: m */
    AlertDialog f9971m;

    /* renamed from: n */
    RecyclerView.LayoutManager f9972n;

    /* renamed from: o */
    AlertDialog.Builder f9973o;

    /* renamed from: p */
    protected GeoDataClient f9974p;

    /* renamed from: q */
    PlaceAutocompleteAdapter f9975q;

    /* renamed from: r */
    InterstitialAd f9976r;

    /* renamed from: s */
    RetrofitInterface f9977s;
    @BindView(R.id.searchbox)
    AutoCompleteTextView searchtxt;

    /* renamed from: t */
    private GoogleMap f9978t;
    @BindView(R.id.tv_lat)
    TextView tv_lat;
    @BindView(R.id.tv_lon)
    TextView tv_lon;

    /* renamed from: u */
    private Menu f9979u;

    /* renamed from: v */
    private boolean f9980v;

    /* renamed from: w */
    private String f9981w;

    /* renamed from: x */
    private String f9982x;

    /* renamed from: y */
    private String f9983y;

    /* renamed from: z */
    private HistoryAdapter f9984z;

    /* renamed from: A */
    private List<History> f9955A = new ArrayList();

    /* renamed from: F */
    private List<Bookmark> f9960F = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m572a(int i, DialogInterface dialogInterface, int i2) {
        if (i2 == 0) {
            m551a(true, this.f9960F.get(i), i);
        } else {
            m542c(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m570a(Location location) {
        if (location != null) {
            this.f9978t.m1481b(CameraUpdateFactory.m1489a(new LatLng(location.getLatitude(), location.getLongitude()), 18.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m569a(View view) {
        this.f9957C.m468c();
        m532j();
        mo578a("History Cleared");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m567a(AdapterView adapterView, View view, int i, long j) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
        final AutocompletePrediction item = this.f9975q.getItem(i);
        if (item != null) {
            Places.f9055c.mo1518a(this.f9962H, item.mo1530b()).mo7243a(new ResultCallback() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$uBVijMW5h-YIC6jb8zUhj9I2X3g
                @Override // com.google.android.gms.common.api.ResultCallback
                public final void onResult(Result result) {
                    FgpsActivity.this.m561a(item, (PlaceBuffer) result);
                }
            });
            CharSequence mo1529b = item.mo1529b(null);
            mo578a("Location : " + ((Object) mo1529b));
        }
        m528n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m566a(EditText editText, EditText editText2, DialogInterface dialogInterface, int i) {
        if (editText.getText().toString().matches("") || editText2.getText().toString().matches("")) {
            mo578a("Masukin dulu kordinatnya");
            return;
        }
        f9953d = Double.valueOf(editText.getText().toString());
        f9954e = Double.valueOf(editText2.getText().toString());
        this.f9979u.findItem(R.id.start).setIcon(17301539);
        this.f9964f.m445a("FGPS", true);
        this.f9964f.m446a("lat", String.valueOf(f9953d));
        this.f9964f.m446a("lon", String.valueOf(f9954e));
        m525q();
        m554a("History", String.valueOf(f9953d), String.valueOf(f9954e));
        if (m590a(GPSTracker.class)) {
            return;
        }
        startService(new Intent(this, GPSTracker.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m565a(EditText editText, EditText editText2, EditText editText3, android.app.AlertDialog alertDialog, boolean z, Bookmark bookmark, int i, View view) {
        if (TextUtils.isEmpty(editText.getText().toString()) || TextUtils.isEmpty(editText2.getText().toString()) || TextUtils.isEmpty(editText3.getText().toString())) {
            mo578a("Enter Bookmark !");
            return;
        }
        alertDialog.dismiss();
        if (!z || bookmark == null) {
            m544b(editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString());
        } else {
            m553a(editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m564a(EditText editText, EditText editText2, EditText editText3, DialogInterface dialogInterface, int i) {
        if (editText.getText().toString().matches("") || editText2.getText().toString().matches("") || editText3.getText().toString().matches("")) {
            mo578a("Isi yang bener");
        } else {
            m544b(editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m563a(VolleyError volleyError) {
        VolleyLog.m8219b("DUH", "Error: " + volleyError.getMessage());
        mo578a(volleyError.getMessage());
        this.google_now.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m561a(AutocompletePrediction autocompletePrediction, PlaceBuffer placeBuffer) {
        if (placeBuffer.mo1255w_().m7459d()) {
            LatLng mo1552b = placeBuffer.mo1593a(0).mo1552b();
            f9953d = Double.valueOf(mo1552b.f9279a);
            f9954e = Double.valueOf(mo1552b.f9280b);
            this.tv_lat.setText(getResources().getString(R.string.lat, String.valueOf(f9953d)));
            this.tv_lon.setText(getResources().getString(R.string.lon, String.valueOf(f9954e)));
            CharSequence mo1529b = autocompletePrediction.mo1529b(null);
            this.f9978t.m1482b();
            this.f9978t.m1484a(new MarkerOptions().m1327a(mo1552b).m1326a(String.valueOf(mo1529b)));
            this.f9978t.m1487a(CameraUpdateFactory.m1490a(mo1552b));
        }
        placeBuffer.mo5550c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m559a(LatLng latLng) {
        this.f9978t.m1482b();
        m526p();
        GoogleMap googleMap = this.f9978t;
        MarkerOptions m1327a = new MarkerOptions().m1327a(latLng);
        googleMap.m1484a(m1327a.m1326a("LAT : " + latLng.f9279a + " LON : " + latLng.f9280b));
        f9953d = Double.valueOf(latLng.f9279a);
        f9954e = Double.valueOf(latLng.f9280b);
        this.tv_lat.setText(getResources().getString(R.string.lat, String.valueOf(f9953d)));
        this.tv_lon.setText(getResources().getString(R.string.lon, String.valueOf(f9954e)));
        this.f9978t.m1487a(CameraUpdateFactory.m1490a(new LatLng(latLng.f9279a, latLng.f9280b)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m557a(Marker marker, DialogInterface dialogInterface, int i) {
        this.f9980v = true;
        this.f9981w = "Marker Position";
        this.f9982x = String.valueOf(marker.m1331a().f9279a);
        this.f9983y = String.valueOf(marker.m1331a().f9280b);
        m521u();
    }

    /* renamed from: a */
    private void m554a(String str, String str2, String str3) {
        History m471a = this.f9957C.m471a(this.f9957C.m470a(str, str2, str3));
        if (m471a != null) {
            this.f9955A.add(0, m471a);
            this.f9984z.notifyDataSetChanged();
            m523s();
        }
    }

    /* renamed from: a */
    private void m553a(String str, String str2, String str3, int i) {
        Bookmark bookmark = this.f9960F.get(i);
        bookmark.setName(str);
        bookmark.setLat(str2);
        bookmark.setLon(str3);
        this.f9958D.m477a(bookmark);
        this.f9960F.set(i, bookmark);
        this.f9959E.notifyItemChanged(i);
        m522t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m552a(JSONArray jSONArray) {
        Log.d("DUH", jSONArray.toString());
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                String[] split = ((JSONObject) jSONArray.get(i)).getString("driverLatLong").split(",");
                String str = split[0];
                String str2 = split[1];
                Location location = new Location("starting point");
                location.setLatitude(this.f9978t.m1488a().f9242a.f9279a);
                location.setLongitude(this.f9978t.m1488a().f9242a.f9280b);
                Location location2 = new Location("ending point");
                location2.setLatitude(Double.valueOf(str).doubleValue());
                location2.setLongitude(Double.valueOf(str2).doubleValue());
                float bearingTo = location.bearingTo(location2);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.m1327a(new LatLng(Double.valueOf(str).doubleValue(), Double.valueOf(str2).doubleValue()));
                markerOptions.m1326a(str + "," + str2);
                markerOptions.m1329a(bearingTo);
                markerOptions.m1325a(true);
                markerOptions.m1328a(BitmapDescriptorFactory.m1359a((int) R.drawable.marker_gjk));
                this.f9978t.m1484a(markerOptions);
                this.google_now.setVisibility(8);
            } catch (JSONException e) {
                e.printStackTrace();
                mo578a("Error: " + e.getMessage());
                this.google_now.setVisibility(8);
                return;
            }
        }
    }

    /* renamed from: a */
    private void m551a(final boolean z, final Bookmark bookmark, final int i) {
        View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_fgps_add, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflate);
        final EditText editText = (EditText) inflate.findViewById(R.id.et_nama);
        final EditText editText2 = (EditText) inflate.findViewById(R.id.et_lat);
        final EditText editText3 = (EditText) inflate.findViewById(R.id.et_lon);
        if (z && bookmark != null) {
            editText.setText(bookmark.getName());
            editText2.setText(bookmark.getLat());
            editText3.setText(bookmark.getLon());
        }
        builder.setCancelable(false).setPositiveButton(z ? "update" : "save", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$8QR0Kf8sqLEXttE9G0kok_Z_4_E
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                FgpsActivity.m536d(dialogInterface, i2);
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$NseJBFHo88YYrhpy51FEDr9bpuQ
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.cancel();
            }
        });
        final android.app.AlertDialog create = builder.create();
        create.show();
        create.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$_qH82_ggvmGhf5OA4nY9vJMt66o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FgpsActivity.this.m565a(editText, editText2, editText3, create, z, bookmark, i, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ boolean m558a(final Marker marker) {
        AlertDialog.Builder m9138a = new AlertDialog.Builder(this).m9138a("Set Location");
        m9138a.m9133b("Lat : " + marker.m1331a().f9279a + "\nLon : " + marker.m1331a().f9280b + "\n\nYou can save or mock to this position").m9137a("Start Mock", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$a-IsaIG9O7R3l5KzKtQ5s--w-yc
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                FgpsActivity.this.m546b(marker, dialogInterface, i);
            }
        }).m9132b("Save", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$8jMHUzp9aiZvvZ6XYlrKD3niLe0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                FgpsActivity.this.m557a(marker, dialogInterface, i);
            }
        }).m9143a(17301543).m9131c();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m550b(final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(new CharSequence[]{"Edit", "Delete"}, new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$c6uiBdFR7TdZFrfixMXUNnwNbss
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                FgpsActivity.this.m572a(i, dialogInterface, i2);
            }
        });
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m548b(View view) {
        if (this.f9964f.m443b("grbc", false)) {
            this.f9964f.m445a("grbc", false);
        } else {
            this.f9964f.m445a("grbc", true);
        }
        m520v();
        m526p();
        if (this.f9976r.m8052a()) {
            this.f9976r.m8045b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m547b(VolleyError volleyError) {
        VolleyLog.m8219b("DUH", "Error: " + volleyError.getMessage());
        mo578a(volleyError.getMessage());
        this.google_now.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m546b(Marker marker, DialogInterface dialogInterface, int i) {
        if (this.f9964f.m443b("FGPS", false)) {
            this.f9964f.m446a("lat", String.valueOf(marker.m1331a().f9279a));
            this.f9964f.m446a("lon", String.valueOf(marker.m1331a().f9280b));
            mo578a("FGPS Service Started");
            return;
        }
        this.f9979u.findItem(R.id.start).setIcon(17301539);
        this.f9964f.m445a("FGPS", true);
        this.f9964f.m446a("lat", String.valueOf(marker.m1331a().f9279a));
        this.f9964f.m446a("lon", String.valueOf(marker.m1331a().f9280b));
        m554a("History", String.valueOf(f9953d), String.valueOf(f9954e));
        m525q();
        if (!m590a(GPSTracker.class)) {
            startService(new Intent(this, GPSTracker.class));
        }
        mo578a("FGPS Service Started");
    }

    /* renamed from: b */
    private void m544b(String str, String str2, String str3) {
        Bookmark m478a = this.f9958D.m478a(this.f9958D.m476a(str, str2, str3));
        if (m478a != null) {
            this.f9960F.add(0, m478a);
            this.f9959E.notifyDataSetChanged();
            m522t();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m543b(JSONArray jSONArray) {
        Log.d("DUH", jSONArray.toString());
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String string = jSONObject.getString("latitude");
                String string2 = jSONObject.getString("longitude");
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.m1327a(new LatLng(Double.valueOf(string).doubleValue(), Double.valueOf(string2).doubleValue()));
                markerOptions.m1326a(string + "," + string2);
                markerOptions.m1328a(BitmapDescriptorFactory.m1359a((int) R.mipmap.grabc));
                this.f9978t.m1484a(markerOptions);
                this.google_now.setVisibility(8);
            } catch (JSONException e) {
                e.printStackTrace();
                mo578a("Error: " + e.getMessage());
                this.google_now.setVisibility(8);
                return;
            }
        }
    }

    /* renamed from: c */
    private void m542c(int i) {
        this.f9958D.m474b(this.f9960F.get(i));
        this.f9960F.remove(i);
        this.f9959E.notifyItemRemoved(i);
        m522t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public /* synthetic */ void m540c(View view) {
        if (this.f9964f.m443b("grbb", false)) {
            this.f9964f.m445a("grbb", false);
        } else {
            this.f9964f.m445a("grbb", true);
        }
        m520v();
        m526p();
        if (this.f9976r.m8052a()) {
            this.f9976r.m8045b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public /* synthetic */ void m539c(VolleyError volleyError) {
        VolleyLog.m8219b("DUH", "Error: " + volleyError.getMessage());
        mo578a(volleyError.getMessage());
        this.google_now.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public /* synthetic */ void m537c(JSONArray jSONArray) {
        Log.d("DUH", jSONArray.toString());
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String string = jSONObject.getString("latitude");
                String string2 = jSONObject.getString("longitude");
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.m1327a(new LatLng(Double.valueOf(string).doubleValue(), Double.valueOf(string2).doubleValue()));
                markerOptions.m1326a(string + "," + string2);
                markerOptions.m1328a(BitmapDescriptorFactory.m1359a((int) R.mipmap.grabb));
                this.f9978t.m1484a(markerOptions);
                this.google_now.setVisibility(8);
            } catch (JSONException e) {
                e.printStackTrace();
                mo578a("Error: " + e.getMessage());
                this.google_now.setVisibility(8);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static /* synthetic */ void m536d(DialogInterface dialogInterface, int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public /* synthetic */ void m535d(View view) {
        if (this.f9964f.m443b("gr", false)) {
            this.f9964f.m445a("gr", false);
        } else {
            this.f9964f.m445a("gr", true);
        }
        m520v();
        m526p();
        if (this.f9976r.m8052a()) {
            this.f9976r.m8045b();
        }
    }

    /* renamed from: l */
    private void m530l() {
        this.f9965g = LayoutInflater.from(this);
        this.f9966h = this.f9965g.inflate(R.layout.dialog_fgps_history, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) this.f9966h.findViewById(R.id.recycler_view);
        this.f9956B = (TextView) this.f9966h.findViewById(R.id.empty_history_view);
        ((Button) this.f9966h.findViewById(R.id.btn_clear)).setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$6ySfcVHL86rN-ORUFxb8S33YvV8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FgpsActivity.this.m569a(view);
            }
        });
        this.f9968j = new LinearLayoutManager(getApplicationContext());
        this.f9955A.addAll(this.f9957C.m472a());
        this.f9984z = new HistoryAdapter(this, this.f9955A);
        recyclerView.setLayoutManager(this.f9968j);
        recyclerView.setAdapter(this.f9984z);
        m523s();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() { // from class: hamba.allah.swv.activities.FgpsActivity.1
            @Override // hamba.allah.swv.utils.RecyclerTouchListener.ClickListener
            /* renamed from: a */
            public void mo450a(View view, int i) {
                History history = (History) FgpsActivity.this.f9955A.get(i);
                LatLng latLng = new LatLng(Double.valueOf(history.getLat()).doubleValue(), Double.valueOf(history.getLon()).doubleValue());
                FgpsActivity.this.f9978t.m1482b();
                GoogleMap googleMap = FgpsActivity.this.f9978t;
                MarkerOptions m1327a = new MarkerOptions().m1327a(latLng);
                googleMap.m1484a(m1327a.m1326a("LAT : " + latLng.f9279a + " LON : " + latLng.f9280b));
                FgpsActivity.f9953d = Double.valueOf(latLng.f9279a);
                FgpsActivity.f9954e = Double.valueOf(latLng.f9280b);
                FgpsActivity.this.tv_lat.setText(FgpsActivity.this.getResources().getString(R.string.lat, String.valueOf(FgpsActivity.f9953d)));
                FgpsActivity.this.tv_lon.setText(FgpsActivity.this.getResources().getString(R.string.lon, String.valueOf(FgpsActivity.f9954e)));
                FgpsActivity.this.f9978t.m1487a(CameraUpdateFactory.m1490a(new LatLng(latLng.f9279a, latLng.f9280b)));
                FgpsActivity.this.f9967i.dismiss();
            }

            @Override // hamba.allah.swv.utils.RecyclerTouchListener.ClickListener
            /* renamed from: b */
            public void mo449b(View view, int i) {
            }
        }));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.m9134b(this.f9966h);
        builder.m9136a(true).m9132b("CLOSE", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$1P-J3Z4hSGJ7dtug3NnTOwHSKis
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        this.f9967i = builder.m9135b();
    }

    /* renamed from: m */
    private void m529m() {
        this.f9969k = LayoutInflater.from(this);
        this.f9970l = this.f9969k.inflate(R.layout.dialog_fgps_bookmark, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) this.f9970l.findViewById(R.id.recycler_view);
        this.f9961G = (TextView) this.f9970l.findViewById(R.id.empty_bookmark_view);
        this.f9972n = new LinearLayoutManager(getApplicationContext());
        this.f9960F.addAll(this.f9958D.m479a());
        this.f9959E = new BookmarkAdapter(this, this.f9960F);
        recyclerView.setLayoutManager(this.f9972n);
        recyclerView.setAdapter(this.f9959E);
        m531k();
        m522t();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() { // from class: hamba.allah.swv.activities.FgpsActivity.2
            @Override // hamba.allah.swv.utils.RecyclerTouchListener.ClickListener
            /* renamed from: a */
            public void mo450a(View view, int i) {
                Bookmark bookmark = (Bookmark) FgpsActivity.this.f9960F.get(i);
                LatLng latLng = new LatLng(Double.valueOf(bookmark.getLat()).doubleValue(), Double.valueOf(bookmark.getLon()).doubleValue());
                FgpsActivity.this.f9978t.m1482b();
                GoogleMap googleMap = FgpsActivity.this.f9978t;
                MarkerOptions m1327a = new MarkerOptions().m1327a(latLng);
                googleMap.m1484a(m1327a.m1326a("LAT : " + latLng.f9279a + " LON : " + latLng.f9280b));
                FgpsActivity.f9953d = Double.valueOf(latLng.f9279a);
                FgpsActivity.f9954e = Double.valueOf(latLng.f9280b);
                FgpsActivity.this.tv_lat.setText(String.valueOf(FgpsActivity.f9953d));
                FgpsActivity.this.tv_lon.setText(String.valueOf(FgpsActivity.f9954e));
                FgpsActivity.this.f9978t.m1487a(CameraUpdateFactory.m1490a(new LatLng(latLng.f9279a, latLng.f9280b)));
                FgpsActivity.this.f9971m.dismiss();
            }

            @Override // hamba.allah.swv.utils.RecyclerTouchListener.ClickListener
            /* renamed from: b */
            public void mo449b(View view, int i) {
                FgpsActivity.this.m550b(i);
            }
        }));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.m9134b(this.f9970l);
        builder.m9136a(true).m9132b("CLOSE", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$qTNOLQXsAs2pbk_ryW1QEteN2jI
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        this.f9971m = builder.m9135b();
    }

    /* renamed from: n */
    private void m528n() {
        getWindow().setSoftInputMode(3);
    }

    /* renamed from: o */
    private void m527o() {
        if (this.f9964f.m443b("FGPS", false)) {
            this.f9979u.findItem(R.id.start).setIcon(17301539);
        } else {
            this.f9979u.findItem(R.id.start).setIcon(17301540);
        }
    }

    /* renamed from: p */
    private void m526p() {
        this.f9978t.m1482b();
        String str = "https://api.gojekapi.com/gojek/service_type/1/drivers/nearby?location=" + f9953d + "," + f9954e;
        String str2 = "https://p.grabtaxi.com/api/passenger/v2/bookings/nearbydrivers?latitude=" + (String.valueOf(f9953d) + "&longitude=" + String.valueOf(f9954e) + "&taxiTypeId=71");
        String str3 = "https://p.grabtaxi.com/api/passenger/v2/bookings/nearbydrivers?latitude=" + (String.valueOf(f9953d) + "&longitude=" + String.valueOf(f9954e) + "&taxiTypeId=97");
        if (this.f9964f.m443b("grbb", false)) {
            this.google_now.setVisibility(0);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(str2, new Response.Listener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$WmUX9kd6GF8VCh8yWQrsgQ0l4-M
                @Override // com.android.volley.Response.Listener
                public final void onResponse(Object obj) {
                    FgpsActivity.this.m537c((JSONArray) obj);
                }
            }, new Response.ErrorListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$dORJ9c-EGfWEi6DSwEF757Mtu5M
                @Override // com.android.volley.Response.ErrorListener
                public final void onErrorResponse(VolleyError volleyError) {
                    FgpsActivity.this.m539c(volleyError);
                }
            });
            getClass();
            Volley.m8121a(getApplicationContext()).m8236a(jsonArrayRequest);
        }
        if (this.f9964f.m443b("grbc", false)) {
            this.google_now.setVisibility(0);
            JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(str3, new Response.Listener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$fXPLeRBSORUmp-fknxxuexLGDpo
                @Override // com.android.volley.Response.Listener
                public final void onResponse(Object obj) {
                    FgpsActivity.this.m543b((JSONArray) obj);
                }
            }, new Response.ErrorListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$lWAipwFfp8MxiwhfJiN_7Us_tw4
                @Override // com.android.volley.Response.ErrorListener
                public final void onErrorResponse(VolleyError volleyError) {
                    FgpsActivity.this.m547b(volleyError);
                }
            });
            getClass();
            Volley.m8121a(getApplicationContext()).m8236a(jsonArrayRequest2);
        }
        if (this.f9964f.m443b("gr", false)) {
            this.google_now.setVisibility(0);
            JsonArrayRequest jsonArrayRequest3 = new JsonArrayRequest(str, new Response.Listener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$Rk0AaUcdqc2iHIAt0ZPoEtWWEq4
                @Override // com.android.volley.Response.Listener
                public final void onResponse(Object obj) {
                    FgpsActivity.this.m552a((JSONArray) obj);
                }
            }, new Response.ErrorListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$ljch9_vG9OXBISnJYa4hDZtwiuc
                @Override // com.android.volley.Response.ErrorListener
                public final void onErrorResponse(VolleyError volleyError) {
                    FgpsActivity.this.m563a(volleyError);
                }
            }) { // from class: hamba.allah.swv.activities.FgpsActivity.3
                @Override // com.android.volley.Request
                /* renamed from: j */
                public Map<String, String> mo510j() {
                    HashMap hashMap = new HashMap();
                    hashMap.put("Authorization", "Bearer " + FgpsActivity.this.f9964f.m444b("keyGojek", ""));
                    return hashMap;
                }
            };
            getClass();
            Volley.m8121a(getApplicationContext()).m8236a(jsonArrayRequest3);
        }
    }

    /* renamed from: q */
    private void m525q() {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("FGPS").setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(false).setOngoing(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, FgpsActivity.class), 134217728));
        RemoteViews remoteViews = new RemoteViews(getPackageName(), (int) R.layout.view_notification);
        remoteViews.setOnClickPendingIntent(R.id.view_notification__ImageView_Close, PendingIntent.getBroadcast(this, 0, new Intent("hamba.allah.swv.action.NotificationBroadcast_Close"), 1073741824));
        remoteViews.setTextViewText(R.id.notif_lat, "LAT : " + this.f9964f.m444b("lat", ""));
        remoteViews.setTextViewText(R.id.notif_lon, "LON : " + this.f9964f.m444b("lon", ""));
        builder.setContent(remoteViews);
        if (notificationManager != null) {
            notificationManager.notify(1231328131, builder.build());
        }
    }

    /* renamed from: r */
    private void m524r() {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
    }

    /* renamed from: s */
    private void m523s() {
        if (this.f9957C.m469b() > 0) {
            this.f9956B.setVisibility(8);
        } else {
            this.f9956B.setVisibility(0);
        }
    }

    /* renamed from: t */
    private void m522t() {
        if (this.f9958D.m475b() > 0) {
            this.f9961G.setVisibility(8);
        } else {
            this.f9961G.setVisibility(0);
        }
    }

    /* renamed from: u */
    private void m521u() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_fgps_add, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.m9134b(inflate);
        final EditText editText = (EditText) inflate.findViewById(R.id.et_nama);
        final EditText editText2 = (EditText) inflate.findViewById(R.id.et_lat);
        final EditText editText3 = (EditText) inflate.findViewById(R.id.et_lon);
        if (this.f9980v) {
            editText.setText(this.f9981w);
            editText2.setText(this.f9982x);
            editText3.setText(this.f9983y);
        }
        builder.m9137a("SAVE", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$4WcHteg8tTDy2JIQ3pdXvUQ_zr8
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                FgpsActivity.this.m564a(editText, editText2, editText3, dialogInterface, i);
            }
        }).m9132b("Cancel", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$7RPwd7iEy_w9zcoXZRARV9fd7bs
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.m9135b().show();
    }

    /* renamed from: v */
    private void m520v() {
        if (this.f9964f.m443b("gr", false)) {
            this.btn_gojek.setColorFilter(ContextCompat.getColor(this, 17170452), PorterDuff.Mode.SRC_IN);
        } else {
            this.btn_gojek.setColorFilter(ContextCompat.getColor(this, 17170443), PorterDuff.Mode.SRC_IN);
        }
        if (this.f9964f.m443b("grbb", false)) {
            this.btn_grabbike.setColorFilter(ContextCompat.getColor(this, 17170452), PorterDuff.Mode.SRC_IN);
        } else {
            this.btn_grabbike.setColorFilter(ContextCompat.getColor(this, 17170443), PorterDuff.Mode.SRC_IN);
        }
        if (this.f9964f.m443b("grbc", false)) {
            this.btn_grabcar.setColorFilter(ContextCompat.getColor(this, 17170452), PorterDuff.Mode.SRC_IN);
        } else {
            this.btn_grabcar.setColorFilter(ContextCompat.getColor(this, 17170443), PorterDuff.Mode.SRC_IN);
        }
    }

    /* renamed from: w */
    private void m519w() {
        this.f9977s.getKey("custkey").enqueue(new Callback<ModelList>() { // from class: hamba.allah.swv.activities.FgpsActivity.4
            @Override // retrofit2.Callback
            public void onFailure(Call<ModelList> call, Throwable th) {
                call.cancel();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<ModelList> call, retrofit2.Response<ModelList> response) {
                ModelList body = response.body();
                if (body != null) {
                    FgpsActivity.this.f9964f.m446a("keyGojek", body.url);
                }
            }
        });
    }

    @Override // com.hlab.fabrevealmenu.listeners.OnFABMenuSelectedListener
    /* renamed from: a */
    public void mo568a(View view, int i) {
        switch (i) {
            case R.id.fgps_add_bookmark /* 2131296350 */:
                m521u();
                return;
            case R.id.fgps_bookmark /* 2131296351 */:
                this.f9971m.show();
                return;
            case R.id.fgps_gobox /* 2131296352 */:
            case R.id.fgps_gocar /* 2131296353 */:
            case R.id.fgps_goride /* 2131296356 */:
            case R.id.fgps_grabbike /* 2131296358 */:
            case R.id.fgps_grabcar /* 2131296359 */:
            default:
                return;
            case R.id.fgps_gofood /* 2131296354 */:
                if (this.f9976r.m8052a()) {
                    this.f9976r.m8045b();
                }
                startActivity(new Intent(this, GofoodActivity.class));
                return;
            case R.id.fgps_gomart /* 2131296355 */:
                if (this.f9976r.m8052a()) {
                    this.f9976r.m8045b();
                }
                mo578a("Sedang Perbaikan !");
                return;
            case R.id.fgps_goto /* 2131296357 */:
                View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_fgps_goto, (ViewGroup) null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.m9134b(inflate);
                final EditText editText = (EditText) inflate.findViewById(R.id.et_lat);
                final EditText editText2 = (EditText) inflate.findViewById(R.id.et_lon);
                builder.m9136a(false).m9137a("GO", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$yrVWDJilFUTOMo4mCoaBt4aehng
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        FgpsActivity.this.m566a(editText, editText2, dialogInterface, i2);
                    }
                }).m9132b("Cancel", new DialogInterface.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$QQ0Jncl9WxvjBN7lvwVHKJVQyjM
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        dialogInterface.cancel();
                    }
                });
                builder.m9135b().show();
                return;
            case R.id.fgps_grabfood /* 2131296360 */:
                if (this.f9976r.m8052a()) {
                    this.f9976r.m8045b();
                }
                startActivity(new Intent(this, GrabfoodActivity.class));
                return;
            case R.id.fgps_history /* 2131296361 */:
                this.f9967i.show();
                return;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    /* renamed from: a */
    public void mo562a(ConnectionResult connectionResult) {
        mo578a("Error");
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public void mo560a(GoogleMap googleMap) {
        this.f9978t = googleMap;
        this.f9978t.m1483a(true);
        this.f9978t.m1480c().m1426c(false);
        this.f9978t.m1480c().m1428a(true);
        this.f9978t.m1480c().m1427b(false);
        if (f9953d != null || f9954e != null) {
            this.tv_lat.setText(getResources().getString(R.string.lat, String.valueOf(f9953d)));
            this.tv_lon.setText(getResources().getString(R.string.lon, String.valueOf(f9954e)));
            LatLng latLng = new LatLng(f9953d.doubleValue(), f9954e.doubleValue());
            this.f9978t.m1482b();
            GoogleMap googleMap2 = this.f9978t;
            MarkerOptions m1327a = new MarkerOptions().m1327a(latLng);
            googleMap2.m1484a(m1327a.m1326a("LAT : " + latLng.f9279a + " LON : " + latLng.f9280b));
            this.f9978t.m1487a(CameraUpdateFactory.m1490a(latLng));
            this.f9978t.m1481b(CameraUpdateFactory.m1492a(18.0f));
        }
        this.f9978t.m1486a(new GoogleMap.OnMapClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$Gbhp-Z5gImMQ6F7j2ZBtUs5sZho
            @Override // com.google.android.gms.maps.GoogleMap.OnMapClickListener
            public final void onMapClick(LatLng latLng2) {
                FgpsActivity.this.m559a(latLng2);
            }
        });
        this.f9978t.m1485a(new GoogleMap.OnMarkerClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$C8zZvm_mwt9BhGwHQw7-Wgzyu-s
            @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
            public final boolean onMarkerClick(Marker marker) {
                boolean m558a;
                m558a = FgpsActivity.this.m558a(marker);
                return m558a;
            }
        });
    }

    @OnClick({R.id.fab_location})
    @SuppressLint({"MissingPermission"})
    public void getLocation() {
        LocationServices.m1614a(this).m1622g().mo1174a(this, new OnSuccessListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$BU99tmgL8DrXjaqWBwH9ls43gXA
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FgpsActivity.this.m570a((Location) obj);
            }
        });
    }

    /* renamed from: j */
    public void m532j() {
        this.f9955A.clear();
        this.f9955A.addAll(this.f9957C.m472a());
        this.f9984z.notifyDataSetChanged();
        m523s();
    }

    /* renamed from: k */
    public void m531k() {
        this.f9960F.clear();
        this.f9960F.addAll(this.f9958D.m479a());
        this.f9959E.notifyDataSetChanged();
        m522t();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // hamba.allah.swv.Base, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.SupportActivity, android.app.Activity
    @SuppressLint({"WorldReadableFiles", "SetWorldReadable"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fgps);
        m528n();
        ButterKnife.bind(this);
        this.f9964f = SharedPreferenceUtils.m447a(this);
        this.f9977s = (RetrofitInterface) NetworkUtil.m465a().create(RetrofitInterface.class);
        m519w();
        this.f9973o = new AlertDialog.Builder(this);
        this.f9957C = new HistoryDBHandler(this);
        this.f9958D = new BookmarkDBHandler(this);
        m520v();
        ActionBar a = m9130a();
        a.getClass();
        a.mo8994a(true);
        m9130a().mo8995a("FGPS");
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        FABRevealMenu fABRevealMenu = (FABRevealMenu) findViewById(R.id.fabMenu);
        if (floatingActionButton != null && fABRevealMenu != null) {
            try {
                m591a(fABRevealMenu);
                fABRevealMenu.m829a(floatingActionButton);
                fABRevealMenu.setOnFABMenuSelectedListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.f9963c = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        this.f9963c.m1455a(this);
        this.f9974p = Places.m1589a(this, null);
        this.f9962H = new GoogleApiClient.Builder(this).m7470a(Places.f9053a).m7470a(Places.f9054b).m7471a(this, this).m7469b();
        this.f9975q = new PlaceAutocompleteAdapter(this, this.f9974p, f9952I, null);
        this.searchtxt.setAdapter(this.f9975q);
        this.searchtxt.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$5U_kawm40oi9vqCQHCtt8AxS24w
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                FgpsActivity.this.m567a(adapterView, view, i, j);
            }
        });
        m530l();
        m529m();
        this.f9976r = new InterstitialAd(this);
        this.f9976r.m8047a("ca-app-pub-1305986134697546/4193507098");
        this.f9976r.m8050a(new AdRequest.Builder().m8070a());
        this.btn_gojek.setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$OCFQ5gawZnbWliC9PXz8FHReUAw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FgpsActivity.this.m535d(view);
            }
        });
        this.btn_grabbike.setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$VeMrMCgIjiO8nypQxf5czE8wnQs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FgpsActivity.this.m540c(view);
            }
        });
        this.btn_grabcar.setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$FgpsActivity$JMO0gjtUoJhMiaUKNqDNEDxZmpg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FgpsActivity.this.m548b(view);
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fgps_menu, menu);
        this.f9979u = menu;
        m527o();
        return true;
    }

    @Override // hamba.allah.swv.Base, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.fgps_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        } else if (itemId != R.id.start) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            if (this.f9964f.m443b("FGPS", false)) {
                this.f9979u.findItem(R.id.start).setIcon(17301540);
                this.f9964f.m445a("FGPS", false);
                mo578a("FGPS Service Stoped");
                m524r();
                if (m590a(GPSTracker.class)) {
                    stopService(new Intent(this, GPSTracker.class));
                }
            } else {
                this.f9979u.findItem(R.id.start).setIcon(17301539);
                this.f9964f.m445a("FGPS", true);
                this.f9964f.m446a("lat", String.valueOf(f9953d));
                this.f9964f.m446a("lon", String.valueOf(f9954e));
                m554a("History", String.valueOf(f9953d), String.valueOf(f9954e));
                m525q();
                if (!m590a(GPSTracker.class)) {
                    startService(new Intent(this, GPSTracker.class));
                }
                mo578a("FGPS Service Started");
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p001v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        m519w();
        m520v();
    }
}
