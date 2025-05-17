package service;

import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FriendService {
    private final UserService userService;

    public FriendService(UserService userService) {
        this.userService = userService;
    }
    public void sendFriendRequest(String fromId, String toId){
        User fromUser = userService.getUser(fromId);
        User toUser = userService.getUser(toId);

        if(fromUser == null || toUser == null)
            return;

        fromUser.getOutGoingRequests().add(toId);
        toUser.getInComingRequests().add(fromId);
    }

    public void acceptFriendRequest(String userId, String requesterId){
        User user = userService.getUser(userId);
        User requester = userService.getUser(requesterId);

        if(user == null || requester == null)
            return;

        if(user.getInComingRequests().contains(requesterId)){
            user.getFriends().add(requesterId);
            requester.getFriends().add(userId);
            user.getInComingRequests().remove(requesterId);
            requester.getOutGoingRequests().remove(userId);
        }

    }

    public void rejectFriendRequest(String userId, String requesterId){
        User user = userService.getUser(userId);
        User requester = userService.getUser(requesterId);

        if(user == null || requester == null)
            return;

        if(user.getInComingRequests().contains(requesterId)){
            user.getInComingRequests().remove(requesterId);
            requester.getOutGoingRequests().remove(userId);
        }
    }

    public Set<String> getAllFriends(String userId){
        User user = userService.getUser(userId);
        if(user == null)
            return null;
        return user.getFriends();
    }

    public List<User> getPeopleYouMayKnow(String userId){
        User user = userService.getUser(userId);
        if(user == null)
            return null;
        Set<String> suggestions = new HashSet<>();

        for(String friendId: user.getFriends()){
            User friend = userService.getUser(friendId);
            for(String foaf: friend.getFriends()){
                if(!foaf.equals(userId) && !user.getFriends().contains(foaf)){
                    suggestions.add(foaf);
                }
            }
        }

        List<User> suggestedFriends = new ArrayList<>();
        for (String id: suggestions){
            suggestedFriends.add(userService.getUser(id));
        }

        return suggestedFriends;


    }

}
