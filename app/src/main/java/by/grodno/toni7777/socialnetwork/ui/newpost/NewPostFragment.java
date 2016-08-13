package by.grodno.toni7777.socialnetwork.ui.newpost;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import javax.inject.Inject;

import butterknife.BindView;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.base.BaseMvpViewStateFragment;

public class NewPostFragment extends BaseMvpViewStateFragment<NewPostMVP.NewPostView, NewPostPresenter>
        implements NewPostMVP.NewPostView {

    @BindView(R.id.new_post_text)
    EditText mTextPostView;

    @Inject
    NewPostPresenter mPresenter;

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initProgressDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_new, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_post, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.new_post_item) {
            presenter.sendNewPost(mTextPostView.getText().toString(), null);
//            mProgressDialog.show();
            return true;
        } else if (id == R.id.clear_image_item) {
            mProgressDialog.dismiss();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Send post to server");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
    }

    @Override
    public NewPostPresenter createPresenter() {
        return mPresenter;
    }

    @Override
    public ViewState createViewState() {
        return new NewPostViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        showPostForm();
    }

    @Override
    public void showPostForm() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void sendSuccess() {

    }
}
