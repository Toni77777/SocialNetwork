package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PeopleDTO {

    @SerializedName("friends")
    private List<FriendDTO> mFriends;

    @SerializedName("friends_number")
    private long mFriendNumber;

    @SerializedName("persons")
    private List<FriendDTO> mPersons;

    @SerializedName("persons_number")
    private long mPersonsNumber;

    public PeopleDTO() {
    }

    public PeopleDTO(List<FriendDTO> friends, long friendNumber, List<FriendDTO> persons, long personsNumber) {
        mFriends = friends;
        mFriendNumber = friendNumber;
        mPersons = persons;
        mPersonsNumber = personsNumber;
    }

    public List<FriendDTO> getFriends() {
        return mFriends;
    }

    public void setFriends(List<FriendDTO> friends) {
        mFriends = friends;
    }

    public long getFriendNumber() {
        return mFriendNumber;
    }

    public void setFriendNumber(long friendNumber) {
        mFriendNumber = friendNumber;
    }

    public List<FriendDTO> getPersons() {
        return mPersons;
    }

    public void setPersons(List<FriendDTO> persons) {
        mPersons = persons;
    }

    public long getPersonsNumber() {
        return mPersonsNumber;
    }

    public void setPersonsNumber(long personsNumber) {
        mPersonsNumber = personsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeopleDTO peopleDTO = (PeopleDTO) o;

        if (mFriendNumber != peopleDTO.mFriendNumber) return false;
        if (mPersonsNumber != peopleDTO.mPersonsNumber) return false;
        if (mFriends != null ? !mFriends.equals(peopleDTO.mFriends) : peopleDTO.mFriends != null)
            return false;
        return mPersons != null ? mPersons.equals(peopleDTO.mPersons) : peopleDTO.mPersons == null;

    }

    @Override
    public int hashCode() {
        int result = mFriends != null ? mFriends.hashCode() : 0;
        result = 31 * result + (int) (mFriendNumber ^ (mFriendNumber >>> 32));
        result = 31 * result + (mPersons != null ? mPersons.hashCode() : 0);
        result = 31 * result + (int) (mPersonsNumber ^ (mPersonsNumber >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PeopleDTO{" +
                "mFriends=" + mFriends +
                ", mFriendNumber=" + mFriendNumber +
                ", mPersons=" + mPersons +
                ", mPersonsNumber=" + mPersonsNumber +
                '}';
    }
}
