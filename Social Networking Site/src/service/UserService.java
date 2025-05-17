package service;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private final Map<String, User> users = new HashMap<>();
    public User addUser(String userId, String userName){
        User user = new User(userId, userName);
        users.put(userId, user);
        return user;
    }

    public void removeUser(String userId){
        users.remove(userId);
    }

    public User getUser(String userId){
        return users.get(userId);
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(users.values());
    }
}
