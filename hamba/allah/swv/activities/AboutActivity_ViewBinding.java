package hamba.allah.swv.activities;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import hamba.allah.swv.R;
/* loaded from: classes.dex */
public class AboutActivity_ViewBinding implements Unbinder {

    /* renamed from: a */
    private AboutActivity f9948a;

    public AboutActivity_ViewBinding(AboutActivity aboutActivity, View view) {
        this.f9948a = aboutActivity;
        aboutActivity.tv_version = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_version, "field 'tv_version'", TextView.class);
        aboutActivity.btn_check_update = (Button) Utils.findRequiredViewAsType(view, R.id.btn_check_update, "field 'btn_check_update'", Button.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AboutActivity aboutActivity = this.f9948a;
        if (aboutActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.f9948a = null;
        aboutActivity.tv_version = null;
        aboutActivity.btn_check_update = null;
    }
}
