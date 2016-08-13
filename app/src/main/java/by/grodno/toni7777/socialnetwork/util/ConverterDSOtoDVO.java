package by.grodno.toni7777.socialnetwork.util;

import by.grodno.toni7777.socialnetwork.database.model.ProfileDSO;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;

public final class ConverterDSOtoDVO {

    public static ProfileDVO converteDTOtoDSO(ProfileDSO source) {
        return new ProfileDVO(source.getId(), source.getName(),source.getSurname(),
                source.getSex(), source.getBirthday(), source.getAvatar(),
                source.getCity(), source.getAbout(),source.getContact().getMobile(),
                source.getContact().getSkype(), source.getContact().getEmail());
    }

    private ConverterDSOtoDVO() {
    }
}
