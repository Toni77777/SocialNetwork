package by.grodno.toni7777.socialnetwork.test;

public class ContactInfo {

    private String phone;
    private String skype;
    private String mail;

    public ContactInfo() {
    }

    public ContactInfo(String phone, String skype, String mail) {
        this.phone = phone;
        this.skype = skype;
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactInfo that = (ContactInfo) o;

        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (skype != null ? !skype.equals(that.skype) : that.skype != null) return false;
        return mail != null ? mail.equals(that.mail) : that.mail == null;

    }

    @Override
    public int hashCode() {
        int result = phone != null ? phone.hashCode() : 0;
        result = 31 * result + (skype != null ? skype.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "phone='" + phone + '\'' +
                ", skype='" + skype + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
