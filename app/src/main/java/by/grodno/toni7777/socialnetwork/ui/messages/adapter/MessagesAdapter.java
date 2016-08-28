package by.grodno.toni7777.socialnetwork.ui.messages.adapter;

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
import by.grodno.toni7777.socialnetwork.test.MessagesObject;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

    private final List<MessagesObject> mMessagesObject;

    public MessagesAdapter(List<MessagesObject> messagesObject) {
        mMessagesObject = messagesObject;
    }

    @Override
    public MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MessagesViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(MessagesViewHolder holder, int position) {
        MessagesObject messages = mMessagesObject.get(position);
        holder.bind(messages);
    }

    @Override
    public int getItemCount() {
        return mMessagesObject.size();
    }

    public void clear() {
        mMessagesObject.clear();
    }

    public void update(List<MessagesObject> groups) {
        mMessagesObject.addAll(groups);
        notifyDataSetChanged();
    }

    public List<MessagesObject> getGroups() {
        return mMessagesObject;
    }

    static class MessagesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.interlocutor_image)
        ImageView mAvatarView;

        @BindView(R.id.interlocutor_name)
        TextView mNameView;

        public MessagesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(MessagesObject messagesObject) {
            ImageLoad.loadCircleImage(mAvatarView, messagesObject.getInterlocutorAvatar());
            mNameView.setText(messagesObject.getInterlocutorName());
        }

        @NonNull
        public static MessagesViewHolder newInstance(ViewGroup parent) {
            return new MessagesViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_messages, parent, false));
        }

//        @OnClick(R.id.group_layout)
//        void openGroup() {
//            EventBus.getDefault().post(new GroupEvent(mId));
//        }
    }
}
