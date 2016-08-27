package by.grodno.toni7777.socialnetwork.ui.group.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class PaginationGroupListener extends RecyclerView.OnScrollListener {

    private GroupPagination mListener;
    private LinearLayoutManager mLinearLayoutManager;
    private View mProgressView;
    private long mGroupId;

    public PaginationGroupListener(LinearLayoutManager layoutManager,
                                   View progressView, GroupPagination listener, long groupId) {
        mProgressView = progressView;
        mLinearLayoutManager = layoutManager;
        mListener = listener;
        mGroupId = groupId;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItems = mLinearLayoutManager.getChildCount();
        int totalItems = mLinearLayoutManager.getItemCount();
        int pastVisibleItems = mLinearLayoutManager.findFirstVisibleItemPosition();
        if ((visibleItems + pastVisibleItems) >= totalItems) {
            mProgressView.setVisibility(View.VISIBLE);
            mListener.loadDataWithOffset(mGroupId, true, totalItems);
        }
    }
}
