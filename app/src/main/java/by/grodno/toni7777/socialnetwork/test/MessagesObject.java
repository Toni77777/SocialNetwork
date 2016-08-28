package by.grodno.toni7777.socialnetwork.test;

public class MessagesObject {

    private String mInterlocutorName;
    private String mInterlocutorAvatar;

    public MessagesObject(String interlocutorName, String interlocutorAvatar) {
        mInterlocutorName = interlocutorName;
        mInterlocutorAvatar = interlocutorAvatar;
    }

    public String getInterlocutorName() {
        return mInterlocutorName;
    }

    public void setInterlocutorName(String interlocutorName) {
        mInterlocutorName = interlocutorName;
    }

    public String getInterlocutorAvatar() {
        return mInterlocutorAvatar;
    }

    public void setInterlocutorAvatar(String interlocutorAvatar) {
        mInterlocutorAvatar = interlocutorAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagesObject that = (MessagesObject) o;

        if (mInterlocutorName != null ? !mInterlocutorName.equals(that.mInterlocutorName) : that.mInterlocutorName != null)
            return false;
        return mInterlocutorAvatar != null ? mInterlocutorAvatar.equals(that.mInterlocutorAvatar) : that.mInterlocutorAvatar == null;

    }

    @Override
    public int hashCode() {
        int result = mInterlocutorName != null ? mInterlocutorName.hashCode() : 0;
        result = 31 * result + (mInterlocutorAvatar != null ? mInterlocutorAvatar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessagesObject{" +
                "mInterlocutorName='" + mInterlocutorName + '\'' +
                ", mInterlocutorAvatar='" + mInterlocutorAvatar + '\'' +
                '}';
    }
}
