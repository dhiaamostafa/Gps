package hamba.allah.swt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import hamba.allah.swt.R;
/* loaded from: classes.dex */
public class AdsActivity extends AppCompatActivity {

    /* renamed from: a */
    TextView f9947a;

    /* renamed from: b */
    TextView f9948b;

    /* renamed from: c */
    TextView f9949c;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m570a(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p003v7.app.AppCompatActivity, android.support.p000v4.app.FragmentActivity, android.support.p000v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ads_activity);
        this.f9947a = (TextView) findViewById(R.id.title);
        this.f9948b = (TextView) findViewById(R.id.content);
        this.f9949c = (TextView) findViewById(R.id.btn_close);
        this.f9949c.setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swt.activities.-$$Lambda$AdsActivity$e75mLiTge_XmvoSf9ubKgK7c0wk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdsActivity.this.m570a(view);
            }
        });
        Intent intent = getIntent();
        if (intent.hasExtra("from_id") && intent.getStringExtra("from_id").equals("iklan")) {
            this.f9947a.setText(intent.getStringExtra("title"));
            this.f9948b.setText(intent.getStringExtra("content"));
            intent.removeExtra("from_id");
        }
    }
}
