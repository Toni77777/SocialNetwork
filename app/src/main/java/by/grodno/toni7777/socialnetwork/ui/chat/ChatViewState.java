package by.grodno.toni7777.socialnetwork.ui.chat;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

public class ChatViewState implements ViewState<ChatMVP.View> {

    private static final int STATE_SHOW_CHAT_FORM = 0;
    private static final int STATE_SHOW_LOADING = 1;
    private static final int STATE_SHOW_ERROR = 2;

    int state = STATE_SHOW_CHAT_FORM;

    public void setShowLoginForm() {
        state = STATE_SHOW_CHAT_FORM;
    }

    public void setShowError() {
        state = STATE_SHOW_ERROR;
    }

    public void setShowLoading() {
        state = STATE_SHOW_LOADING;
    }

    @Override
    public void apply(ChatMVP.View view, boolean retained) {
        switch (state) {
            case STATE_SHOW_LOADING:
                view.showLoading();
                break;

            case STATE_SHOW_ERROR:
                view.showError();
                break;

            case STATE_SHOW_CHAT_FORM:
                view.showChatForm();
                break;
        }
    }
}
