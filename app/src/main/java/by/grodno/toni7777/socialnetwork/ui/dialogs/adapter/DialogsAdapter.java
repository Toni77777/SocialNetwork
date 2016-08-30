package by.grodno.toni7777.socialnetwork.ui.dialogs.adapter;

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
import by.grodno.toni7777.socialnetwork.base.event.ChatEvent;
import by.grodno.toni7777.socialnetwork.base.event.ChatIdEvent;
import by.grodno.toni7777.socialnetwork.network.model.DialogDTO;
import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class DialogsAdapter extends RecyclerView.Adapter<DialogsAdapter.DialogsViewHolder> {

    private final List<DialogDTO> mDialogs;

    public DialogsAdapter(List<DialogDTO> dialogs) {
        mDialogs = new ArrayList<>(dialogs);
    }

    @Override
    public DialogsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DialogsViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(DialogsViewHolder holder, int position) {
        DialogDTO dialog = mDialogs.get(position);
        holder.bind(dialog);
    }

    @Override
    public int getItemCount() {
        return mDialogs.size();
    }

    public void clear() {
        mDialogs.clear();
    }

    public void update(List<DialogDTO> dialgos) {
        mDialogs.addAll(dialgos);
        notifyDataSetChanged();
    }

    public List<DialogDTO> getDialogs() {
        return mDialogs;
    }

    static class DialogsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.interlocutor_image)
        ImageView mAvatarView;

        @BindView(R.id.interlocutor_name)
        TextView mNameView;

        @BindView(R.id.last_message)
        TextView mLastMessageView;

        private long mChatId;
        private String mNameFriend;
        private String mAvatarFriend;

        public DialogsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(DialogDTO dialog) {
            FriendDTO friendDTO = dialog.getFriends().get(0);
            ImageLoad.loadCircleImage(mAvatarView, friendDTO.getAvatar());
            mNameView.setText(friendDTO.getName() + " " + friendDTO.getSurname());
            mChatId = dialog.getChatId();
            mLastMessageView.setText(dialog.getLastMessage());
            mNameFriend = friendDTO.getName();
            mAvatarFriend = friendDTO.getAvatar();
        }

        @NonNull
        public static DialogsViewHolder newInstance(ViewGroup parent) {
            return new DialogsViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_dialog, parent, false));
        }

        @OnClick(R.id.dialog_layout)
        void openChat() {
            EventBus.getDefault().post(new ChatEvent(mChatId, mNameFriend, mAvatarFriend));
//            EventBus.getDefault().post(new ChatIdEvent(mChatId));
        }
    }
}
