package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

import Helper.Converters;

@Entity(tableName = "Order")
@TypeConverters({Converters.class})
public class Order {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "orderId")
    public int orderId;

    @ColumnInfo(name = "userId")
    @NotNull
    public int userId;

    @ColumnInfo(name = "orderDate")
    @NotNull
    @TypeConverters
    public String orderDate;

    @ColumnInfo(name = "shippedDate")
    @TypeConverters
    public String shippedDate;

    @ColumnInfo(name = "shipAddress")
    @NotNull
    public String shipAddress;      //dia chi nhan hang

    @ColumnInfo(name = "shipPhone")
    @NotNull
    public String shipPhone;        //sdt nhan hang

    @ColumnInfo(name = "shipName")
    @NotNull
    public String shipName;         //ten nguoi nhan hang

    @ColumnInfo(name = "statusId")
    @NotNull
    public int statusId;            //0: chờ xác nhận; 1: đã xác nhận; 2: giao thành công; 3: hủy/giao thất bại

    @ColumnInfo(name = "total")
    public double total;

}
