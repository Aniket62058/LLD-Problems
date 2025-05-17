package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private String userId;
    private String name;
    private List<Post> posts = new ArrayList<>();
    private Set<String> friends = new HashSet<>();
    private Set<String> inComingRequests = new HashSet<>();
    private Set<String> outGoingRequests = new HashSet<>();

    public User(String userId, String name){
        this.userId=userId;
        this.name=name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    public Set<String> getInComingRequests() {
        return inComingRequests;
    }

    public void setInComingRequests(Set<String> inComingRequests) {
        this.inComingRequests = inComingRequests;
    }

    public Set<String> getOutGoingRequests() {
        return outGoingRequests;
    }

    public void setOutGoingRequests(Set<String> outGoingRequests) {
        this.outGoingRequests = outGoingRequests;
    }
}
