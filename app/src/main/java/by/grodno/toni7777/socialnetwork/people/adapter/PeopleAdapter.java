package by.grodno.toni7777.socialnetwork.people.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.event.NewFriend;
import by.grodno.toni7777.socialnetwork.base.event.PersonEvent;
import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private static final int FRIEND = R.layout.item_friends;
    private static final int PEOPLE = R.layout.item_person;

    private List<FriendDTO> mFriends;

    public PeopleAdapter(List<FriendDTO> friends) {
        mFriends = friends;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FRIEND) {
            return FriendViewHolder.newInstance(parent);
        } else if (viewType == PEOPLE) {
            return PersonViewHolder.newInstance(parent);
        } else {
            throw new IllegalArgumentException("Unknown view type " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        FriendDTO friend = mFriends.get(position);

        if (viewType == FRIEND) {
            ((FriendViewHolder) holder).bind(friend);
        } else if (viewType == PEOPLE) {
            ((PersonViewHolder) holder).bind(friend);
        } else {
            throw new IllegalArgumentException("View type not found");
        }
    }

    @Override
    public int getItemViewType(int position) {
        FriendDTO friend = mFriends.get(position);

        if (friend.isOnline()) {
            return FRIEND;
        } else if (!friend.isOnline()) {
            return PEOPLE;
        } else {
            throw new IllegalArgumentException("View type not found");
        }
    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    public void clear() {
        mFriends.clear();
    }

    public void update(List<FriendDTO> newFriends) {
        mFriends.addAll(newFriends);
        notifyDataSetChanged();
    }

    public List<FriendDTO> getFriends() {
        return mFriends;
    }

    static class PeopleViewHolder extends RecyclerView.ViewHolder {
        public PeopleViewHolder(View v) {
            super(v);
        }
    }


    static class FriendViewHolder extends PeopleViewHolder {

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
            mName.setText(friend.getFullName());
            if (friend.isOnline()) {
                mOnline.setChecked(true);
            } else {
                mOnline.setChecked(false);
            }
        }

        @NonNull
        public static FriendViewHolder newInstance(ViewGroup parent) {
            return new FriendViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_friends, parent, false));
        }
    }

    static class PersonViewHolder extends PeopleViewHolder {

        @BindView(R.id.person_avatar)
        ImageView mAvatar;

        @BindView(R.id.person_name)
        TextView mName;

        @BindView(R.id.friend_progress)
        ProgressBar mProgress;

        @BindView(R.id.person_add_friend)
        ImageButton mAddFriend;

        @BindView(R.id.add_switcher)
        ViewSwitcher mAddSwitcher;

        private long mId;

        public PersonViewHolder(View view) {
            super(view);
            EventBus.getDefault().register(this);
            ButterKnife.bind(this, view);
        }

        void bind(FriendDTO friend) {
            mId = friend.getId();
            ImageLoad.loadCircleImage(mAvatar, friend.getAvatar());
            mName.setText(friend.getFullName());
        }

        @NonNull
        public static PersonViewHolder newInstance(ViewGroup parent) {
            return new PersonViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_person, parent, false));
        }

        @OnClick(R.id.person_add_friend)
        void addNewFriend() {
            mAddFriend.setVisibility(View.GONE);
            mProgress.setVisibility(View.VISIBLE);
            EventBus.getDefault().post(new PersonEvent(mId));
        }

        @Subscribe
        public void addFriendComplited(NewFriend event) {
            if (mId == event.mId) {
                mAddSwitcher.showNext();
            }
        }

    }
}