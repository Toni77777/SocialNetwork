package by.grodno.toni7777.socialnetwork.ui.search.groups.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.network.model.GroupDTO;
import by.grodno.toni7777.socialnetwork.ui.groups.adapter.GroupsAdapter;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class SearchGroupsAdapter extends RecyclerView.Adapter<SearchGroupsAdapter.GroupViewHolder> {

    private List<GroupDTO> mGroups;

    public SearchGroupsAdapter(List<GroupDTO> groups) {
        mGroups = groups;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return GroupViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        GroupDTO groups = mGroups.get(position);
        holder.bind(groups);
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

    static class GroupViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.group_image)
        ImageView mAvatarView;

        @BindView(R.id.group_name)
        TextView mNameView;

        @BindView(R.id.group_members)
        TextView mMembersView;

        private long mId;

        public GroupViewHolder(View view) {
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
        public static GroupViewHolder newInstance(ViewGroup parent) {
            return new GroupViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_group, parent, false));
        }

//        @OnClick(R.id.friend_layout)
//        void friendClick() {
//            EventBus.getDefault().post(new FriendEvent(mId));
//        }
    }
}
