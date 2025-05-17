package service;

import model.Comment;
import model.Post;
import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PostService {
    private final Map<String, Post> allPosts = new HashMap<>();
    private final UserService userService;

    public PostService(UserService userService) {
        this.userService = userService;
    }

    public Post addPost(String userId, String content){
        User user = userService.getUser(userId);
        if(user == null)
            return null;

        String postId = UUID.randomUUID().toString();
        Post post = new Post(postId, userId, content);
        user.getPosts().add(post);
        allPosts.put(postId, post);
        return post;
    }

    public void likePost(String userId, String postId){
        Post post= allPosts.get(postId);
        if (post != null){
            post.getLikes().add(userId);
        }
    }

    public void addComment(String userId, String postId, String commentText){
        Post post= allPosts.get(postId);
        if(post != null){
            Comment comment = new Comment(userId, commentText);
            post.getComments().add(comment);
        }
    }

    public Post getPost(String postId){
        return allPosts.get(postId);
    }
}
