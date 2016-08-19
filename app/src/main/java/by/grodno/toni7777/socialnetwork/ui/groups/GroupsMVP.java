package by.grodno.toni7777.socialnetwork.ui.groups;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import by.grodno.toni7777.socialnetwork.base.LoadPagination;
import by.grodno.toni7777.socialnetwork.base.event.GroupEvent;
import by.grodno.toni7777.socialnetwork.network.model.GroupDTO;
import by.grodno.toni7777.socialnetwork.network.model.GroupsDTO;

public interface GroupsMVP {

    interface Model {

        void loadGroups(int offset);
    }

    interface View extends MvpLceView<List<GroupDTO>> {

         void openGroup(GroupEvent event);

    }

    interface Presenter extends LoadPagination {

    }
}
