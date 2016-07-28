package by.grodno.toni7777.socialnetwork.network.model;

public class PostDTO {

    private OwnerDTO owner;
    private String image;
    private String text;
    private int like;
    private int dislike;

    public PostDTO() {
    }

    public PostDTO(OwnerDTO owner, String image, String text, int like, int dislike) {
        this.owner = owner;
        this.image = image;
        this.text = text;
        this.like = like;
        this.dislike = dislike;
    }

    public OwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostDTO postDTO = (PostDTO) o;

        if (like != postDTO.like) return false;
        if (dislike != postDTO.dislike) return false;
        if (owner != null ? !owner.equals(postDTO.owner) : postDTO.owner != null) return false;
        if (image != null ? !image.equals(postDTO.image) : postDTO.image != null) return false;
        return text != null ? text.equals(postDTO.text) : postDTO.text == null;

    }

    @Override
    public int hashCode() {
        int result = owner != null ? owner.hashCode() : 0;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + like;
        result = 31 * result + dislike;
        return result;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "owner=" + owner +
                ", image='" + image + '\'' +
                ", text='" + text + '\'' +
                ", like=" + like +
                ", dislike=" + dislike +
                '}';
    }
}
