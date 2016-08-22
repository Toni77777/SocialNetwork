package by.grodno.toni7777.socialnetwork.ui.search.persons.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.base.event.PersonEvent;
import by.grodno.toni7777.socialnetwork.network.model.PersonDTO;
import by.grodno.toni7777.socialnetwork.util.ImageLoad;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.ViewHolder> {

    private List<PersonDTO> mPersons;
    private static final int FRIEND = R.id.type_people_friend;
    private static final int PERSON = R.id.type_post_image;

    public PersonsAdapter(List<PersonDTO> persons) {
        mPersons = persons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FRIEND) {
            return FriendViewHolder.newInstance(parent);
        } else if (viewType == PERSON) {
            return PersonViewHolder.newInstance(parent);
        } else {
            throw new IllegalArgumentException("Unknown view type " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        PersonDTO person = mPersons.get(position);
        if (viewType == PERSON) {
            ((PersonViewHolder) holder).bind(person);
        } else if (viewType == FRIEND) {
            ((FriendViewHolder) holder).bind(person);
        }
    }

    @Override
    public int getItemViewType(int position) {
        PersonDTO person = mPersons.get(position);
        if (person.getFriend() == 1) {
            return FRIEND;
        } else if (person.getFriend() == 0) {
            return PERSON;
        } else {
            throw new IllegalArgumentException("View type not found");
        }
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    public void clear() {
        mPersons.clear();
    }

    public void update(List<PersonDTO> friends) {
        mPersons.addAll(friends);
        notifyDataSetChanged();
    }

    public List<PersonDTO> getPersons() {
        return mPersons;
    }

    public void updateNewFriend(Long userId) {
        for (PersonDTO person : mPersons) {
            if (person.getId() == userId) {
                int index = mPersons.indexOf(person);
                person.setFriend(1);
                notifyItemChanged(index);
                return;
            }
        }
    }

    public String getNewFriendName(Long userId) {
        for (PersonDTO person : mPersons) {
            if (person.getId() == userId) {
                return person.getName() + " " + person.getSurname();
            }
        }

        throw new IllegalArgumentException("New Friend  not found from Person adapter");
    }

    static abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    static class PersonViewHolder extends ViewHolder {

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
            ButterKnife.bind(this, view);
        }

        void bind(PersonDTO person) {
            mId = person.getId();
            ImageLoad.loadCircleImage(mAvatar, person.getAvatar());
            mName.setText(person.getName() + " " + person.getSurname());
        }

        @NonNull
        public static PersonViewHolder newInstance(ViewGroup parent) {
            return new PersonViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_search_person, parent, false));
        }

        @OnClick(R.id.person_add_friend)
        void addNewFriend() {
            mAddFriend.setVisibility(View.GONE);
            mProgress.setVisibility(View.VISIBLE);
            EventBus.getDefault().post(new PersonEvent(mId));
        }
    }

    static class FriendViewHolder extends ViewHolder {

        @BindView(R.id.search_friend_avatar)
        ImageView mAvatarView;

        @BindView(R.id.search_friend_name)
        TextView mNameView;

        public FriendViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(PersonDTO person) {
            ImageLoad.loadCircleImage(mAvatarView, person.getAvatar());
            mNameView.setText(person.getName() + " " + person.getSurname());
        }

        @NonNull
        public static FriendViewHolder newInstance(ViewGroup parent) {
            return new FriendViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_search_friend, parent, false));
        }
    }
}
