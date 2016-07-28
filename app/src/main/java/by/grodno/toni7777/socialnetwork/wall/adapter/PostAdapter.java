package by.grodno.toni7777.socialnetwork.wall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class PostAdapter extends BaseAdapter {

    private List<PostDTO> posts;
    private LayoutInflater mLayoutInflater;

    public PostAdapter(Context context, List<PostDTO> posts) {
        this.posts = posts;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        View view = contentView;
        PostViewHolder holder = null;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_post, viewGroup, false);
            holder = new PostViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (PostViewHolder) view.getTag();
        }

        PostDTO post = posts.get(position);
        OwnerDTO owner = post.getOwner();

        loadImage(holder.ownerAvatar, owner.getAvatar());
        holder.owner.setText(owner.getName() + " " + owner.getLastname());
        loadImage(holder.postImage, post.getImage());
        holder.postText.setText(post.getText());
        holder.likeCount.setText(String.valueOf(post.getLike()));
        holder.dislikeCount.setText(String.valueOf(post.getDislike()));
        return view;
    }

    static class PostViewHolder {

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
            ButterKnife.bind(this, view);
        }
    }

    public void update(List<PostDTO> newPosts) {
        posts.clear();
        posts.addAll(newPosts);
        notifyDataSetChanged();
    }

    public List<PostDTO> getPosts() {
        return posts;
    }
}
