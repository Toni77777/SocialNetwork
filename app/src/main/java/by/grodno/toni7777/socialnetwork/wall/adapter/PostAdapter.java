package by.grodno.toni7777.socialnetwork.wall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;

public class PostAdapter extends BaseAdapter {

    private Context mContext;
    private List<PostDTO> posts;
    private LayoutInflater mLayoutInflater;

    public PostAdapter(Context context, List<PostDTO> posts) {
        mContext = context;
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
            holder.name = (TextView) view.findViewById(R.id.owner);
            holder.text = (TextView) view.findViewById(R.id.post_text);
            holder.image = (ImageView) view.findViewById(R.id.post_image);
            view.setTag(holder);
        } else {
            holder = (RepoViewHolder) view.getTag();

        }

        PostDTO post = posts.get(position);
        holder.name.setText(post.getOwner().getName() + " " + post.getOwner().getLastname());
        holder.text.setText(post.getText());

        Glide.with(mContext).load(post.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
        return view;
    }

    private static class RepoViewHolder {
        protected TextView name;
        protected TextView text;
        protected ImageView image;
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
