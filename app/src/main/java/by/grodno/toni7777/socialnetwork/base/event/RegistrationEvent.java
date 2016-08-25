package by.grodno.toni7777.socialnetwork.base.event;

public class RegistrationEvent {

    private String mNameUser;

    public RegistrationEvent(String nameUser) {
        mNameUser = nameUser;
    }

    public String getNameUser() {
        return mNameUser;
    }
}
