package by.grodno.toni7777.socialnetwork.util;


import java.util.UUID;

import by.grodno.toni7777.socialnetwork.database.model.ContactProfileDSO;
import by.grodno.toni7777.socialnetwork.database.model.FriendDSO;
import by.grodno.toni7777.socialnetwork.database.model.FriendsDSO;
import by.grodno.toni7777.socialnetwork.database.model.OwnerDSO;
import by.grodno.toni7777.socialnetwork.database.model.PostDSO;
import by.grodno.toni7777.socialnetwork.database.model.ProfileDSO;
import by.grodno.toni7777.socialnetwork.database.model.WallDSO;
import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;
import by.grodno.toni7777.socialnetwork.network.model.FriendsDTO;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.network.model.ProfileDTO;
import by.grodno.toni7777.socialnetwork.network.model.UserDTO;
import by.grodno.toni7777.socialnetwork.network.model.WallDTO;
import io.realm.RealmList;

public final class ConverterDTOtoDSO {

    public static ProfileDSO converteDTOtoDSO(ProfileDTO sourceDTO) {
        UserDTO user = sourceDTO.getUser();
        return new ProfileDSO(user.getId(), user.getName(), user.getSurname(),
                user.getSex(), user.getBirthday(), user.getAvatar(), user.getCity(), user.getAbout(),
                new ContactProfileDSO(user.getContact().getMobile(), user.getContact().getSkype(),
                        user.getContact().getEmail()));
    }

    public static WallDSO converteDTOtoDSO(WallDTO sourceDTO) {
        RealmList<PostDSO> postDSO = new RealmList<>();
        for (PostDTO sourse : sourceDTO.getPosts()) {
            PostDSO post = converteDTOtoDSO(sourse);
            postDSO.add(post);
        }
        return new WallDSO(postDSO);
    }

    public static PostDSO converteDTOtoDSO(PostDTO sourceDTO) {
        return new PostDSO(UUID.randomUUID().toString(), sourceDTO.getPostId(), new OwnerDSO(sourceDTO.getOwner().getName(),
                sourceDTO.getOwner().getSurname(), sourceDTO.getOwner().getAvatar()),
                sourceDTO.getImage(), sourceDTO.getDate(), sourceDTO.getText(), sourceDTO.getLike(),
                sourceDTO.getDislike());
    }

    public static FriendsDSO toFriendDSO(FriendsDTO sourceDTO) {
        RealmList<FriendDSO> friendsDSO = new RealmList<>();
        for (FriendDTO sourse : sourceDTO.getFriends()) {
            FriendDSO friend = toFriendDSO(sourse);
            friendsDSO.add(friend);
        }
        return new FriendsDSO(friendsDSO);
    }

    public static FriendDSO toFriendDSO(FriendDTO sourceDTO) {
        return new FriendDSO(UUID.randomUUID().toString(), sourceDTO.getName(), sourceDTO.getSurname(),
                sourceDTO.getId(), sourceDTO.getAvatar(), sourceDTO.isOnline());
    }

    private ConverterDTOtoDSO() {
    }
}
