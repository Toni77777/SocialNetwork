package by.grodno.toni7777.socialnetwork.ui.model;

import java.util.List;

public class GroupStateDVO {

    private GroupInfoDVO mGroupInfo;
    private List<PostDVO> mPosts;

    public GroupStateDVO(GroupInfoDVO groupInfo, List<PostDVO> posts) {
        mGroupInfo = groupInfo;
        mPosts = posts;
    }

    public GroupInfoDVO getGroupInfo() {
        return mGroupInfo;
    }

    public void setGroupInfo(GroupInfoDVO groupInfo) {
        mGroupInfo = groupInfo;
    }

    public List<PostDVO> getPosts() {
        return mPosts;
    }

    public void setPosts(List<PostDVO> posts) {
        mPosts = posts;
    }
}
