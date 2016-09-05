package by.grodno.toni7777.socialnetwork.base.event;

import by.grodno.toni7777.socialnetwork.ui.model.PostDVO;

public class LikeEvent {

    private PostDVO mPost;
    private Integer mLike;

    public LikeEvent() {
    }

    public LikeEvent(PostDVO post, Integer like) {
        mPost = post;
        mLike = like;
    }

    public PostDVO getPost() {
        return mPost;
    }

    public void setPost(PostDVO post) {
        mPost = post;
    }

    public Integer getLike() {
        return mLike;
    }

    public void setLike(Integer like) {
        mLike = like;
    }
}
