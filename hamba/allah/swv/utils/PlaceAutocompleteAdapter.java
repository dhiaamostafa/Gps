package hamba.allah.swv.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.data.DataBufferUtils;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class PlaceAutocompleteAdapter extends ArrayAdapter<AutocompletePrediction> implements Filterable {

    /* renamed from: a */
    private static final CharacterStyle f10049a = new StyleSpan(1);

    /* renamed from: b */
    private ArrayList<AutocompletePrediction> f10050b;

    /* renamed from: c */
    private GeoDataClient f10051c;

    /* renamed from: d */
    private LatLngBounds f10052d;

    /* renamed from: e */
    private AutocompleteFilter f10053e;

    public PlaceAutocompleteAdapter(Context context, GeoDataClient geoDataClient, LatLngBounds latLngBounds, AutocompleteFilter autocompleteFilter) {
        super(context, 17367047, 16908308);
        this.f10051c = geoDataClient;
        this.f10052d = latLngBounds;
        this.f10053e = autocompleteFilter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"LongLogTag"})
    /* renamed from: a */
    public ArrayList<AutocompletePrediction> m453a(CharSequence charSequence) {
        Log.i("PlaceAutocompleteAdapter", "AutoComplete" + ((Object) charSequence));
        Task<AutocompletePredictionBufferResponse> m1597a = this.f10051c.m1597a(charSequence.toString(), this.f10052d, this.f10053e);
        try {
            Tasks.m1199a(m1597a, 60L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        try {
            AutocompletePredictionBufferResponse mo1160d = m1597a.mo1160d();
            Log.i("PlaceAutocompleteAdapter", "Query completed. Received " + mo1160d.mo7105a() + " predictions.");
            return DataBufferUtils.m7094a(mo1160d);
        } catch (RuntimeExecutionException e2) {
            Context context = getContext();
            Toast.makeText(context, "Error contacting API: " + e2.toString(), 0).show();
            Log.e("PlaceAutocompleteAdapter", "Error getting autocomplete prediction API call", e2);
            return null;
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /* renamed from: a */
    public AutocompletePrediction getItem(int i) {
        return this.f10050b.get(i);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.f10050b.size();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Filterable
    public Filter getFilter() {
        return new Filter() { // from class: hamba.allah.swv.utils.PlaceAutocompleteAdapter.1
            @Override // android.widget.Filter
            public CharSequence convertResultToString(Object obj) {
                return obj instanceof AutocompletePrediction ? ((AutocompletePrediction) obj).mo1531a(null) : super.convertResultToString(obj);
            }

            @Override // android.widget.Filter
            protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                Filter.FilterResults filterResults = new Filter.FilterResults();
                ArrayList arrayList = new ArrayList();
                if (charSequence != null) {
                    arrayList = PlaceAutocompleteAdapter.this.m453a(charSequence);
                }
                filterResults.values = arrayList;
                if (arrayList != null) {
                    filterResults.count = arrayList.size();
                } else {
                    filterResults.count = 0;
                }
                return filterResults;
            }

            @Override // android.widget.Filter
            protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                if (filterResults == null || filterResults.count <= 0) {
                    PlaceAutocompleteAdapter.this.notifyDataSetInvalidated();
                    return;
                }
                PlaceAutocompleteAdapter.this.f10050b = (ArrayList) filterResults.values;
                PlaceAutocompleteAdapter.this.notifyDataSetChanged();
            }
        };
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        AutocompletePrediction item = getItem(i);
        ((TextView) view2.findViewById(16908308)).setText(item.mo1529b(f10049a));
        ((TextView) view2.findViewById(16908309)).setText(item.mo1527c(f10049a));
        return view2;
    }
}
