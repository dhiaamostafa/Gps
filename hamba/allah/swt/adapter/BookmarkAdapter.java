package hamba.allah.swt.adapter;

import android.content.Context;
import android.support.p003v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import hamba.allah.swt.R;
import hamba.allah.swt.model.Bookmark;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
/* loaded from: classes.dex */
public class BookmarkAdapter extends RecyclerView.Adapter<MyViewHolder> {

    /* renamed from: a */
    private Context f10005a;

    /* renamed from: b */
    private List<Bookmark> f10006b;

    /* loaded from: classes.dex */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public TextView f10007a;

        /* renamed from: b */
        public TextView f10008b;

        /* renamed from: c */
        public TextView f10009c;

        /* renamed from: d */
        public TextView f10010d;

        public MyViewHolder(View view) {
            super(view);
            this.f10007a = (TextView) view.findViewById(R.id.name);
            this.f10008b = (TextView) view.findViewById(R.id.lat);
            this.f10009c = (TextView) view.findViewById(R.id.lon);
            this.f10010d = (TextView) view.findViewById(R.id.timestamp);
        }
    }

    public BookmarkAdapter(Context context, List<Bookmark> list) {
        this.f10005a = context;
        this.f10006b = list;
    }

    /* renamed from: a */
    private String m489a(String str) {
        try {
            return new SimpleDateFormat("MMM d").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str));
        } catch (ParseException unused) {
            return "";
        }
    }

    @Override // android.support.p003v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookmark_list_row, viewGroup, false));
    }

    @Override // android.support.p003v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Bookmark bookmark = this.f10006b.get(i);
        myViewHolder.f10007a.setText(bookmark.getName());
        myViewHolder.f10008b.setText(bookmark.getLat());
        myViewHolder.f10009c.setText(bookmark.getLon());
        myViewHolder.f10010d.setText(m489a(bookmark.getTimestamp()));
    }

    @Override // android.support.p003v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f10006b.size();
    }
}
