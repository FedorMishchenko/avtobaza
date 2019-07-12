package model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * entity class represents table 'request'
 */
public class Request implements Serializable {
    private static final long serialVersionUID = -4403032144137640913L;

    private Integer id;
    private Integer userId;
    private Integer orderId;

    public Request(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return Objects.equals(getId(), request.getId()) &&
                getUserId().equals(request.getUserId()) &&
                getOrderId().equals(request.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getOrderId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Request{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", orderId=").append(orderId);
        sb.append('}');
        return sb.toString();
    }
}
