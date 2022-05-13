package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import Models.User;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);

    @Update
    void update (User user);

    @Delete
    void delete(User user);




}
