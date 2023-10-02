package hamba.allah.swt.utils;

import android.content.Context;
import android.support.p003v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
/* loaded from: classes.dex */
public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    /* renamed from: a */
    private ClickListener f10053a;

    /* renamed from: b */
    private GestureDetector f10054b;

    /* loaded from: classes.dex */
    public interface ClickListener {
        /* renamed from: a */
        void mo447a(View view, int i);

        /* renamed from: b */
        void mo446b(View view, int i);
    }

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.f10053a = clickListener;
        this.f10054b = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: hamba.allah.swt.utils.RecyclerTouchListener.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (findChildViewUnder == null || clickListener == null) {
                    return;
                }
                clickListener.mo446b(findChildViewUnder, recyclerView.getChildAdapterPosition(findChildViewUnder));
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }
        });
    }

    @Override // android.support.p003v7.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (findChildViewUnder == null || this.f10053a == null || !this.f10054b.onTouchEvent(motionEvent)) {
            return false;
        }
        this.f10053a.mo447a(findChildViewUnder, recyclerView.getChildAdapterPosition(findChildViewUnder));
        return false;
    }

    @Override // android.support.p003v7.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // android.support.p003v7.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }
}
