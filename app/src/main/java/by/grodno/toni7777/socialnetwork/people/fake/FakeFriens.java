package by.grodno.toni7777.socialnetwork.people.fake;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;

public class FakeFriens {

    public static List<FriendDTO> createFriends(int count) {
        List<FriendDTO> friends = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            friends.add(newFriend(i));
        }

        return friends;
    }

    public static List<FriendDTO> createAfterSearch(int count) {
        List<FriendDTO> friends = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            friends.add(newFriend(i));
        }

        for (int i = 0; i < count; i++) {
            friends.add(newPerson(i));
        }
        return friends;
    }

    private static FriendDTO newFriend(int index) {
        return new FriendDTO("Name" + index, "Surname" + index, index, "http://www.imagetown.in/wp-content/uploads/2015/11/Leopard-HD-Wallpapers025.jpg", true);
    }

    private static FriendDTO newPerson(int index) {
        return new FriendDTO("Name" + index, "Surname" + index, index, "http://s.picsfab.com/static/contents/images/0/f/1/50cf1a38fb6142af04a1dec042c06.jpg", false);
    }
}
