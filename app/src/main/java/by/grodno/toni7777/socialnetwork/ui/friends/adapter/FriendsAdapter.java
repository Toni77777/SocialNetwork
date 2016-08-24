package by.grodno.toni7777.socialnetwork.ui.friends.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.event.FriendEvent;
import by.grodno.toni7777.socialnetwork.ui.model.FriendDVO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {

    private final List<FriendDVO> mFriends;

    public FriendsAdapter(List<FriendDVO> friends) {
        mFriends = new ArrayList<>(friends);
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return FriendViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        FriendDVO friend = mFriends.get(position);
        holder.bind(friend);
    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    public void clear() {
        mFriends.clear();
    }

    public void update(List<FriendDVO> friends) {
        mFriends.addAll(friends);
        notifyDataSetChanged();
    }

    public List<FriendDVO> getFriends() {
        return mFriends;
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.friend_avatar)
        ImageView mAvatarView;

        @BindView(R.id.friend_name)
        TextView mNameView;

        @BindView(R.id.friend_online)
        CheckBox mOnlineView;

        private long mId;

        public FriendViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        // Need server responce online/unonline
        void bind(FriendDVO friend) {
            ImageLoad.loadCircleImage(mAvatarView, friend.getAvatar());
            mNameView.setText(friend.getFullName());
            mOnlineView.setChecked(true);
            mId = friend.getId();
            if (friend.isOnline()) {
                mOnlineView.setChecked(true);
            } else {
                mOnlineView.setChecked(false);
            }
        }

        @NonNull
        public static FriendViewHolder newInstance(ViewGroup parent) {
            return new FriendViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_friend, parent, false));
        }

        @OnClick(R.id.friend_layout)
        void friendClick() {
            EventBus.getDefault().post(new FriendEvent(mId));
        }
    }

}
