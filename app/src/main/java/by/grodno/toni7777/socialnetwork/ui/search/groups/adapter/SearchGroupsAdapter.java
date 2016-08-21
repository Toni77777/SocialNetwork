package by.grodno.toni7777.socialnetwork.ui.search.groups.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.network.model.GroupDTO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class SearchGroupsAdapter extends RecyclerView.Adapter<SearchGroupsAdapter.ViewHolder> {

    private List<GroupDTO> mGroups;
    private static final int FAVORITE = R.id.type_groups_favorite;
    private static final int SIMPLE = R.id.type_groups_simple;

    public SearchGroupsAdapter(List<GroupDTO> groups) {
        mGroups = groups;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FAVORITE) {
            return FavoriteGroupViewHolder.newInstance(parent);
        } else if (viewType == SIMPLE) {
            return SimpleGroupViewHolder.newInstance(parent);
        } else {
            throw new IllegalArgumentException("Unknown view type " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        GroupDTO group = mGroups.get(position);
        if (viewType == FAVORITE) {
            ((FavoriteGroupViewHolder) holder).bind(group);
        } else if (viewType == SIMPLE) {
            ((SimpleGroupViewHolder) holder).bind(group);
        }
    }

    @Override
    public int getItemViewType(int position) {
        GroupDTO group = mGroups.get(position);
        if (group.getMember() == 1) {
            return FAVORITE;
        } else if (group.getMember() == 0) {
            return SIMPLE;
        } else {
            throw new IllegalArgumentException("View type not found");
        }
    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }

    public void clear() {
        mGroups.clear();
    }

    public void update(List<GroupDTO> friends) {
        mGroups.addAll(friends);
        notifyDataSetChanged();
    }

    public List<GroupDTO> getGroups() {
        return mGroups;
    }

    static abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    static class FavoriteGroupViewHolder extends ViewHolder {

        @BindView(R.id.search_favorite_group_image)
        ImageView mAvatarView;

        @BindView(R.id.search_favorite_group_name)
        TextView mNameView;

        @BindView(R.id.search_favorite_group_members)
        TextView mMembersView;

        public FavoriteGroupViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(GroupDTO group) {
            ImageLoad.loadCircleImage(mAvatarView, group.getImage());
            mNameView.setText(group.getName());
            mMembersView.setText("Members " + String.valueOf(group.getMember()));
        }

        @NonNull
        public static FavoriteGroupViewHolder newInstance(ViewGroup parent) {
            return new FavoriteGroupViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_search_group_favorite, parent, false));
        }
    }

    static class SimpleGroupViewHolder extends ViewHolder {

        @BindView(R.id.search_simple_group_image)
        ImageView mAvatarView;

        @BindView(R.id.search_simple_group_name)
        TextView mNameView;

        @BindView(R.id.search_simple_group_members)
        TextView mMembersView;
//
//        @BindView(R.id.friend_progress)
//        ProgressBar mProgress;
//
//        @BindView(R.id.person_add_friend)
//        ImageButton mAddFriend;
//
//        @BindView(R.id.add_switcher)
//        ViewSwitcher mAddSwitcher;

        private long mId;

        public SimpleGroupViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(GroupDTO group) {
            ImageLoad.loadCircleImage(mAvatarView, group.getImage());
            mNameView.setText(group.getName());
            mMembersView.setText("Members " + String.valueOf(group.getMember()));
            mId = group.getGroupId();

        }

        @NonNull
        public static SimpleGroupViewHolder newInstance(ViewGroup parent) {
            return new SimpleGroupViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_search_group_simple, parent, false));
        }

//        @OnClick(R.id.friend_layout)
//        void friendClick() {
//            EventBus.getDefault().post(new FriendEvent(mId));
//        }
    }
}
