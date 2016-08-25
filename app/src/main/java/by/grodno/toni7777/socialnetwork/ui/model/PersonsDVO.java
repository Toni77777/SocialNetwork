package by.grodno.toni7777.socialnetwork.ui.model;

import java.util.List;

public class PersonsDVO {

    private List<PersonDVO> mPersons;

    public List<PersonDVO> getPersons() {
        return mPersons;
    }

    public PersonsDVO(List<PersonDVO> persons) {
        mPersons = persons;
    }

    public void setPersons(List<PersonDVO> persons) {
        mPersons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonsDVO that = (PersonsDVO) o;

        return mPersons != null ? mPersons.equals(that.mPersons) : that.mPersons == null;

    }

    @Override
    public int hashCode() {
        return mPersons != null ? mPersons.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PersonsDVO{" +
                "mPersons=" + mPersons +
                '}';
    }
}
