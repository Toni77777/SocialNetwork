package by.grodno.toni7777.socialnetwork.login;

import by.grodno.toni7777.socialnetwork.network.model.UserLoginDTO;

public interface LoginView {

    void loginSuccess(UserLoginDTO userLogin);

    void loginError(Throwable e);
}
