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

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.PersonViewHolder> {

    private List<PersonDTO> mPersons;

    public PersonsAdapter(List<PersonDTO> persons) {
        mPersons = persons;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PersonViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        PersonDTO person = mPersons.get(position);
        holder.bind(person);
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

    static class PersonViewHolder extends RecyclerView.ViewHolder {

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
                    .inflate(R.layout.item_person, parent, false));
        }

        @OnClick(R.id.person_add_friend)
        void addNewFriend() {
            mAddFriend.setVisibility(View.GONE);
            mProgress.setVisibility(View.VISIBLE);
            EventBus.getDefault().post(new PersonEvent(mId));
        }
    }
}
