package by.grodno.toni7777.socialnetwork.ui.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.BaseFragment;
import by.grodno.toni7777.socialnetwork.ui.chat.adapter.ChatAdapter;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class ChatFragment extends BaseFragment {

    @BindView(R.id.input_message)
    EditText mInput;

    @BindView(R.id.messages_list_view)
    ListView mMessagesListView;

    private ChatAdapter mChatAdapter;

    //    private String mURI = "ws://192.168.7.121:8080/chat/"; // Sasha
    private static String mURI = "ws://192.168.7.116:8080/chat/"; // Masha
    private static final WebSocketConnection mConnection = new WebSocketConnection();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
//        String number = intent.getStringExtra("room").trim();
//        mURI += number;
        mURI += "24";
        start();
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
    }

    @OnClick(R.id.send_message)
    void send() {
        String s = mInput.getText().toString();
        Gson gson = new Gson();
        ChatMessage chatMessage = new ChatMessage(2, s);
        String json = gson.toJson(chatMessage);
        mConnection.sendTextMessage(json);
        mInput.setText(null);
    }

    private void update(ChatMessage message) {
        mChatAdapter.add(message);
        mChatAdapter.notifyDataSetChanged();
        mMessagesListView.setSelection(mMessagesListView.getCount() - 1);
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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            Log.e("My content", "Json =" + payload);
                            if (payload.contains("message")) {
                                ChatMessage chatMessage = gson.fromJson(payload, ChatMessage.class);
                                update(chatMessage);
                            }
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
