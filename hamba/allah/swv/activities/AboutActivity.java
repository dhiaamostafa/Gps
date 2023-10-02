package hamba.allah.swv.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.p001v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import hamba.allah.swv.Base;
import hamba.allah.swv.R;
import hamba.allah.swv.model.ModelList;
import hamba.allah.swv.utils.NetworkUtil;
import hamba.allah.swv.utils.RetrofitInterface;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes.dex */
public class AboutActivity extends Base {
    @BindView(R.id.btn_check_update)
    Button btn_check_update;

    /* renamed from: c */
    RetrofitInterface f9944c;

    /* renamed from: d */
    ProgressDialog f9945d;
    @BindView(R.id.tv_version)
    TextView tv_version;

    @SuppressLint({"StaticFieldLeak"})
    /* loaded from: classes.dex */
    private class dapk extends AsyncTask<String, Integer, String> {
        private dapk() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public String doInBackground(String... strArr) {
            try {
                URL url = new URL(strArr[0]);
                URLConnection openConnection = url.openConnection();
                openConnection.setRequestProperty("Accept-Encoding", "identity");
                openConnection.connect();
                int contentLength = openConnection.getContentLength();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream(), 10240);
                FileOutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/updateFGPS.apk");
                byte[] bArr = new byte[1024];
                long j = 0;
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        bufferedInputStream.close();
                        return null;
                    }
                    long j2 = j + read;
                    publishProgress(Integer.valueOf((int) ((100 * j2) / contentLength)));
                    Log.d("Calculating FIle Size", String.valueOf(contentLength) + "TOTAL : " + j2);
                    fileOutputStream.write(bArr, 0, read);
                    j = j2;
                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public void onPostExecute(String str) {
            AboutActivity.this.f9945d.dismiss();
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/", "updateFGPS.apk");
            if (Build.VERSION.SDK_INT >= 24) {
                Intent dataAndType = new Intent("android.intent.action.VIEW").setDataAndType(AboutActivity.this.m580a(AboutActivity.this, file), "application/vnd.android.package-archive");
                dataAndType.addFlags(1);
                AboutActivity.this.startActivity(dataAndType);
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.addFlags(268435456);
            AboutActivity.this.startActivity(intent);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate(numArr);
            AboutActivity.this.f9945d.setProgress(numArr[0].intValue());
            Log.d("AB Progress", "in onProgressUpdate: " + numArr[0]);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            AboutActivity.this.f9945d = new ProgressDialog(AboutActivity.this);
            AboutActivity.this.f9945d.setMax(100);
            AboutActivity.this.f9945d.setProgress(0);
            AboutActivity.this.f9945d.setMessage("Loading...");
            AboutActivity.this.f9945d.setTitle("Updating FGPS");
            AboutActivity.this.f9945d.setProgressStyle(1);
            AboutActivity.this.f9945d.setCancelable(false);
            AboutActivity.this.f9945d.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m579a(View view) {
        m577j();
    }

    /* renamed from: j */
    private void m577j() {
        this.f9944c.update().enqueue(new Callback<ModelList>() { // from class: hamba.allah.swv.activities.AboutActivity.1
            @Override // retrofit2.Callback
            public void onFailure(Call<ModelList> call, Throwable th) {
                call.cancel();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<ModelList> call, Response<ModelList> response) {
                ModelList body = response.body();
                if (7 >= Integer.parseInt(body.code)) {
                    AboutActivity.this.mo578a("No Updates Available");
                    return;
                }
                AboutActivity.this.mo578a("Update Available, Please wait...");
                new dapk().execute(body.url);
            }
        });
    }

    /* renamed from: a */
    Uri m580a(Context context, File file) {
        return FileProvider.getUriForFile(context, "hamba.allah.swv.fileprovider", file);
    }

    @Override // hamba.allah.swv.Base
    /* renamed from: a */
    public void mo578a(String str) {
        Snackbar.make(findViewById(16908290), str, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // hamba.allah.swv.Base, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(512, 512);
        }
        setContentView(R.layout.about_activity);
        ButterKnife.bind(this);
        this.f9944c = (RetrofitInterface) NetworkUtil.m465a().create(RetrofitInterface.class);
        this.tv_version.setText("2.5");
        this.btn_check_update.setOnClickListener(new View.OnClickListener() { // from class: hamba.allah.swv.activities.-$$Lambda$AboutActivity$ZpIQ9RdVse6uAPBDYlYVVMBmzxI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AboutActivity.this.m579a(view);
            }
        });
    }
}
