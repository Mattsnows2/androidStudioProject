package ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import Models.User;
import repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private final UserRepository mUserRepository;

    public UserViewModel(Application app){
        super(app);
        mUserRepository=UserRepository.getInstance(app);
    }

    public void insert(final User user){
        mUserRepository.insert(user);
    }

    public void update(final User user){
        mUserRepository.update(user);
    }

    public void delete (final User user){
        mUserRepository.delete(user);
    }
}
