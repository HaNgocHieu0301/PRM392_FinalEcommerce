package models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Status")
public class Status {
    @PrimaryKey
    @NotNull
    private int statusId;
    @NotNull
    private String statusName;

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @NotNull
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(@NotNull String statusName) {
        this.statusName = statusName;
    }
}
