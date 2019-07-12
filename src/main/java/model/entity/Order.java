package model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * entity class represents table 'orders'
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 8078266908707449532L;
    private Integer id;
    private String startPoint;
    private String destination;
    private String date;
    private Integer distance;
    private String status;
    private Integer userId;

    public Order(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId().equals(order.getId()) &&
                getStartPoint().equals(order.getStartPoint()) &&
                getDestination().equals(order.getDestination()) &&
                Objects.equals(getDate(), order.getDate()) &&
                getDistance().equals(order.getDistance()) &&
                getStatus().equals(order.getStatus()) &&
                getUserId().equals(order.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartPoint(), getDestination(), getDate(), getDistance(), getStatus(), getUserId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", startPoint='").append(startPoint).append('\'');
        sb.append(", destination='").append(destination).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", distance='").append(distance).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", user_id=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
