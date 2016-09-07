package by.grodno.toni7777.socialnetwork.ui.dialogs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.base.BaseEventStateFragment;
import by.grodno.toni7777.socialnetwork.base.DividerItemDecoration;
import by.grodno.toni7777.socialnetwork.base.EmptyRecyclerView;
import by.grodno.toni7777.socialnetwork.base.PaginationOnScrollListener;
import by.grodno.toni7777.socialnetwork.base.event.ChatEvent;
import by.grodno.toni7777.socialnetwork.network.model.DialogDTO;
import by.grodno.toni7777.socialnetwork.ui.chat.ChatActivity;
import by.grodno.toni7777.socialnetwork.ui.dialogs.adapter.DialogsAdapter;
import by.grodno.toni7777.socialnetwork.ui.model.ChatDataDVO;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.ErrorHanding;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class DialogsFragment extends BaseEventStateFragment<SwipeRefreshLayout, List<DialogDTO>, DialogsMVP.View, DialogsPresenter>
        implements DialogsMVP.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.dialogs_recycler)
    EmptyRecyclerView mDialogsRecycler;

    @BindView(R.id.progress_pagination_view)
    View mProgressPaginView;

    @BindView(R.id.empty_recycler)
    View mEmptyView;

    @Inject
    DialogsPresenter mDialogsPresenter;

    private DialogsAdapter mDialogsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialogs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        mDialogsAdapter = new DialogsAdapter(new ArrayList<>(), presenter.getMyId(), presenter.getMyAvatar());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mDialogsRecycler.setEmptyView(mEmptyView);
        mDialogsRecycler.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mDialogsRecycler.setAdapter(mDialogsAdapter);
        mDialogsRecycler.setLayoutManager(linearLayoutManager);
        mDialogsRecycler.addOnScrollListener(new PaginationOnScrollListener(linearLayoutManager, mProgressPaginView, presenter));
    }

    @Override
    public void onRefresh() {
        mDialogsAdapter.clear();
        loadData(true);
    }

    @Override
    public LceViewState<List<DialogDTO>, DialogsMVP.View> createViewState() {
        setRetainInstance(true);
        return new RetainingLceViewState<>();
    }

    @Override
    public List<DialogDTO> getData() {
        return mDialogsAdapter.getDialogs();
    }

    @Override
    public DialogsPresenter createPresenter() {
        return mDialogsPresenter;
    }

    @Override
    public void setData(List<DialogDTO> data) {
        contentView.setRefreshing(false);
        mDialogsAdapter.update(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        mEmptyView.setVisibility(View.GONE);
        presenter.loadDataWithOffset(pullToRefresh, Constants.START_LOAD);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
        mProgressPaginView.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showError(Throwable throwable, boolean pullToRefresh) {
        contentView.setRefreshing(false);
        mProgressPaginView.setVisibility(View.GONE);
        Snackbar.make(mDialogsRecycler, ErrorHanding.getErrorMessage(throwable, getContext()), Snackbar.LENGTH_SHORT)
                .show();
    }

    @Subscribe
    public void openChat(ChatEvent event) {
        Log.e("TAG", "Dialogs fragment " + event.getChatId());
        LoginPreferences preferences = new LoginPreferences(getContext());
        ChatDataDVO shareDate = new ChatDataDVO(event.getChatId(), preferences.getUserId(), preferences.getUserFullName(),
                preferences.getUserAvatar(), event.getFriendName(), event.getFriendAvatar());
        Intent chatIntent = new Intent(getContext(), ChatActivity.class);
        chatIntent.putExtra(Constants.SHARE_CHAT_ID, shareDate);
        startActivity(chatIntent);
    }
}
