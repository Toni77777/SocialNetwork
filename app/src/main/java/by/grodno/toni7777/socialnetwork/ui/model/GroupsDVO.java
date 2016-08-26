package by.grodno.toni7777.socialnetwork.ui.model;

import java.util.List;


public class GroupsDVO {

    private List<GroupsDVO> mGroupsDVO;

    public GroupsDVO() {
    }

    public GroupsDVO(List<GroupsDVO> groupsDVO) {
        mGroupsDVO = groupsDVO;
    }

    public List<GroupsDVO> getGroupsDVO() {
        return mGroupsDVO;
    }

    public void setGroupsDVO(List<GroupsDVO> groupsDVO) {
        mGroupsDVO = groupsDVO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsDVO groupsDVO = (GroupsDVO) o;

        return mGroupsDVO != null ? mGroupsDVO.equals(groupsDVO.mGroupsDVO) : groupsDVO.mGroupsDVO == null;

    }

    @Override
    public int hashCode() {
        return mGroupsDVO != null ? mGroupsDVO.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GroupsDVO{" +
                "mGroupsDVO=" + mGroupsDVO +
                '}';
    }
}
