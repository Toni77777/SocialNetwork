package by.grodno.toni7777.socialnetwork.test;

import java.util.Date;

import by.grodno.toni7777.socialnetwork.test.Like;

public class Post {

    private long userID;
    private long fromUserID;
    private Date time;
    private String text;
    private String photoUrl;
    private String audioUrl;
    private String videoUrl;
    private Like like;

    public Post() {
    }

    public Post(long userID, long fromUserID, Date time, String text, String photoUrl, String audioUrl, String videoUrl, Like like) {
        this.userID = userID;
        this.fromUserID = fromUserID;
        this.time = time;
        this.text = text;
        this.photoUrl = photoUrl;
        this.audioUrl = audioUrl;
        this.videoUrl = videoUrl;
        this.like = like;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(long fromUserID) {
        this.fromUserID = fromUserID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (userID != post.userID) return false;
        if (fromUserID != post.fromUserID) return false;
        if (time != null ? !time.equals(post.time) : post.time != null) return false;
        if (text != null ? !text.equals(post.text) : post.text != null) return false;
        if (photoUrl != null ? !photoUrl.equals(post.photoUrl) : post.photoUrl != null)
            return false;
        if (audioUrl != null ? !audioUrl.equals(post.audioUrl) : post.audioUrl != null)
            return false;
        if (videoUrl != null ? !videoUrl.equals(post.videoUrl) : post.videoUrl != null)
            return false;
        return like != null ? like.equals(post.like) : post.like == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (userID ^ (userID >>> 32));
        result = 31 * result + (int) (fromUserID ^ (fromUserID >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (photoUrl != null ? photoUrl.hashCode() : 0);
        result = 31 * result + (audioUrl != null ? audioUrl.hashCode() : 0);
        result = 31 * result + (videoUrl != null ? videoUrl.hashCode() : 0);
        result = 31 * result + (like != null ? like.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userID=" + userID +
                ", fromUserID=" + fromUserID +
                ", time=" + time +
                ", text='" + text + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", like=" + like +
                '}';
    }
}
