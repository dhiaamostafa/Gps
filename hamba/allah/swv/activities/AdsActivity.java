package hamba.allah.swv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.p004v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import hamba.allah.swv.R;
/* loaded from: classes.dex */
public class AdsActivity extends AppCompatActivity {

    /* renamed from: a */
    TextView f9949a;

    /* renamed from: b */
    TextView f9950b;

    /* renamed from: c */
    TextView f9951c;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m573a(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ads_activity);
        this.f9949a = (TextView) findViewById(R.id.title);
        this.f9950b = (TextView) findViewById(R.id.content);
        this.f9951c = (TextView) findViewById(R.id.btn_close);
        this.f9951c.setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$AdsActivity$e75mLiTge_XmvoSf9ubKgK7c0wk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdsActivity.this.m573a(view);
            }
        });
        Intent intent = getIntent();
        if (intent.hasExtra("from_id") && intent.getStringExtra("from_id").equals("iklan")) {
            this.f9949a.setText(intent.getStringExtra("title"));
            this.f9950b.setText(intent.getStringExtra("content"));
            intent.removeExtra("from_id");
        }
    }
}
