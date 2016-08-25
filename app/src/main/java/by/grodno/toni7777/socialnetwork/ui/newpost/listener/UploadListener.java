package by.grodno.toni7777.socialnetwork.ui.newpost.listener;

public interface UploadListener {

    void onUploadImage(String imageURL);

    void uploadError(Throwable e);
}
