package com.example.movielistapplication.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielistapplication.Database.entities.User;
import com.example.movielistapplication.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final List<User> userList;
    private final OnUserActionListener actionListener;

    public interface OnUserActionListener {
        void onUserSelected(User user);
        void onMakeAdmin(User user);
        void onDeleteUser(User user);
    }

    public UserAdapter(List<User> userList, OnUserActionListener actionListener) {
        this.userList = userList;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_manage_users, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewUsername;
        private Button buttonMakeAdmin;
        private Button buttonDeleteUser;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.text_view_username);
            buttonMakeAdmin = itemView.findViewById(R.id.button_make_admin);
            buttonDeleteUser = itemView.findViewById(R.id.button_delete_user);

            itemView.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    User user = userList.get(getAdapterPosition());
                    actionListener.onUserSelected(user);
                }
            });

            buttonMakeAdmin.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    User user = userList.get(getAdapterPosition());
                    actionListener.onMakeAdmin(user);
                }
            });

            buttonDeleteUser.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    User user = userList.get(getAdapterPosition());
                    actionListener.onDeleteUser(user);
                }
            });
        }

        public void bind(User user) {
            textViewUsername.setText(user.getUsername());
            buttonMakeAdmin.setVisibility(user.isAdmin() ? View.GONE : View.VISIBLE);
        }
    }
}
