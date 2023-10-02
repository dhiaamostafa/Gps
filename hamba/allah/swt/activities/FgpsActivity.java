package hamba.allah.swt.activities;

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
import android.support.p000v4.content.ContextCompat;
import android.support.p003v7.app.ActionBar;
import android.support.p003v7.app.AlertDialog;
import android.support.p003v7.widget.AppCompatImageView;
import android.support.p003v7.widget.LinearLayoutManager;
import android.support.p003v7.widget.RecyclerView;
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
import hamba.allah.swt.Base;
import hamba.allah.swt.R;
import hamba.allah.swt.adapter.BookmarkAdapter;
import hamba.allah.swt.adapter.HistoryAdapter;
import hamba.allah.swt.model.Bookmark;
import hamba.allah.swt.model.History;
import hamba.allah.swt.model.ModelList;
import hamba.allah.swt.service.GPSTracker;
import hamba.allah.swt.utils.BookmarkDBHandler;
import hamba.allah.swt.utils.HistoryDBHandler;
import hamba.allah.swt.utils.NetworkUtil;
import hamba.allah.swt.utils.PlaceAutocompleteAdapter;
import hamba.allah.swt.utils.RecyclerTouchListener;
import hamba.allah.swt.utils.RetrofitInterface;
import hamba.allah.swt.utils.SharedPreferenceUtils;
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
    private static final LatLngBounds f9950I = new LatLngBounds(new LatLng(-40.0d, -168.0d), new LatLng(71.0d, 136.0d));

    /* renamed from: d */
    public static Double f9951d;

    /* renamed from: e */
    public static Double f9952e;

    /* renamed from: B */
    private TextView f9954B;

    /* renamed from: C */
    private HistoryDBHandler f9955C;

    /* renamed from: D */
    private BookmarkDBHandler f9956D;

    /* renamed from: E */
    private BookmarkAdapter f9957E;

    /* renamed from: G */
    private TextView f9959G;

    /* renamed from: H */
    private GoogleApiClient f9960H;
    @BindView(R.id.btn_gojek)
    AppCompatImageView btn_gojek;
    @BindView(R.id.btn_grabbike)
    AppCompatImageView btn_grabbike;
    @BindView(R.id.btn_grabcar)
    AppCompatImageView btn_grabcar;

    /* renamed from: c */
    MapFragment f9961c;

    /* renamed from: f */
    SharedPreferenceUtils f9962f;
    @BindView(R.id.fab_location)
    ImageView fab_location;

    /* renamed from: g */
    LayoutInflater f9963g;
    @BindView(R.id.google_now)
    SmoothProgressBar google_now;

    /* renamed from: h */
    View f9964h;

    /* renamed from: i */
    AlertDialog f9965i;

    /* renamed from: j */
    RecyclerView.LayoutManager f9966j;

    /* renamed from: k */
    LayoutInflater f9967k;

    /* renamed from: l */
    View f9968l;

    /* renamed from: m */
    AlertDialog f9969m;

    /* renamed from: n */
    RecyclerView.LayoutManager f9970n;

    /* renamed from: o */
    AlertDialog.Builder f9971o;

    /* renamed from: p */
    protected GeoDataClient f9972p;

    /* renamed from: q */
    PlaceAutocompleteAdapter f9973q;

    /* renamed from: r */
    InterstitialAd f9974r;

    /* renamed from: s */
    RetrofitInterface f9975s;
    @BindView(R.id.searchbox)
    AutoCompleteTextView searchtxt;

    /* renamed from: t */
    private GoogleMap f9976t;
    @BindView(R.id.tv_lat)
    TextView tv_lat;
    @BindView(R.id.tv_lon)
    TextView tv_lon;

    /* renamed from: u */
    private Menu f9977u;

    /* renamed from: v */
    private boolean f9978v;

    /* renamed from: w */
    private String f9979w;

    /* renamed from: x */
    private String f9980x;

    /* renamed from: y */
    private String f9981y;

    /* renamed from: z */
    private HistoryAdapter f9982z;

    /* renamed from: A */
    private List<History> f9953A = new ArrayList();

    /* renamed from: F */
    private List<Bookmark> f9958F = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m569a(int i, DialogInterface dialogInterface, int i2) {
        if (i2 == 0) {
            m548a(true, this.f9958F.get(i), i);
        } else {
            m539c(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m567a(Location location) {
        if (location != null) {
            this.f9976t.m1472b(CameraUpdateFactory.m1480a(new LatLng(location.getLatitude(), location.getLongitude()), 18.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m566a(View view) {
        this.f9955C.m465c();
        m529j();
        mo575a("History Cleared");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m564a(AdapterView adapterView, View view, int i, long j) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
        final AutocompletePrediction item = this.f9973q.getItem(i);
        if (item != null) {
            Places.f9053c.mo1509a(this.f9960H, item.mo1521b()).mo7234a(new ResultCallback() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$uBVijMW5h-YIC6jb8zUhj9I2X3g
                @Override // com.google.android.gms.common.api.ResultCallback
                public final void onResult(Result result) {
                    FgpsActivity.this.m558a(item, (PlaceBuffer) result);
                }
            });
            CharSequence mo1520b = item.mo1520b(null);
            mo575a("Location : " + ((Object) mo1520b));
        }
        m525n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m563a(EditText editText, EditText editText2, DialogInterface dialogInterface, int i) {
        if (editText.getText().toString().matches("") || editText2.getText().toString().matches("")) {
            mo575a("Masukin dulu kordinatnya");
            return;
        }
        f9951d = Double.valueOf(editText.getText().toString());
        f9952e = Double.valueOf(editText2.getText().toString());
        this.f9977u.findItem(R.id.start).setIcon(17301539);
        this.f9962f.m442a("FGPS", true);
        this.f9962f.m443a("lat", String.valueOf(f9951d));
        this.f9962f.m443a("lon", String.valueOf(f9952e));
        m522q();
        m551a("History", String.valueOf(f9951d), String.valueOf(f9952e));
        if (m587a(GPSTracker.class)) {
            return;
        }
        startService(new Intent(this, GPSTracker.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m562a(EditText editText, EditText editText2, EditText editText3, android.app.AlertDialog alertDialog, boolean z, Bookmark bookmark, int i, View view) {
        if (TextUtils.isEmpty(editText.getText().toString()) || TextUtils.isEmpty(editText2.getText().toString()) || TextUtils.isEmpty(editText3.getText().toString())) {
            mo575a("Enter Bookmark !");
            return;
        }
        alertDialog.dismiss();
        if (!z || bookmark == null) {
            m541b(editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString());
        } else {
            m550a(editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m561a(EditText editText, EditText editText2, EditText editText3, DialogInterface dialogInterface, int i) {
        if (editText.getText().toString().matches("") || editText2.getText().toString().matches("") || editText3.getText().toString().matches("")) {
            mo575a("Isi yang bener");
        } else {
            m541b(editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m560a(VolleyError volleyError) {
        VolleyLog.m8177b("DUH", "Error: " + volleyError.getMessage());
        mo575a(volleyError.getMessage());
        this.google_now.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m558a(AutocompletePrediction autocompletePrediction, PlaceBuffer placeBuffer) {
        if (placeBuffer.mo1246w_().m7450d()) {
            LatLng mo1543b = placeBuffer.mo1584a(0).mo1543b();
            f9951d = Double.valueOf(mo1543b.f9277a);
            f9952e = Double.valueOf(mo1543b.f9278b);
            this.tv_lat.setText(getResources().getString(R.string.lat, String.valueOf(f9951d)));
            this.tv_lon.setText(getResources().getString(R.string.lon, String.valueOf(f9952e)));
            CharSequence mo1520b = autocompletePrediction.mo1520b(null);
            this.f9976t.m1473b();
            this.f9976t.m1475a(new MarkerOptions().m1318a(mo1543b).m1317a(String.valueOf(mo1520b)));
            this.f9976t.m1478a(CameraUpdateFactory.m1481a(mo1543b));
        }
        placeBuffer.mo5541c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m556a(LatLng latLng) {
        this.f9976t.m1473b();
        m523p();
        GoogleMap googleMap = this.f9976t;
        MarkerOptions m1318a = new MarkerOptions().m1318a(latLng);
        googleMap.m1475a(m1318a.m1317a("LAT : " + latLng.f9277a + " LON : " + latLng.f9278b));
        f9951d = Double.valueOf(latLng.f9277a);
        f9952e = Double.valueOf(latLng.f9278b);
        this.tv_lat.setText(getResources().getString(R.string.lat, String.valueOf(f9951d)));
        this.tv_lon.setText(getResources().getString(R.string.lon, String.valueOf(f9952e)));
        this.f9976t.m1478a(CameraUpdateFactory.m1481a(new LatLng(latLng.f9277a, latLng.f9278b)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m554a(Marker marker, DialogInterface dialogInterface, int i) {
        this.f9978v = true;
        this.f9979w = "Marker Position";
        this.f9980x = String.valueOf(marker.m1322a().f9277a);
        this.f9981y = String.valueOf(marker.m1322a().f9278b);
        m518u();
    }

    /* renamed from: a */
    private void m551a(String str, String str2, String str3) {
        History m468a = this.f9955C.m468a(this.f9955C.m467a(str, str2, str3));
        if (m468a != null) {
            this.f9953A.add(0, m468a);
            this.f9982z.notifyDataSetChanged();
            m520s();
        }
    }

    /* renamed from: a */
    private void m550a(String str, String str2, String str3, int i) {
        Bookmark bookmark = this.f9958F.get(i);
        bookmark.setName(str);
        bookmark.setLat(str2);
        bookmark.setLon(str3);
        this.f9956D.m474a(bookmark);
        this.f9958F.set(i, bookmark);
        this.f9957E.notifyItemChanged(i);
        m519t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m549a(JSONArray jSONArray) {
        Log.d("DUH", jSONArray.toString());
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                String[] split = ((JSONObject) jSONArray.get(i)).getString("driverLatLong").split(",");
                String str = split[0];
                String str2 = split[1];
                Location location = new Location("starting point");
                location.setLatitude(this.f9976t.m1479a().f9240a.f9277a);
                location.setLongitude(this.f9976t.m1479a().f9240a.f9278b);
                Location location2 = new Location("ending point");
                location2.setLatitude(Double.valueOf(str).doubleValue());
                location2.setLongitude(Double.valueOf(str2).doubleValue());
                float bearingTo = location.bearingTo(location2);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.m1318a(new LatLng(Double.valueOf(str).doubleValue(), Double.valueOf(str2).doubleValue()));
                markerOptions.m1317a(str + "," + str2);
                markerOptions.m1320a(bearingTo);
                markerOptions.m1316a(true);
                markerOptions.m1319a(BitmapDescriptorFactory.m1350a((int) R.drawable.marker_gjk));
                this.f9976t.m1475a(markerOptions);
                this.google_now.setVisibility(8);
            } catch (JSONException e) {
                e.printStackTrace();
                mo575a("Error: " + e.getMessage());
                this.google_now.setVisibility(8);
                return;
            }
        }
    }

    /* renamed from: a */
    private void m548a(final boolean z, final Bookmark bookmark, final int i) {
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
        builder.setCancelable(false).setPositiveButton(z ? "update" : "save", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$8QR0Kf8sqLEXttE9G0kok_Z_4_E
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                FgpsActivity.m533d(dialogInterface, i2);
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$NseJBFHo88YYrhpy51FEDr9bpuQ
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.cancel();
            }
        });
        final android.app.AlertDialog create = builder.create();
        create.show();
        create.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$_qH82_ggvmGhf5OA4nY9vJMt66o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FgpsActivity.this.m562a(editText, editText2, editText3, create, z, bookmark, i, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ boolean m555a(final Marker marker) {
        AlertDialog.Builder m9096a = new AlertDialog.Builder(this).m9096a("Set Location");
        m9096a.m9091b("Lat : " + marker.m1322a().f9277a + "\nLon : " + marker.m1322a().f9278b + "\n\nYou can save or mock to this position").m9095a("Start Mock", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$a-IsaIG9O7R3l5KzKtQ5s--w-yc
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                FgpsActivity.this.m543b(marker, dialogInterface, i);
            }
        }).m9090b("Save", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$8jMHUzp9aiZvvZ6XYlrKD3niLe0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                FgpsActivity.this.m554a(marker, dialogInterface, i);
            }
        }).m9101a(17301543).m9089c();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m547b(final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(new CharSequence[]{"Edit", "Delete"}, new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$c6uiBdFR7TdZFrfixMXUNnwNbss
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                FgpsActivity.this.m569a(i, dialogInterface, i2);
            }
        });
        builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m545b(View view) {
        if (this.f9962f.m440b("grbc", false)) {
            this.f9962f.m442a("grbc", false);
        } else {
            this.f9962f.m442a("grbc", true);
        }
        m517v();
        m523p();
        if (this.f9974r.m8043a()) {
            this.f9974r.m8036b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m544b(VolleyError volleyError) {
        VolleyLog.m8177b("DUH", "Error: " + volleyError.getMessage());
        mo575a(volleyError.getMessage());
        this.google_now.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m543b(Marker marker, DialogInterface dialogInterface, int i) {
        if (this.f9962f.m440b("FGPS", false)) {
            this.f9962f.m443a("lat", String.valueOf(marker.m1322a().f9277a));
            this.f9962f.m443a("lon", String.valueOf(marker.m1322a().f9278b));
            mo575a("FGPS Service Started");
            return;
        }
        this.f9977u.findItem(R.id.start).setIcon(17301539);
        this.f9962f.m442a("FGPS", true);
        this.f9962f.m443a("lat", String.valueOf(marker.m1322a().f9277a));
        this.f9962f.m443a("lon", String.valueOf(marker.m1322a().f9278b));
        m551a("History", String.valueOf(f9951d), String.valueOf(f9952e));
        m522q();
        if (!m587a(GPSTracker.class)) {
            startService(new Intent(this, GPSTracker.class));
        }
        mo575a("FGPS Service Started");
    }

    /* renamed from: b */
    private void m541b(String str, String str2, String str3) {
        Bookmark m475a = this.f9956D.m475a(this.f9956D.m473a(str, str2, str3));
        if (m475a != null) {
            this.f9958F.add(0, m475a);
            this.f9957E.notifyDataSetChanged();
            m519t();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m540b(JSONArray jSONArray) {
        Log.d("DUH", jSONArray.toString());
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String string = jSONObject.getString("latitude");
                String string2 = jSONObject.getString("longitude");
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.m1318a(new LatLng(Double.valueOf(string).doubleValue(), Double.valueOf(string2).doubleValue()));
                markerOptions.m1317a(string + "," + string2);
                markerOptions.m1319a(BitmapDescriptorFactory.m1350a((int) R.mipmap.grabc));
                this.f9976t.m1475a(markerOptions);
                this.google_now.setVisibility(8);
            } catch (JSONException e) {
                e.printStackTrace();
                mo575a("Error: " + e.getMessage());
                this.google_now.setVisibility(8);
                return;
            }
        }
    }

    /* renamed from: c */
    private void m539c(int i) {
        this.f9956D.m471b(this.f9958F.get(i));
        this.f9958F.remove(i);
        this.f9957E.notifyItemRemoved(i);
        m519t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public /* synthetic */ void m537c(View view) {
        if (this.f9962f.m440b("grbb", false)) {
            this.f9962f.m442a("grbb", false);
        } else {
            this.f9962f.m442a("grbb", true);
        }
        m517v();
        m523p();
        if (this.f9974r.m8043a()) {
            this.f9974r.m8036b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public /* synthetic */ void m536c(VolleyError volleyError) {
        VolleyLog.m8177b("DUH", "Error: " + volleyError.getMessage());
        mo575a(volleyError.getMessage());
        this.google_now.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public /* synthetic */ void m534c(JSONArray jSONArray) {
        Log.d("DUH", jSONArray.toString());
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String string = jSONObject.getString("latitude");
                String string2 = jSONObject.getString("longitude");
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.m1318a(new LatLng(Double.valueOf(string).doubleValue(), Double.valueOf(string2).doubleValue()));
                markerOptions.m1317a(string + "," + string2);
                markerOptions.m1319a(BitmapDescriptorFactory.m1350a((int) R.mipmap.grabb));
                this.f9976t.m1475a(markerOptions);
                this.google_now.setVisibility(8);
            } catch (JSONException e) {
                e.printStackTrace();
                mo575a("Error: " + e.getMessage());
                this.google_now.setVisibility(8);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static /* synthetic */ void m533d(DialogInterface dialogInterface, int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public /* synthetic */ void m532d(View view) {
        if (this.f9962f.m440b("gr", false)) {
            this.f9962f.m442a("gr", false);
        } else {
            this.f9962f.m442a("gr", true);
        }
        m517v();
        m523p();
        if (this.f9974r.m8043a()) {
            this.f9974r.m8036b();
        }
    }

    /* renamed from: l */
    private void m527l() {
        this.f9963g = LayoutInflater.from(this);
        this.f9964h = this.f9963g.inflate(R.layout.dialog_fgps_history, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) this.f9964h.findViewById(R.id.recycler_view);
        this.f9954B = (TextView) this.f9964h.findViewById(R.id.empty_history_view);
        ((Button) this.f9964h.findViewById(R.id.btn_clear)).setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$6ySfcVHL86rN-ORUFxb8S33YvV8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FgpsActivity.this.m566a(view);
            }
        });
        this.f9966j = new LinearLayoutManager(getApplicationContext());
        this.f9953A.addAll(this.f9955C.m469a());
        this.f9982z = new HistoryAdapter(this, this.f9953A);
        recyclerView.setLayoutManager(this.f9966j);
        recyclerView.setAdapter(this.f9982z);
        m520s();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() { // from class: hamba.allah.swt.activities.FgpsActivity.1
            @Override // hamba.allah.swt.utils.RecyclerTouchListener.ClickListener
            /* renamed from: a */
            public void mo447a(View view, int i) {
                History history = (History) FgpsActivity.this.f9953A.get(i);
                LatLng latLng = new LatLng(Double.valueOf(history.getLat()).doubleValue(), Double.valueOf(history.getLon()).doubleValue());
                FgpsActivity.this.f9976t.m1473b();
                GoogleMap googleMap = FgpsActivity.this.f9976t;
                MarkerOptions m1318a = new MarkerOptions().m1318a(latLng);
                googleMap.m1475a(m1318a.m1317a("LAT : " + latLng.f9277a + " LON : " + latLng.f9278b));
                FgpsActivity.f9951d = Double.valueOf(latLng.f9277a);
                FgpsActivity.f9952e = Double.valueOf(latLng.f9278b);
                FgpsActivity.this.tv_lat.setText(FgpsActivity.this.getResources().getString(R.string.lat, String.valueOf(FgpsActivity.f9951d)));
                FgpsActivity.this.tv_lon.setText(FgpsActivity.this.getResources().getString(R.string.lon, String.valueOf(FgpsActivity.f9952e)));
                FgpsActivity.this.f9976t.m1478a(CameraUpdateFactory.m1481a(new LatLng(latLng.f9277a, latLng.f9278b)));
                FgpsActivity.this.f9965i.dismiss();
            }

            @Override // hamba.allah.swt.utils.RecyclerTouchListener.ClickListener
            /* renamed from: b */
            public void mo446b(View view, int i) {
            }
        }));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.m9092b(this.f9964h);
        builder.m9094a(true).m9090b("CLOSE", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$1P-J3Z4hSGJ7dtug3NnTOwHSKis
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        this.f9965i = builder.m9093b();
    }

    /* renamed from: m */
    private void m526m() {
        this.f9967k = LayoutInflater.from(this);
        this.f9968l = this.f9967k.inflate(R.layout.dialog_fgps_bookmark, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) this.f9968l.findViewById(R.id.recycler_view);
        this.f9959G = (TextView) this.f9968l.findViewById(R.id.empty_bookmark_view);
        this.f9970n = new LinearLayoutManager(getApplicationContext());
        this.f9958F.addAll(this.f9956D.m476a());
        this.f9957E = new BookmarkAdapter(this, this.f9958F);
        recyclerView.setLayoutManager(this.f9970n);
        recyclerView.setAdapter(this.f9957E);
        m528k();
        m519t();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() { // from class: hamba.allah.swt.activities.FgpsActivity.2
            @Override // hamba.allah.swt.utils.RecyclerTouchListener.ClickListener
            /* renamed from: a */
            public void mo447a(View view, int i) {
                Bookmark bookmark = (Bookmark) FgpsActivity.this.f9958F.get(i);
                LatLng latLng = new LatLng(Double.valueOf(bookmark.getLat()).doubleValue(), Double.valueOf(bookmark.getLon()).doubleValue());
                FgpsActivity.this.f9976t.m1473b();
                GoogleMap googleMap = FgpsActivity.this.f9976t;
                MarkerOptions m1318a = new MarkerOptions().m1318a(latLng);
                googleMap.m1475a(m1318a.m1317a("LAT : " + latLng.f9277a + " LON : " + latLng.f9278b));
                FgpsActivity.f9951d = Double.valueOf(latLng.f9277a);
                FgpsActivity.f9952e = Double.valueOf(latLng.f9278b);
                FgpsActivity.this.tv_lat.setText(String.valueOf(FgpsActivity.f9951d));
                FgpsActivity.this.tv_lon.setText(String.valueOf(FgpsActivity.f9952e));
                FgpsActivity.this.f9976t.m1478a(CameraUpdateFactory.m1481a(new LatLng(latLng.f9277a, latLng.f9278b)));
                FgpsActivity.this.f9969m.dismiss();
            }

            @Override // hamba.allah.swt.utils.RecyclerTouchListener.ClickListener
            /* renamed from: b */
            public void mo446b(View view, int i) {
                FgpsActivity.this.m547b(i);
            }
        }));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.m9092b(this.f9968l);
        builder.m9094a(true).m9090b("CLOSE", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$qTNOLQXsAs2pbk_ryW1QEteN2jI
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        this.f9969m = builder.m9093b();
    }

    /* renamed from: n */
    private void m525n() {
        getWindow().setSoftInputMode(3);
    }

    /* renamed from: o */
    private void m524o() {
        if (this.f9962f.m440b("FGPS", false)) {
            this.f9977u.findItem(R.id.start).setIcon(17301539);
        } else {
            this.f9977u.findItem(R.id.start).setIcon(17301540);
        }
    }

    /* renamed from: p */
    private void m523p() {
        this.f9976t.m1473b();
        String str = "https://api.gojekapi.com/gojek/service_type/1/drivers/nearby?location=" + f9951d + "," + f9952e;
        String str2 = "https://p.grabtaxi.com/api/passenger/v2/bookings/nearbydrivers?latitude=" + (String.valueOf(f9951d) + "&longitude=" + String.valueOf(f9952e) + "&taxiTypeId=71");
        String str3 = "https://p.grabtaxi.com/api/passenger/v2/bookings/nearbydrivers?latitude=" + (String.valueOf(f9951d) + "&longitude=" + String.valueOf(f9952e) + "&taxiTypeId=97");
        if (this.f9962f.m440b("grbb", false)) {
            this.google_now.setVisibility(0);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(str2, new Response.Listener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$WmUX9kd6GF8VCh8yWQrsgQ0l4-M
                @Override // com.android.volley.Response.Listener
                public final void onResponse(Object obj) {
                    FgpsActivity.this.m534c((JSONArray) obj);
                }
            }, new Response.ErrorListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$dORJ9c-EGfWEi6DSwEF757Mtu5M
                @Override // com.android.volley.Response.ErrorListener
                public final void onErrorResponse(VolleyError volleyError) {
                    FgpsActivity.this.m536c(volleyError);
                }
            });
            getClass();
            Volley.m8079a(getApplicationContext()).m8194a(jsonArrayRequest);
        }
        if (this.f9962f.m440b("grbc", false)) {
            this.google_now.setVisibility(0);
            JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(str3, new Response.Listener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$fXPLeRBSORUmp-fknxxuexLGDpo
                @Override // com.android.volley.Response.Listener
                public final void onResponse(Object obj) {
                    FgpsActivity.this.m540b((JSONArray) obj);
                }
            }, new Response.ErrorListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$lWAipwFfp8MxiwhfJiN_7Us_tw4
                @Override // com.android.volley.Response.ErrorListener
                public final void onErrorResponse(VolleyError volleyError) {
                    FgpsActivity.this.m544b(volleyError);
                }
            });
            getClass();
            Volley.m8079a(getApplicationContext()).m8194a(jsonArrayRequest2);
        }
        if (this.f9962f.m440b("gr", false)) {
            this.google_now.setVisibility(0);
            JsonArrayRequest jsonArrayRequest3 = new JsonArrayRequest(str, new Response.Listener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$Rk0AaUcdqc2iHIAt0ZPoEtWWEq4
                @Override // com.android.volley.Response.Listener
                public final void onResponse(Object obj) {
                    FgpsActivity.this.m549a((JSONArray) obj);
                }
            }, new Response.ErrorListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$ljch9_vG9OXBISnJYa4hDZtwiuc
                @Override // com.android.volley.Response.ErrorListener
                public final void onErrorResponse(VolleyError volleyError) {
                    FgpsActivity.this.m560a(volleyError);
                }
            }) { // from class: hamba.allah.swt.activities.FgpsActivity.3
                @Override // com.android.volley.Request
                /* renamed from: j */
                public Map<String, String> mo507j() {
                    HashMap hashMap = new HashMap();
                    hashMap.put("Authorization", "Bearer " + FgpsActivity.this.f9962f.m441b("keyGojek", ""));
                    return hashMap;
                }
            };
            getClass();
            Volley.m8079a(getApplicationContext()).m8194a(jsonArrayRequest3);
        }
    }

    /* renamed from: q */
    private void m522q() {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("FGPS").setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(false).setOngoing(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, FgpsActivity.class), 134217728));
        RemoteViews remoteViews = new RemoteViews(getPackageName(), (int) R.layout.view_notification);
        remoteViews.setOnClickPendingIntent(R.id.view_notification__ImageView_Close, PendingIntent.getBroadcast(this, 0, new Intent("hamba.allah.swt.action.NotificationBroadcast_Close"), 1073741824));
        remoteViews.setTextViewText(R.id.notif_lat, "LAT : " + this.f9962f.m441b("lat", ""));
        remoteViews.setTextViewText(R.id.notif_lon, "LON : " + this.f9962f.m441b("lon", ""));
        builder.setContent(remoteViews);
        if (notificationManager != null) {
            notificationManager.notify(1231328131, builder.build());
        }
    }

    /* renamed from: r */
    private void m521r() {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
    }

    /* renamed from: s */
    private void m520s() {
        if (this.f9955C.m466b() > 0) {
            this.f9954B.setVisibility(8);
        } else {
            this.f9954B.setVisibility(0);
        }
    }

    /* renamed from: t */
    private void m519t() {
        if (this.f9956D.m472b() > 0) {
            this.f9959G.setVisibility(8);
        } else {
            this.f9959G.setVisibility(0);
        }
    }

    /* renamed from: u */
    private void m518u() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_fgps_add, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.m9092b(inflate);
        final EditText editText = (EditText) inflate.findViewById(R.id.et_nama);
        final EditText editText2 = (EditText) inflate.findViewById(R.id.et_lat);
        final EditText editText3 = (EditText) inflate.findViewById(R.id.et_lon);
        if (this.f9978v) {
            editText.setText(this.f9979w);
            editText2.setText(this.f9980x);
            editText3.setText(this.f9981y);
        }
        builder.m9095a("SAVE", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$4WcHteg8tTDy2JIQ3pdXvUQ_zr8
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                FgpsActivity.this.m561a(editText, editText2, editText3, dialogInterface, i);
            }
        }).m9090b("Cancel", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$7RPwd7iEy_w9zcoXZRARV9fd7bs
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.m9093b().show();
    }

    /* renamed from: v */
    private void m517v() {
        if (this.f9962f.m440b("gr", false)) {
            this.btn_gojek.setColorFilter(ContextCompat.getColor(this, 17170452), PorterDuff.Mode.SRC_IN);
        } else {
            this.btn_gojek.setColorFilter(ContextCompat.getColor(this, 17170443), PorterDuff.Mode.SRC_IN);
        }
        if (this.f9962f.m440b("grbb", false)) {
            this.btn_grabbike.setColorFilter(ContextCompat.getColor(this, 17170452), PorterDuff.Mode.SRC_IN);
        } else {
            this.btn_grabbike.setColorFilter(ContextCompat.getColor(this, 17170443), PorterDuff.Mode.SRC_IN);
        }
        if (this.f9962f.m440b("grbc", false)) {
            this.btn_grabcar.setColorFilter(ContextCompat.getColor(this, 17170452), PorterDuff.Mode.SRC_IN);
        } else {
            this.btn_grabcar.setColorFilter(ContextCompat.getColor(this, 17170443), PorterDuff.Mode.SRC_IN);
        }
    }

    /* renamed from: w */
    private void m516w() {
        this.f9975s.getKey("custkey").enqueue(new Callback<ModelList>() { // from class: hamba.allah.swt.activities.FgpsActivity.4
            @Override // retrofit2.Callback
            public void onFailure(Call<ModelList> call, Throwable th) {
                call.cancel();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<ModelList> call, retrofit2.Response<ModelList> response) {
                ModelList body = response.body();
                if (body != null) {
                    FgpsActivity.this.f9962f.m443a("keyGojek", body.url);
                }
            }
        });
    }

    @Override // com.hlab.fabrevealmenu.listeners.OnFABMenuSelectedListener
    /* renamed from: a */
    public void mo565a(View view, int i) {
        switch (i) {
            case R.id.fgps_add_bookmark /* 2131296350 */:
                m518u();
                return;
            case R.id.fgps_bookmark /* 2131296351 */:
                this.f9969m.show();
                return;
            case R.id.fgps_gobox /* 2131296352 */:
            case R.id.fgps_gocar /* 2131296353 */:
            case R.id.fgps_goride /* 2131296356 */:
            case R.id.fgps_grabbike /* 2131296358 */:
            case R.id.fgps_grabcar /* 2131296359 */:
            default:
                return;
            case R.id.fgps_gofood /* 2131296354 */:
                if (this.f9974r.m8043a()) {
                    this.f9974r.m8036b();
                }
                startActivity(new Intent(this, GofoodActivity.class));
                return;
            case R.id.fgps_gomart /* 2131296355 */:
                if (this.f9974r.m8043a()) {
                    this.f9974r.m8036b();
                }
                mo575a("Sedang Perbaikan !");
                return;
            case R.id.fgps_goto /* 2131296357 */:
                View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_fgps_goto, (ViewGroup) null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.m9092b(inflate);
                final EditText editText = (EditText) inflate.findViewById(R.id.et_lat);
                final EditText editText2 = (EditText) inflate.findViewById(R.id.et_lon);
                builder.m9094a(false).m9095a("GO", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$yrVWDJilFUTOMo4mCoaBt4aehng
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        FgpsActivity.this.m563a(editText, editText2, dialogInterface, i2);
                    }
                }).m9090b("Cancel", new DialogInterface.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$QQ0Jncl9WxvjBN7lvwVHKJVQyjM
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        dialogInterface.cancel();
                    }
                });
                builder.m9093b().show();
                return;
            case R.id.fgps_grabfood /* 2131296360 */:
                if (this.f9974r.m8043a()) {
                    this.f9974r.m8036b();
                }
                startActivity(new Intent(this, GrabfoodActivity.class));
                return;
            case R.id.fgps_history /* 2131296361 */:
                this.f9965i.show();
                return;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    /* renamed from: a */
    public void mo559a(ConnectionResult connectionResult) {
        mo575a("Error");
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public void mo557a(GoogleMap googleMap) {
        this.f9976t = googleMap;
        this.f9976t.m1474a(true);
        this.f9976t.m1471c().m1417c(false);
        this.f9976t.m1471c().m1419a(true);
        this.f9976t.m1471c().m1418b(false);
        if (f9951d != null || f9952e != null) {
            this.tv_lat.setText(getResources().getString(R.string.lat, String.valueOf(f9951d)));
            this.tv_lon.setText(getResources().getString(R.string.lon, String.valueOf(f9952e)));
            LatLng latLng = new LatLng(f9951d.doubleValue(), f9952e.doubleValue());
            this.f9976t.m1473b();
            GoogleMap googleMap2 = this.f9976t;
            MarkerOptions m1318a = new MarkerOptions().m1318a(latLng);
            googleMap2.m1475a(m1318a.m1317a("LAT : " + latLng.f9277a + " LON : " + latLng.f9278b));
            this.f9976t.m1478a(CameraUpdateFactory.m1481a(latLng));
            this.f9976t.m1472b(CameraUpdateFactory.m1483a(18.0f));
        }
        this.f9976t.m1477a(new GoogleMap.OnMapClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$Gbhp-Z5gImMQ6F7j2ZBtUs5sZho
            @Override // com.google.android.gms.maps.GoogleMap.OnMapClickListener
            public final void onMapClick(LatLng latLng2) {
                FgpsActivity.this.m556a(latLng2);
            }
        });
        this.f9976t.m1476a(new GoogleMap.OnMarkerClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$C8zZvm_mwt9BhGwHQw7-Wgzyu-s
            @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
            public final boolean onMarkerClick(Marker marker) {
                boolean m555a;
                m555a = FgpsActivity.this.m555a(marker);
                return m555a;
            }
        });
    }

    @OnClick({R.id.fab_location})
    @SuppressLint({"MissingPermission"})
    public void getLocation() {
        LocationServices.m1605a(this).m1613g().mo1165a(this, new OnSuccessListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$BU99tmgL8DrXjaqWBwH9ls43gXA
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FgpsActivity.this.m567a((Location) obj);
            }
        });
    }

    /* renamed from: j */
    public void m529j() {
        this.f9953A.clear();
        this.f9953A.addAll(this.f9955C.m469a());
        this.f9982z.notifyDataSetChanged();
        m520s();
    }

    /* renamed from: k */
    public void m528k() {
        this.f9958F.clear();
        this.f9958F.addAll(this.f9956D.m476a());
        this.f9957E.notifyDataSetChanged();
        m519t();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // hamba.allah.swt.Base, android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.FragmentActivity, android.support.p000v4.app.SupportActivity, android.app.Activity
    @SuppressLint({"WorldReadableFiles", "SetWorldReadable"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fgps);
        m525n();
        ButterKnife.bind(this);
        this.f9962f = SharedPreferenceUtils.m444a(this);
        this.f9975s = (RetrofitInterface) NetworkUtil.m462a().create(RetrofitInterface.class);
        m516w();
        this.f9971o = new AlertDialog.Builder(this);
        this.f9955C = new HistoryDBHandler(this);
        this.f9956D = new BookmarkDBHandler(this);
        m517v();
        ActionBar a = m9088a();
        a.getClass();
        a.mo8952a(true);
        m9088a().mo8953a("FGPS");
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        FABRevealMenu fABRevealMenu = (FABRevealMenu) findViewById(R.id.fabMenu);
        if (floatingActionButton != null && fABRevealMenu != null) {
            try {
                m588a(fABRevealMenu);
                fABRevealMenu.m820a(floatingActionButton);
                fABRevealMenu.setOnFABMenuSelectedListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.f9961c = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        this.f9961c.m1446a(this);
        this.f9972p = Places.m1580a(this, null);
        this.f9960H = new GoogleApiClient.Builder(this).m7461a(Places.f9051a).m7461a(Places.f9052b).m7462a(this, this).m7460b();
        this.f9973q = new PlaceAutocompleteAdapter(this, this.f9972p, f9950I, null);
        this.searchtxt.setAdapter(this.f9973q);
        this.searchtxt.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$5U_kawm40oi9vqCQHCtt8AxS24w
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                FgpsActivity.this.m564a(adapterView, view, i, j);
            }
        });
        m527l();
        m526m();
        this.f9974r = new InterstitialAd(this);
        this.f9974r.m8038a("ca-app-pub-1305986134697546/4193507098");
        this.f9974r.m8041a(new AdRequest.Builder().m8061a());
        this.btn_gojek.setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$OCFQ5gawZnbWliC9PXz8FHReUAw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FgpsActivity.this.m532d(view);
            }
        });
        this.btn_grabbike.setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$VeMrMCgIjiO8nypQxf5czE8wnQs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FgpsActivity.this.m537c(view);
            }
        });
        this.btn_grabcar.setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$FgpsActivity$JMO0gjtUoJhMiaUKNqDNEDxZmpg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FgpsActivity.this.m545b(view);
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fgps_menu, menu);
        this.f9977u = menu;
        m524o();
        return true;
    }

    @Override // hamba.allah.swt.Base, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.fgps_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        } else if (itemId != R.id.start) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            if (this.f9962f.m440b("FGPS", false)) {
                this.f9977u.findItem(R.id.start).setIcon(17301540);
                this.f9962f.m442a("FGPS", false);
                mo575a("FGPS Service Stoped");
                m521r();
                if (m587a(GPSTracker.class)) {
                    stopService(new Intent(this, GPSTracker.class));
                }
            } else {
                this.f9977u.findItem(R.id.start).setIcon(17301539);
                this.f9962f.m442a("FGPS", true);
                this.f9962f.m443a("lat", String.valueOf(f9951d));
                this.f9962f.m443a("lon", String.valueOf(f9952e));
                m551a("History", String.valueOf(f9951d), String.valueOf(f9952e));
                m522q();
                if (!m587a(GPSTracker.class)) {
                    startService(new Intent(this, GPSTracker.class));
                }
                mo575a("FGPS Service Started");
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p000v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        m516w();
        m517v();
    }
}
