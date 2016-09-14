package by.grodno.toni7777.socialnetwork.ui.chat;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import by.grodno.toni7777.socialnetwork.network.SocialNetworkAPI;
import by.grodno.toni7777.socialnetwork.network.model.ChatMessageDTO;
import by.grodno.toni7777.socialnetwork.ui.chat.listener.ChatListener;
import by.grodno.toni7777.socialnetwork.util.LoginPreferences;

public class ChatPresenter extends MvpBasePresenter<ChatMVP.View>
        implements ChatListener, MvpPresenter<ChatMVP.View>, ChatMVP.Presenter {

    private final ChatModel mModel;

    @Inject
    public ChatPresenter(SocialNetworkAPI socialNetworkAPI, LoginPreferences loginPreferences) {
        mModel = new ChatModel(socialNetworkAPI, loginPreferences, this);
    }

    @Override
    public void getLastMessages(long chatId, int offset) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        mModel.getLastMessages(chatId, offset);
    }

    @Override
    public void onChatLoadCompleted(List<ChatMessageDTO> message) {
        if (isViewAttached()) {
            getView().getMessages(message);
        }
    }

    @Override
    public void loadChatIdError(Throwable throwable) {
        if (isViewAttached()) {
            getView().showErrors(throwable);
        }
    }
}
