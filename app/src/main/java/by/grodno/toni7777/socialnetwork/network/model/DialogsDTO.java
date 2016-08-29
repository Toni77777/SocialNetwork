package by.grodno.toni7777.socialnetwork.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DialogsDTO {

    @SerializedName("entity")
    private List<DialogDTO> mDialogs;

    @SerializedName("metadata")
    private MetadataDTO mMetadata;

    public DialogsDTO(List<DialogDTO> dialogs, MetadataDTO metadata) {
        mDialogs = dialogs;
        mMetadata = metadata;
    }

    public List<DialogDTO> getDialogs() {
        return mDialogs;
    }

    public void setDialogs(List<DialogDTO> dialogs) {
        mDialogs = dialogs;
    }

    public MetadataDTO getMetadata() {
        return mMetadata;
    }

    public void setMetadata(MetadataDTO metadata) {
        mMetadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DialogsDTO that = (DialogsDTO) o;

        if (mDialogs != null ? !mDialogs.equals(that.mDialogs) : that.mDialogs != null)
            return false;
        return mMetadata != null ? mMetadata.equals(that.mMetadata) : that.mMetadata == null;

    }

    @Override
    public int hashCode() {
        int result = mDialogs != null ? mDialogs.hashCode() : 0;
        result = 31 * result + (mMetadata != null ? mMetadata.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DialogsDTO{" +
                "mDialogs=" + mDialogs +
                ", mMetadata=" + mMetadata +
                '}';
    }
}
