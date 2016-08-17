package by.grodno.toni7777.socialnetwork.util;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;
import by.grodno.toni7777.socialnetwork.network.model.FriendsDTO;
import by.grodno.toni7777.socialnetwork.ui.model.FriendDVO;
import by.grodno.toni7777.socialnetwork.ui.model.FriendsDVO;

public final class ConverterDTOtoDVO {

    public static FriendDVO converteDTOtoDSO(FriendDTO sourceDTO) {
        return new FriendDVO(sourceDTO.getName(), sourceDTO.getSurname(), sourceDTO.getId(),
                sourceDTO.getAvatar());
    }

    public static FriendsDVO converteDTOtoDSO(FriendsDTO sourceDTO) {
        return new FriendsDVO(converteDTOtoDSO(sourceDTO.getFriends()), sourceDTO.getMetadata().getCount());
    }

    private static List<FriendDVO> converteDTOtoDSO(List<FriendDTO> sourceDTO) {
        List<FriendDVO> friends = new ArrayList<>(sourceDTO.size());
        for (FriendDTO friendDTO : sourceDTO) {
            friends.add(converteDTOtoDSO(friendDTO));
        }
        return friends;
    }
}
