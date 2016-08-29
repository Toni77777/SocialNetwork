package by.grodno.toni7777.socialnetwork.ui.dialogs;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.network.model.DialogDTO;

public final class DialogsMVP {

    interface Model {

        void loadDialogs(int offset);

    }

    interface View extends MvpLceView<List<DialogDTO>> {

    }

    interface Presenter extends LoadPagination {

    }

    private DialogsMVP() {
    }
}
