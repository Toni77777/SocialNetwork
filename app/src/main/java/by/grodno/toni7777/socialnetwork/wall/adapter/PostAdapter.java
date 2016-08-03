package by.grodno.toni7777.socialnetwork.wall.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private static final int FULL = 0;
    private static final int IMAGE = 1;
    private static final int TEXT = 2;

    public PostAdapter(List<PostDTO> posts) {
        mPosts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FULL) {
            return PostViewHolder.newInstance(parent);
        } else if (viewType == IMAGE) {
            return ImageViewHolder.newInstance(parent);
        } else if (viewType == TEXT) {
            return TextViewHolder.newInstance(parent);
        } else {
            throw new IllegalArgumentException("Unknown view type " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        PostDTO post = mPosts.get(position);

        if (viewType == FULL) {
            ((PostViewHolder) holder).bind(post);
        } else if (viewType == IMAGE) {
            ((ImageViewHolder) holder).bind(post);
        } else if (viewType == TEXT) {
            ((TextViewHolder) holder).bind(post);
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

    static class PostViewHolder extends ViewHolder {

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
        public static PostViewHolder newInstance(ViewGroup parent) {
            return new PostViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post_full, parent, false));
        }

        private PostViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(PostDTO post) {
            OwnerDTO ownerDTO = post.getOwner();
            loadImage(ownerAvatar, ownerDTO.getAvatar());
            owner.setText(ownerDTO.getFullName());
            loadImage(postImage, post.getImage());
            postText.setText(post.getText());
            likeCount.setText(String.valueOf(post.getLike()));
            dislikeCount.setText(String.valueOf(post.getDislike()));
        }
    }


    static class ImageViewHolder extends ViewHolder {

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
        public static ImageViewHolder newInstance(ViewGroup parent) {
            return new ImageViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post_image, parent, false));
        }

        private ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(PostDTO post) {
            OwnerDTO ownerDTO = post.getOwner();
            loadImage(ownerAvatar, ownerDTO.getAvatar());
            owner.setText(ownerDTO.getFullName());
            loadImage(postImage, post.getImage());
            likeCount.setText(String.valueOf(post.getLike()));
            dislikeCount.setText(String.valueOf(post.getDislike()));
        }

    }

    static class TextViewHolder extends ViewHolder {

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
        public static TextViewHolder newInstance(ViewGroup parent) {
            return new TextViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post_text, parent, false));
        }

        private TextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(PostDTO post) {
            OwnerDTO ownerDTO = post.getOwner();
            loadImage(ownerAvatar, ownerDTO.getAvatar());
            owner.setText(ownerDTO.getFullName());
            postText.setText(post.getText());
            likeCount.setText(String.valueOf(post.getLike()));
            dislikeCount.setText(String.valueOf(post.getDislike()));
        }
    }

}
