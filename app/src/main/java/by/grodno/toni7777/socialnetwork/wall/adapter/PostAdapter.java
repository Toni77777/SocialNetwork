package by.grodno.toni7777.socialnetwork.wall.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.grodno.toni7777.socialnetwork.R;

import static by.grodno.toni7777.socialnetwork.util.ImageLoad.*;

import by.grodno.toni7777.socialnetwork.network.model.OwnerDTO;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private final List<PostDTO> mPosts;
    private static final int FULL = R.layout.item_post_full;
    private static final int IMAGE = R.layout.item_post_image;
    private static final int TEXT = R.layout.item_post_text;

    public PostAdapter(List<PostDTO> posts) {
        mPosts = posts;
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
        PostDTO post = mPosts.get(position);

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
        PostDTO post = mPosts.get(position); // backend return null or " " - is all empty field
        if ((post.getImage() == null) | (TextUtils.isEmpty(post.getImage().trim()))) {
            return TEXT;
        } else if ((post.getText() == null) | (TextUtils.isEmpty(post.getText().trim()))) {
            return IMAGE;
        } else if (((!TextUtils.isEmpty(post.getImage().trim())) & (!TextUtils.isEmpty(post.getText().trim())))
                | ((post.getImage() != null) & (post.getText() != null))) {
            return FULL;
        } else {
            throw new IllegalArgumentException("View type not found");
        }
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public void update(List<PostDTO> newPosts) {
        mPosts.addAll(newPosts);
        notifyDataSetChanged();
    }

    public List<PostDTO> getPosts() {
        return mPosts;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    static class FullPostViewHolder extends ViewHolder {

        @BindView(R.id.owner_image)
        ImageView ownerAvatar;

        @BindView(R.id.owner_name)
        TextView owner;

        @BindView(R.id.post_image)
        ImageView postImage;

        @BindView(R.id.post_text)
        TextView postText;

        @BindView(R.id.like)
        ImageButton like;

        @BindView(R.id.like_count)
        TextView likeCount;

        @BindView(R.id.dislike)
        ImageButton dislike;

        @BindView(R.id.dislike_count)
        TextView dislikeCount;

        @NonNull
        public static FullPostViewHolder newInstance(ViewGroup parent) {
            return new FullPostViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post_full, parent, false));
        }

        private FullPostViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(PostDTO post) {
            OwnerDTO ownerDTO = post.getOwner();
            loadCircleImage(ownerAvatar, ownerDTO.getAvatar());
            owner.setText(ownerDTO.getFullName());
            loadImage(postImage, post.getImage());
            postText.setText(post.getText());
            likeCount.setText(String.valueOf(post.getLike()));
            dislikeCount.setText(String.valueOf(post.getDislike()));
        }
    }


    static class ImagePostViewHolder extends ViewHolder {

        @BindView(R.id.owner_image)
        ImageView ownerAvatar;

        @BindView(R.id.owner_name)
        TextView owner;

        @BindView(R.id.post_image)
        ImageView postImage;

        @BindView(R.id.like)
        ImageButton like;

        @BindView(R.id.like_count)
        TextView likeCount;

        @BindView(R.id.dislike)
        ImageButton dislike;

        @BindView(R.id.dislike_count)
        TextView dislikeCount;

        @NonNull
        public static ImagePostViewHolder newInstance(ViewGroup parent) {
            return new ImagePostViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post_image, parent, false));
        }

        private ImagePostViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(PostDTO post) {
            OwnerDTO ownerDTO = post.getOwner();
            loadCircleImage(ownerAvatar, ownerDTO.getAvatar());
            owner.setText(ownerDTO.getFullName());
            loadImage(postImage, post.getImage());
            likeCount.setText(String.valueOf(post.getLike()));
            dislikeCount.setText(String.valueOf(post.getDislike()));
        }

    }

    static class TextPostViewHolder extends ViewHolder {

        @BindView(R.id.owner_image)
        ImageView ownerAvatar;

        @BindView(R.id.owner_name)
        TextView owner;

        @BindView(R.id.post_text)
        TextView postText;

        @BindView(R.id.like)
        ImageButton like;

        @BindView(R.id.like_count)
        TextView likeCount;

        @BindView(R.id.dislike)
        ImageButton dislike;

        @BindView(R.id.dislike_count)
        TextView dislikeCount;

        @NonNull
        public static TextPostViewHolder newInstance(ViewGroup parent) {
            return new TextPostViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post_text, parent, false));
        }

        private TextPostViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(PostDTO post) {
            OwnerDTO ownerDTO = post.getOwner();
            loadCircleImage(ownerAvatar, ownerDTO.getAvatar());
            owner.setText(ownerDTO.getFullName());
            postText.setText(post.getText());
            likeCount.setText(String.valueOf(post.getLike()));
            dislikeCount.setText(String.valueOf(post.getDislike()));
        }
    }

}
