package by.grodno.toni7777.socialnetwork.wall.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.grodno.toni7777.socialnetwork.R;

import static by.grodno.toni7777.socialnetwork.util.ImageLoad.*;

import by.grodno.toni7777.socialnetwork.network.model.OwnerDTO;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostDTO> mPosts;

    public PostAdapter(List<PostDTO> posts) {
        mPosts = posts;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        PostDTO post = mPosts.get(position);
        OwnerDTO owner = post.getOwner();
        loadImage(holder.ownerAvatar, owner.getAvatar());
        holder.owner.setText(owner.getName() + " " + owner.getLastname());
        loadImage(holder.postImage, post.getImage());
        holder.postText.setText(post.getText());
        holder.likeCount.setText(String.valueOf(post.getLike()));
        holder.dislikeCount.setText(String.valueOf(post.getDislike()));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.owner_image)
        ImageView ownerAvatar;

        @BindView(R.id.owner_name)
        TextView owner;

        @BindView(R.id.post_image)
        ImageView postImage;

        @BindView(R.id.post_text)
        TextView postText;

        @BindView(R.id.like)
        Button like;

        @BindView(R.id.like_count)
        TextView likeCount;

        @BindView(R.id.dislike)
        Button dislike;

        @BindView(R.id.dislike_count)
        TextView dislikeCount;

        public PostViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public void update(List<PostDTO> newPosts) {
        mPosts.clear();
        mPosts.addAll(newPosts);
        notifyDataSetChanged();
    }

    public List<PostDTO> getPosts() {
        return mPosts;
    }
}
