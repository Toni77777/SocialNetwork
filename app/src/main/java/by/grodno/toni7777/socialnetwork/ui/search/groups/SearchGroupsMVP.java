package by.grodno.toni7777.socialnetwork.ui.search.groups;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.network.model.GroupDTO;

public interface SearchGroupsMVP {

    interface Model {

        void findGroups(String nameGroup, int offset);
    }

    interface View extends MvpLceView<List<GroupDTO>> {

    }

    interface Presenter {

        void loadDataWithOffset(String nameGroup, boolean forceRefresh, int offset);

    }
}
