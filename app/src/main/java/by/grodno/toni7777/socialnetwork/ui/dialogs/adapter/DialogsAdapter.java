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
import by.grodno.toni7777.socialnetwork.network.model.DialogDTO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class DialogsAdapter extends RecyclerView.Adapter<DialogsAdapter.MessagesViewHolder> {

    private final List<DialogDTO> mDialogs;

    public DialogsAdapter(List<DialogDTO> dialogs) {
        mDialogs = new ArrayList<>(dialogs);
    }

    @Override
    public MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MessagesViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(MessagesViewHolder holder, int position) {
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

    static class MessagesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.interlocutor_image)
        ImageView mAvatarView;

        @BindView(R.id.interlocutor_name)
        TextView mNameView;

        public MessagesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(DialogDTO dialog) {
            ImageLoad.loadCircleImage(mAvatarView, dialog.getFriend().getAvatar());
            mNameView.setText(dialog.getFriend().getName() + " " + dialog.getFriend().getSurname());
        }

        @NonNull
        public static MessagesViewHolder newInstance(ViewGroup parent) {
            return new MessagesViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_messages, parent, false));
        }

        @OnClick(R.id.message_layout)
        void openChat() {
            EventBus.getDefault().post(new ChatEvent());
        }
    }
}
