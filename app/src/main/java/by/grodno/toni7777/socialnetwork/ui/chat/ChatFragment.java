package by.grodno.toni7777.socialnetwork.ui.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.BaseFragment;
import by.grodno.toni7777.socialnetwork.network.model.ChatMessageDTO;
import by.grodno.toni7777.socialnetwork.ui.chat.adapter.ChatAdapter;
import by.grodno.toni7777.socialnetwork.ui.model.ChatDataDVO;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class ChatFragment extends BaseFragment {

    @BindView(R.id.friend_name)
    TextView mFriendNameView;

    @BindView(R.id.my_name)
    TextView mMyNameView;

    @BindView(R.id.friend_avatar)
    ImageView mFriendAvatarView;

    @BindView(R.id.my_avatar)
    ImageView mMyAvatarView;

    @BindView(R.id.input_message)
    EditText mInputView;

    @BindView(R.id.messages_list_view)
    ListView mMessagesListView;

    private ChatAdapter mChatAdapter;
    private ChatDataDVO mChatData;
    private String mURI = "ws://192.168.7.121:8080/chat/"; // Sasha
    //        private static String mURI = "ws://192.168.1.2:8080/chat/"; // Masha
    private static final WebSocketConnection mConnection = new WebSocketConnection();
    private static final String STATE_URI = "sateURI";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(Constants.SHARE_CHAT_ID)) {
                mChatData = bundle.getParcelable(Constants.SHARE_CHAT_ID);
                Log.e("Chat", "Chat id =  " + mChatData.getChatId());
                mURI += String.valueOf(mChatData.getChatId());
                LoginPreferences loginPreferences = new LoginPreferences(getContext());
                String token = loginPreferences.getAccessToken();
                mURI += Constants.PLACEHOLDER + token;
            }
        }
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(STATE_URI)) {
                mURI = savedInstanceState.getString(STATE_URI);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mChatAdapter = new ChatAdapter(getActivity(), new ArrayList<>(), mChatData.getId());
        mMessagesListView.setAdapter(mChatAdapter);
        mFriendNameView.setText(mChatData.getNameFriend());
        mMyNameView.setText(mChatData.getFullName());
        ImageLoad.loadCircleImage(mFriendAvatarView, mChatData.getFriendAvatar());
        ImageLoad.loadCircleImage(mMyAvatarView, mChatData.getAvatar());
    }

    @OnClick(R.id.send_message)
    void send() {
        String message = mInputView.getText().toString();
        if (!TextUtils.isEmpty(message.trim())) {
            mConnection.sendTextMessage(new Gson().toJson(new ChatMessageDTO(mChatData.getId(), message)));
            mInputView.setText(null);
        } else {
            mInputView.setText(null);
        }
    }

    private void update(ChatMessageDTO message) {
        mChatAdapter.add(message);
        mChatAdapter.notifyDataSetChanged();
        mMessagesListView.setSelection(mMessagesListView.getCount() - 1);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_URI, mURI);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mConnection.isConnected()) {
            start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mConnection.isConnected()) {
            mConnection.disconnect();
        }
    }

    private void start() {
        try {
            mConnection.connect(mURI, new WebSocketHandler() {

                @Override
                public void onOpen() {
                    Log.e("Socet", "Status: Connected to " + mURI);
                }

                @Override
                public void onTextMessage(final String message) {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> {
                            Log.e("My content", "Json =" + message);
                            if (message.contains("message")) {
                                ChatMessageDTO chatMessage = new Gson().fromJson(message, ChatMessageDTO.class);
                                update(chatMessage);
                            }
                        });
                    }
                    Log.e("Socet", "Got echo: " + message);
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.e("Socet", "Connection lost.");
                    Log.e("Socet", "code = " + code);
                    Log.e("Socet", "Respon =" + reason);
                }

            });
        } catch (WebSocketException e) {
            Log.e("Socet", e.toString());
            // TODO: 8/29/16 mConnection close
        }
    }
}
