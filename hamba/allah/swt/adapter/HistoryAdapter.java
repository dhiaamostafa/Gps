package hamba.allah.swt.adapter;

import android.content.Context;
import android.support.p003v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import hamba.allah.swt.R;
import hamba.allah.swt.model.History;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
/* loaded from: classes.dex */
public class HistoryAdapter extends RecyclerView.Adapter<MyViewHolder> {

    /* renamed from: a */
    private Context f10012a;

    /* renamed from: b */
    private List<History> f10013b;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public TextView f10014a;

        /* renamed from: b */
        public TextView f10015b;

        /* renamed from: c */
        public TextView f10016c;

        /* renamed from: d */
        public TextView f10017d;

        public MyViewHolder(View view) {
            super(view);
            this.f10014a = (TextView) view.findViewById(R.id.name);
            this.f10015b = (TextView) view.findViewById(R.id.lat);
            this.f10016c = (TextView) view.findViewById(R.id.lon);
            this.f10017d = (TextView) view.findViewById(R.id.timestamp);
        }
    }

    public HistoryAdapter(Context context, List<History> list) {
        this.f10012a = context;
        this.f10013b = list;
    }

    /* renamed from: a */
    private String m486a(String str) {
        try {
            return new SimpleDateFormat("MMM d").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str));
        } catch (ParseException unused) {
            return "";
        }
    }

    @Override // android.support.p003v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_list_row, viewGroup, false));
    }

    @Override // android.support.p003v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        History history = this.f10013b.get(i);
        myViewHolder.f10014a.setText(history.getName());
        myViewHolder.f10015b.setText(history.getLat());
        myViewHolder.f10016c.setText(history.getLon());
        myViewHolder.f10017d.setText(m486a(history.getTimestamp()));
    }

    @Override // android.support.p003v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f10013b.size();
    }
}
