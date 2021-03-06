package by.grodno.toni7777.socialnetwork.ui.wall.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;

import static by.grodno.toni7777.socialnetwork.util.ImageLoad.*;

import by.grodno.toni7777.socialnetwork.base.event.PostEvent;
import by.grodno.toni7777.socialnetwork.network.model.OwnerDTO;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.ui.model.OwnerDVO;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private final List<PostDVO> mPosts;
    private static final int FULL = R.id.type_post_full;
    private static final int IMAGE = R.id.type_post_image;
    private static final int TEXT = R.id.type_post_text;

    public PostAdapter(List<PostDVO> posts) {
        mPosts = new ArrayList<>(posts);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FULL) {
            return FullPostViewHolder.newInstance(parent);
        } else if (viewType == IMAGE) {
            return ImagePostViewHolder.newInstance(parent);
        } else if (viewType == TEXT) {
            return TextPostViewHolder.newInstance(parent);
        } else {
            throw new IllegalArgumentException("Unknown view type " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        PostDVO post = mPosts.get(position);

        if (viewType == FULL) {
            ((FullPostViewHolder) holder).bind(post);
        } else if (viewType == IMAGE) {
            ((ImagePostViewHolder) holder).bind(post);
        } else if (viewType == TEXT) {
            ((TextPostViewHolder) holder).bind(post);
        }
    }

    @Override
    public int getItemViewType(int position) {
        PostDVO post = mPosts.get(position); // backend return null or " " - is all empty field
        if ((post.getImage() == null) || (TextUtils.isEmpty(post.getImage().trim()))) {
            return TEXT;
        } else if ((post.getText() == null) || (TextUtils.isEmpty(post.getText().trim()))) {
            return IMAGE;
        } else if (((!TextUtils.isEmpty(post.getImage().trim())) && (!TextUtils.isEmpty(post.getText().trim())))
                || ((post.getImage() != null) & (post.getText() != null))) {
            return FULL;
        } else {
            throw new IllegalArgumentException("View type not found");
        }
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public void clear() {
        mPosts.clear();
    }

    public void update(List<PostDVO> newPosts) {
        mPosts.addAll(newPosts);
        notifyDataSetChanged();
    }

    public List<PostDVO> getPosts() {
        return mPosts;
    }

    public void deleteRemovedPost(long postId) {
        for (PostDVO post : mPosts) {
            if (post.getPostId() == postId) {
                int index = mPosts.indexOf(post);
                mPosts.remove(post);
                notifyItemRemoved(index);
                return;
            }
        }
    }

    static abstract class ViewHolder extends RecyclerView.ViewHolder implements PopupMenu.OnMenuItemClickListener {
        public ViewHolder(View v) {
            super(v);
        }
    }

    static class FullPostViewHolder extends ViewHolder {

        @BindView(R.id.owner_image)
        ImageView mOwnerAvatarView;

        @BindView(R.id.owner_name)
        TextView mOwnerFullNameView;

        @BindView(R.id.post_date)
        TextView mDateView;

        @BindView(R.id.post_menu_dot)
        ImageView mPopupView;

        @BindView(R.id.post_image)
        ImageView mPostImageView;

        @BindView(R.id.post_text)
        TextView mPostTextView;

        @BindView(R.id.like)
        ImageView mLikeView;

        @BindView(R.id.like_count)
        TextView mLikeCountView;

        @BindView(R.id.dislike)
        ImageView mDislikeView;

        @BindView(R.id.dislike_count)
        TextView mDislikeCountView;

        private long mPostId;

        @NonNull
        public static FullPostViewHolder newInstance(ViewGroup parent) {
            return new FullPostViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post_full, parent, false));
        }

        private FullPostViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(PostDVO post) {
            OwnerDVO ownerDVO = post.getOwner();
            loadCircleImage(mOwnerAvatarView, ownerDVO.getAvatar());
            mDateView.setText(post.getDate());
            mOwnerFullNameView.setText(ownerDVO.getFullName());
            loadImage(mPostImageView, post.getImage());
            mPostTextView.setText(post.getText());
            mLikeCountView.setText(String.valueOf(post.getLike()));
            mDislikeCountView.setText(String.valueOf(post.getDislike()));
            mPostId = post.getPostId();
        }

        @OnClick(R.id.post_menu_dot)
        void showPopup() {
            PopupMenu popupMenu = new PopupMenu(mPopupView.getContext(), mPopupView);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.menu_post_popup);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == R.id.post_remove_item) {
                EventBus.getDefault().post(new PostEvent(mPostId));
            }
            return false;
        }
    }


    static class ImagePostViewHolder extends ViewHolder {

        @BindView(R.id.owner_image)
        ImageView mOwnerAvatarView;

        @BindView(R.id.owner_name)
        TextView mOwnerFullNameView;

        @BindView(R.id.post_date)
        TextView mDateView;

        @BindView(R.id.post_menu_dot)
        ImageView mPopupView;

        @BindView(R.id.post_image)
        ImageView mPostImageView;

        @BindView(R.id.like)
        ImageView mLikeView;

        @BindView(R.id.like_count)
        TextView mLikeCountView;

        @BindView(R.id.dislike)
        ImageView mDislikeView;

        @BindView(R.id.dislike_count)
        TextView mDislikeCountView;

        private long mPostId;

        @NonNull
        public static ImagePostViewHolder newInstance(ViewGroup parent) {
            return new ImagePostViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post_image, parent, false));
        }

        private ImagePostViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(PostDVO post) {
            OwnerDVO ownerDVO = post.getOwner();
            loadCircleImage(mOwnerAvatarView, ownerDVO.getAvatar());
            mDateView.setText(post.getDate());
            mOwnerFullNameView.setText(ownerDVO.getFullName());
            loadImage(mPostImageView, post.getImage());
            mLikeCountView.setText(String.valueOf(post.getLike()));
            mDislikeCountView.setText(String.valueOf(post.getDislike()));
            mPostId = post.getPostId();
        }

        @OnClick(R.id.post_menu_dot)
        void showPopup() {
            PopupMenu popupMenu = new PopupMenu(mPopupView.getContext(), mPopupView);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.menu_post_popup);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == R.id.post_remove_item) {
                EventBus.getDefault().post(new PostEvent(mPostId));
            }
            return false;
        }

    }

    static class TextPostViewHolder extends ViewHolder {

        @BindView(R.id.owner_image)
        ImageView mOwnerAvatarView;

        @BindView(R.id.owner_name)
        TextView mOwnerFullNameView;

        @BindView(R.id.post_date)
        TextView mDateView;

        @BindView(R.id.post_menu_dot)
        ImageView mPopupView;

        @BindView(R.id.post_text)
        TextView mPostTextView;

        @BindView(R.id.like)
        ImageView mLikeView;

        @BindView(R.id.like_count)
        TextView mLikeCountView;

        @BindView(R.id.dislike)
        ImageView mDislikeView;

        @BindView(R.id.dislike_count)
        TextView mDislikeCountView;

        private long mPostId;

        @NonNull
        public static TextPostViewHolder newInstance(ViewGroup parent) {
            return new TextPostViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post_text, parent, false));
        }

        private TextPostViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(PostDVO post) {
            OwnerDVO ownerDVO = post.getOwner();
            loadCircleImage(mOwnerAvatarView, ownerDVO.getAvatar());
            mOwnerFullNameView.setText(ownerDVO.getFullName());
            mDateView.setText(post.getDate());
            mPostTextView.setText(post.getText());
            mLikeCountView.setText(String.valueOf(post.getLike()));
            mDislikeCountView.setText(String.valueOf(post.getDislike()));
            mPostId = post.getPostId();
        }

        @OnClick(R.id.post_menu_dot)
        void showPopup() {
            PopupMenu popupMenu = new PopupMenu(mPopupView.getContext(), mPopupView);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.menu_post_popup);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == R.id.post_remove_item) {
                EventBus.getDefault().post(new PostEvent(mPostId));
            }
            return false;
        }
    }

}
