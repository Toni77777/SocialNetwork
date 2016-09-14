package by.grodno.toni7777.socialnetwork.ui.chat;

import android.util.Log;

import java.util.List;

import by.grodno.toni7777.socialnetwork.mvp.BaseModel;
import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.HistoryMessagesDTO;
import by.grodno.toni7777.socialnetwork.ui.chat.listener.ChatListener;
import by.grodno.toni7777.socialnetwork.util.Constants;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;
import by.grodno.toni7777.socialnetwork.util.RxUtil;
import rx.Observable;
import rx.Subscription;

public class ChatModel implements BaseModel, ChatMVP.Model {

    private ChatListener mListener;
    private Subscription mSubscription;
    private LoginPreferences mPreferences;
    private SocialNetworkAPI mNetworkAPI;

    public ChatModel(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences,
                     ChatListener listener) {
        mNetworkAPI = socialNetworkAPI;
        mPreferences = loginPreferences;
        mListener = listener;
    }

    @Override
    public void getLastMessages(long chatId, int offset) {
        Observable<HistoryMessagesDTO> messagesObservable = mNetworkAPI.getLastMessages(chatId, offset, Constants.HIGHT_LIMIT, mPreferences.getAccessToken());
        unsubscribe();
        mSubscription = messagesObservable
                .compose(RxUtil.<HistoryMessagesDTO>applySchedulers())
                .subscribe(
                        chat -> {
                            Log.e("Chat", "Chat message  " + chat.getChatDTO().getChatMessages());
                            mListener.onChatLoadCompleted(chat.getChatDTO().getChatMessages());
                        },
                        throwable -> {
                            unsubscribe();
                            mListener.loadChatIdError(throwable);
                            Log.e("Chat", "Chat error " + throwable);
                        },
                        () -> {
                            unsubscribe();
                        }
                );
    }

    @Override
    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
