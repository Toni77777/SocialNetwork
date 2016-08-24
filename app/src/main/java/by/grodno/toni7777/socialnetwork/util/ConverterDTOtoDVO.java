package by.grodno.toni7777.socialnetwork.util;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.socialnetwork.database.model.PostDSO;
import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;
import by.grodno.toni7777.socialnetwork.network.model.FriendsDTO;
import by.grodno.toni7777.socialnetwork.network.model.PersonDTO;
import by.grodno.toni7777.socialnetwork.network.model.PersonsDTO;
import by.grodno.toni7777.socialnetwork.ui.model.FriendDVO;
import by.grodno.toni7777.socialnetwork.ui.model.FriendsDVO;
import by.grodno.toni7777.socialnetwork.ui.model.OwnerDVO;
import by.grodno.toni7777.socialnetwork.ui.model.PersonDVO;
import by.grodno.toni7777.socialnetwork.ui.model.PersonsDVO;
import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import io.realm.RealmList;

public final class ConverterDTOtoDVO {

    public static FriendDVO converteDTOtoDSO(FriendDTO sourceDTO) {
        return new FriendDVO(sourceDTO.getName(), sourceDTO.getSurname(), sourceDTO.getId(),
                sourceDTO.getAvatar(), sourceDTO.isOnline());
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

    public static PersonsDVO converteDTOtoDSO(PersonsDTO sourceDTO) {
        return new PersonsDVO(converteDTOtoDSOPersons(sourceDTO.getPersons()));
    }

    private static List<PersonDVO> converteDTOtoDSOPersons(List<PersonDTO> personDTOs) {
        List<PersonDVO> persons = new ArrayList<>(personDTOs.size());
        for (PersonDTO personDTO : personDTOs) {
            persons.add(converteDTOtoDSO(personDTO));
        }
        return persons;
    }

    private static PersonDVO converteDTOtoDSO(PersonDTO sourceDTO) {
        return new PersonDVO(sourceDTO.getId(), sourceDTO.getName(), sourceDTO.getSurname(),
                sourceDTO.getAvatar());
    }

    public static List<PostDVO> converteDSOtoDVO(RealmList<PostDSO> soursePost) {
        List<PostDVO> postsDTO = new ArrayList<>();
        for (int index = 0; index < soursePost.size(); index++) {
            PostDSO postDSO = soursePost.get(index);
            PostDVO postDTO = converteDSOtoDVO(postDSO);
            postsDTO.add(postDTO);
        }
        return postsDTO;
    }

    private static PostDVO converteDSOtoDVO(PostDSO source) {
        return new PostDVO(source.getPostId(), new OwnerDVO(source.getOwner().getName(), source.getOwner().getSurname(),
                source.getOwner().getAvatar()), source.getImage(), source.getDate(), source.getText(),
                source.getLike(), source.getDislike());
    }

}
