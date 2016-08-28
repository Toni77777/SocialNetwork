package by.grodno.toni7777.socialnetwork.ui.newpost;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.app.SocialNetworkApp;
import by.grodno.toni7777.socialnetwork.base.BaseMvpViewStateFragment;
import by.grodno.toni7777.socialnetwork.base.event.PostPublishSuccess;
import by.grodno.toni7777.socialnetwork.util.FileUtils;

public class NewPostFragment extends BaseMvpViewStateFragment<NewPostMVP.View, NewPostPresenter>
        implements NewPostMVP.View {

    @BindView(R.id.new_post_text)
    EditText mTextPostView;

    @BindView(R.id.new_image_post)
    ImageView mImagePostView;

    @Inject
    NewPostPresenter mPresenter;

    private String mFileName;
    private ProgressDialog mProgressDialog;
    private static final String SATE_FILE_NAME = "stateFileName";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        initProgressDialog();
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_new, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        ((SocialNetworkApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SATE_FILE_NAME)) {
                mFileName = savedInstanceState.getString(SATE_FILE_NAME);
                if (mFileName != null) {
                    Bitmap image = FileUtils.readFileStorage(getContext(), mFileName);
                    mImagePostView.setImageBitmap(image);
                }
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_post, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.new_post_item) {
            if (mFileName != null) {
                File file = FileUtils.getAbsolutePathFile(getContext(), mFileName);
                presenter.sendImagePost(file);
            } else {
                presenter.sendNewPost(mTextPostView.getText().toString(), null);
            }

            return true;
        } else if (id == R.id.clear_image_item) {
            mImagePostView.setImageResource(0);
            mFileName = null;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SATE_FILE_NAME, mFileName);
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage(getString(R.string.progress_dialog_message));
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
        mProgressDialog.dismiss();

    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void publishSuccess() {
        mProgressDialog.dismiss();
        EventBus.getDefault().post(new PostPublishSuccess());
        getActivity().finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (CAMERA_REQUEST == requestCode && (data != null) && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bitmap = (Bitmap) bundle.get(DATA_KEY);
                mFileName = FileUtils.writeFileStorage(getContext(), bitmap);
                Bitmap image = FileUtils.readFileStorage(getContext(), mFileName);
                mImagePostView.setImageBitmap(image);
            }
        } else if (GALLERY_REQUEST == requestCode && (data != null) && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContext().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            mFileName = FileUtils.writeFileStorage(getContext(), bitmap);
            Bitmap image = FileUtils.readFileStorage(getContext(), mFileName);
            mImagePostView.setImageBitmap(image);
            Log.e("File", "File " + mFileName);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.open_camera)
    void openCameraToMakePhoto() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }


    @OnClick(R.id.open_gallery)
    void openGallery() {
        Log.e("Gallery", "Gallery");
        Intent gallery = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, GALLERY_REQUEST);
    }


    public void onImagePostUploaded(String imageURL) {
        Log.e("Post", "Fragment onImagePostUploaded(Long imageId) Id = " + imageURL);
        presenter.sendNewPost(mTextPostView.getText().toString(), imageURL);
    }

    private static final int CAMERA_REQUEST = 0;
    private static int GALLERY_REQUEST = 1;
    private static final String DATA_KEY = "data";
}
