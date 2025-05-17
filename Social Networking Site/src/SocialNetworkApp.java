import model.Post;
import model.User;
import service.FriendService;
import service.PostService;
import service.UserService;

public class SocialNetworkApp {
    public static void main(String[] args) {
        UserService userService = new UserService();
        FriendService friendService = new FriendService(userService);
        PostService postService = new PostService(userService);

        // Users
        User user1 = userService.addUser("1", "user1");
        User user2 = userService.addUser("2", "user2");
        User user3 = userService.addUser("3", "user3");
        User user4 = userService.addUser("4", "user4");

        // Friend requests
        friendService.sendFriendRequest("1", "2");
        friendService.acceptFriendRequest("2", "1");

        friendService.sendFriendRequest("2", "3");
        friendService.acceptFriendRequest("3", "2");

        friendService.sendFriendRequest("3", "4");
        friendService.acceptFriendRequest("4", "3");

        // Posts
        Post post1 = postService.addPost("2", "Hello, world!");
        postService.likePost("1", post1.getId());
        postService.addComment("1", post1.getId(), "Nice post!");

        // Recommendations
        System.out.println("People you may know for user1:");
        for (User u : friendService.getPeopleYouMayKnow("1")) {
            System.out.println("- " + u.getName());
        }
    }
}