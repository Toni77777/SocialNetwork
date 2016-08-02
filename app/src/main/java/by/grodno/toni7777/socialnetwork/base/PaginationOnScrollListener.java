package by.grodno.toni7777.socialnetwork.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class PaginationOnScrollListener extends RecyclerView.OnScrollListener {

    private LoadPagination mListener;
    private LinearLayoutManager mLinearLayoutManager;
    private View mProgressView;

    public PaginationOnScrollListener(LinearLayoutManager layoutManager,
                                      View progressView, LoadPagination listener) {
        mProgressView = progressView;
        mLinearLayoutManager = layoutManager;
        mListener = listener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            int visibleItems = mLinearLayoutManager.getChildCount();
            int totalItems = mLinearLayoutManager.getItemCount();
            int pastVisibleItems = mLinearLayoutManager.findFirstVisibleItemPosition();
            if ((visibleItems + pastVisibleItems) >= totalItems) {
                mProgressView.setVisibility(View.VISIBLE);
                mListener.loadDataWithOffset(true, totalItems);
            }
        }
    }
}
