package by.grodno.toni7777.socialnetwork.ui.newpost.listener;

public interface UploadListener {

    void onUploadImage(Long imageId);

    void uploadError(Throwable e);
}
