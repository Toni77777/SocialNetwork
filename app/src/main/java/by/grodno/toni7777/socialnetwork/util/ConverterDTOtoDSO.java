package by.grodno.toni7777.socialnetwork.util;

import by.grodno.toni7777.socialnetwork.database.model.ContactProfileDSO;
import by.grodno.toni7777.socialnetwork.database.model.OwnerDSO;
import by.grodno.toni7777.socialnetwork.database.model.PostDSO;
import by.grodno.toni7777.socialnetwork.database.model.ProfileDSO;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import by.grodno.toni7777.socialnetwork.network.model.ProfileDTO;
import by.grodno.toni7777.socialnetwork.network.model.UserDTO;

public final class ConverterDTOtoDSO {

    public static ProfileDSO converteDTOtoDSO(ProfileDTO sourceDTO) {
        UserDTO user = sourceDTO.getUser();
        return new ProfileDSO(user.getId(), user.getName(), user.getSurname(),
                user.getSex(), user.getBirthday(), user.getAvatar(), user.getCity(), user.getAbout(),
                new ContactProfileDSO(user.getContact().getMobile(), user.getContact().getSkype(),
                        user.getContact().getEmail()));
    }

    public static PostDSO converteDTOtoDSO(PostDTO sourceDTO) {
        return new PostDSO(sourceDTO.getPostId(), new OwnerDSO(sourceDTO.getOwner().getName(),
                sourceDTO.getOwner().getSurname(), sourceDTO.getOwner().getAvatar()),
                sourceDTO.getImage(), sourceDTO.getDate(), sourceDTO.getText(), sourceDTO.getLike(),
                sourceDTO.getDislike());
    }

    private ConverterDTOtoDSO() {
    }
}
