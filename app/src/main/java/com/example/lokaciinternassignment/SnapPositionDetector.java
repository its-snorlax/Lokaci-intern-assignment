package com.example.lokaciinternassignment;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SnapPositionDetector extends RecyclerView.OnScrollListener {

    private int lastSnapPosition = RecyclerView.NO_POSITION;
    private OnSnapPositionChangeListener onSnapPositionChangeListener;
    private PagerSnapHelper pagerSnapHelper;

    public SnapPositionDetector(OnSnapPositionChangeListener onSnapPositionChangeListener, PagerSnapHelper pagerSnapHelper) {
        this.onSnapPositionChangeListener = onSnapPositionChangeListener;
        this.pagerSnapHelper = pagerSnapHelper;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int currentSnapPosition = getSnapPosition(recyclerView, pagerSnapHelper);
        if(lastSnapPosition != currentSnapPosition){
            onSnapPositionChangeListener.onSnapPositionChange(currentSnapPosition);
            lastSnapPosition = currentSnapPosition;
        }

    }

    private int getSnapPosition(RecyclerView recyclerView, PagerSnapHelper pagerSnapHelper) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager == null){
            return RecyclerView.NO_POSITION;
        }
        View snapView = pagerSnapHelper.findSnapView(layoutManager);
        if(snapView == null){
            return RecyclerView.NO_POSITION;
        }
        return layoutManager.getPosition(snapView);
    }
}
