package by.grodno.toni7777.socialnetwork.ui.people.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;
import by.grodno.toni7777.socialnetwork.network.model.FriendsDTO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {

    private List<FriendDTO> mFriends;

    public FriendsAdapter(List<FriendDTO> friends) {
        mFriends = friends;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return FriendViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        FriendDTO friend = mFriends.get(position);
        holder.bind(friend);
    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    public void clear() {
        mFriends.clear();
    }

    public void update(List<FriendDTO> friends) {
        mFriends.addAll(friends);
        notifyDataSetChanged();
    }

    public List<FriendDTO> getFriends() {
        return mFriends;
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.friend_avatar)
        ImageView mAvatar;

        @BindView(R.id.friend_name)
        TextView mName;

        @BindView(R.id.friend_online)
        CheckBox mOnline;

        public FriendViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(FriendDTO friend) {
            ImageLoad.loadCircleImage(mAvatar, friend.getAvatar());
            mName.setText(friend.getName() + " " + friend.getSurname());
            mOnline.setChecked(true);
//            if (friend.isOnline()) {
//                mOnline.setChecked(true);
//            } else {
//                mOnline.setChecked(false);
//            }
        }

        @NonNull
        public static FriendViewHolder newInstance(ViewGroup parent) {
            return new FriendViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_friend, parent, false));
        }
    }

}
