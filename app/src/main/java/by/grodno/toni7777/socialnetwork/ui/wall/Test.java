package by.grodno.toni7777.socialnetwork.ui.wall;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.socialnetwork.database.model.PostDSO;
import by.grodno.toni7777.socialnetwork.network.model.OwnerDTO;
import by.grodno.toni7777.socialnetwork.network.model.PostDTO;
import io.realm.RealmList;

public class Test {

    public static List<PostDTO> converter(RealmList<PostDSO> soursePost) {
        List<PostDTO> postsDTO = new ArrayList<>();
        for (int index = 0; index < soursePost.size(); index++) {
            PostDSO postDSO = soursePost.get(index);
            PostDTO postDTO = converteDTOtoDSO(postDSO);
            postsDTO.add(postDTO);
        }
        return postsDTO;
    }


    private static PostDTO converteDTOtoDSO(PostDSO source) {
        return new PostDTO(source.getPostId(), new OwnerDTO(source.getOwner().getName(), source.getOwner().getSurname(),
                source.getOwner().getAvatar()), source.getImage(), source.getDate(), source.getText(),
                source.getLike(), source.getDislike());
    }
}
