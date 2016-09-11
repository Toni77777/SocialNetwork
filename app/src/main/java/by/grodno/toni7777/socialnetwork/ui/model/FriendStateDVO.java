package by.grodno.toni7777.socialnetwork.ui.model;

import java.util.List;

import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;
import by.grodno.toni7777.socialnetwork.ui.model.ProfileDVO;

public class FriendStateDVO {

    private ProfileDVO mProfile;
    private List<PostDVO> mPosts;

    public FriendStateDVO(ProfileDVO profile, List<PostDVO> posts) {
        mProfile = profile;
        mPosts = posts;
    }

    public ProfileDVO getProfile() {
        return mProfile;
    }

    public void setProfile(ProfileDVO profile) {
        mProfile = profile;
    }

    public List<PostDVO> getPosts() {
        return mPosts;
    }

    public void setPosts(List<PostDVO> posts) {
        mPosts = posts;
    }
}