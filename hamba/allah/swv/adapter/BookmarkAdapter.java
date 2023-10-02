package hamba.allah.swv.adapter;

import android.content.Context;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import hamba.allah.swv.R;
import hamba.allah.swv.model.Bookmark;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
/* loaded from: classes.dex */
public class BookmarkAdapter extends RecyclerView.Adapter<MyViewHolder> {

    /* renamed from: a */
    private Context f10007a;

    /* renamed from: b */
    private List<Bookmark> f10008b;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public TextView f10009a;

        /* renamed from: b */
        public TextView f10010b;

        /* renamed from: c */
        public TextView f10011c;

        /* renamed from: d */
        public TextView f10012d;

        public MyViewHolder(View view) {
            super(view);
            this.f10009a = (TextView) view.findViewById(R.id.name);
            this.f10010b = (TextView) view.findViewById(R.id.lat);
            this.f10011c = (TextView) view.findViewById(R.id.lon);
            this.f10012d = (TextView) view.findViewById(R.id.timestamp);
        }
    }

    public BookmarkAdapter(Context context, List<Bookmark> list) {
        this.f10007a = context;
        this.f10008b = list;
    }

    /* renamed from: a */
    private String m492a(String str) {
        try {
            return new SimpleDateFormat("MMM d").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str));
        } catch (ParseException unused) {
            return "";
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookmark_list_row, viewGroup, false));
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Bookmark bookmark = this.f10008b.get(i);
        myViewHolder.f10009a.setText(bookmark.getName());
        myViewHolder.f10010b.setText(bookmark.getLat());
        myViewHolder.f10011c.setText(bookmark.getLon());
        myViewHolder.f10012d.setText(m492a(bookmark.getTimestamp()));
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f10008b.size();
    }
}
