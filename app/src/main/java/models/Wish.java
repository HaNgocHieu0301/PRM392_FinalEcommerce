package models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity
public class Wish {
    @PrimaryKey(autoGenerate = true)
    public int wishId;
    @NotNull
    @ColumnInfo(name = "UserId")
    public int userId;
    @NotNull
    @ColumnInfo(name = "ProductId")
    public int productId;
    @NotNull
    @ColumnInfo(name = "Quantity")
    public int quantity;
    public Wish() {

    }

    public Wish(int userId, int productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
