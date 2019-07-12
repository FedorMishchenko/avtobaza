package model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * entity class represents table 'user_info'
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -2417171095891851861L;
    private Integer id;
    private String truck;
    private String status;
    private String capacity;
    private Integer userID;

    public UserInfo(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;
        UserInfo info = (UserInfo) o;
        return Objects.equals(getId(), info.getId()) &&
                getTruck().equals(info.getTruck()) &&
                getStatus().equals(info.getStatus()) &&
                getCapacity().equals(info.getCapacity()) &&
                getUserID().equals(info.getUserID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTruck(), getStatus(), getCapacity(), getUserID());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserInfo{");
        sb.append("id=").append(id);
        sb.append(", truck='").append(truck).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", capacity='").append(capacity).append('\'');
        sb.append(", userID=").append(userID);
        sb.append('}');
        return sb.toString();
    }
}
