package by.grodno.toni7777.socialnetwork.util;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.socialnetwork.database.model.FriendDSO;
import by.grodno.toni7777.socialnetwork.database.model.GroupDSO;
import by.grodno.toni7777.socialnetwork.database.model.GroupsDSO;
import by.grodno.toni7777.socialnetwork.database.model.PostDSO;
import by.grodno.toni7777.socialnetwork.database.model.ProfileDSO;
import by.grodno.toni7777.socialnetwork.ui.model.FriendDVO;
import by.grodno.toni7777.socialnetwork.ui.model.GroupDVO;
import by.grodno.toni7777.socialnetwork.ui.model.OwnerDVO;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;
import io.realm.RealmList;
import io.realm.internal.Group;

public final class ConverterDSOtoDVO {

    public static ProfileDVO converteDTOtoDSO(ProfileDSO source) {
        return new ProfileDVO(source.getId(), source.getName(), source.getSurname(),
                source.getSex(), source.getBirthday(), source.getAvatar(),
                source.getCity(), source.getAbout(), source.getContact().getMobile(),
                source.getContact().getSkype(), source.getContact().getEmail());
    }

    public static PostDVO converteDTOtoDSO(PostDSO source) {
        return new PostDVO(source.getPostId(), new OwnerDVO(source.getOwner().getName(), source.getOwner().getSurname(),
                source.getOwner().getAvatar()), source.getImage(), source.getDate(), source.getText(),
                source.getLike(), source.getDislike(),source.getIsLike());
    }

    public static List<FriendDVO> toFriendDVO(RealmList<FriendDSO> sourseFriends) {
        List<FriendDVO> friendsDVO = new ArrayList<>();
        for (int index = 0; index < sourseFriends.size(); index++) {
            FriendDSO friendDSO = sourseFriends.get(index);
            FriendDVO friendDVO = toFriendDVO(friendDSO);
            friendsDVO.add(friendDVO);
        }
        return friendsDVO;
    }

    private static FriendDVO toFriendDVO(FriendDSO source) {
        return new FriendDVO(source.getName(), source.getSurname(), source.getId(),
                source.getAvatar(), source.isOnline());
    }

    public static List<GroupDVO> toGroupDVO(RealmList<GroupDSO> sourseGroups) {
        List<GroupDVO> groupsDVO = new ArrayList<>();
        for (int index = 0; index < sourseGroups.size(); index++) {
            GroupDSO groupDSO = sourseGroups.get(index);
            GroupDVO groupDVO = toGroupDVO(groupDSO);
            groupsDVO.add(groupDVO);
        }
        return groupsDVO;
    }

    private static GroupDVO toGroupDVO(GroupDSO source) {
        return new GroupDVO(source.getGroupId(), source.getName(), source.getMembers(), source.getImage(), source.getDescription(),
                source.isMember(), source.isOwner());
    }

    private ConverterDSOtoDVO() {
    }
}
