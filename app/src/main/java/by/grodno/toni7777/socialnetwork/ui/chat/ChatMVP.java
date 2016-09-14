package by.grodno.toni7777.socialnetwork.ui.chat;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.network.model.ChatMessageDTO;

public class ChatMVP {

    interface Model {

        void getLastMessages(long chatId, int offset);

    }

    interface View extends MvpView {

        void showChatForm();

        void showError();

        void showLoading();

        void getMessages(List<ChatMessageDTO> data);

        void showErrors(Throwable throwable);

    }

    interface Presenter {

        void getLastMessages(long chatId, int offset);

    }

    private ChatMVP() {
    }
}
