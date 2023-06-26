package models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

@Entity(tableName = "Order")
public class Order {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private String orderId;
    @NotNull
    private String userId;
    @NotNull
    private Date created_at;
    private Date modified_at;
    @NotNull
    private int statusId;
    private List<OrderDetail> orderDetails;
    private double total;

    @NotNull
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotNull String orderId) {
        this.orderId = orderId;
    }

    @NotNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull String userId) {
        this.userId = userId;
    }

    @NotNull
    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(@NotNull Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
