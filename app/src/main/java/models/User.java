package models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;

import Helper.Converters;

@Entity(tableName = "User")
@TypeConverters({Converters.class})
public class User {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int userId;
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    private String email;
    @NotNull
    private boolean isMale;
    private String address;
    @NotNull
    private String phoneNumber;
    @TypeConverters
    private Date created_at;

    @TypeConverters
    private Date modified_at;
    private boolean isLocked;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    @NotNull
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(@NotNull String first_name) {
        this.first_name = first_name;
    }

    @NotNull
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(@NotNull String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modifed_at) {
        this.modified_at = modifed_at;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
