package by.grodno.toni7777.socialnetwork.ui.groups.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.event.GroupEvent;
import by.grodno.toni7777.socialnetwork.ui.model.GroupDVO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupViewHolder> {

    private final List<GroupDVO> mGroups;

    public GroupsAdapter(List<GroupDVO> groups) {
        mGroups = new ArrayList<>(groups);
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return GroupViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        GroupDVO groups = mGroups.get(position);
        holder.bind(groups);
    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }

    public void clear() {
        mGroups.clear();
    }

    public void update(List<GroupDVO> groups) {
        mGroups.addAll(groups);
        notifyDataSetChanged();
    }

    public List<GroupDVO> getGroups() {
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

        void bind(GroupDVO group) {
            ImageLoad.loadCircleImage(mAvatarView, group.getImage());
            mNameView.setText(group.getName());
            mMembersView.setText("Members " + String.valueOf(group.getMembers()));
            mId = group.getGroupId();

        }

        @NonNull
        public static GroupViewHolder newInstance(ViewGroup parent) {
            return new GroupViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_group, parent, false));
        }

        @OnClick(R.id.group_layout)
        void openGroup() {
            EventBus.getDefault().post(new GroupEvent(mId));
        }
    }
}
