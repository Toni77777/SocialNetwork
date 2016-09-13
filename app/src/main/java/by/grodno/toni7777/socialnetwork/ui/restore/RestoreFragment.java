package by.grodno.toni7777.socialnetwork.ui.restore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.base.BaseMvpViewStateFragment;
import by.grodno.toni7777.socialnetwork.base.event.RestoreEvent;
import by.grodno.toni7777.socialnetwork.util.ErrorHanding;

public class RestoreFragment extends BaseMvpViewStateFragment<RestoreMVP.View, RestorePresenter> implements RestoreMVP.View {


    @BindView(R.id.mail_layout)
    TextInputLayout mMailView;

    @BindView(R.id.restore)
    Button mRestoreView;

    @BindView(R.id.loading)
    View mLoadView;

    @BindView(R.id.restore_layout)
    View mRestView;

    @Inject
    RestorePresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restore, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        mMailView.getEditText().setText("toxa95401@gmail.com");
    }

    @Override
    public ViewState createViewState() {
        return new RestoreViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showRestoreForm();
    }

    @Override
    public RestorePresenter createPresenter() {
        return mPresenter;
    }

    @Override
    public void showRestoreForm() {
        ((RestoreViewState) viewState).setShowLoginForm();
        mLoadView.setVisibility(View.GONE);
        setViewsEnabled(true);
    }

    @Override
    public void showError() {
        ((RestoreViewState) viewState).setShowError();
        mRestView.setVisibility(View.VISIBLE);
        mLoadView.setVisibility(View.GONE);
        setViewsEnabled(true);
    }

    @Override
    public void showLoading() {
        ((RestoreViewState) viewState).setShowLoading();
        setViewsEnabled(false);
        mRestView.setVisibility(View.GONE);
        mLoadView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrors(Throwable throwable) {
        Snackbar.make(mMailView, ErrorHanding.getErrorMessage(throwable, getContext()), Snackbar.LENGTH_SHORT)
                .show();
    }

    @OnClick(R.id.restore)
    void restore() {
        presenter.restorePassword(mMailView.getEditText().getText().toString().trim());
    }

    @Override
    public void onRestoreSuccess() {
        EventBus.getDefault().post(new RestoreEvent());
        getActivity().finish();
    }

    private void setViewsEnabled(boolean enabled) {
        mRestoreView.setEnabled(enabled);
    }
}
