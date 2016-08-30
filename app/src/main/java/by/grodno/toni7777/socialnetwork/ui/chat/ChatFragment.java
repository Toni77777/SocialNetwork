package by.grodno.toni7777.socialnetwork.ui.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import by.grodno.toni7777.socialnetwork.ui.dialogs.ShareDate;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class ChatFragment extends BaseFragment {

    @BindView(R.id.friend_name)
    TextView mFriendName;

    @BindView(R.id.my_name)
    TextView mMyName;

    @BindView(R.id.input_message)
    EditText mInput;

    @BindView(R.id.messages_list_view)
    ListView mMessagesListView;

    private ChatAdapter mChatAdapter;
    private ShareDate mShareDate;
    //    private String mURI = "ws://192.168.7.121:8080/chat/"; // Sasha
    private static String mURI = "ws://192.168.7.116:8080/chat/"; // Masha
    private static final WebSocketConnection mConnection = new WebSocketConnection();
    private static final String STATE_URI = "sateURI";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(Constants.SHARE_CHAT_ID)) {
//                long id = bundle.getLong(Constants.SHARE_CHAT_ID);
                ShareDate shareDate = bundle.getParcelable(Constants.SHARE_CHAT_ID);
                mShareDate = shareDate;
                Log.e("Chat", "Chat id =  " + shareDate.getChatId());

                mURI += String.valueOf(shareDate.getChatId());
                LoginPreferences loginPreferences = new LoginPreferences(getContext());
                String token = loginPreferences.getAccessToken();
                mURI += "/" + token;
            }
        }
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(STATE_URI)) {
                mURI = savedInstanceState.getString(STATE_URI);
            }
        }

        if (!mConnection.isConnected()) {
            start();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mChatAdapter = new ChatAdapter(getActivity(), new ArrayList<>());
        mMessagesListView.setAdapter(mChatAdapter);
        mFriendName.setText(mShareDate.getNameFriend());
        mMyName.setText(mShareDate.getFullname());
    }

    @OnClick(R.id.send_message)
    void send() {
        String message = mInput.getText().toString();
        if (!TextUtils.isEmpty(message.trim())) {
            mConnection.sendTextMessage(new Gson().toJson(new ChatMessageDTO(1, message)));
            mInput.setText(null);
        } else {
            mInput.setText(null);
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

//    @Override
//    public void onStart() {
//        super.onStart();
//        start();
//    }

    @Override
    public void onStop() {
        super.onStop();
//        if (mConnection.isConnected()) {
//            mConnection.disconnect();
//        }
    }

    private void start() {

        try {
            mConnection.connect(mURI, new WebSocketHandler() {

                @Override
                public void onOpen() {
                    Log.e("Socet", "Status: Connected to " + mURI);
                }

                @Override
                public void onTextMessage(final String payload) {
                    getActivity().runOnUiThread(() -> {
                        Log.e("My content", "Json =" + payload);
                        if (payload.contains("message")) {
                            ChatMessageDTO chatMessage = new Gson().fromJson(payload, ChatMessageDTO.class);
                            update(chatMessage);
                        }
                    });
                    Log.e("Socet", "Got echo: " + payload);
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
