package database;


import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import Models.User;
import dao.UserDAO;

@Database(entities = {User.class}, version = 1)
public abstract class  UserDatabase extends RoomDatabase{

    private static UserDatabase instance;
    public abstract UserDAO mUserDAO();
     public static synchronized UserDatabase getInstance(Context context){
         if(instance==null){
             instance=Room.databaseBuilder(context,
                     UserDatabase.class,"user_database")
                     .fallbackToDestructiveMigration()
                     .build();
         }

         return instance;
     }
}
