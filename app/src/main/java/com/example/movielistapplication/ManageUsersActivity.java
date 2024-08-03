package com.example.movielistapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielistapplication.Database.entities.User;
import com.example.movielistapplication.viewholders.UserAdapter;
import com.example.movielistapplication.viewholders.UserViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ManageUsersActivity allows the admin to manage user accounts.
 */
public class ManageUsersActivity extends AppCompatActivity implements UserAdapter.OnUserActionListener {

    private RecyclerView usersRecyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;
    private UserViewModel userViewModel;
    private Button buttonMakeAdmin;
    private Button buttonDeleteUser;
    private User selectedUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        // Initialize UI components
        usersRecyclerView = findViewById(R.id.text_view_username);
        buttonMakeAdmin = findViewById(R.id.button_make_admin);
        buttonDeleteUser = findViewById(R.id.button_delete_user);

        // Initialize user list and adapter
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList, this);

        // Setup RecyclerView
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersRecyclerView.setAdapter(userAdapter);

        // Initialize ViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Observe user data
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userList.clear();
                userList.addAll(users);
                userAdapter.notifyDataSetChanged();
            }
        });

        // Button click listeners
        buttonMakeAdmin.setOnClickListener(v -> {
            if (selectedUser != null) {
                userViewModel.makeAdmin(selectedUser);
                Toast.makeText(this, "User made admin", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDeleteUser.setOnClickListener(v -> {
            if (selectedUser != null) {
                userViewModel.deleteUser(selectedUser);
                Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onUserSelected(User user) {
        selectedUser = user;
        // Update button visibility or any other UI elements if needed
        buttonMakeAdmin.setVisibility(user.isAdmin() ? View.GONE : View.VISIBLE);
        buttonDeleteUser.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMakeAdmin(User user) {
        // This method will be invoked when the "Make Admin" button in the RecyclerView item is clicked
        if (user != null) {
            userViewModel.makeAdmin(user);
            Toast.makeText(this, "User made admin", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDeleteUser(User user) {
        // This method will be invoked when the "Delete User" button in the RecyclerView item is clicked
        if (user != null) {
            userViewModel.deleteUser(user);
            Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
