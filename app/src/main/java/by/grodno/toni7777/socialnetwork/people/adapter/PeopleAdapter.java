package by.grodno.toni7777.socialnetwork.people.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.event.NewFriend;
import by.grodno.toni7777.socialnetwork.base.event.PersonEvent;
import by.grodno.toni7777.socialnetwork.network.model.FriendDTO;
import by.grodno.toni7777.socialnetwork.network.model.PeopleDTO;
import by.grodno.toni7777.socialnetwork.network.model.PersonDTO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private static final int FRIEND = R.layout.item_friends;
    private static final int PERSON = R.layout.item_person;

    private List<FriendDTO> mFriends;
    private List<PersonDTO> mPersons;

    public PeopleAdapter(List<FriendDTO> friends, List<PersonDTO> persons) {
        mFriends = friends;
        mPersons = persons;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FRIEND) {
            return FriendViewHolder.newInstance(parent);
        } else if (viewType == PERSON) {
            return PersonViewHolder.newInstance(parent);
        } else {
            throw new IllegalArgumentException("Unknown view type " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        int viewType = holder.getItemViewType();

        Object people = null;
        if (mFriends != null) {
            if (mFriends.size() > position) {
                people = mFriends.get(position);
            } else if (mPersons != null) {
                people = mFriends.get(position - mFriends.size());
            }
        }

        if (viewType == FRIEND) {
            ((FriendViewHolder) holder).bind((FriendDTO) people);
        } else if (viewType == PERSON) {
            ((PersonViewHolder) holder).bind((PersonDTO) people);
        } else {
            throw new IllegalArgumentException("View type not found");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mFriends != null) {
            if (mFriends.size() > position) {
                return FRIEND;
            } else if (mPersons != null) {
                return PERSON;
            }
        } else {
            return PERSON;
        }
        throw new IllegalArgumentException("View type not found");
    }


    @Override
    public int getItemCount() {
        if ((mFriends != null) & (mPersons != null)) {
            return mFriends.size() + mPersons.size();
        } else if ((mPersons == null) & (mFriends != null)) {
            return mFriends.size();
        } else if (((mFriends == null) & (mPersons != null))) {
            return mPersons.size();
        } else {
            throw new IllegalArgumentException("Have not data");
        }
    }

    public void clear() {
        mPersons.clear();
        mFriends.clear();
    }

    public void update(PeopleDTO people) {
        if ((people.getFriends() != null) & (mFriends != null)) {
            mFriends.addAll(people.getFriends());
        } else if (mFriends == null) {
            mFriends = people.getFriends();
        }
        if ((people.getPersons() != null) & (mPersons != null)) {
            mPersons.addAll(people.getFriends());
        } else if (mPersons == null) {
            mPersons = people.getPersons();
        }
        notifyDataSetChanged();
    }

    public PeopleDTO getPeople() {
        PeopleDTO people = new PeopleDTO();
        if (mFriends != null) {
            people.setFriends(mFriends);
        }
        if (mPersons != null) {
            people.setPersons(mPersons);
        }
        return people;
    }

    static class PeopleViewHolder extends RecyclerView.ViewHolder {
        public PeopleViewHolder(View v) {
            super(v);
        }
    }

    static class FriendViewHolder extends PeopleViewHolder {

        @BindView(R.id.friend_avatar)
        ImageView mAvatar;

        @BindView(R.id.friend_name)
        TextView mName;

        @BindView(R.id.friend_online)
        CheckBox mOnline;

        public FriendViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(FriendDTO friend) {
            ImageLoad.loadCircleImage(mAvatar, friend.getAvatar());
            mName.setText(friend.getFullName());
            if (friend.isOnline()) {
                mOnline.setChecked(true);
            } else {
                mOnline.setChecked(false);
            }
        }

        @NonNull
        public static FriendViewHolder newInstance(ViewGroup parent) {
            return new FriendViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_friends, parent, false));
        }
    }

    static class PersonViewHolder extends PeopleViewHolder {

        @BindView(R.id.person_avatar)
        ImageView mAvatar;

        @BindView(R.id.person_name)
        TextView mName;

        @BindView(R.id.friend_progress)
        ProgressBar mProgress;

        @BindView(R.id.person_add_friend)
        ImageButton mAddFriend;

        @BindView(R.id.add_switcher)
        ViewSwitcher mAddSwitcher;

        private long mId;

        public PersonViewHolder(View view) {
            super(view);
            EventBus.getDefault().register(this);
            ButterKnife.bind(this, view);
        }

        void bind(PersonDTO person) {
            mId = person.getId();
            ImageLoad.loadCircleImage(mAvatar, person.getAvatar());
            mName.setText(person.getFullName());
        }

        @NonNull
        public static PersonViewHolder newInstance(ViewGroup parent) {
            return new PersonViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_person, parent, false));
        }

        @OnClick(R.id.person_add_friend)
        void addNewFriend() {
            mAddFriend.setVisibility(View.GONE);
            mProgress.setVisibility(View.VISIBLE);
            EventBus.getDefault().post(new PersonEvent(mId));
        }

        @Subscribe
        public void addFriendComplited(NewFriend event) {
            if (mId == event.mId) {
                mAddSwitcher.showNext();
            }
        }

    }
}