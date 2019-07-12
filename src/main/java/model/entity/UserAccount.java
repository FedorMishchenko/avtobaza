package model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * entity class represents table 'users'
 */
public class UserAccount implements Serializable {
    private static final long serialVersionUID = 1412742662955003440L;
    private Integer id;
    private String userName;
    private String password;
    private String role;
    private String email;
    private String phone;

    public UserAccount() {}

    public UserAccount(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;
        UserAccount account = (UserAccount) o;
        return Objects.equals(getId(), account.getId()) &&
                getUserName().equals(account.getUserName()) &&
                getPassword().equals(account.getPassword()) &&
                getRole().equals(account.getRole()) &&
                getEmail().equals(account.getEmail()) &&
                getPhone().equals(account.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getPassword(), getRole(), getEmail(), getPhone());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserAccount{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

