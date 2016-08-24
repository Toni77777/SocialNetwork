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

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.event.SearchGroupEvent;
import by.grodno.toni7777.socialnetwork.network.model.GroupDTO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class SearchGroupsAdapter extends RecyclerView.Adapter<SearchGroupsAdapter.ViewHolder> {

    private final List<GroupDTO> mGroups;
    private static final int FAVORITE = R.id.type_groups_favorite;
    private static final int SIMPLE = R.id.type_groups_simple;

    public SearchGroupsAdapter(List<GroupDTO> groups) {
        mGroups = new ArrayList<>(groups);
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
        if (group.isMember()) {
            return FAVORITE;
        } else {
            return SIMPLE;
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

    public void updateNewFavoriteGroup(Long groupId) {
        for (GroupDTO group : mGroups) {
            if (group.getGroupId() == groupId) {
                int index = mGroups.indexOf(group);
                group.setMember(true);
                notifyItemChanged(index);
                return;
            }
        }
    }

    public String getNewFavoriteName(Long groupId) {
        for (GroupDTO group : mGroups) {
            if (group.getGroupId() == groupId) {
                return group.getName();
            }
        }
        throw new IllegalArgumentException("New Friend  not found from Person adapter");
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
            mMembersView.setText("Members " + String.valueOf(group.getMembers()));
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
        @BindView(R.id.favorite_progress)
        ProgressBar mProgress;

        @BindView(R.id.group_add_favorite)
        ImageButton mAddFavorite;

        @BindView(R.id.add_switcher)
        ViewSwitcher mAddSwitcher;

        private long mId;

        public SimpleGroupViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(GroupDTO group) {
            ImageLoad.loadCircleImage(mAvatarView, group.getImage());
            mNameView.setText(group.getName());
            mMembersView.setText("Members " + String.valueOf(group.getMembers()));
            mId = group.getGroupId();

        }

        @NonNull
        public static SimpleGroupViewHolder newInstance(ViewGroup parent) {
            return new SimpleGroupViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_search_group_simple, parent, false));
        }

        @OnClick(R.id.group_add_favorite)
        void addNewFavorite() {
            mAddFavorite.setVisibility(View.GONE);
            mProgress.setVisibility(View.VISIBLE);
            EventBus.getDefault().post(new SearchGroupEvent(mId));
        }
    }
}
