package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "OrderDetail")
public class OrderDetail {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "orderDetailId")
    public int orderDetailId;

    @NotNull
    @ColumnInfo(name = "orderId")
    public int orderId;

    @NotNull
    @ColumnInfo(name = "productId")
    public int productId;

    @NotNull
    @ColumnInfo(name = "unitPrice")
    public double unitPrice;

    @NotNull
    @ColumnInfo(name = "quantity")
    public int quantity;

    @NotNull
    @ColumnInfo(name = "discount")
    public double discount;

}
