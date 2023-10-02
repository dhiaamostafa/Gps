package hamba.allah.swt.utils;

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
    private static final CharacterStyle f10047a = new StyleSpan(1);

    /* renamed from: b */
    private ArrayList<AutocompletePrediction> f10048b;

    /* renamed from: c */
    private GeoDataClient f10049c;

    /* renamed from: d */
    private LatLngBounds f10050d;

    /* renamed from: e */
    private AutocompleteFilter f10051e;

    public PlaceAutocompleteAdapter(Context context, GeoDataClient geoDataClient, LatLngBounds latLngBounds, AutocompleteFilter autocompleteFilter) {
        super(context, 17367047, 16908308);
        this.f10049c = geoDataClient;
        this.f10050d = latLngBounds;
        this.f10051e = autocompleteFilter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"LongLogTag"})
    /* renamed from: a */
    public ArrayList<AutocompletePrediction> m450a(CharSequence charSequence) {
        Log.i("PlaceAutocompleteAdapter", "AutoComplete" + ((Object) charSequence));
        Task<AutocompletePredictionBufferResponse> m1588a = this.f10049c.m1588a(charSequence.toString(), this.f10050d, this.f10051e);
        try {
            Tasks.m1190a(m1588a, 60L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        try {
            AutocompletePredictionBufferResponse mo1151d = m1588a.mo1151d();
            Log.i("PlaceAutocompleteAdapter", "Query completed. Received " + mo1151d.mo7096a() + " predictions.");
            return DataBufferUtils.m7085a(mo1151d);
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
        return this.f10048b.get(i);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public int getCount() {
        return this.f10048b.size();
    }

    @Override // android.widget.ArrayAdapter, android.widget.Filterable
    public Filter getFilter() {
        return new Filter() { // from class: hamba.allah.swt.utils.PlaceAutocompleteAdapter.1
            @Override // android.widget.Filter
            public CharSequence convertResultToString(Object obj) {
                return obj instanceof AutocompletePrediction ? ((AutocompletePrediction) obj).mo1522a(null) : super.convertResultToString(obj);
            }

            @Override // android.widget.Filter
            protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                Filter.FilterResults filterResults = new Filter.FilterResults();
                ArrayList arrayList = new ArrayList();
                if (charSequence != null) {
                    arrayList = PlaceAutocompleteAdapter.this.m450a(charSequence);
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
                PlaceAutocompleteAdapter.this.f10048b = (ArrayList) filterResults.values;
                PlaceAutocompleteAdapter.this.notifyDataSetChanged();
            }
        };
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        AutocompletePrediction item = getItem(i);
        ((TextView) view2.findViewById(16908308)).setText(item.mo1520b(f10047a));
        ((TextView) view2.findViewById(16908309)).setText(item.mo1518c(f10047a));
        return view2;
    }
}
