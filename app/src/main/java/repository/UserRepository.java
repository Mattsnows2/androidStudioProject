package repository;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Models.User;
import dao.UserDAO;
import database.UserDatabase;
import io.bloco.faker.components.App;


public class UserRepository {

    private static UserRepository instance;
    private final UserDAO userDAO;
    private final ExecutorService executorService;

    private UserRepository(Application application){
        UserDatabase database = UserDatabase.getInstance(application);
        userDAO = database.mUserDAO();
        executorService = Executors.newFixedThreadPool(2);
    }
    public static synchronized UserRepository getInstance(Application application) {
      if(instance==null)
          instance=new UserRepository(application);

      return instance;
      }






    public void insert (User user){

        executorService.execute(()-> userDAO.   insert(user));
    }

    public void update (User user){
        executorService.execute(()->userDAO.update(user));

    }

    public void delete (User user){
        executorService.execute(()->userDAO.delete(user));
    }
}
