package by.grodno.toni7777.socialnetwork.test;

public class UserLogin {

    private String token;
    private int code;

    public UserLogin() {
    }

    public UserLogin(String token, int code) {
        this.token = token;
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLogin user = (UserLogin) o;

        if (code != user.code) return false;
        return token != null ? token.equals(user.token) : user.token == null;

    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + code;
        return result;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "token='" + token + '\'' +
                ", code=" + code +
                '}';
    }
}
