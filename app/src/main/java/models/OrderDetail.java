package models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "OrderDetail")
public class OrderDetail {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private String orderDetailId;
    @NotNull
    private String orderId;
    @NotNull
    private String productId;
    @NotNull
    private int quantity;

    @NotNull
    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(@NotNull String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    @NotNull
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotNull String orderId) {
        this.orderId = orderId;
    }

    @NotNull
    public String getProductId() {
        return productId;
    }

    public void setProductId(@NotNull String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
