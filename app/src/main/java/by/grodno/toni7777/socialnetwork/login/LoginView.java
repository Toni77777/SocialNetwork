package by.grodno.toni7777.socialnetwork.login;

import by.grodno.toni7777.socialnetwork.test.UserLogin;

public interface LoginView {

    void loginSuccess(UserLogin userLogin);

    void loginError(Throwable e);
}
