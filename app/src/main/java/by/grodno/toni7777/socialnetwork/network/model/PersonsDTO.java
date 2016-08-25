package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonsDTO {

    @SerializedName("entity")
    private List<PersonDTO> persons;

    // TODO server need response metadata

    public List<PersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDTO> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonsDTO that = (PersonsDTO) o;

        return persons != null ? persons.equals(that.persons) : that.persons == null;

    }

    @Override
    public int hashCode() {
        return persons != null ? persons.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PersonsDTO{" +
                "persons=" + persons +
                '}';
    }
}
