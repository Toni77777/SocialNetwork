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
        RepoViewHolder holder = null;

        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_post, viewGroup, false);
            holder = new RepoViewHolder();
            holder.ownerAvatar = (ImageView) view.findViewById(R.id.owner_image);
            holder.owner = (TextView) view.findViewById(R.id.owner_name);
            holder.postImage = (ImageView) view.findViewById(R.id.post_image);
            holder.postText = (TextView) view.findViewById(R.id.post_text);
            holder.like = (Button) view.findViewById(R.id.like);
            holder.likeCount = (TextView) view.findViewById(R.id.like_count);
            holder.dislike = (Button) view.findViewById(R.id.dislike);
            holder.dislikeCount = (TextView) view.findViewById(R.id.dislike_count);
            view.setTag(holder);
        } else {
            holder = (RepoViewHolder) view.getTag();
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

    private static class RepoViewHolder {
        protected ImageView ownerAvatar;
        protected TextView owner;
        protected ImageView postImage;
        protected TextView postText;
        protected Button like;
        protected TextView likeCount;
        protected Button dislike;
        protected TextView dislikeCount;
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
