package hamba.allah.swt.activities;

import android.support.p003v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import hamba.allah.swt.R;
/* loaded from: classes.dex */
public class FgpsActivity_ViewBinding implements Unbinder {

    /* renamed from: a */
    private FgpsActivity f9987a;

    /* renamed from: b */
    private View f9988b;

    public FgpsActivity_ViewBinding(final FgpsActivity fgpsActivity, View view) {
        this.f9987a = fgpsActivity;
        fgpsActivity.tv_lat = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_lat, "field 'tv_lat'", TextView.class);
        fgpsActivity.tv_lon = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_lon, "field 'tv_lon'", TextView.class);
        fgpsActivity.searchtxt = (AutoCompleteTextView) Utils.findRequiredViewAsType(view, R.id.searchbox, "field 'searchtxt'", AutoCompleteTextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.fab_location, "field 'fab_location' and method 'getLocation'");
        fgpsActivity.fab_location = (ImageView) Utils.castView(findRequiredView, R.id.fab_location, "field 'fab_location'", ImageView.class);
        this.f9988b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: hamba.allah.swt.activities.FgpsActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fgpsActivity.getLocation();
            }
        });
        fgpsActivity.btn_gojek = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.btn_gojek, "field 'btn_gojek'", AppCompatImageView.class);
        fgpsActivity.btn_grabbike = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.btn_grabbike, "field 'btn_grabbike'", AppCompatImageView.class);
        fgpsActivity.btn_grabcar = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.btn_grabcar, "field 'btn_grabcar'", AppCompatImageView.class);
        fgpsActivity.google_now = (SmoothProgressBar) Utils.findRequiredViewAsType(view, R.id.google_now, "field 'google_now'", SmoothProgressBar.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        FgpsActivity fgpsActivity = this.f9987a;
        if (fgpsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f9987a = null;
        fgpsActivity.tv_lat = null;
        fgpsActivity.tv_lon = null;
        fgpsActivity.searchtxt = null;
        fgpsActivity.fab_location = null;
        fgpsActivity.btn_gojek = null;
        fgpsActivity.btn_grabbike = null;
        fgpsActivity.btn_grabcar = null;
        fgpsActivity.google_now = null;
        this.f9988b.setOnClickListener(null);
        this.f9988b = null;
    }
}
