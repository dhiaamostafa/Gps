package hamba.allah.swv.adapter;

import android.content.Context;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import hamba.allah.swv.R;
import hamba.allah.swv.model.History;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
/* loaded from: classes.dex */
public class HistoryAdapter extends RecyclerView.Adapter<MyViewHolder> {

    /* renamed from: a */
    private Context f10014a;

    /* renamed from: b */
    private List<History> f10015b;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public TextView f10016a;

        /* renamed from: b */
        public TextView f10017b;

        /* renamed from: c */
        public TextView f10018c;

        /* renamed from: d */
        public TextView f10019d;

        public MyViewHolder(View view) {
            super(view);
            this.f10016a = (TextView) view.findViewById(R.id.name);
            this.f10017b = (TextView) view.findViewById(R.id.lat);
            this.f10018c = (TextView) view.findViewById(R.id.lon);
            this.f10019d = (TextView) view.findViewById(R.id.timestamp);
        }
    }

    public HistoryAdapter(Context context, List<History> list) {
        this.f10014a = context;
        this.f10015b = list;
    }

    /* renamed from: a */
    private String m489a(String str) {
        try {
            return new SimpleDateFormat("MMM d").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str));
        } catch (ParseException unused) {
            return "";
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_list_row, viewGroup, false));
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        History history = this.f10015b.get(i);
        myViewHolder.f10016a.setText(history.getName());
        myViewHolder.f10017b.setText(history.getLat());
        myViewHolder.f10018c.setText(history.getLon());
        myViewHolder.f10019d.setText(m489a(history.getTimestamp()));
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f10015b.size();
    }
}
