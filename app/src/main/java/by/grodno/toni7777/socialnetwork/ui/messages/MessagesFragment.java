package by.grodno.toni7777.socialnetwork.ui.messages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.BaseFragment;
import by.grodno.toni7777.socialnetwork.base.event.ChatEvent;
import by.grodno.toni7777.socialnetwork.test.MessagesObject;
import by.grodno.toni7777.socialnetwork.ui.chat.ChatActivity;
import by.grodno.toni7777.socialnetwork.ui.messages.adapter.MessagesAdapter;

public class MessagesFragment extends BaseFragment {

    @BindView(R.id.messages_recycler)
    RecyclerView mMessagesRecycler;

    private MessagesAdapter mMessagesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMessagesAdapter = new MessagesAdapter(createFakeFriend(5));
        mMessagesRecycler.setAdapter(mMessagesAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mMessagesRecycler.setLayoutManager(linearLayoutManager);
        EventBus.getDefault().register(this);
    }

    private List<MessagesObject> createFakeFriend(int count) {
        List<MessagesObject> messagesObjects = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            MessagesObject messages = new MessagesObject("Name Surname", "http://fotocop.ru/img/picture/Jun/05/b30d097dfb01cc919320330be45e5f6c/norm_3.jpg");
            messagesObjects.add(messages);
        }
        return messagesObjects;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void openChat(ChatEvent event) {
        Intent chatIntent = new Intent(getContext(), ChatActivity.class);
        startActivity(chatIntent);
    }
}
