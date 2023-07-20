package models;

import android.widget.TextView;

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
    public int userId;
    @NotNull
    public String username;
    @NotNull
    public String password;
    @NotNull
    public String first_name;
    @NotNull
    public String last_name;
    public String email;
    @NotNull
    public boolean isMale;
    public String address;
    @NotNull
    public String phoneNumber;
    @TypeConverters
    public Date created_at;
    @TypeConverters
    public Date modified_at;
    public boolean isLocked;
    public User(){}
    public User(String username, @NotNull String password, @NotNull String first_name, @NotNull String last_name, String email, boolean isMale, String address, @NotNull String phoneNumber, Date created_at, Date modified_at, boolean isLocked) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.isMale = isMale;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.isLocked = isLocked;
    }
}
