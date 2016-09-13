package by.grodno.toni7777.socialnetwork.ui.restore;

import com.hannesdorfmann.mosby.mvp.MvpView;

public class RestoreMVP {

    interface Model {

        void restorePassword(String email);

    }

    interface View extends MvpView {

        void showRestoreForm();

        void showError();

        void showLoading();

        void showErrors(Throwable throwable);

        void onRestoreSuccess();

    }

    interface Presenter {

        void restorePassword(String email);

    }

    private RestoreMVP() {
    }
}
