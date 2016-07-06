package by.grodno.toni7777.socialnetwork.login;

public interface LoginPresenter {

    void loginRequest(String login, String password);

    void rxUnSubscribe();
}
